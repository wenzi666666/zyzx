package net.tfedu.zhl.cloud.resource.poolTypeFormat.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.dao.ResPoolTypeMapper;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.dao.ResTypeMapper;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.FirstLevelResType;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResType;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResTypeService;
import net.tfedu.zhl.cloud.resource.resourceList.dao.DistrictResMapper;
import net.tfedu.zhl.cloud.resource.resourceList.entity.DisAndSchoolEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 资源类型的serviceImpl
 * 
 * @author WeiCuicui
 *
 */
@Service("resTypeService")
public class ResTypeServiceImpl implements ResTypeService {

    // 资源类型的mapper
    @Resource
    ResTypeMapper resTypeMapper;

    // 资源库、资源类型的关联表的mapper
    @Resource
    ResPoolTypeMapper resPoolTypeMapper;
    
    @Resource DistrictResMapper districtResMapper;
    
    //写入日志
    Logger logger = LoggerFactory.getLogger(ResTypeServiceImpl.class);
    
    
    /**
     * 系统资源：查询资源类型
     */
    @Override
    public List<ResType> getSysResTypes(long poolId, String pTfcode,List<Integer> sys_from) {
        List<ResType> types = new ArrayList<ResType>();

        /**
         * 当资源库选择 “全部” 或 “教学素材” 时 显示所有一级类型
         */
        if (poolId == 0 || poolId == 4) {
        	
            types = resTypeMapper.getSysFirstLevelType(poolId, pTfcode,sys_from);

        } else { // 当资源库选择 “动画教具”、“名师微课”、“教学案例”
                 // 时，显示所有二级类型；当资源库为“理化生实验”时，只显示“全部”。
            types = resTypeMapper.getSysSecondLevelType(poolId, pTfcode, sys_from);
        }

        // 资源类型中增加一个“全部”
        ResType all = new ResType();
        all.setId(0);
        all.setMtype("全部");
        types.add(0,all);

        return types;
    }
    
    /**
     * 系统资源：查询资源类型，e备课
     */
    @Override
    public List<ResType> getSysResTypes_EPrepare(long poolId, String pTfcode,List<Integer> removeTypeIds,List<Integer> sys_from) {
        List<ResType> types = new ArrayList<ResType>();
        
        /**
         * 当资源库选择 “全部” 或 “教学素材” 时 显示所有一级类型
         */
        if (poolId == 0 || poolId == 4) {
          
        	types = resTypeMapper.getSysFirstLevelType_ePrepare(poolId, pTfcode,sys_from,removeTypeIds);

        } else { // 当资源库选择 “动画教具”、“名师微课”、“教学案例”
                 // 时，显示所有二级类型；当资源库为“理化生实验”时，只显示“全部”。
            types = resTypeMapper.getSysSecondLevelType_ePrepare(poolId, pTfcode, sys_from,removeTypeIds);
        }

        // 资源类型中增加一个“全部”
        ResType all = new ResType();
        all.setId(0);
        all.setMtype("全部");
        types.add(0,all);

        return types;
    }
    
    /**
     * 区本校本资源：查询资源类型
     * 
     * @return
     */
    @Override
    public List<ResType> getDisResTypes(String tfcode, int fromFlag,long userId) {
        // 定义类型结果集
        List<ResType> types = new ArrayList<ResType>();

       /* // 根据tfcode获得区本校本资源ids
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("pTfcode", tfcode);
        List<Long> resourceIds = getAllDisResIds(map);*/
        
        long schoolId = 0;
        long districtId = 0;

        // 根据userId查询schoolId 和 districtId
        DisAndSchoolEntity disAndSchoolIds = districtResMapper.getDisAndSchool(userId);
        if (disAndSchoolIds != null) {
            schoolId = disAndSchoolIds.getSchoolId();
            districtId = disAndSchoolIds.getDistrictId();
        }
        
        logger.debug("schoolId: " + schoolId);
        logger.debug("districtId: " + districtId);

        // 查询资源类型
        types = resTypeMapper.getDisResType(fromFlag,tfcode,schoolId,districtId);

        // 资源类型中增加一个“全部”
        ResType all = new ResType();
        all.setId(0);
        all.setMtype("全部");
        types.add(0,all);

        return types;
    }

    
    
