package net.tfedu.zhl.cloud.resource.thirdpartyAPI.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import com.alibaba.fastjson.JSONObject;

import net.tfedu.zhl.cloud.resource.config.ResourceThirdPartyConfig;
import net.tfedu.zhl.cloud.resource.thirdpartyAPI.jnzx.entity.ZXUserInfoResult;
import net.tfedu.zhl.sso.users.entity.RegisterAddForm;
import net.tfedu.zhl.sso.users.entity.SRegister;
import net.tfedu.zhl.sso.users.service.RegisterService;

/**
 * 延庆对接的工具类
 * 
 * @author wangwr
 *
 */
public class YanQingUtil {
	
	/**
	 * 登录后传递的sessid的标识
	 */
	public static final String USER_SESSID = "sessid";
	
	
	
	/**
	 * 判断是否登录的方法名
	 */
	private static final String METHOD_ISUSERLOGIN = "user.isUserLogin";
	
	
	/**
	 * 获取用户信息的方法名
	 */
	private static final String METHOD_GETUSERINFO = "user.getUserInfo";
	
	
	/**
	 * 默认的接口地址
	 */
	private static final String DEFAULT_HOST = "http://yquac.k12/platform/rpc/index.php";
	
	
	
	
	
	/**
	 * 获取接口路径
	 * @return
	 */
	public static String getHost(ResourceThirdPartyConfig resourceThirdPartyConfig){
		// xml-rpc的url地址
		String host = resourceThirdPartyConfig.getYq_host();
		if(StringUtils.isEmpty(host)){
			host = DEFAULT_HOST;
		}
				
		return host ;
	}
	
	
	
	

	/**
	 * 获取用户id
	 * 
	 * @param sessid  登録後的令牌
	 * @param resourceThirdPartyConfig
	 *            对接配置信息
	 * @return   返回user_id
	 * @throws MalformedURLException
	 * @throws XmlRpcException
	 */
	public static String getUserId(ResourceThirdPartyConfig resourceThirdPartyConfig,String sessid)
			throws MalformedURLException, XmlRpcException {
		//获取接口
		String host = getHost(resourceThirdPartyConfig);
		
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		config.setServerURL(new URL(host));
		XmlRpcClient client = new XmlRpcClient();
		client.setConfig(config);
		
		
		return (String) client.execute(METHOD_ISUSERLOGIN, new Object[] {sessid});
	}
	
	
	/**
	 * 返回map
	 * @param resourceThirdPartyConfig
	 * @param user_id
	 * @return
	 * @throws MalformedURLException
	 * @throws XmlRpcException
	 */
	public static HashMap<String,Object> getUserInfo(ResourceThirdPartyConfig resourceThirdPartyConfig,String user_id)
			throws MalformedURLException, XmlRpcException {
		
		
		//获取接口
		String host = getHost(resourceThirdPartyConfig);

		
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		config.setServerURL(new URL(host));
		XmlRpcClient client = new XmlRpcClient();
		client.setConfig(config);
		
		Object o = client.execute(METHOD_GETUSERINFO,
				new Object[] { " user_id='" + user_id + "'", " user_id asc", 1, 0 });
		if(null !=o && ((Object[])o).length>0){
			return  (HashMap<String,Object>)(((Object[])o)[0]); 
		}
		return  null;
	}
	
	
	

	public static void main(String[] args) throws MalformedURLException, XmlRpcException {
		
		
		/*String sessid = "79c9nepjic6v09p3u16sho6m90";
		
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		config.setServerURL(new URL("http://yquac.k12/platform/rpc/index-utf8.php"));
		XmlRpcClient client = new XmlRpcClient();
		client.setConfig(config);
		String user_id = (String) client.execute("user.isUserLogin", new Object[] { "79c9nepjic6v09p3u16sho6m90" });

		Object o = client.execute("user.getUserInfo",
				new Object[] { " user_id='" + user_id + "'", " user_id asc", 5, 0 });
		System.out.println(((Object[])o).length);
		Object userInfo = (((Object[])o)[0]);
		
		System.out.println(userInfo.toString());
		*/
		
		String s = "{'user_id':'test001','k12_tag_id':[],'k12_tag':[],'k12_school_id':0,'k12_school':''}";
		
		HashMap temp = JSONObject.parseObject(s, HashMap.class);
		
		
		System.out.println(temp.get("k12_school"));
		
	}
	
	
	
	/**
	 * 增加注册并增加映射关系(指定roleId)
	 * @param form      注册信息 
	 * @param registerService   用户服务接口
	 * @param userName    用户在 第三方平台的用户名
	 * @param platformcode   第三方平台code
	 * @return
	 * @throws Exception
	 */
	public static SRegister registNewUserByForm(RegisterAddForm form, RegisterService registerService, String userName, String platformcode)
			throws Exception {

		return registerService.addRegister(form, userName, platformcode);
	}

}
