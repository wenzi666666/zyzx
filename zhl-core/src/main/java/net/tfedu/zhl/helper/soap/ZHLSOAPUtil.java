package net.tfedu.zhl.helper.soap;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 @author wangwr
 @date 2016年9月22日
 @desc
 			
 		提供SOPA的webService 接口 基于 android ksoap2 
 copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
public class ZHLSOAPUtil {

	
	
	/**
	 * 
	 * @param namespace 	 指定WebService的命名空间
	 * @param name	    	 指定WebService的命名空间函数名
	 * @param url			 指定WebService的 url 
	 * @param properties	 指定WebService 需要的参数
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String  doneWebService(String namespace,String name,String url,Map<String,String> properties) throws UnsupportedEncodingException{
		
		// 指定WebService的命名空间和函数名
		SoapObject request = new SoapObject(
				namespace, name);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER10);
		
		//request.addProperty("ProvinceName", URLEncoder.encode(ProvinceName, "utf-8"));
		
		if(properties!=null && properties.size()>0){
			Set<Entry<String, String>> set = properties.entrySet();
			for (Iterator<Entry<String, String>> iterator = set.iterator(); iterator.hasNext();) {
				Entry<String, String> entry = iterator.next();
				String value = entry.getValue();
				
				request.addProperty(entry.getKey()
						, StringUtils.isEmpty(value)?"":URLEncoder.encode(value, "utf-8"));
			}
		}
		
		envelope.bodyOut = request;
		envelope.setOutputSoapObject(request);
		HttpTransportSE ht = new HttpTransportSE(url);
		try {
			long beginTime = System.currentTimeMillis();
			// 使用call方法调用WebService方法
			ht.call(null, envelope);
			long endTime = System.currentTimeMillis();
			System.out.println((endTime - beginTime) + "ms");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// step6 使用getResponse方法获得WebService方法的返回结果
		Object tempresult = null;
		try {
			tempresult = (Object) envelope.getResponse();
		} catch (SoapFault e) {
			e.printStackTrace();
		}
		System.out.println(tempresult);
		return null==tempresult?"":tempresult.toString();
	}
	
	
	
	
	
	
	
	
	
	
	
}
