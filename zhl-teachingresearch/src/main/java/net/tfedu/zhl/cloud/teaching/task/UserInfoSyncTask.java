package net.tfedu.zhl.cloud.teaching.task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import net.tfedu.zhl.sso.school.entity.JSchool;
import net.tfedu.zhl.sso.school.service.JSchoolService;
import net.tfedu.zhl.sso.subject.entity.JTeacherSubject;
import net.tfedu.zhl.sso.subject.service.JSubjectService;
import net.tfedu.zhl.sso.term.entity.JUserTerm;
import net.tfedu.zhl.sso.term.service.JUserTermService;
import net.tfedu.zhl.sso.user.entity.JUser;
import net.tfedu.zhl.sso.user.service.JUserService;

/**
 * 教研平台和jx平台有用户信息同步的要求
 * 
 * 其中SSO使用的是阿里上的maincenter_yun的数据库，可以不用考虑同步问题
 * 但是业务表j_user,j_userinfo,j_userterm,j_teachersubject需要同步，
 * 
 * 
 * @author wangwr
 * 
 * 直接使用jdbc连接 jx平台的业务数据库
 * 
 * 定时每日凌晨2时完成
 * 
 * 
 *
 */
@Component
public class UserInfoSyncTask {
	
	
	Logger logger = LoggerFactory.getLogger(UserInfoSyncTask.class);
	

	
	@Resource
	JUserService userService;

	@Resource
	JUserTermService userTermService;
	
	
	@Resource
	JSchoolService schoolService;
	
	
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String host = "rm-2zet9s7pg9680839co.mysql.rds.aliyuncs.com";
	private static final int port = 3306;
	private static final String database = "yun2-8";
	private static final String user = "tfzhl";
	private static final String password = "bwxl33tfedu_net";
	
	
	
	
	/**
	 * 定时同步用户数据的方法
	 * 
	 *  cron 说明： http://wentao365.iteye.com/blog/1962375
	 *  
	 */
//	@Scheduled(fixedRate = 1000*60)	  // 1分钟一次

	@Scheduled(cron="0 00 2 * * ?")  //每天凌晨2点执行一次
	public void syncUserInfo(){
		
		logger.info("start---to---syncUserInfo");
		
		//jdbc链接数据库
		Connection conn = null;
			try {
			Class.forName(driver);
			// 指定连接类型
			conn = DriverManager.getConnection(
					"jdbc:mysql://"+host+":"+port+"/"+database
					+"?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true"
						, user, password);// 获取连接
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.info("-----ClassNotFoundException----");
		} catch (SQLException e) {
			logger.info("-----SQLException----");
			e.printStackTrace();
		}

		//处理同步操作
		if(conn!=null){
			 try {
				doUserInfoSyncOperate(conn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//完成同步操作，关闭连接
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn=null;
		}
		logger.info("end---to---syncUserInfo");
		
	}

	
	
	
	

	/**
	 * 进行同步操作的方法
	 * @param conn
	 * @throws Exception 
	 */
	private void doUserInfoSyncOperate(Connection conn) throws Exception {
		
		//已经存在的学校的id列表
		//只判断id
		List<Long> existsSchool = new ArrayList<Long>();
		
		
		

		List<Long> missList = userService.getMissUserBetweenJXAndSSO();
		for (Iterator iterator = missList.iterator(); iterator.hasNext();) {
			//获取id
			Long id = (Long) iterator.next();
			
			//
			PreparedStatement  ps = conn.prepareStatement((JDBC_GET_USER+id));
			ResultSet rs = ps.executeQuery();

			//如果不存在则跳过
			if(null ==rs ){
				ps = null;
				continue;
			}else if(rs.next()){
				

				logger.info("-----start---to--sync--"+rs.getString("name"));

				
				long schoolId = rs.getLong("schoolid");
				String schoolName = rs.getString("schoolname");
				
				//如果没有这个学校,插入
				if(!existsSchool.contains(schoolId)){
					JSchool temp  = (JSchool)schoolService.get(schoolId).getData();
					if(null ==temp || temp.getId()<=0){
						JSchool c = new JSchool();
						c.setId(schoolId);
						c.setDistrictid(rs.getString("districtid"));
						c.setName(schoolName);
						c.setFlag(false);
						schoolService.insert(c);	
						existsSchool.add(schoolId);
					}
				}
				
				JUser user = new JUser();
				user.setId(rs.getLong("id"));
				user.setName(rs.getString("name"));
				user.setRoleid(rs.getString("roleid"));
				user.setSchoolid(schoolId);
				user.setTruename(rs.getString("truename"));
				user.setNickname(rs.getString("nickname"));
				user.setMale(rs.getBoolean("male"));
				user.setLasttipid(rs.getLong("lasttipid"));
				user.setFlag(false);
				userService.addUser(user);
				
				
				if(StringUtils.isNotEmpty(rs.getString("termid"))){
					
					JUserTerm ut = new JUserTerm();
					ut.setUserid(rs.getLong("id"));
					ut.setTermid(rs.getLong("termid"));
					ut.setFlag(false);
					userTermService.insert(ut);
				}
				
				if(StringUtils.isNotEmpty(rs.getString("subjectids"))){
					String ids = rs.getString("subjectids");
					String _ids[] = ids.split(",");
					
					List<JTeacherSubject> datas = new ArrayList<JTeacherSubject>();
					for (int i = 0; i < _ids.length; i++) {
						JTeacherSubject su = new JTeacherSubject();
						su.setUserid(rs.getLong("id"));
						su.setSubjectid(Long.parseLong(_ids[i]));
						su.setFlag(false);
						datas.add(su);
						
					}
					userTermService.addTeacherSubject(datas);
				}
			}
			
			rs = null;
			ps = null;
			
		}
	}
	
	
	
	
	
	
	
	
	
 private static final String JDBC_GET_USER = 
		 new StringBuffer()
		 .append("SELECT u.id,u.NAME,u.roleid,	u.schoolid,	u.truename,	u.nickname,	u.issee,	u.male,	u.lasttipid,s.districtid,s.NAME AS schoolname,")
		 .append("ut.termid,(SELECT GROUP_CONCAT(ts.SubjectId) from j_teachersubject ts where ts.UserId = u.id) as subjectids ")
		 .append(" FROM 	j_user u LEFT JOIN j_school s ON s.id = u.SchoolId ")
		 .append(" LEFT JOIN j_userterm ut on ut.UserId = u.id ")
		 .append(" WHERE  u.id = ")
		 .toString()
		 ;
	
	
	
	
	
	
	
	

}
