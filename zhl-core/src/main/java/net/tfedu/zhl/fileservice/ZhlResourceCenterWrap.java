package net.tfedu.zhl.fileservice;

import java.io.File;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
/**
 * 访问IIS文件服务器的工具类
 * 
 * @author wangwr
 * @copyright 同方知好乐教育技术北京有限公司
 * @createtime 2015-3-27下午2:45:20
 * @desc:
 * @version:
 */
public class ZhlResourceCenterWrap {

	private static final String CustomerID = "100798967857546";
	private static final String CustomerKey = "JWJ83OPR0985LJL";

	// 历史上IIS服务器的虚拟目录
	public static final String MTDATA = "mtdata";
	
	
	/**
	 * 加密的文件格式
	 */
	public static final String FileType_encrypt = ".swf.mp4";

	/**
	 * 缩略图图片格式后缀
	 */
	public static final String THUMBNAILS_IMG_TYPE = "_icon.jpg";

	/**
	 * 文件系统中 用于 限时展示的系统学习资源文件后缀 (现在只有mp4限时展示):_split.mp4
	 */
	public static final String STUDYRES_LIMIT_TYPE = "_split.mp4";

	/**
	 * 需要限时展示的系统学习资源后缀.mp4
	 */
	public static final String STUDYRES_LIMIT = ".mp4";

	public static final int default_diskOrder = 1;
	
	
	
	/**
	 * 打包文件路径前缀
	 */
	public static final String zipPath_prefix = "zipPath";
	

	/**
	 * 上传文件路径前缀
	 */
	public static final String upload_prefix = "upFile";
	
	
	/**
	 * 获取用户的上传文件
	 * @param userId
	 * @return
	 */
	public static String getUserUploadPath(Long userId,Long schoolId){
		Calendar time =  Calendar.getInstance();
		
		return  new StringBuffer()
		.append(upload_prefix)
		.append(File.separator)
		.append(time.get(Calendar.YEAR))
		.append(File.separator)
		.append(userId)
		.append(File.separator)
		.append(schoolId)
		.append(File.separator)
		.toString();
	}
	
	
	/**
	 * 获取用户的打包文件
	 * @param userId
	 * @return
	 */
	public static String getUserZipPath(Long userId){
		Calendar time =  Calendar.getInstance();
		
		return  new StringBuffer()
		.append(zipPath_prefix)
		.append(File.separator)
		.append(time.get(Calendar.YEAR))
		.append(File.separator)
		.append(userId)
		.append(File.separator)
		.append(time.getTimeInMillis())
		.append(".zip")
		.toString();
	}
	

	/**
	 * 返回 资源的播放路径
	 * 
	 * @param resSerUrl
	 *            资源服务器的地址
	 * @return
	 */
	public static String getWebPlayUrl(String resSerUrl, String resPath,
			Boolean IsMultiFile, int diskOrder) {

		return new zhldowncenter(CustomerID, CustomerKey, resSerUrl)
				.GetResourcePlayURL(resPath, IsMultiFile, diskOrder);
	}

	/**
	 * 获取电子教辅路径
	 * 
	 * @param args
	 * 
	 */
	public static String GetEBookPlayerURL(String EBookPath, String serverURL,
			int diskOrder) {
		return getWebPlayUrl(serverURL, EBookPath, true, diskOrder)
				+ "&mode=ebook";
	}

	/**
	 * 返回 资源的播放路径
	 * 
	 * @param resSerUrl
	 *            资源服务器的地址
	 * @return
	 */
	public static String getWebPlayUrl(String resSerUrl, String resPath,
			Boolean IsMultiFile) {

		return getWebPlayUrl(resSerUrl, resPath, IsMultiFile, default_diskOrder);
	}

	/**
	 * 单文件获取资源播放路径
	 * 
	 * @param resSerUrl
	 * @param resPath
	 * @return
	 */
	public static String getWebPlayUrl(String resSerUrl, String resPath) {

		return getWebPlayUrl(resSerUrl, resPath, false);
	}

