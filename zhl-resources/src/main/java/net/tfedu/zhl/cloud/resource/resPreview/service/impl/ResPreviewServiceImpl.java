package net.tfedu.zhl.cloud.resource.resPreview.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.resPreview.entity.AllResNav;
import net.tfedu.zhl.cloud.resource.resPreview.entity.ResPreviewInfo;
import net.tfedu.zhl.cloud.resource.resPreview.service.ResPreviewService;
import net.tfedu.zhl.cloud.resource.resourceList.dao.DistrictResMapper;
import net.tfedu.zhl.cloud.resource.resourceList.dao.SysResourceMapper;

import org.springframework.stereotype.Service;

/**
 * 资源预览的 serviceImpl
 * @author WeiCuicui
 *
 */
@Service("resPreviewService")
public class ResPreviewServiceImpl implements ResPreviewService{
	
	@Resource SysResourceMapper sysResourceMapper;
	@Resource DistrictResMapper districtResMapper;

	//根据resId和fromFlag，查询资源具体信息
	@Override
	public ResPreviewInfo getResPreviewInfo(long resId,int fromFlag){
		ResPreviewInfo info = null;
		if(fromFlag == 0){//系统资源
			info = sysResourceMapper.getSysResInfo(resId);
			
		} else if(fromFlag == 1){//自建资源
			
			
		} else if(fromFlag == 3){//校本资源
			info = districtResMapper.getDisResInfo(fromFlag, resId);
			
		} else if(fromFlag == 4){//区本资源
			info = districtResMapper.getDisResInfo(fromFlag, resId);
			
		}
		
		return info;
	}
	
	//对一个资源进行预览时，查询该资源所在的所有版本目录
	@Override
	public List<AllResNav> getAllResNavs(long resId,int fromFlag){
		List<AllResNav> info = new ArrayList<AllResNav>();
		if(fromFlag == 0){//系统资源
			List<String> structCodes = sysResourceMapper.getAllRescodes(resId);
			if(structCodes != null){
				for(int i = 0; i < structCodes.size(); i++){
					AllResNav nav = sysResourceMapper.getOneSysNav(structCodes.get(i));
					info.add(nav);
				}
			}
			
		} else if(fromFlag == 1){//自建资源
			
			
		} else if(fromFlag == 3 || fromFlag == 4){//校本资源、区本资源
			List<String> structCodes = districtResMapper.getAllDisRescodes(resId);
			if(structCodes != null){
				for(int i = 0; i < structCodes.size(); i++){
					AllResNav nav = sysResourceMapper.getOneSysNav(structCodes.get(i));
					info.add(nav);
				}
			}
		} 
		
		return info;
	}
}
