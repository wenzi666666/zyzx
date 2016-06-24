package net.tfedu.zhl.sso.thirdpartyAPI.jnzx.entity;

import java.util.List;

/**
 * 请求的返回信息
 * @author wangwr
 * 
 *  "BODY": {
        "CLASS_ARRAY": [
            //老师所教的班级列表{
                "GRADE_NAME": "六年级",
                "CLASS_NAME": "六（4）班",
                "GRADE_ID": "6",
                "CLASS_ID": "476"
            }
        ],
        "VERIFY_AUTH": "0",
        "TERM_ID": "3",
        //学年id"CITY_ID": "1408",
        "CLASS_NAME": "六（4）班",
        "isSuccess": "true",
        "AREAL_NAME": "芮城县",
        "GROUP_TYPE": "S",
        "NICKNAME": "houxiaoyu",
        "resultMessageKey": "SUCCESS",
        "PHONE": "13994095547",
        "AREAL_ID": 140830,
        "USER_IMG_PATH": "/eduSpace/task/style/images/avatar/avatar03.jpg",
        //用户头像"GRADE_NAME": "六年级",
        "LOGO": "",
        "SCHOOL_NAME": "试验小学",
        "SEX": "0",  //性别：0男  1女
        "PART_ID": "1408",
        "EMAIL": "",
        "PASSWORD": "e10adc3949ba59abbe56e057f20f883e",
        "COURSE_ID": "2",
        "USER_ID": 4800052,
        //用户id"wid": "S",
        "SCHOOL_ID": "1408300223",
        //学校id"MOTTO": "真的勇士敢于直面惨淡人生！",
        //座右铭"GRADE_ID": "6",
        "USER_TYPE": "11",
        "resultMessage": "SUCCESS",
        "PROVINCE_NAME": "山西",
        "TELEPHONE": "",
        "PROVINCE_ID": "14",
        //省id"VERIFICATE_EMAIL": "1",
        "CITY_NAME": "运城",
        "USER_NAME": "侯晓宇",
        //用户名"BIRTH_DATE": "2015-11-11",
        "CLASS_ID": "476"
    }
 *
 */
public class ZXUserInfoBody {

    /**
     * 登录名
     */
	String USER_ID;
	/**性别：0男  1女
	 * 
	 */
	String SEX;
	/**
	 * 用户名
	 */
	String USER_NAME;
	
	/**
	 * USER_TYPE是用户类型，00代表学生，01代表老师，02代表家长，11代表管理员。
	 */
	String USER_TYPE;
	
	
	/**
	 * 昵称
	 */
	String NICKNAME;
	/**
	 * 座右铭
	 */
	String MOTTO;

	/**
	 * 生日
	 */
	String BIRTH_DATE ;

	/**
	 * 省
	 */
	String PROVINCE_NAME;
	/**
	 * 市
	 */
	String CITY_NAME;
	/**
	 * 区
	 */
	String AREAL_NAME;
	/**
	 * 校
	 */
	String SCHOOL_NAME;
	
	
	/**
	 * 老师所教的班级列表
	 */
	List<ZXClassEntity>  CLASS_ARRAY;
	
	
}
