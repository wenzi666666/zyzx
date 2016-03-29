package net.tfedu.zhl.cloud.resource.resSearch.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.dao.FileFormatMapper;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.SysFrom;
import net.tfedu.zhl.cloud.resource.resSearch.dao.ResSearchMapper;
import net.tfedu.zhl.cloud.resource.resSearch.entity.ResSearchResultEntity;
import net.tfedu.zhl.cloud.resource.resSearch.service.ResSearchService;
import net.tfedu.zhl.cloud.resource.resourceList.entity.PageInfoToPagination;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
/**
 * 资源跨库检索的service
 * @author WeiCuicui
 *
 */
@Service("resSearchService")
public class ResSearchServiceImpl implements ResSearchService{
	
	@Resource ResSearchMapper resSearchMapper;
	@Resource FileFormatMapper fileFormatMapper;

	//跨库检索资源
	@Override
	public Pagination<ResSearchResultEntity> getAllResources(int fromFlag,List<Integer> sys_from,String searchKeyword,String format,int page,int perPage){
		
		//根据资源格式，查询所有满足条件的后缀
		List<String> formats = fileFormatMapper.getExtsByFormat(format);
		
		
		//查询满足条件的全部资源
		if(fromFlag == 0){
			
			//Page插件必须放在查询语句之前紧挨的第一个位置
			PageHelper.startPage(page, perPage);
			List<ResSearchResultEntity> list = resSearchMapper.getAllResources(searchKeyword, formats,sys_from);
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
			PageInfoToPagination<ResSearchResultEntity> transfer = new PageInfoToPagination<ResSearchResultEntity>();
			
			return transfer.transfer(list);

		} else if(fromFlag == 1) { //系统资源
			
			//Page插件必须放在查询语句之前紧挨的第一个位置
			PageHelper.startPage(page, perPage);
			List<ResSearchResultEntity> list = resSearchMapper.getAllSysResources(searchKeyword, formats,sys_from);
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
			PageInfoToPagination<ResSearchResultEntity> transfer = new PageInfoToPagination<ResSearchResultEntity>();
			
			return transfer.transfer(list);
			
		} else { //校本资源、区本资源
			
			//Page插件必须放在查询语句之前紧挨的第一个位置
			PageHelper.startPage(page, perPage);
			List<ResSearchResultEntity> list = resSearchMapper.getAllDisResources(searchKeyword, fromFlag, formats);
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
			PageInfoToPagination<ResSearchResultEntity> transfer = new PageInfoToPagination<ResSearchResultEntity>();
			
			return transfer.transfer(list);
		}
	}
}
