package net.tfedu.zhl.cloud.resource.thirdparty.sichuan.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import net.tfedu.zhl.cloud.resource.thirdparty.sichuan.config.SiChuanRelativeProperties;
import net.tfedu.zhl.cloud.resource.thirdparty.sichuan.module.CheckResultJSON;
import net.tfedu.zhl.cloud.resource.thirdparty.sichuan.module.InfoResulltJSON;

/**
 *  * 四川省 实名网络学习空间平台对接
	调用接口的工具类
 *@date 2015-12-21
 *@anthor wangwr
 *@copyRight  同方知好乐教育科技北京有限公司
 */
public class RealNameAPIUtil {
	
	private static final String Http_Encoding = "UTF-8";
	
	private static Logger logger = LoggerFactory.getLogger(RealNameAPIUtil.class);
	
	/**
	 * 认证接口
	 * @return
	 * 
	 * 0 为正常
	 * 1 为 供应商或产品许可码 加密异常
	 * 2访问认证接口失败
	 */
	public static CheckResultJSON check(String ticket,String checkUrl,SiChuanRelativeProperties property){
		String pkey  = "";
		String appkey  = "";
		try {
			pkey  = AESUtil.Encrypt(property.getP_key(), property.getAes_key());
			appkey  = AESUtil.Encrypt(property.getApp_key() , property.getAes_key());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		
		
		try {
			String queryParam = "{\"params\":{\"pkey\":\""+pkey +"\",\"appkey\":\""+appkey+"\",\"ticket\":\""+ticket+"\"}}";
			String result =  HttpHelper.sendPost(checkUrl, queryParam);
			System.out.println(result);
			
			CheckResultJSON json = (CheckResultJSON) JSONObject.parseObject(result, CheckResultJSON.class);		
			
			return json;
		
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return  null ;
	}
	
	
	
	public  static InfoResulltJSON info(String ticket,String infoUrl,SiChuanRelativeProperties property){
		String pkey  = "";
		String appkey  = "";
		try {
	
			pkey  = AESUtil.Encrypt(property.getP_key(), property.getAes_key());
			appkey  = AESUtil.Encrypt(property.getApp_key() , property.getAes_key());

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		
		
		try {
			String queryParam = "{\"params\":{\"pkey\":\""+pkey +"\",\"appkey\":\""+appkey+"\",\"ticket\":\""+ticket+"\"}}";
			String result =  HttpHelper.sendPost(infoUrl, queryParam);
			System.out.println(result);
			InfoResulltJSON info = JSONObject.parseObject(result, InfoResulltJSON.class);
			return info;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null ;
	}
	
	
	public static void main(String[] args) {
		String result = "{\"response\":{\"valid\":\"1\",\"content\":{\"id\":\"FA7A55CFC1743D802D6018A1CD27B823489AB48E2DEE0129D17249DBE8FD2789\",\"modifydate\":\"\",\"name\":\"代老师\",\"type\":\"27\"}}}";
		
		CheckResultJSON json =JSONObject.parseObject(result, CheckResultJSON.class);
		System.out.println(json.getResponse().getValid());
		
	}
	

}
