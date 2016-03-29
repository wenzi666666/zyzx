package net.tfedu.zhl.cloud.resource.resourceList.service.impl;


import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.dao.FileFormatMapper;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.dao.ResTypeMapper;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.SysFrom;
import net.tfedu.zhl.cloud.resource.resourceList.dao.SysResourceMapper;
import net.tfedu.zhl.cloud.resource.resourceList.entity.PageInfoToPagination;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;
import net.tfedu.zhl.cloud.resource.resourceList.entity.SysResourceEntity;
import net.tfedu.zhl.cloud.resource.resourceList.service.SysResourceService;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

/**
 * 系统资源 serviceImpl
 * @author WeiCuicui
 *
 */
@Service("sysResourceService")
public class SysResourceServiceImpl implements SysResourceService{

	@Resource FileFormatMapper fileFormatMapper;
	
	@Resource SysResourceMapper sysResourceMapper;
	
	@Resource ResTypeMapper resTypeMapper;
	
	//根据资源格式，查询所有后缀
	@Override
	public List<String> getFileExtsByFormat(String fileFormat){
		return fileFormatMapper.getExtsByFormat(fileFormat);
	}
	/**
	 * 分页查询系统资源
	 * @return
	 */
	@Override
	//分页查询系统资源信息
	public Pagination<SysResourceEntity> getSysResList(List<Integer> sys_from,List<String> formats,List<Long> resourceIds,String tfcode,int orderBy,List<Integer> typeIds,int page,int perPage){
		
		//Page插件必须放在查询语句之前紧挨的第一个位置
		PageHelper.startPage(page, perPage);
		
		//查询系统资源
		List<SysResourceEntity> list = sysResourceMapper.SelectSysResources(sys_from, formats, resourceIds, tfcode, orderBy, typeIds);
	  
		//判断资源是否为最新
		for(int i = 0; i < list.size(); i++){
			//最后更新日期
			Date date = list.get(i).getUpdateDT();
			//得到当前日期的前多少天
			Calendar calendar = Calendar.getInstance(); 
			calendar.add(Calendar.DATE, -SysFrom.expire);    
			Date expireDate = calendar.getTime();
			//比较
			if(date.getTime() >= expireDate.getTime())
				list.get(i).setNew(true);
		}
		
		//封装结果集
		PageInfoToPagination<SysResourceEntity> transfer = new PageInfoToPagination<SysResourceEntity>();
		
		return transfer.transfer(list);
	    
	}
	
	//查询系统资源列表
	@Override
	public Pagination<SysResourceEntity> getAllSysRes(long poolId,int mTypeId,String fileFormat,String tfcode,int orderBy,int page,int perPage){
		//查询结果，封装为pagination
		Pagination<SysResourceEntity> pagination = null;
		
		//根据fileFormat去查询该格式下的所有 后缀
		List<String> fileExts = getFileExtsByFormat(fileFormat);
		
		//根据当前结点tfcode，以及sys_from，查询系统资源id
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("sys_from", SysFrom.sys_from);
		map.put("pTfcode", tfcode);
		List<Long> resourceIds = resTypeMapper.getAllSysResIds(map);
		
		//根据资源库id和父类型id，得到父类型的所有子类型及其自身
		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map1.put("poolId", poolId);
		map1.put("MType", mTypeId);
		List<Integer> typeIds = resTypeMapper.getTypesByPMTypeAndPool(poolId, mTypeId);
		
		pagination = getSysResList(SysFrom.sys_from, fileExts, resourceIds, tfcode, orderBy, typeIds, page, perPage);
		
		return pagination;
		
	}
}