	/**
	 * 返回 上传的url
	 * 
	 * @param resSerUrl
	 * @param uploadPath
	 *            指定的上传路径
	 * @return
	 */
	public static String getUploadUrl(String resSerUrl, String uploadPath) {
		return new zhldowncenter(CustomerID, CustomerKey, resSerUrl)
				.GetUploadURLString(uploadPath, "rename", "");
	}
	
	/**
	 * 返回 上传的url
	 * 
	 * @param resSerUrl
	 * @param uploadPath
	 *            指定的上传路径
	 * @param extend
	 * @return
	 */
	public static String getUploadUrl(String resSerUrl, String uploadPath,
			String extend) {
		return new zhldowncenter(CustomerID, CustomerKey, resSerUrl)
				.GetUploadURLString(uploadPath, extend, "");
	}

	/**
	 * 返回 自建资源、区本资源、校本资源下载路径 获取下载路径(以附件的形式下载)
	 * 时间永久有效
	 * @param resSerUrl
	 *            下载服务器IIS的路径
	 * @param resPath
	 *            目标资源的相对路径
	 * @return
	 */
	public static String getDownUrl(String resSerUrl, String resPath) {
		boolean IsDown = true;
		return new zhldowncenter(CustomerID, CustomerKey, resSerUrl)
				.GetFreeDownloadURLString(resPath, IsDown);
	}

	
	/**
	 * 返回 自建资源、区本资源、校本资源下载路径 获取下载路径(以附件的形式下载)
	 * 时间永久有效
	 * @param resSerUrl
	 *            下载服务器IIS的路径
	 * @param resPath
	 *            目标资源的相对路径
	 * @return
	 */
	public static String getDownUrl(String resSerUrl, String resPath,
			int diskOrder) {
		boolean IsDown = true;
		return new zhldowncenter(CustomerID, CustomerKey, resSerUrl)
				.GetFreeDownloadURLString(resPath, IsDown, diskOrder);
	}

	
	/**时间永久有效
	 * 获取网页端的缩略图
	 * @param resSerUrl
	 * @param resPath
	 * @param diskOrder
	 * @return
	 */
	public static String getWebThumbnail(String resSerUrl, String resPath,
			int diskOrder) {
		boolean IsDown = false;
		return new zhldowncenter(CustomerID, CustomerKey, resSerUrl)
				.GetFreeDownloadURLString(resPath, IsDown, diskOrder);
	}
	/**时间永久有效
	 * 获取网页端的缩略图
	 * @param resSerUrl
	 * @param resPath
	 * @param diskOrder
	 * @return
	 */
	public static String getWebThumbnail(String resSerUrl, String resPath) {
		boolean IsDown = false;
		return new zhldowncenter(CustomerID, CustomerKey, resSerUrl)
				.GetFreeDownloadURLString(resPath, IsDown, 1);
	}
	
	/**
	 * 返回 系统资源下载方式
	 * 
	 * @param resSerUrl
	 *            下载服务器IIS的路径
	 * @param resPath
	 * @return
	 */
	public static String getDownUrlForSysRes(String resSerUrl, String resPath,
			Boolean IsMultiFile) {
		boolean IsDown = true;
		return new zhldowncenter(CustomerID, CustomerKey, resSerUrl)
				.GetProtectDownloadURLString(resPath, IsDown, IsMultiFile);

	}

	/**
	 * 获取（系统学习）资源（.swf .mp4）离线限时播放包
	 * 时间永久有效
	 * @param FileName
	 *            文件名（FilePath有效时为主文件名）
	 * @param FilePath
	 *            文件路径（多文件时有效）
	 * @param resSerUrl
	 *            下载服务器IIS的路径
	 * @return
	 */
	public static String GetExePackageURL(String resSerUrl, String FileName,
			String FilePath) {
		return new zhldowncenter(CustomerID, CustomerKey, resSerUrl)
				.GetExePackageURL(FileName, FilePath);
	}

