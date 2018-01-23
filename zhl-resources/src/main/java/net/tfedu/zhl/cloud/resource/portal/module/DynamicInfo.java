package net.tfedu.zhl.cloud.resource.portal.module;

import java.io.Serializable;

/**
 
      动态信息
  @author wangwr
  @date 2016年11月11日
  @desc 
  
  copyRight@ 同方知好乐教育科技(北京)有限公司 

*/
public class DynamicInfo  implements Serializable{

	
	String	time	;//	时间说明字符串	
	String	username	;//	用户（校、班级）	
	String	title	;//	资源标题	
	String	mtype	;//	资源类型	
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMtype() {
		return mtype;
	}
	public void setMtype(String mtype) {
		this.mtype = mtype;
	}

	
	
	
	
	
	
	
	
}
