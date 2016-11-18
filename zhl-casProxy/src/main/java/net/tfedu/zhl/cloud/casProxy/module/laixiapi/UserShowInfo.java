package net.tfedu.zhl.cloud.casProxy.module.laixiapi;

import java.io.Serializable;

/**
 
 
  
  莱西show接口返回的用户信息
  @author wangwr
  @date 2016年11月17日
  @desc 
  
  copyRight@ 同方知好乐教育科技(北京)有限公司 

*/
public class UserShowInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户UID
	 */
	String	uid	;
	/**
	 * 用户昵称
	 */
	String	nick_name	;
	
	
	/**
	 * 真实姓名
	 */
	String	real_name	;
	
	
	/**
	 * 用户所在机构ID
	 */
	int	unit_id	;
	
	/**
	 * 用户所在机构名称
	 */
	String	unit_name	;
	
	/**
	 * 用户身份ID(1:学生，3：家长，10002:教师，10000学校普通人员，10004学校行政人员)
	 */
	int	user_type	;
	/**
	 * 用户身份说明
	 */
	String	user_type_name	;
	/**
	 * 用户性别，1：男，0：女
	 */
	int	gender	;
	/**
	 * 用户身份证（如果用户未提供，则该字段没有）
	 */
	String	id_card	;

	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public int getUnit_id() {
		return unit_id;
	}
	public void setUnit_id(int unit_id) {
		this.unit_id = unit_id;
	}
	public String getUnit_name() {
		return unit_name;
	}
	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}
	public int getUser_type() {
		return user_type;
	}
	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}
	public String getUser_type_name() {
		return user_type_name;
	}
	public void setUser_type_name(String user_type_name) {
		this.user_type_name = user_type_name;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getId_card() {
		return id_card;
	}
	public void setId_card(String id_card) {
		this.id_card = id_card;
	}

	
	
	
	
	
	
}
