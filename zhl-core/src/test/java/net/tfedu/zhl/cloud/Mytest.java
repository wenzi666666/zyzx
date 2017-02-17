package net.tfedu.zhl.cloud;

import net.tfedu.zhl.fileservice.MD5;

/**
 
  
    @author wangwr
    @date 2017年2月13日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
public class Mytest {

	
	
	
	public static void main(String[] args) {
		
		
		String appid = "679636";
		String appkey = "0dd97ecaf1d3";
		
		
		String uid = "test_uid";
		
		String url = "http://219.239.146.205/zhl-casProxy/casProxy/login/common?uid="+uid+"&appid="+appid
				+ "&sign="+MD5.getMD5Str(uid+ "〖 zhl〗 "+appid+ "〖 zhl〗 "+appkey);
		
		
		System.out.println(url);
		
	}
}
