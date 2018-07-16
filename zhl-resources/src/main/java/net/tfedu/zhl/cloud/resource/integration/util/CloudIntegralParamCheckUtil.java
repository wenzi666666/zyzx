package net.tfedu.zhl.cloud.resource.integration.util;

import javax.servlet.http.HttpServletRequest;

import net.tfedu.zhl.cloud.resource.integration.entity.ResultInfo;

/**
   
   	云平台对接参数校验类
  
    @author wangwr
    @date 2017年8月10日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
public class CloudIntegralParamCheckUtil {
	
	
	/**
	 * 云平台 用于加密的md5的密钥
	 */
	private static final String CLIENT_KEY = "tfedu1234&*()";

	/**
	 * 云平台对接链接中用于对接校验的时间戳的参数名称
	 */
	private static final String PARAM_MD5_CHECK_NOWDATE = "nowdate";

	/**
	 * 云平台对接链接中的校验字符串
	 */

	private static final String PARAM_MD5_CHECK_NOWDATE_MD5_STR = "mdStr";

	
	private static String getMdStr(String newdate) {
		return newdate != null ? CloudClientMD5.Md5(newdate + CLIENT_KEY, 1, 1) : "";
	}

	private static boolean isCloud(HttpServletRequest request) {
		String newdate = request.getParameter(PARAM_MD5_CHECK_NOWDATE);
		String mdStr = request.getParameter(PARAM_MD5_CHECK_NOWDATE_MD5_STR);
		return newdate != null && mdStr != null && getMdStr(newdate).equals(mdStr);
	}

	public static ResultInfo checkCloudParams(HttpServletRequest request) {

		if (!isCloud(request)) {
			return ResultInfo.error();
		}
		return null;
	}
	
}
