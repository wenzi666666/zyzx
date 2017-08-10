package net.tfedu.zhl.cloud.resource.integration.constant;

import java.util.HashMap;
import java.util.Map;

import net.tfedu.zhl.cloud.resource.integration.entity.CourseNode;

/**
 
 
 	jquery easyui  页面组件的产量
  
    @author wangwr
    @date 2017年8月4日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
public class JQueryEasyUIConstantt {

	/***
	 * 自建目录默认节点id
	 */
	public static final String COURSE_DEFAULT_NODE_ID = "0";
	/***
	 * 自建目录默认节点名称
	 */
	public static final String COURSE_DEFAULT_NODE_NAME = "教学目录";
	/***
	 * 自建目录节点的默认状态（未展开）
	 */
	public static final String COURSE_NODE_DEFAULT_STATE = "closed";
	
	
	/**
	 * 获取默认的自建目录节点
	 * @return
	 */
	public static final CourseNode getDefaultCourseNode(){
		CourseNode course = new CourseNode();
		course.setId(COURSE_DEFAULT_NODE_ID);
		course.setText(COURSE_DEFAULT_NODE_NAME);
		course.setState(COURSE_NODE_DEFAULT_STATE);		
		course.setAttributes(new HashMap<String,Object>());	
		return course ; 
	}
	
	
	
	
	
}
