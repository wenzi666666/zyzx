package net.tfedu.zhl.cloud.resource.prepare.entity;

import java.util.Iterator;
import java.util.List;

import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.fileservice.ZhlResourceCenterWrap;


/**
 * 工具类
 * mapper 中获取的 JPrepareContentView 中的 imagePath,fileSuffix 都不是最终的有效的数据
 * 在此工具类中处理，生成有效数据，
 * 其中 imagePath的值 实为 资源主文件的 （数据库中的相对全）路径
 * 
 * @author wangwr
 *
 */
public class JPrepareContentViewUtil {


	/**
	 * 将 JPrepareContentView 转换生成有效数据
	 * 注意网络资源的处理
	 * @param list
	 * @param resLocalUrl  文件服务器的内网地址
	 * @param currnetResUrl    浏览器上的当前的（可用内、外网文件服务器）地址
	 */
	public static void convertToPurpose(List<JPrepareContentView> list,String resLocalUrl,String currnetResUrl){
		
		if(list!=null && list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				JPrepareContentView view = list.get(i);
				String imgPath = view.getImgPath();
				String size = view.getSize();
				// imgPath 以http开头 并且size 为0或空
				if((StringUtils.isEmpty(size)||"0".equals(size)) 
						&&
						( imgPath.startsWith("http")|| imgPath.startsWith("HTTP"))
				   ){
					view.setFileSuffix("html");
				}else{
					imgPath = "";
					
					
					view.setFileSuffix(imgPath.substring(imgPath.lastIndexOf(".")+1,imgPath.length()));
					String temp_path = ZhlResourceCenterWrap.getDownUrl(resLocalUrl, imgPath);
					
				}
			}
		}
		
		
		
		
	}
	
	
	public static void main(String[] args) {
		String s = "123.doc";
		
		System.out.println(s.substring(s.lastIndexOf(".")+1,s.length()));
		
		
	}
	
}
