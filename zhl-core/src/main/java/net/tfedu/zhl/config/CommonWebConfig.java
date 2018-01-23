package net.tfedu.zhl.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 通用web端的配置项： 主要用于各个内部平台之间的访问配置 不用的话，可以不调用
 * 
 * @author wangwr
 *
 */
@Component("commonWebConfig")
public class CommonWebConfig {

	/**
	 * 应用的访问地址，可以为多个，以逗号分隔
	 */
	@Value("#{configProperties['host']}")
	public String host;

	/**
	 * 应用的内网访问地址，可以为多个，以逗号分隔，但是第一个必须是文件服务器可以调用的（localhost\127.0.0.1不可以）
	 */
	@Value("#{configProperties['hostLocal']}")
	public String hostLocal;

	@Value("#{configProperties['resService']}")
	public String resService;

	@Value("#{configProperties['resServiceLocal']}")
	public String resServiceLocal;

	@Value("#{configProperties['fdHost']}")
	public String fdHost;

	@Value("#{configProperties['fdHostLocal']}")
	public String fdHostLocal;

	/**
	 * 是否允许一个账号使用不同的客户端同时登录
	 */
	@Value("#{configProperties['isRepeatLogin']}")
	public Boolean isRepeatLogin;

	/**
	 * 是否为云模式,默认为false
	 * 
	 * false: 根据浏览器url 和 host、hostlocal的配置,判断currentResPath使用文件服务的内外网地址
	 * 
	 * true: 云模式，直接返回文件服务的外网地址(currentResPath)
	 * 
	 */
	@Value("#{configProperties['isCloudModel']}")
	public Boolean isCloudModel;

	/**
	 * 题库的对接地址
	 */
	@Value("#{configProperties['tkHost']}")
	public String tkHost;
	/**
	 * 题库的对接地址local
	 */
	@Value("#{configProperties['tkHostLocal']}")
	public String tkHostLocal;

	/**
	 * 情景英语的对接地址
	 */
	@Value("#{configProperties['sceneEnglish']}")
	public String sceneEnglish;
	/**
	 * 情景英语的对接地址local
	 */
	@Value("#{configProperties['sceneEnglishLocal']}")
	public String sceneEnglishLocal;

	/**
	 * 论坛3.0的对接地址
	 */
	@Value("#{configProperties['forum3Host']}")
	public String forum3Host;
	/**
	 * 论坛3.0的对接地址local
	 */
	@Value("#{configProperties['forum3HostLocal']}")
	public String forum3HostLocal;

	/**
	 * 资源中心前端的地址
	 */
	@Value("#{configProperties['frontWebURL']}")
	public String frontWebURL;

	/**
	 * 教研服务的前端web地址
	 */
	@Value("#{configProperties['teachingResearchWebUrl']}")
	public String teachingResearchWebUrl;

	/**
	 * 动漫练习（尚学动漫） web端的外网地址
	 */
	@Value("#{configProperties['cartoonWeb']}")
	public String cartoonWeb;

	/**
	 * 动漫练习（尚学动漫） web端的内网地址
	 */
	@Value("#{configProperties['cartoonWebLocal']}")
	public String cartoonWebLocal;

	/**
	 * 动漫练习（尚学动漫） WebService 的地址
	 */
	@Value("#{configProperties['cartoonWebService']}")
	public String cartoonWebService;
	
	/**
	 * 项目密钥appKey  用于支持url中传递token
	 */
	@Value("#{configProperties['appKey']}")
	public String appKey;
	
	
	/**
	 * 当前项目的产品编码 
	 */
	@Value("#{configProperties['productCode']}")
	public String productCode;
	
	
	/**
	 * 多媒体学报的对接地址
	 */
	@Value("#{configProperties['dmtbHost']}")
	public String dmtbHost;
	
	
	/**
	 * 多媒体学报的内網对接地址
	 */
	@Value("#{configProperties['dmtbHostLocal']}")
	public String dmtbHostLocal;
	
	
	/**
	 * 当前产品 
	 */
	@Value("#{configProperties['bussinessname']}")
	public String bussinessname;
	
	
	
	/**
	 * 当前当前版本
	 */
	@Value("#{configProperties['version']}")
	public String version;
	
	
	