	/**
	 * 返回 系统资源下载方式
	 * 
	 * @param resSerUrl
	 *            下载服务器IIS的路径
	 * @param resPath
	 * @return
	 */
	public static String getDownUrlForSysRes(String resSerUrl, String resPath,
			Boolean IsMultiFile, int diskOrder) {
		boolean IsDown = true;
		return new zhldowncenter(CustomerID, CustomerKey, resSerUrl)
				.GetProtectDownloadURLString(resPath, IsDown, IsMultiFile,
						diskOrder);

	}

	/**
	 * 获取（系统学习）资源（.swf .mp4）离线限时播放包
	 * 
	 * @param FileName
	 *            文件名（FilePath有效时为主文件名）
	 * @param FilePath
	 *            文件路径（多文件时有效）
	 * @param resSerUrl
	 *            下载服务器IIS的路径
	 * @return
	 * 时间永久有效
	 * 单文件时，FilePath为空，FileName为flash或者mp4全文件名
		多文件时，FilePath为多文件目录，FileName为不带目录的主文件名
	 */
	public static String GetExePackageURL(String resSerUrl, String FileName,
			String FilePath, int diskOrder) {
		return new zhldowncenter(CustomerID, CustomerKey, resSerUrl)
				.GetExePackageURL(FileName, FilePath, diskOrder);
	}

	/**
	 * 获取文件信息
	 * 
	 * @param resSerUrl
	 * @param FilePath
	 * @return
	 */
	public static String GetFileInfo(String resSerUrl, String FilePath) {
		return new zhldowncenter(CustomerID, CustomerKey, resSerUrl)
				.GetFileInfo(FilePath);
	}
	
	/**
	 * 返回 上传的url
	 * 用于上传文件进行格式转换
	 * @param resSerUrl
	 * @param uploadPath
	 *            指定的上传路径
	 * @return
	 */
	public static String getUploadUrlConvert(String uploadPath,String resServiceLocal
										,String currentResService,String hostLocal,long userId) {
		//回调函数action
		String returnUrl = hostLocal + "/v1.0/resource/uploadConvertCallBack";

		String url =  new zhldowncenter(CustomerID, CustomerKey, resServiceLocal)
				.GetUploadURLString(uploadPath, "rename&userId=" + userId,returnUrl);  //returnUrl中配置回调函数的action 	
		return url.replace(resServiceLocal, currentResService) ;
	}
	
	/**
	 * 将历史文件发送给文件服务器进行格式转换
	 * 
	 */
	public static void sendFileToConvert(String fileName,HttpServletRequest request,long userId){
		String basePath = request.getServletContext().getInitParameter("hostLocal");
		String paths[] = basePath.split(",");
		
		//回调函数action
		String returnUrl = paths[0] + "historyConvert_insert.action";
		
		String resSerPath = request.getServletContext().getInitParameter("resServiceLocal");
		
		//回调函数action
		new zhldowncenter(CustomerID, CustomerKey, resSerPath).SendFileConvertTask(fileName, "rename&userId=" + userId, returnUrl);
	}
	
	
	/**
	 * 发送打包任务给文件服务
	 * @return
	 */
	public static boolean  sendZipTask(ZipTaskContent Content,String resSerPath){
		
		return  new zhldowncenter(CustomerID, CustomerKey, resSerPath).SendZipPackageTask(Content);
	}
	
	/**
	 * 获取执行打包任务URL地址
	 * 
	 * @param FileName
	 *            文件名（单文件时为文件全路径，多文件时为不带路径的主文件名）
	 * @param FilePath
	 *            文件路径（多文件时有效，多文件存放路径）
	 * @return
	 */
	public static String InvokeExePackageTaskURL(String FileName, String FilePath,String resSerPath){
		return  new zhldowncenter(CustomerID, CustomerKey, resSerPath).InvokeExePackageTaskURL(FileName, FilePath);
	}
	
	
	
	
}

