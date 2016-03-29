package net.tfedu.zhl.cloud.resource.prepare.util;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import com.alibaba.druid.util.HttpClientUtils;
import com.alibaba.fastjson.JSONObject;

import net.tfedu.zhl.cloud.resource.prepare.entity.JPrepareContentView;
import net.tfedu.zhl.cloud.resource.prepare.entity.ResourceSimpleInfo;
import net.tfedu.zhl.fileservice.HttpUtil;
import net.tfedu.zhl.fileservice.ZhlResourceCenterWrap;

/**
 * （资源中心）备课夹 常量
 * @author wangwr
 *
 */
public class JPrepareConstant {
	
	/**
	 * 本地资源
	 */
	public static final int CONTTYPE_LOCALRES_OLD = 1;
	/**
	 * 系统资源
	 */	
	public static final  int CONTTYPE_SYSRES_OLD = 2;
	
	/**
	 * 本地资源
	 */
	public static final int CONTTYPE_LOCALRES = 9;
	/**
	 * 系统资源
	 */	
	public static final  int CONTTYPE_SYSRES = 10;
	
	
	/**
	 * 区本资源
	 */
	public static final  int CONTTYPE_DISTRICT_RES = 11;
	
	/**
	 * 校本资源
	 */	
	public static final  int CONTTYPE_SCHOOL_RES = 12;
	
	

	/**
	 * 系统资源的来源表示
	 */
	public static final int fromFlag_sysRes = 0 ;
	/**
	 * 自建资源的来源表示
	 */
	public static final int fromFlag_localRes = 1 ;
	
	
	/**
	 * 共享资源的来源表示
	 */
	public static final int fromFlag_sharedRes =2 ;
	
	/**
	 * 校本资源的来源表示
	 */
	public static final int fromFlag_schoolRes = 3 ;
	
	/**
	 * 区本资源的来源表示
	 */
	 public static final int fromFlag_districtRes = 4 ;
	
	
	
	
	/**
	 * 将fromFlag转换成 备课夹中的conttype
	 * 0系统资源，1自建资源，2共享资源,3校本资源,4区本资源 
	 * @param fromFlag
	 * @return
	 */
	public  static Integer getContTypeByFromFlag(Integer fromFlag){
		switch(fromFlag){
		case fromFlag_sysRes: return CONTTYPE_SYSRES; 
		case fromFlag_localRes: return CONTTYPE_LOCALRES;
		case fromFlag_sharedRes: return CONTTYPE_LOCALRES;
		case fromFlag_schoolRes: return CONTTYPE_SCHOOL_RES;
		case fromFlag_districtRes: return CONTTYPE_DISTRICT_RES;
		default: return null ;
		}
	}
	
	
	
	/**
	 * 将fromFlag转换成 备课夹中的conttype
	 *  1自建资源，2共享资源,3校本资源,4区本资源 
	 * @param conttype
	 * @return
	 */
	public  static Integer getFromFlagByContType(Integer conttype){
		switch(conttype){
		case CONTTYPE_SYSRES :
		case CONTTYPE_SYSRES_OLD:
				return  fromFlag_sysRes;
				
		case CONTTYPE_LOCALRES: 
		case CONTTYPE_LOCALRES_OLD: 
				return fromFlag_localRes;
				
		case CONTTYPE_SCHOOL_RES : 
				return fromFlag_schoolRes;
		case CONTTYPE_DISTRICT_RES: 
			
				return fromFlag_districtRes;
		default: 	return null ;
		}
	}
	
	
	
	
	
	
	/**
	 * 将 ResourceSimpleInfo 中的path（主文件路径） 切换为 资源的播放路径
	 * @param list
	 */
	public static void resetResourceViewUrl(List<ResourceSimpleInfo> list,String resServiceLocal,String currentResService){
		
		for (int i = 0; i < list.size(); i++) {
			resetResourceViewUrl(list.get(i),resServiceLocal,currentResService);
		}
		
	}
	
	
	
	
	
	
	/**
	 * 将 ResourceSimpleInfo 中的path（主文件路径） 切换为 资源的相对路径  加密文件为exe的相对路径
	 * @param list
	 */
	public static void resetResourceDownLoadUrl(List<ResourceSimpleInfo> list,String resServiceLocal){
		for (int i = 0; i < list.size(); i++) {
			resetResourceDownLoadUrl(list.get(i),resServiceLocal);
		}
	}
	
	/**
	 * 将 ResourceSimpleInfo 中的path（主文件路径） 切换为 资源的播放路径
	 * 
	 * @param info
	 */
	public static void resetResourceViewUrl(ResourceSimpleInfo info,String resServiceLocal,String currentResService){

	    String rescode = info.getRescode();
	    Integer fromflag= info.getFromflag();		
	    Boolean isnet = info.getIsnet();	    
	    Boolean isdwj= info.getIsdwj();	    
	    String path = info.getPath();

	    
	    path = ZhlResourceCenterWrap.getWebPlayUrl(resServiceLocal, path, isdwj);
	    path = path.replace(resServiceLocal, currentResService);
	    
		info.setPath(path);
	}
	
	
	/**
	 * 将 ResourceSimpleInfo 中的path（主文件路径） 切换为 资源的下载路径
	 * 用于打包下载
	 * @param info
	 */
	public static void resetResourceDownLoadUrl(ResourceSimpleInfo info,String resServiceLocal){
	    String rescode = info.getRescode();
	    Integer fromflag= info.getFromflag();		
	    Boolean isnet = info.getIsnet();	    
	    Boolean isdwj= info.getIsdwj();	    
	    String path = info.getPath();

	    //如果是系统资源 
	    if(fromflag==fromFlag_sysRes){
			String flag = path.substring(path.lastIndexOf("."),path.length());
			
			
			//如果是加密swf\mp4的文件
			if(ZhlResourceCenterWrap.FileType_encrypt.indexOf(flag)>=0){
				String  url = "";
				if(isdwj){
					String _path =  path.replaceAll("\\\\", "/");
					String _name = _path.substring(_path.lastIndexOf("/")+1,_path.length());
					_path = _path.substring(0,_path.lastIndexOf("/"));
					url = ZhlResourceCenterWrap.InvokeExePackageTaskURL(_name,_path, resServiceLocal);
				}else{
					url = ZhlResourceCenterWrap.InvokeExePackageTaskURL(path, "", resServiceLocal);
				}
				//获取exe文件的路径
				String result =  HttpUtil.PostHttpWebRequest(url);
				HashMap obj =  JSONObject.parseObject(result, HashMap.class);
				path = (String)obj.get("filename");
			}else{
				if(isdwj){
					path = path.substring(0, path.indexOf(rescode))+File.separator+rescode+".zip";
				}
			}
	    }
		
	    
	    //重新赋值path
	    info.setPath(path);
		
	}

	
	
}