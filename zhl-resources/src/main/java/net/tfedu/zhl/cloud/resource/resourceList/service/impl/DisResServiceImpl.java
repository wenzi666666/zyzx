package net.tfedu.zhl.cloud.resource.resourceList.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.SysFrom;
import net.tfedu.zhl.cloud.resource.resourceList.dao.DistrictResMapper;
import net.tfedu.zhl.cloud.resource.resourceList.entity.DisResourceEntity;
import net.tfedu.zhl.cloud.resource.resourceList.entity.PageInfoToPagination;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;

import net.tfedu.zhl.cloud.resource.resourceList.service.DisResService;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

/**
 * 区本、校本资源的serviceImpl
 * @author WeiCuicui
 *
 */
@Service("disResService")
public class DisResServiceImpl implements DisResService{

	@Resource DistrictResMapper districtResMapper;
	
	
	//获得区、校id
	@Override
	public List<HashMap<?, ?>> getDisAndSchool(long userId){
		return districtResMapper.getDisAndSchool(userId);
	}
	
	//查询区本、校本资源信息
	@Override
	public Pagination<DisResourceEntity> selectDisRes(int fromFlag,List<String> formats, List<Long> typeIds, String tfcode,int orderBy,long schoolId,long districtId,int page,int perPage){
		//Page插件必须放在查询语句之前紧挨的第一个位置
		PageHelper.startPage(page, perPage);
		
		//查询系统资源
		List<DisResourceEntity> list = districtResMapper.selectDisRes(fromFlag, formats, typeIds, tfcode, orderBy, schoolId, districtId);
	  
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
		PageInfoToPagination<DisResourceEntity> transfer = new PageInfoToPagination<DisResourceEntity>();
		
		return transfer.transfer(list);

	}
					
}
