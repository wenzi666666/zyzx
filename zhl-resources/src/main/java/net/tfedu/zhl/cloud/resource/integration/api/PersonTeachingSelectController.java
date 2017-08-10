package net.tfedu.zhl.cloud.resource.integration.api;

import org.springframework.stereotype.Controller;

/**
 
  
 * 语文互动课堂--用户自主选教材3.1
 * 
 * 资源中心和云平台公用action
 * 资源中心使用时：  personSelect_*
 * 云平台使用时：  personSelectAJAX_*
 * 
 * 
    @author wangwr
    @date 2017年8月10日
    @desc 
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
@Controller
public class PersonTeachingSelectController {
	
	
	
	/**
	 * 是否是第一次使用
	 * @return
	 * @throws Exception 
	 */
	public String isFirstSelect() throws Exception{
		long userId =0;		
		long dbId = 0;

		/*
		CourseImpl impl = (CourseImpl)CommonConstantWeb.getLogicInterface("CourseService");			
		String message = "success";
		boolean isFirst = true;
		String tfcode="";
		try{
			if(isCloud(request)){//是否是云平台的接口调用
				Map<String,Long> map = getUserIdAndDbId(request);
				userId = map.get("userId");
				dbId = map.get("dbId");
			}else{
				//如果是资源中心的调用，使用拦截器中的参数
				userId = Long.parseLong((String)request.getAttribute(CookieUtil.DEFAULT_USERID));		
				dbId = Long.parseLong((String)request.getAttribute(CookieUtil.DEFAULT_DBID));
			}
			RowSet rs =  impl.getTeachingSelect(userId, dbId);
			
			if(rs!=null && rs.next()){
				isFirst = false;
				tfcode = rs.getString("tfcode");
				result.put("tfcode", tfcode);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			message = "error";
			
		}finally{
			return resultString(message, String.valueOf(isFirst));
		}
		*/
		return null;
	}

	/**
	 * 系统资源页面
	 * @return
	 * @throws Exception 
	 */
	public String subject() throws Exception{
		/*long userId =0;		
		long dbId = 0;

		try {
			if(isCloud(request)){//是否是云平台的接口调用
				Map<String,Long> map = getUserIdAndDbId(request);
				userId = map.get("userId");
				dbId = map.get("dbId");
			}else{
				//如果是资源中心的调用，使用拦截器中的参数
				userId = Long.parseLong((String)request.getAttribute(CookieUtil.DEFAULT_USERID));		
				dbId = Long.parseLong((String)request.getAttribute(CookieUtil.DEFAULT_DBID));
			}
			return getSubject(userId,dbId);
		} catch (Exception e) {
			e.printStackTrace();
			return resultError();
		}*/
		return null;
	}
	
	
	/**
	 * 获取版本
	 * @return
	 * @throws Exception 
	 */
	public String version() throws Exception{
	/*	long dbId = 0;

		
		try {
			
			if(isCloud(request)){//是否是云平台的接口调用
				Map<String,Long> map = getUserIdAndDbId(request);
				dbId = map.get("dbId");
			}else{
				//如果是资源中心的调用，使用拦截器中的参数
				dbId = Long.parseLong((String)request.getAttribute(CookieUtil.DEFAULT_DBID));
			}

			
			
			String subject = request.getParameter("subjectId");
			String term = request.getParameter("termId");
			long subjectId = subject!=null&&!"".equals(subject)?Long.parseLong(subject):0;
			long termId = term!=null&&!"".equals(term)?Long.parseLong(term):0;
			
			
			return getVersion(termId, subjectId, dbId);
			
		} catch (Exception e) {
			e.printStackTrace();
			return resultError();
		}
		*/
		
		return null;
	}
	
	/**
	 * 获取版本下的教材
	 * @return
	 * @throws Exception 
	 */
	public String book() throws Exception{

		
		/*try {
			
			long dbId = 0;

			if(isCloud(request)){//是否是云平台的接口调用
				Map<String,Long> map = getUserIdAndDbId(request);
				dbId = map.get("dbId");
			}else{
				//如果是资源中心的调用，使用拦截器中的参数
				dbId = Long.parseLong((String)request.getAttribute(CookieUtil.DEFAULT_DBID));
			}
			
			String version = request.getParameter("versionId");
			long versionId = version!=null&&!"".equals(version)?Long.parseLong(version):0;
			return getBook(versionId, dbId);
			
		} catch (Exception e) {
			e.printStackTrace();
			return resultError();
		}
		*/
		return null;
	}
	

	/**
	 * 更新教材用户的教材选择
	 * @return
	 * @throws Exception 
	 */
	public String teachingSelect() throws Exception{
		
		/*long userId =0;		
		long dbId = 0;

		
		try {
			if(isCloud(request)){//是否是云平台的接口调用
				Map<String,Long> map = getUserIdAndDbId(request);
				userId = map.get("userId");
				dbId = map.get("dbId");
			}else{
				//如果是资源中心的调用，使用拦截器中的参数
				userId = Long.parseLong((String)request.getAttribute(CookieUtil.DEFAULT_USERID));		
				dbId = Long.parseLong((String)request.getAttribute(CookieUtil.DEFAULT_DBID));
			}
			
			
			
			String subject = request.getParameter("subjectId");
			String term = request.getParameter("termId");
			String syscourse = request.getParameter("syscourseId");
			long subjectId = subject!=null&&!"".equals(subject)?Long.parseLong(subject):0;
			long termId = term!=null&&!"".equals(term)?Long.parseLong(term):0;
			long syscourseId = syscourse!=null&&!"".equals(syscourse)?Long.parseLong(syscourse):0;

			return updateTeachingSelect(userId, dbId, termId, subjectId, syscourseId);
			
		} catch (Exception e) {
			e.printStackTrace();
			return resultError();
		}
		*/
		
		return null;
	}
	
	/**
	 * 返回系统教材给云平台
	 * @return
	 * @throws Exception
	 */
	public String tree()throws Exception{
		/*long userId =0;		
		long dbId = 0;
		
		
		try {
			
			if(isCloud(request)){//是否是云平台的接口调用
				Map<String,Long> map = getUserIdAndDbId(request);
				userId = map.get("userId");
				dbId = map.get("dbId");
			}

			String id = request.getParameter("id");
			ArrayList<CourseNode> treeNodes = new ArrayList<CourseNode>();
			if(id==null||"".equals(id.trim())||"0".equals(id.trim())){ //根据教师用户的 科目学段   初始化树形
				CourseImpl impl = (CourseImpl)CommonConstantWeb.getLogicInterface("CourseService");			
				RowSet rs = impl.getNewUserCourse(userId, dbId);			
				while(rs.next()){
					String node_id = rs.getString("id");
					String node_name = rs.getString("name");
					String tfcode = rs.getString("tfcode");
					CourseNode node = new CourseNode();
					node.setId(node_id);
					node.setText(node_name);
					node.setState("closed");
					HashMap<String,Object> attr = new HashMap<String,Object>();
					attr.put("tfcode", tfcode);
					node.setAttributes(attr);
					treeNodes.add(node);					
				}			
			}else{//查询课程目录
				CourseImpl impl = (CourseImpl)CommonConstantWeb.getLogicInterface("CourseService");			
				RowSet rs = impl.searchSysChildren(Long.parseLong(id), dbId);
				while(rs.next()){
					String node_id = rs.getString("id");
					String node_name = rs.getString("name");
					String tfcode = rs.getString("tfcode");
					int childnums = rs.getInt("childrenNum");
					CourseNode node = new CourseNode();
					node.setId(node_id);
					node.setText(node_name);
					HashMap<String,Object> attr = new HashMap<String,Object>();
					attr.put("tfcode", tfcode);
					node.setAttributes(attr);
					node.setState(childnums>0?"closed":"open");		
					treeNodes.add(node);					
				}	
			}
			
			return resultSuccess(treeNodes);
		} catch (Exception e) {
			e.printStackTrace();
			return resultError();
		}
*/
		return null;
	}
	

	
	
	/**
	 * 获取指定教材的全部树形节点
	 * @return
	 */
	public String getAllTree(){
		
		/*long userId =0;		
		long dbId = 0;
		long syscourseId = 0 ;
		
		try {
			
			if(isCloud(request)){//是否是云平台的接口调用
				Map<String,Long> map = getUserIdAndDbId(request);
				userId = map.get("userId");
				dbId = map.get("dbId");
			}else{
				userId = Long.parseLong((String)request.getAttribute(CookieUtil.DEFAULT_USERID));		
				dbId = Long.parseLong((String)request.getAttribute(CookieUtil.DEFAULT_DBID));
			}
			String _syscourseId = request.getParameter("syscourseId");
			syscourseId = _syscourseId!=null&&!"".equals(_syscourseId)?Long.parseLong(_syscourseId):0;
			CourseImpl impl = (CourseImpl)CommonConstantWeb.getLogicInterface("CourseService");			
			CachedRowSet rs = impl.getAllTreeNodesByBookId(syscourseId, dbId);
			List<Map<String,Object>> map = CommonConstantWeb.CreateListLowerCase(rs);
			return resultSuccess(map);
		}catch(Exception e){
			e.printStackTrace();
			return resultError();
		}*/
		
		return null;
		
	}
	
	
	
	
	
	

}
