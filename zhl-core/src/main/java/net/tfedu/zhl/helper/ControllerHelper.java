
package net.tfedu.zhl.helper;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.core.exception.ParamsException;

/**
 * Controller助手类
 * 
 * @author bruce
 *
 */
public class ControllerHelper {

	/**
	 * 获取当前的用户id
	 * 
	 * @param request
	 * @return
	 */
	public static Long getCurrentUserId(HttpServletRequest request) {
		return (Long) request.getAttribute("currentUserId");
	}

	// /**
	// *
	// *
	// * 获取当前用户id
	// * @return
	// *
	// */
	//
	// public static Long getCurrentUserId(){
	// HttpServletRequest request =
	// ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	// return getCurrentUserId(request);
	// }

	/**
	 * 检测空
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String checkEmpty(String str) throws Exception {
		if (StringUtils.isEmpty(str)) {
			throw new ParamsException();
		}
		return str.trim();
	}

	/**
	 * 检测Long类型的参数是否为null或是否为0
	 * 
	 * @param l
	 * @return
	 * @throws Exception
	 */
	public static void checkLongEmpty(Long l) throws Exception {
		if (l == null || l == 0) {
			throw new ParamsException();
		}
	}

	/**
	 * 检测Integer类型的参数是否为null或是否为0
	 * 
	 * @param l
	 * @return
	 * @throws Exception
	 */
	public static void checkIntegerEmpty(Integer l) throws Exception {
		if (l == null || l == 0) {
			throw new ParamsException();
		}
	}

	/**
	 * 检查数组是否为空
	 * 
	 * @param array
	 * @throws Exception
	 */
	public static void checkArrayEmpty(Object[] array) throws Exception {
		if (array == null || array.length == 0) {
			throw new ParamsException();
		}
	}

	/**
	 * 获取单个参数值
	 * 
	 * @param request
	 * @param paramName
	 * @return 字符串
	 * @throws ParamsException
	 */
	public static String getParameter(HttpServletRequest request, String paramName) throws ParamsException {
		String param;
		if (StringUtils.isNotEmpty(request.getParameter(paramName))) {
			param = request.getParameter(paramName).toString().trim();
		} else {
			throw new ParamsException();
		}
		return param;
	}

	/**
	 * 获取可选的参数
	 * 
	 * @param request
	 * @param paramName
	 * @return
	 * @throws ParamsException
	 */
	public static String getOptionalParameter(HttpServletRequest request, String paramName) throws ParamsException {
		String param = request.getParameter(paramName);
		return StringUtils.isNotEmpty(param) ? param.toString().trim() : "";
	}

	/**
	 * 获取可选的int参数
	 * 
	 * @param request
	 * @param paramName
	 * @return
	 * @throws ParamsException
	 */
	public static int getOptionalIntegerParameter(HttpServletRequest request, String paramName) throws ParamsException {
		String param = request.getParameter(paramName);
		return StringUtils.isNotEmpty(param) ? Integer.parseInt(param.toString().trim()) : 0;
	}

	/**
	 * 获取可选的long参数
	 * 
	 * @param request
	 * @param paramName
	 * @return
	 * @throws ParamsException
	 */
	public static long getOptionalLongParameter(HttpServletRequest request, String paramName) throws ParamsException {
		String param = request.getParameter(paramName);
		return StringUtils.isNotEmpty(param) ? Long.parseLong(param.toString().trim()) : 0;
	}

	/**
	 * 获取单个参数值
	 * 
	 * @param request
	 * @param paramName
	 * @return 字符串
	 * @throws ParamsException
	 */
	public static String getHeaderParameter(HttpServletRequest request, String paramName) throws ParamsException {
		String param;
		if (StringUtils.isNotEmpty(request.getHeader(paramName))) {
			param = request.getHeader(paramName).toString().trim();
		} else {
			throw new ParamsException();
		}
		return param;
	}

	/**
	 * 获取单个参数值
	 * 
	 * @param request
	 * @param paramName
	 * @return 整数
	 * @throws ParamsException
	 */
	public static int getIntParameter(HttpServletRequest request, String paramName) throws ParamsException {
		int param;
		if (StringUtils.isNotEmpty(request.getParameter(paramName))) {
			param = Integer.parseInt(request.getParameter(paramName).toString().trim());
		} else {
			throw new ParamsException();
		}
		return param;
	}

	/**
	 * 获取单个参数值，如果获取失败，则赋值0
	 * 
	 * @param request
	 * @param paramName
	 * @return 整数
	 * @throws ParamsException
	 */
	public static int getIntWithDefault(HttpServletRequest request, String paramName) throws ParamsException {
		int param;
		if (StringUtils.isNotEmpty(request.getParameter(paramName))) {
			param = Integer.parseInt(request.getParameter(paramName).toString().trim());
		} else {
			param = 0;
		}
		return param;
	}

	/**
	 * 获取单个参数值
	 * 
	 * @param request
	 * @param paramName
	 * @return 长整形
	 * @throws ParamsException
	 */
	public static long getLongParameter(HttpServletRequest request, String paramName) throws ParamsException {
		long param;
		if (StringUtils.isNotEmpty(request.getParameter(paramName))) {
			param = Long.parseLong(request.getParameter(paramName).toString().trim());
		} else {
			throw new ParamsException();
		}
		return param;
	}

	/**
	 * 获取页码
	 * 
	 * @param request
	 * @return
	 * @throws ParamsException
	 */
	public static int getPage(HttpServletRequest request) throws ParamsException {
		String strPageNum = request.getParameter("page");
		return StringUtils.isNotEmpty(strPageNum) ? Integer.parseInt(strPageNum) : 1;
	}

	/**
	 * 获取页大小
	 * 
	 * @param request
	 * @return
	 * @throws ParamsException
	 */
	public static int getPageSize(HttpServletRequest request) throws ParamsException {
		String strPageSize = request.getParameter("perPage");
		return StringUtils.isNotEmpty(strPageSize) ? Integer.parseInt(strPageSize) : 10;
	}

	/**
	 * 获取页大小
	 * 
	 * @param request
	 * @return
	 * @throws ParamsException
	 */
	public static int getPageSize(HttpServletRequest request, Integer perPage) throws ParamsException {
		String strPageSize = request.getParameter("perPage");
		if (null == perPage || 0 == perPage) {
			return getPageSize(request);
		}

		return StringUtils.isNotEmpty(strPageSize) ? Integer.parseInt(strPageSize) : perPage;
	}

	/**
	 * 不同的子系统，使用不同的model参数
	 * 
	 * @param request
	 * @return
	 */
	public static String getModel(HttpServletRequest request) {
		return request.getParameter("model") == null ? " " : request.getParameter("model");
	}

	/**
	 * 不同的子系统，使用不同的model参数
	 * 
	 * @param request
	 * @return
	 */
	public static String getTokenByURL(HttpServletRequest request) {
		return request.getParameter("token") == null ? "" : request.getParameter("token");
	}

	/**
	 * 获取当前系统时间
	 * 
	 * @return
	 */
	public static Date getCurrentDate() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * 根据资源的路径，判断是否是网络资源
	 * 
	 * @param path
	 *            是否以"http://"或"https://" 开头
	 * @return
	 */
	public static Boolean isNetResource(String path) {

		if (StringUtils.isEmpty(path)) {
			return false;
		}

		if (path.startsWith("http://") || path.startsWith("https://")) {
			return true;
		}

		return false;
	}

}
