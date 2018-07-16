package net.tfedu.zhl.cloud.resource.intergral.constant;


/**
 * @author wangwr
 * @date 2017-5-12
 * @desc 资源积分相关SQL的常量类
 * 
 *       copyRight@ 同方知好乐教育科技(北京)有限公司
 */
public class ResourceIntegralConstants {
	
	
	

	/**
	 * 下载共享资源
	 */
	public static final String DOWN_SHAREDRES_CODE = "down_shared_res";
	/**
	 * 引用共享资源
	 */
	public static final String IMP_SHAREDRES_CODE = "imp_shared_res";
	/**
	 * 审核通过推荐的（区校）资源	
	 */
	public static final String PASS_RECOMMENDEDASSET_CODE = "pass_recommend_dres";
	
	
	
	
	
	/**
	 * 获取推荐资源通过的积分分值
	 */
	public static final String getScore_auditPass = "SELECT * FROM `resource_intergral_operate_type` where code = '"+PASS_RECOMMENDEDASSET_CODE+"'";
	

	/**
	 * 获取用户的资源积分
	 */
	public static final String get_user_integral = "SELECT * FROM `user_resource_intergral` where userid = ?";

	/**
	 * 添加（用户的积分）记录
	 */
	public static final String add_user_integral_record = "INSERT INTO user_resource_intergral(userid,total_score,useable_score) values(?,0,0)";

	/**
	 * 增加用户积分
	 */
	public static final String increase_user_integral = "UPDATE user_resource_intergral set total_score = total_score+? ,useable_score=useable_score+?  where userid = ?";

	/**
	 * 减少用户积分
	 */
	public static final String decrease_user_integral = "UPDATE user_resource_intergral set total_score = total_score-? ,useable_score=useable_score-?  where userid = ?";

	
	/**
	 * 获取用户指定操作类型，指定操作对象的 积分操作记录
	 */
	public static final String get_user_operate_record = "SELECT * FROM user_resource_intergral_record where flag= false  and createman = ? and operatetype =? and operateid =? ";

	/**
	 * 增加用户的积分操作记录
	 */
	public static final String add_user_operate_record = new StringBuffer()
	.append("INSERT INTO  user_resource_intergral_record ")
	.append("(userid,createman,score,createtime,invalidtime,flag,operatetype,operateid) ")
	.append("values(?,?,?,?,?,?,?,?) ")
	.toString();
	
	
	
	
	/**
	 *  共享（获取的积分）
	 *  （参数2个）
	 */
	public static final String getIntegralIncrementByShare = new StringBuffer()
	.append(" SELECT sum(score) as score ")
	.append(" from  ( ")
	.append(" SELECT  IFNULL(SUM(score),0) as score from user_resource_intergral_record where userid = ? and flag = false and  operatetype = 'down_shared_res' and score > 0 ")
	.append(" UNION ")
	.append(" SELECT IFNULL(SUM(score),0) as score from user_resource_intergral_record where userid = ? and flag = false and  operatetype = 'imp_shared_res' and score > 0 ")
	.append(" )result ")
	.toString();
	
	  
	
	/**
	 *  获取用户推荐资源  获取的积分
	 *  （参数1个）
	 */
	public static final String getIntegralIncrementByRecommend = new StringBuffer()
	.append(" SELECT  IFNULL(SUM(score),0) as score  from user_resource_intergral_record where userid = ? and flag = false and  operatetype ='pass_recommend_dres' and score > 0  ")
	.toString();
	
	
	
	
	/**
	 * 获取资源消费的总积分
	 * （参数1个）
	 */
	public static final String getIntegralDecrement = new StringBuffer()
	.append("SELECT IFNULL(SUM(score),0) as score  from user_resource_intergral_record where userid = ? and flag = false and  score < 0  ")
	.toString();
	
	
	
	/**
	 * 获取用户积分的历史记录
	 * （参数2个）
	 */
	public static final String queryIntegralHistory = new StringBuffer()
		.append(" SELECT * FROM ( ")
		.append(" SELECT r.createtime,operatetype,a.Name AS resName,score, m.scope AS scope  FROM user_resource_INtergral_record r  ")
		.append(" LEFT JOIN z_ASset_recommended m on m.Id = r.operateid ")
		.append(" LEFT JOIN z_ASset a on m.ASsetid = a.id  ")
		.append(" WHERE r.userid = ? AND r.flag = false AND  operatetype ='pASs_recommend_dres' ")
		.append(" UNION ALL ")
		.append(" SELECT r.createtime,operatetype,a.Name AS resName,score,0 AS scope  FROM user_resource_INtergral_record r  ")
		.append(" LEFT JOIN z_ASset a on r.operateid = a.id  ")
		.append(" WHERE r.userid = ? AND r.flag = false AND r.operatetype IN ('down_shared_res','imp_shared_res') ")
		.append(" )te123 ORDER BY createtime DESC,score  ")
		.toString();
	
}