	/**
	 * 访问云平台的外网地址
	 */
	@Value("#{configProperties['cloudPlatForm']}")	
	public String  cloudPlatForm;
	
	
	
	/**
	 * 访问云平台的内网地址
	 */
	@Value("#{configProperties['cloudPlatFormLocal']}")	
	public String  cloudPlatFormLocal;
	
	
	
	/**
	 * 访问语文双课堂后端接口的外网地址
	 */
	@Value("#{configProperties['sktHost']}")	
	public String  sktHost;
	
	
	
	/**
	 * 访问语文双课堂后端接口的内网地址
	 */
	@Value("#{configProperties['sktHostLocal']}")	
	public String  sktHostLocal;
	
	
	
	
	
	
	/**
	 * 获取当前浏览器下云平台的访问地址
	 * @param request
	 * @return
	 */
	public String getCurrentCloudPlatform(HttpServletRequest request){
		String URI = request.getScheme() + "://" + request.getServerName() + (request.getServerPort() == 80 ? "" : (":" + request.getServerPort())) + request.getContextPath() + "/";
		// 当前云平台的访问地址
		String currentResPath = "";
		
		//云模式，直接返回外网地址
		if(isCloudModel){
			currentResPath = cloudPlatForm;
			return currentResPath;
		}
		
		
		if (host.contains(URI)) {
			currentResPath = cloudPlatForm;
		}
		else if (hostLocal.contains(URI)) {
			currentResPath = cloudPlatFormLocal;
		}
		
		return currentResPath;
	}
	
	
	
	public String getCloudPlatForm() {
		return cloudPlatForm;
	}

	public void setCloudPlatForm(String cloudPlatForm) {
		this.cloudPlatForm = cloudPlatForm;
	}

	public String getCloudPlatFormLocal() {
		return cloudPlatFormLocal;
	}

	public void setCloudPlatFormLocal(String cloudPlatFormLocal) {
		this.cloudPlatFormLocal = cloudPlatFormLocal;
	}

	public String getDmtbHost() {
		return dmtbHost;
	}

	public void setDmtbHost(String dmtbHost) {
		this.dmtbHost = dmtbHost;
	}

	public String getDmtbHostLocal() {
		return dmtbHostLocal;
	}

