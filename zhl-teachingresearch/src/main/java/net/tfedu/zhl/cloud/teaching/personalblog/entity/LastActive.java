package net.tfedu.zhl.cloud.teaching.personalblog.entity;

import java.util.Date;

/**
copyRight@ 同方知好乐教育科技(北京)有限公司
@author　wangwr
@date 　
@desc 　　LastActive.java
*/

/**
 * 最新动态实体类 用户实现组内排序（时间倒序） public int compareTo(LastActive o) {
 * 
 * }
 */
public class LastActive implements Comparable {
	/** 活动类型或是“个人反思” */
	private String typeName;
	/** 创建人头像 */
	private String userImage;

	/** 个人反思主键或活动主键 */
	public String uuid;
	/** 创建人 */
	public long userId;
	/** 标题 */
	public String title;
	/** 内容 */
	public String content;
	/** 创建时间 */
	public Date createTime;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public int compareTo(Object o) {
		
		if( o  instanceof  LastActive){
			
			LastActive a = (LastActive)o;
		
			//当前对象的创建时间比 o中的创建时间大 
			if(a.getCreateTime().before(this.createTime)){
				return 1;
			}else{
				return -1 ;
			}
			
		}
		
		return 0;
	}

}