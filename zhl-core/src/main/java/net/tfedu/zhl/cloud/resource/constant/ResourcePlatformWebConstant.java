package net.tfedu.zhl.cloud.resource.constant;


/**
 
  web的常量
  
    @author wangwr
    @date 2017年8月3日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
public class ResourcePlatformWebConstant {

	
	
	/**
	 * 获取前端访问（默认为系统资源）的URL
	 * @param fontWebURL   前端访问地址
	 * @param userId       用户id
	 * @param token        登录成功后的token
	 * @return
	 */
	public static final String getWEbURL(String fontWebURL,String userId,String token){
		
		return getWEbURL(fontWebURL, userId, token, "");
	}
	
	
	
	/**
	 * 获取前端访问（默认为系统资源）的URL--支持第三方
	 * @param fontWebURL    前端访问地址
	 * @param userId        用户id
	 * @param token         登录成功后的token
	 * @param iscoursewares 第三方系统的code
	 * @return
	 */
	public static final String getWEbURL(String fontWebURL,String userId,String token,String iscoursewares){
		
		return new StringBuffer().append(fontWebURL)
				.append("/router").append("?tocken=").append(token)
				.append("&userId=").append(userId)
				.append("&iscoursewares=").append(iscoursewares)
				.toString();
	}
	
	
	/**
	 * 成功标识
	 */
	public static final String SUCCESS = "success";
	
	/**
	 * 失败标识
	 */
	public static final String ERROR = "error";
	
	/**
	 * 失败标识
	 */
	public static final String FAIL = "fail";
	
	/**
	 * 信息标识
	 */
	public static final String MESSAGE = "message";
	
	/**
	 * 结果表示
	 */
	public static final String RESULT = "result";
	
	/**
	 * 自建目录标识
	 */
	public static final String COURSE = "course";
	
	
	
}
