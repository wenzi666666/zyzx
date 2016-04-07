package net.tfedu.zhl.cloud.resource.downloadrescord.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.tfedu.zhl.cloud.resource.downloadrescord.service.ResDownPathService;

import org.springframework.stereotype.Service;

/**
 * 获取资源的下载路径service
 * @author WeiCuicui
 *
 */
@Service("resDownPathService")
public class ResDownPathServiceImpl implements ResDownPathService{

	
	/**
	 * 获取资源的下载链接
	 */
	@Override
	public List<HashMap<String, Object>> getResDownPath(long userId,long resId,int fromFlag){
		
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		
		//资源路径
		String resPath = "";
		
		//缩略图下载地址
		String imgDownPath = "";
		
		//转换格式后的pdf下载地址
		String pdfPath = "";
		
		int diskOrder= 1 ;
		
		
		
		switch(fromFlag){
		case 0 :  
			
			break;
		case 1 :
			
			break ;
		
		case 3: // 3 区本  4 校本资源
		case 4:
				
			break;
		}
		
		return list;
	}
}
