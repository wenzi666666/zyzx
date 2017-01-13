package net.tfedu.zhl.sso.users.util;



public class RegisterQuery {
	/**
	 * 注册卡号
	 */
	private String cardId;
	/**
	 * 注册卡号 密码
	 */
	private String cardpassword;
	/**
	 * 机器码（未使用）
	 */
	private String machineCode;
	/**
	 * 动态码（未使用）
	 */
	private String activeCode;
	/**
	 * 用户名 （mysql j_user表 name）
	 */
	private String userId = "";
	/**
	 * 昵称（未使用）
	 */
	private String username = "";
	/**
	 * 密码
	 */
	private String password = "";
	/**
	 * 真实姓名
	 */
	private String truename = "";
	/**
	 *  （未使用）
	 */
	private String course = "";
	/**
	 * 性别 
	 */
	private String sex = "";
	/**
	 *  邮箱
	 */
	private String email = "";
	/**
	 *  学校
	 */
	private String schoolCode = "";
	/**
	 *  身份证号
	 */
	private String idCardId = "";
	/**
	 *  电话
	 */
	private String mobilephone = "";
	/**
	 *  保密问题
	 */
	private String question = "";
	/**
	 * 保密答案
	 */
	private String answer = "";
	/**
	 *  注册节点
	 */
	private String dbserver = "";
	/**
	 *  注册信息 经过数据库 验证是否有误
	 */
	private boolean error = false;
	/**
	 * 错误信息
	 */
	private String errorMessage = "";
	/**
	 *  excel 下标
	 */
	private int lineNo;
	/**
	 *  班级名称
	 */
	private String className = "";
	/**
	 *  学科
	 */
	private String subject = "";
	/**
	 * 站点
	 */
	private String nodeId = "";
	/**
	 *  角色
	 */
	private String roleName = "";
	/**
	 *  学段
	 */
	private String termId = "";
	
	
	public String getTermId() {
		return termId;
	}
	public void setTermId(String termId) {
		this.termId = termId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getCardpassword() {
		return cardpassword;
	}
	public void setCardpassword(String cardPassword) {
		this.cardpassword = cardPassword;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTruename() {
		return truename;
	}
	public void setTruename(String truename) {
		this.truename = truename;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}
	public String getSchoolCode() {
		return schoolCode;
	}
	public void setIdCardId(String idCardId) {
		this.idCardId = idCardId;
	}
	public String getIdCardId() {
		return idCardId;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}
	public String getMachineCode() {
		return machineCode;
	}
	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}
	public String getActiveCode() {
		return activeCode;
	}
	public String getQuestion() {
		return question;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public void setDbserver(String dbserver) {
		this.dbserver = dbserver;
	}
	public String getDbserver() {
		return dbserver;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public boolean isError() {
		return error;
	}
	public void setLineNo(int lineNo) {
		this.lineNo = lineNo;
	}
	public int getLineNo() {
		return lineNo;
	}
	
}
