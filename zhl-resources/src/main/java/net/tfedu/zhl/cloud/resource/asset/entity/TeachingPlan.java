package net.tfedu.zhl.cloud.resource.asset.entity;

import java.util.Date;

/**
 
	  
	  
	  教案的封裝类
	  教案是一种特殊的自建资源：类型为教案；且文本内容保存在从表z_teaching_plan_content 中
     
     
  
  @author wangwr
  @date 2016年11月10日
  
  copyRight@ 同方知好乐教育科技(北京)有限公司 

*/
public class TeachingPlan {

	
	public static final String FILE_TYPE = "B";
	
	public static final Long RESOURCE_TYPE = 53l;
	
	
	
	
	Long id ; // id主键
	String	title	;//	教案名称
	String	tfcode	;//	所属章节
	String	keyword	;//	关键字
	String	content	;//	教案内容
	Long userId ;//创建人
	Date createTime;//創建時間
	
	
	
	
	
	
	
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTfcode() {
		return tfcode;
	}
	public void setTfcode(String tfcode) {
		this.tfcode = tfcode;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
