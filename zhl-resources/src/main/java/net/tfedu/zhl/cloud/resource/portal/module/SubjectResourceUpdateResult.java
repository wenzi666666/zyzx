package net.tfedu.zhl.cloud.resource.portal.module;

import java.util.List;

/**
 
    学科更新信息
  @author wangwr
  @date 2016年11月12日
  @desc 
  
  copyRight@ 同方知好乐教育科技(北京)有限公司 

*/
public class SubjectResourceUpdateResult {

	/**
	 * 	学科名称
	 */
	String subjectName ; 
	
	/**
	 * 学科下的系统资源排行信息集合
	 */
	List<SubjectResourceUpdate> list ;

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public List<SubjectResourceUpdate> getList() {
		return list;
	}

	public void setList(List<SubjectResourceUpdate> list) {
		this.list = list;
	} 

}
