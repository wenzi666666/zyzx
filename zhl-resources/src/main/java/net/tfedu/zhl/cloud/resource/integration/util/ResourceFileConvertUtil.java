package net.tfedu.zhl.cloud.resource.integration.util;

/**
 * 
 * 
 * 文件转换格式工具类
 * 
 * @author wangwr
 * @date 2017年8月8日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 */
public class ResourceFileConvertUtil {
	
	
	
	public static final String PDF_SUFFIX = ".pdf";
	public static final String TFSWF_SUFFIX = ".tfswf";
	public static final String TFMP4_SUFFIX = ".tfmp4";
	public static final String SWF_SUFFIX = ".swf";
	public static final String MP4_SUFFIX = ".mp4";
	public static final String ZIP_SUFFIX = ".zip";
	
	

	
	/**
	 * 指定播放pdf文件的系统资源文件格式或需要转换为pdf的自建资源
	 */
	public static final String FILE_PATTERN_PDF = ".kdh.caj.doc.docx.ppt.pptx.pptm.xls.xlsx.xlsm.pdf.teb.nh";

	
	// 这些扩展名替换为.pdf
	private static final String PDF_DOC_TYPES = ".doc .docx .ppt .pptx .xls .xlsx ";

	// 这些扩展名替换为.mp4
	private static final String MP4_VIDEO_TYPES = ".wmv .avi .flv ";

	// 这些扩展名替换为.mp3
	private static final String MP3_AUDIO_TYPES = ".wav .wma .m4a ";

	// 对现有类型进行扩展名替换
	public static String convertType(String tempPath) throws Exception {

		int index = tempPath.indexOf(".");
		String type = tempPath.substring(index + 1, tempPath.length());

		// 若当前文件属于需要转码的文本类型
		if (PDF_DOC_TYPES.indexOf(type) >= 0)
			tempPath = tempPath.replace(type, "pdf");

		// 若当前文件属于需要转码的视频类
		else if (MP4_VIDEO_TYPES.indexOf(type) >= 0) {
			tempPath = tempPath.replace(type, "mp4");

			// 若当前文件属于需要转码的音频类
		} else if (MP3_AUDIO_TYPES.indexOf(type) >= 0) {
			tempPath = tempPath.replace(type, "mp3");
		}

		return tempPath;

	}

	/**
	 * 根据上传的资源类型，判断是否需要转码
	 * 
	 * @param tempPath
	 * @return
	 * @throws Exception
	 */
	public static boolean isNeedConvert(String tempPath) throws Exception {
		int index = tempPath.indexOf(".");
		String type = tempPath.substring(index + 1, tempPath.length());

		// 若当前文件类型需要转码
		if (PDF_DOC_TYPES.indexOf(type) >= 0 || MP4_VIDEO_TYPES.indexOf(type) >= 0 || MP3_AUDIO_TYPES.indexOf(type) >= 0)
			return true;
		else
			return false;
	}

}
