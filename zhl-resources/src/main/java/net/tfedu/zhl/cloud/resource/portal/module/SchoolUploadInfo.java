package net.tfedu.zhl.cloud.resource.portal.module;

import java.io.Serializable;

/**
 * 
 * 学校上传信息
 * 
 * @author wangwr
 * @date 2016年11月12日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 * 
 */
public class SchoolUploadInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String schoolName;// 学校名称
	int uploadNumbers;// 上传总量

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public int getUploadNumbers() {
		return uploadNumbers;
	}

	public void setUploadNumbers(int uploadNumbers) {
		this.uploadNumbers = uploadNumbers;
	}

}