    /**
     * 区本校本资源：查询资源类型，e备课
     * 
     * @return
     */
    @Override
    public List<ResType> getDisResType_EPrepare(String tfcode, int fromFlag,long userId,List<Integer> removeTypeIds) {
        // 定义类型结果集
        List<ResType> types = new ArrayList<ResType>();

        /*// 根据tfcode获得区本校本资源ids
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("pTfcode", tfcode);
        List<Long> resourceIds = getAllDisResIds(map);*/
        
        long schoolId = 0;
        long districtId = 0;

        // 根据userId查询schoolId 和 districtId
        DisAndSchoolEntity disAndSchoolIds = districtResMapper.getDisAndSchool(userId);
        if (disAndSchoolIds != null) {
            schoolId = disAndSchoolIds.getSchoolId();
            districtId = disAndSchoolIds.getDistrictId();
        }

        logger.debug("schoolId: " + schoolId);
        logger.debug("districtId: " + districtId);
        
        // 查询资源类型
        types = resTypeMapper.getDisResType_EPrepare(fromFlag,tfcode, schoolId,districtId,removeTypeIds);

        // 资源类型中增加一个“全部”
        ResType all = new ResType();
        all.setId(0);
        all.setMtype("全部");
        types.add(0,all);

        return types;
    }
    
/*
    *//**
     * 系统资源 获取当前节点下所有资源id，pTfcode为父结点的tfcode
     * 
     * @param map
     * @return
     *//*
    @Override
    public List<Long> getAllSysResIds(HashMap<String, Object> map) {
        return resTypeMapper.getAllSysResIds(map);
    }*/

    /**
     * 区本校本资源 获取当前节点下所有资源id，pTfcode为父结点的tfcode
     * 
     * @param map
     * @return
     */
    @Override
    public List<Long> getAllDisResIds(HashMap<String, Object> map) {
        return resTypeMapper.getAllDisResIds(map);
    }

   
    /**
     * 系统资源：当资源库选择 “全部” 或 “教学素材” 时，显示所有一级类型
     *//*
    @Override
    public List<ResType> getSysFirstLevelType(List<Long> resourceIds, List<Integer> typeIds) {
        return resTypeMapper.getSysFirstLevelType(resourceIds, typeIds);
    }*/
    
   /* *//**
     * 系统资源：当资源库选择 “全部” 或 “教学素材” 时，显示所有一级类型
     *//*
    @Override
    public List<ResType> getSysFirstLevelType(long poolId, String pTfcode,List<Integer> sys_from) {
        return resTypeMapper.getSysFirstLevelType(poolId, pTfcode,sys_from);
    }*/
    

   /* *//**
     * 系统资源：当资源库选择 “动画焦教具”、“名师微课”、“教学案例” 时，显示所有二级类型。当资源库为“理化生实验”时，只显示“全部”
     *//*
    @Override
    public List<ResType> getSysSecondLevelType(List<Long> resourceIds, List<Integer> typeIds) {
        return resTypeMapper.getSysSecondLevelType(resourceIds, typeIds);
    }*/

    /**
     * 系统资源：根据资源库id和父类型id，得到父类型的所有子类型及其自身
     * 
     * @param map
     * @return
     */
    @Override
    public List<Integer> getTypesByPMTypeAndPool(long poolId, int MType) {
        return resTypeMapper.getTypesByPMTypeAndPool(poolId, MType);
    }
    
    
    /**
     * 系统资源：根据资源库id，得到父类型的所有子类型及其自身
     */
    @Override
    public List<Integer> getAllTypeIdsByPool(long poolId) {
        return resPoolTypeMapper.getAllTypeIdsByPool(poolId);
    }


   /* *//**
     * 区本校本资源：根据资源ids和fromFlag（区本/校本），查询资源类型
     * 
     * @param resourceIds
     * @param fromFlag
     * @return
     *//*
    @Override
    public List<ResType> getDisResType(List<Long> resourceIds, int fromFlag) {
        return resTypeMapper.getDisResType(resourceIds, fromFlag);
    }*/

    /**
     * 区本校本资源：查询父类型及其所有子类型
     * 
     * @param MType
     * @return
     */
    @Override
    public List<Integer> getDisResTypesByPMType(int MType) {
        return resTypeMapper.getDisResTypesByPMType(MType);
    }


    @Override
    public List<FirstLevelResType> getAllFirstLevelResType() {
        return resTypeMapper.getAllFirstLevelResType();
    }
}
