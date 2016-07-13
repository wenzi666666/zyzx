package net.tfedu.zhl.cloud.resource.thirdparty.sichuan.module;


import java.util.ArrayList;



/**
 * @date 2015-12-23
 * @anthor wangwr
 * @copyRight 同方知好乐教育科技北京有限公司
 * 
 * 
 *            "classId": "12312311", "classname": "2014级2班", "semesterName":
 *            "上学期", "gradename": "2014级", "section": "2", "courseList": [ {
 *            "courseId": "123", "courseName": "语文" }, { "courseId": "121",
 *            "courseName": "数学" } ]
 */
public class ClassJSON {

	private String  classId;
	private String  classname;
	private String  semesterName;
	private String  gradename;
	private String  section;
	private ArrayList<CourseDetail> courseList;
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getSemesterName() {
		return semesterName;
	}
	public void setSemesterName(String semesterName) {
		this.semesterName = semesterName;
	}
	public String getGradename() {
		return gradename;
	}
	public void setGradename(String gradename) {
		this.gradename = gradename;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public ArrayList<CourseDetail> getCourseList() {
		return courseList;
	}
	public void setCourseList(ArrayList<CourseDetail> courseList) {
		this.courseList = courseList;
	}
	
	
	
	
	
}
