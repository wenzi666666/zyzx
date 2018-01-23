package net.tfedu.zhl.cloud.teaching.discuss.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.fileservice.Base64;

/**
 * 转换url 的工具类
 * @author wangwr
 *
 */
public class DiscussURLUtil {
	
	
	public static String convert(String url,String username,String pwd) throws UnsupportedEncodingException{
		//未设定url时
		if(StringUtils.isEmpty(url)){
			return "";
		}
		
		String yun_service = null ;
		String temp = url.replace("http://", "");
		yun_service = "http://"+temp.substring(0, temp.indexOf("/")+1);


		String p =  username+":"+pwd;
		p = Base64.encode(p.getBytes());
		String target = yun_service+"net_jyForum.action?args="+p+"&targetPage="+URLEncoder.encode(url, "utf-8");
		return target;
	}
	

}
