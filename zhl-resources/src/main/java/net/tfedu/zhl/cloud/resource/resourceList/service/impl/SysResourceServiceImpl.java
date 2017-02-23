package net.tfedu.zhl.cloud.resource.resourceList.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.dao.ResPoolTypeMapper;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.dao.ResTypeMapper;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResPoolType;
import net.tfedu.zhl.cloud.resource.poolconfig.dao.SAppUserPoolConfigMapper;
import net.tfedu.zhl.cloud.resource.resourceList.dao.SysResourceMapper;
import net.tfedu.zhl.cloud.resource.resourceList.entity.PageInfoToPagination;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;
import net.tfedu.zhl.cloud.resource.resourceList.entity.SysResource;
import net.tfedu.zhl.cloud.resource.resourceList.entity.SysResourceEntity;
import net.tfedu.zhl.cloud.resource.resourceList.service.SysResourceService;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.sso.subject.dao.JSubjectMapper;
import net.tfedu.zhl.sso.term.dao.JTermMapper;
import tk.mybatis.mapper.entity.Example;

/**
 * 系统资源 serviceImpl
 * 
 * @author WeiCuicui
 *
 */
@Service("sysResourceService")
public class SysResourceServiceImpl implements SysResourceService {

	@Autowired
    SysResourceMapper sysResourceMapper;

	@Autowired
    ResTypeMapper resTypeMapper;

	@Autowired
	SAppUserPoolConfigMapper appUserPoolConfigmapper;

	@Autowired
	ResPoolTypeMapper poolTypeMapper;
	

	
	@Autowired
	JTermMapper termMapper;
	
	@Autowired
	JSubjectMapper subjectMapper;
	
	
    /**
     * 查询系统资源列表
     */
    @Override
    public Pagination<SysResourceEntity> getAllSysRes(long poolId, int mTypeId, String fileFormat, String tfcode,
            int orderBy, int page, int perPage,List<Integer> sys_from,int expire) {
      
    	//根据资源库id、父类型id，获取父类型及其子类型
    	List<Integer> typeIds = resTypeMapper.getTypesByPMTypeAndPool(poolId, mTypeId);
    	
        List<SysResourceEntity> list = new ArrayList<SysResourceEntity>();
    	
    	if(typeIds != null && typeIds.size() > 0){
    		// Page插件必须放在查询语句之前紧挨的第一个位置
            PageHelper.startPage(page, perPage);

            // 查询系统资源
            list = sysResourceMapper.getAllSysRes(sys_from, fileFormat,tfcode, orderBy,typeIds);
    	}
               
        // 判断资源是否为最新
        for (int i = 0; i < list.size(); i++) {
        	
            // 最后更新日期
            Date date = list.get(i).getUpdateDT();
            // 得到当前日期的前多少天
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -expire);
            Date expireDate = calendar.getTime();
            // 比较
            if (date.getTime() >= expireDate.getTime())
                list.get(i).setNew(true);
        }

        // 封装结果集
        PageInfoToPagination<SysResourceEntity> transfer = new PageInfoToPagination<SysResourceEntity>();

        return transfer.transfer(list);
    }
    
    
    /**
     * e备课，查询系统资源（限制资源类型，资源title模糊查询）
     */
    @Override
	public Pagination<SysResourceEntity> getAllSysRes_EPrepare(long poolId, int mTypeId, String fileFormat, String tfcode,
            int orderBy, int page, int perPage,String searchWord,List<Integer> removeTypeIds,List<Integer> sys_from,int expire){
    	
    	//查询受限类型及其子类型
    	List<Integer> removeTypes = resTypeMapper.getLimitedResTypes(removeTypeIds);
    
    	List<Integer> typeIds = resTypeMapper.getTypesByPMTypeAndPool_EPrepare(poolId, mTypeId, removeTypes);
    	
    	List<SysResourceEntity> list = new ArrayList<SysResourceEntity>();
    	
    	if(typeIds != null && typeIds.size() > 0){
    		// Page插件必须放在查询语句之前紧挨的第一个位置
            PageHelper.startPage(page, perPage);

            // 查询系统资源
            list = sysResourceMapper.getAllSysRes_EPrepare(sys_from, fileFormat,tfcode, orderBy, searchWord,typeIds);
    	}
               
        // 判断资源是否为最新
        for (int i = 0; i < list.size(); i++) {

            // 最后更新日期
            Date date = list.get(i).getUpdateDT();
            // 得到当前日期的前多少天
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -expire);
            Date expireDate = calendar.getTime();
            // 比较
            if (date.getTime() >= expireDate.getTime())
                list.get(i).setNew(true);
        }

        // 封装结果集
        PageInfoToPagination<SysResourceEntity> transfer = new PageInfoToPagination<SysResourceEntity>();

        return transfer.transfer(list);
    }
    
    
	@Override
	public boolean hasPermissionToViewAndDown(String userName, String appId, Long resId) throws CustomException {
		
		//资源库id
		long poolId = 0 ;
		boolean hasPermission =false;
		
		
		//获取资源的(类型)信息
		SysResource  obj =  sysResourceMapper.selectByPrimaryKey(resId);
		
		//获取类型所在的库
		Example example = new Example(ResPoolType.class);
		example.createCriteria().andCondition(" flag= false ")
		.andCondition(" resTypeId in ( select id from x_resourcetype where id ="+obj.getMtype()
		+" union all select pid from x_resourcetype where id ="+obj.getMtype()+")");
		List<ResPoolType> pList = poolTypeMapper.selectByExample(example);
		
		//找到资源所在的库
		poolId = pList!=null && pList.size()>0?pList.get(0).getPoolid():poolId;
		
		
		
		
		List<Map<String, Object>> list = appUserPoolConfigmapper.getAppUserPoolConfig(0l, 0l, userName, appId);
		if(list!=null && list.size()>0){
			
			for (Iterator<Map<String, Object>> iterator = list.iterator(); iterator.hasNext();) {
				Map<String, Object> map = iterator.next();
				long _poolId = (Long)map.get("id");
				
				//需找指定库的权限记录
				if(poolId!=_poolId){
					continue;
				}else{
					//指定库的物权找到学段、学科
					long _termId = (Long)map.get("termid");
					long _subjectId = (Long)map.get("subjectid");
					//如果物权记录 不区分学段、学科
					if(0==_termId && 0 == _subjectId){
						hasPermission = true ;
						break;
					}else{
						//获取学段学科的code用于和资源的path比对
						StringBuffer comparingCode = new StringBuffer().append("\\");
						if(_termId>0){
							comparingCode.append(termMapper.selectByPrimaryKey(_termId).getCode())
							.append("\\")
							;
						}
						if(_subjectId>0){
							comparingCode.append(subjectMapper.selectByPrimaryKey(_subjectId).getAbbrev())
							.append("\\")
							;					
						}
						//比对成功则有权访问
						if(obj.getFpath().contains(comparingCode.toString())){
							hasPermission = true ;
							break;
						}
					}
				}
				
			}
			
		}
		return hasPermission;
	}

	
}