	public void setDmtbHostLocal(String dmtbHostLocal) {
		this.dmtbHostLocal = dmtbHostLocal;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getTeachingResearchWebUrl() {
		return teachingResearchWebUrl;
	}

	public void setTeachingResearchWebUrl(String teachingResearchWebUrl) {
		this.teachingResearchWebUrl = teachingResearchWebUrl;
	}

	/**
	 * 获取配置的host
	 * 
	 * @return
	 */
	public String getHost() {
		return host;
	}

	/**
	 * 
	 * @param host
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * 获取配置的hostlocal
	 * 
	 * @return
	 */
	public String getHostLocal() {
		return hostLocal;
	}

	public void setHostLocal(String hostLocal) {
		this.hostLocal = hostLocal;
	}

	/**
	 * 获取配置的ResService
	 * 
	 * @return
	 */
	public String getResService() {
		return resService;
	}

	public void setResService(String resService) {
		this.resService = resService;
	}

	/**
	 * 获取配置的resServiceLocal
	 * 
	 * @return
	 */
	public String getResServiceLocal() {
		return resServiceLocal;
	}

	public void setResServiceLocal(String resServiceLocal) {
		this.resServiceLocal = resServiceLocal;
	}

	/**
	 * 获取配置的fdHost
	 * 
	 * @return
	 */
	public String getFdHost() {
		return fdHost;
	}

	public void setFdHost(String fdHost) {
		this.fdHost = fdHost;
	}

	/**
	 * 获取配置的fdHostLocal
	 * 
	 * @return
	 */
	public String getFdHostLocal() {
		return fdHostLocal;
	}

	public void setFdHostLocal(String fdHostLocal) {
		this.fdHostLocal = fdHostLocal;
	}

	/**
	 * 获取当前浏览器下的文件服务路径
	 * 
	 * @param request
	 * @return
	 */
	public String getCurrentResPath(HttpServletRequest request) {
		String URI = request.getScheme() + "://" + request.getServerName()
				+ (request.getServerPort() == 80 ? "" : (":" + request.getServerPort())) + request.getContextPath()
				+ "/";
		// 当前文件 资源服务器地址
		String currentResPath = "";

		// 云模式，直接返回文件服务的外网地址
		if (isCloudModel) {
			currentResPath = resService;
			return currentResPath;
		}

		if (host.contains(URI)) {
			currentResPath = resService;
		} else if (hostLocal.contains(URI)) {
			currentResPath = resServiceLocal;
		}

		return currentResPath;
	}

	/**
	 * 获取当前浏览器下的自主学习平台的路径
	 * 
	 * @param request
	 * @return
	 */
	public String getCurrentFdHost(HttpServletRequest request) {
		String URI = request.getScheme() + "://" + request.getServerName()
				+ (request.getServerPort() == 80 ? "" : (":" + request.getServerPort())) + request.getContextPath()
				+ "/";
		// 当前 自主学习平台服务器地址
		String currentFdHost = "";

		// 云模式，直接返回自主学习平台服务器地址的外网地址
		if (isCloudModel) {
			currentFdHost = fdHost;
			return currentFdHost;
		}

		if (host.contains(URI)) {
			currentFdHost = fdHost;
		} else if (hostLocal.contains(URI)) {
			currentFdHost = fdHostLocal;
		}

		return currentFdHost;
	}

	/**
	 * 获取一个用于内部调用的host的地址
	 * 
	 * @return
	 */
	public String getHostLocalOne() {
		// 如果内网地址有多个的话 取第一个
		if (hostLocal.indexOf(",") > 0) {
			return hostLocal.split(",")[0];
		}
		return hostLocal;

	}

	public Boolean getIsRepeatLogin() {
		return isRepeatLogin;
	}

	public void setIsRepeatLogin(Boolean isRepeatLogin) {
		this.isRepeatLogin = isRepeatLogin;
	}

	public Boolean getIsCloudModel() {
		return isCloudModel;
	}

	public void setIsCloudModel(Boolean isCloudModel) {
		this.isCloudModel = isCloudModel;
	}

	public String getTkHost() {
		return tkHost;
	}

	public void setTkHost(String tkHost) {
		this.tkHost = tkHost;
	}

	public String getTkHostLocal() {
		return tkHostLocal;
	}

	public void setTkHostLocal(String tkHostLocal) {
		this.tkHostLocal = tkHostLocal;
	}

	public String getSceneEnglish() {
		return sceneEnglish;
	}

	public void setSceneEnglish(String sceneEnglish) {
		this.sceneEnglish = sceneEnglish;
	}

	public String getSceneEnglishLocal() {
		return sceneEnglishLocal;
	}

	public void setSceneEnglishLocal(String sceneEnglishLocal) {
		this.sceneEnglishLocal = sceneEnglishLocal;
	}

	public String getForum3Host() {
		return forum3Host;
	}

	public void setForum3Host(String forum3Host) {
		this.forum3Host = forum3Host;
	}

	public String getForum3HostLocal() {
		return forum3HostLocal;
	}

	public void setForum3HostLocal(String forum3HostLocal) {
		this.forum3HostLocal = forum3HostLocal;
	}

	/**
	 * 获取当前浏览器下的题库的路径
	 * 
	 * @param request
	 * @return
	 */
	public String getCurrentTkHost(HttpServletRequest request) {
		String URI = request.getScheme() + "://" + request.getServerName()
				+ (request.getServerPort() == 80 ? "" : (":" + request.getServerPort())) + request.getContextPath()
				+ "/";
		// 当前 题库服务器地址
		String currentTkHost = "";

		// 云模式，直接返回题库服务器地址的外网地址
		if (isCloudModel) {
			currentTkHost = tkHost;
			return currentTkHost;
		}

		if (host.contains(URI)) {
			currentTkHost = tkHost;
		} else if (hostLocal.contains(URI)) {
			currentTkHost = tkHostLocal;
		}

		return currentTkHost;
	}

	/**
	 * 获取当前浏览器下的情景英语的对接路径
	 * 
	 * @param request
	 * @return
	 */
	public String getCurrentSceneEnglish(HttpServletRequest request) {
		String URI = request.getScheme() + "://" + request.getServerName()
				+ (request.getServerPort() == 80 ? "" : (":" + request.getServerPort())) + request.getContextPath()
				+ "/";
		// 当前 情景英语服务器地址
		String currentSceneEnglish = "";

		// 云模式，直接返回情景英语服务器地址的外网地址
		if (isCloudModel) {
			currentSceneEnglish = sceneEnglish;
			return currentSceneEnglish;
		}

		if (host.contains(URI)) {
			currentSceneEnglish = sceneEnglish;
		} else if (hostLocal.contains(URI)) {
			currentSceneEnglish = sceneEnglishLocal;
		}

		return currentSceneEnglish;
	}

	/**
	 * 获取当前浏览器下的论坛3.0的路径
	 * 
	 * @param request
	 * @return
	 */
	public String getCurrentForum3(HttpServletRequest request) {
		String URI = request.getScheme() + "://" + request.getServerName()
				+ (request.getServerPort() == 80 ? "" : (":" + request.getServerPort())) + request.getContextPath()
				+ "/";
		// 当前 论坛3.0台服务器地址
		String currentForum3 = "";

		// 云模式，直接返回论坛3.0服务器地址的外网地址
		if (isCloudModel) {
			currentForum3 = forum3Host;
			return currentForum3;
		}

		if (host.contains(URI)) {
			currentForum3 = forum3Host;
		} else if (hostLocal.contains(URI)) {
			currentForum3 = forum3HostLocal;
		}

		return currentForum3;
	}

	public String getFrontWebURL() {
		return frontWebURL;
	}

	public void setFrontWebURL(String frontWebURL) {
		this.frontWebURL = frontWebURL;
	}

	public String getCartoonWeb() {
		return cartoonWeb;
	}

	public void setCartoonWeb(String cartoonWeb) {
		this.cartoonWeb = cartoonWeb;
	}

	public String getCartoonWebLocal() {
		return cartoonWebLocal;
	}

	public void setCartoonWebLocal(String cartoonWebLocal) {
		this.cartoonWebLocal = cartoonWebLocal;
	}

	public String getCartoonWebService() {
		return cartoonWebService;
	}

	public void setCartoonWebService(String cartoonWebService) {
		this.cartoonWebService = cartoonWebService;
	}
	
	/**
	 * 获取当前浏览器下的 动漫练习（尚学动漫） web端的地址
	 * 
	 * @param request
	 * @return
	 */
	public String getCurrentCartoonWeb(HttpServletRequest request) {
		String URI = request.getScheme() + "://" + request.getServerName()
				+ (request.getServerPort() == 80 ? "" : (":" + request.getServerPort())) + request.getContextPath()
				+ "/";
		// 动漫练习（尚学动漫） web端
		String current = "";

		// 云模式，直接返回论坛3.0服务器地址的外网地址
		if (isCloudModel) {
			current = cartoonWeb;
			return current;
		}

		if (host.contains(URI)) {
			current = cartoonWeb;
		} else if (hostLocal.contains(URI)) {
			current = cartoonWebLocal;
		}

		return current;
	}
	
	/**
	 * 获取当前浏览器下的 动漫练习（尚学动漫） web端的地址
	 * 
	 * @param request
	 * @return
	 */
	public String getCurrentDMTBWeb(HttpServletRequest request) {
		String URI = request.getScheme() + "://" + request.getServerName()
				+ (request.getServerPort() == 80 ? "" : (":" + request.getServerPort())) + request.getContextPath()
				+ "/";
		// 动漫练习（尚学动漫） web端
		String current = "";

		// 云模式，直接返回论坛3.0服务器地址的外网地址
		if (isCloudModel) {
			current = dmtbHost;
			return current;
		}

		if (host.contains(URI)) {
			current = dmtbHost;
		} else if (hostLocal.contains(URI)) {
			current = dmtbHostLocal;
		}

		return current;
	}

	public String getBussinessname() {
		return bussinessname;
	}

	public void setBussinessname(String bussinessname) {
		this.bussinessname = bussinessname;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}



	public String getSktHost() {
		return sktHost;
	}



	public void setSktHost(String sktHost) {
		this.sktHost = sktHost;
	}



	public String getSktHostLocal() {
		return sktHostLocal;
	}



	public void setSktHostLocal(String sktHostLocal) {
		this.sktHostLocal = sktHostLocal;
	}

	
	
	
}
