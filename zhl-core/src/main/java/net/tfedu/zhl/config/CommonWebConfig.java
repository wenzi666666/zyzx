package net.tfedu.zhl.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * 通用web端的配置项：
 * 主要用于各个内部平台之间的访问配置
 * 不用的话，可以不调用
 * @author wangwr
 *
 */
@Component("commonWebConfig")
public class CommonWebConfig {

	@Value("#{configProperties['host']}")
	public String host;
	
	
	@Value("#{configProperties['hostLocal']}")
	public String hostLocal ;
	
	
	@Value("#{configProperties['resService']}")
	public String resService ;
	
	
	
	@Value("#{configProperties['resServiceLocal']}")
	public String resServiceLocal ;
	
	
	@Value("#{configProperties['fdHost']}")
	public String fdHost ;
	
	
	
	@Value("#{configProperties['fdHostLocal']}")
	public String fdHostLocal ;


	
	/**
	 * 是否允许一个账号使用不同的客户端同时登录
	 */
	@Value("#{configProperties['isRepeatLogin']}")
	public Boolean isRepeatLogin;
	
	
	

	/**
	 *获取配置的host
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
	 * @param request
	 * @return
	 */
	public String getCurrentResPath(HttpServletRequest request){
        String URI = request.getScheme() + "://" + request.getServerName() + (request.getServerPort() == 80 ? "" : (":" + request.getServerPort())) + request.getContextPath() + "/";
        // 当前文件 资源服务器地址
        String currentResPath = "";
		
        if (host.contains(URI)) {
            currentResPath = resService;
        }
        else if (hostLocal.contains(URI)) {
            currentResPath = resServiceLocal;
        }
       
        return currentResPath;
	}
	
	
	/**
	 * 获取当前浏览器下的自主学习平台的路径
	 * @param request
	 * @return
	 */
	public String getCurrentFdHost(HttpServletRequest request){
        String URI = request.getScheme() + "://" + request.getServerName() + (request.getServerPort() == 80 ? "" : (":" + request.getServerPort())) + request.getContextPath() + "/";
        // 当前  自主学习平台服务器地址
        String currentFdHost = "";
		
        if (host.contains(URI)) {
        	currentFdHost = fdHost;
        }
        else if (hostLocal.contains(URI)) {
        	currentFdHost = fdHostLocal;
        }

        return currentFdHost;
	}
	
	
	/**
	 * 获取一个用于内部调用的host的地址
	 * @return
	 */
	public String getHostLocalOne(){
		// 如果内网地址有多个的话 取第一个
        if (hostLocal.indexOf(",") > 0) {
           return  hostLocal.split(",")[0];
        }
		return hostLocal;

	}


	public Boolean getIsRepeatLogin() {
		return isRepeatLogin;
	}


	public void setIsRepeatLogin(Boolean isRepeatLogin) {
		this.isRepeatLogin = isRepeatLogin;
	}
	
	
}
