package net.tfedu.zhl.cloud.resource.poolTypeFormat.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.dao.FileFormatMapper;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.dao.ResTypeMapper;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResFormatService;
import net.tfedu.zhl.cloud.resource.resourceList.dao.DistrictResMapper;
import net.tfedu.zhl.cloud.resource.resourceList.entity.DisAndSchoolEntity;

import org.springframework.stereotype.Service;

/**
 * 资源格式 serviceImpl
 * 
 * @author WeiCuicui
 *
 */
@Service("resFormatService")
public class ResFormatServiceImpl implements ResFormatService {

    @Resource
    ResTypeMapper resTypeMapper;
    @Resource
    FileFormatMapper fileFormatMapper;
    
    @Resource DistrictResMapper districtResMapper;

    /**
     *  查询区本校本资源格式
     */
    @Override
    public List<String> getDisResFormats(int mtype,String tfcode,int fromFlag,long userId) {

        List<String> formats = new ArrayList<String>();

        /*// 获得资源id
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("pTfcode", tfcode);

        List<Long> resourceIds = resTypeMapper.getAllDisResIds(map);*/
        
        long schoolId = 0;
        long districtId = 0;

        // 根据userId查询schoolId 和 districtId
        DisAndSchoolEntity disAndSchoolIds = districtResMapper.getDisAndSchool(userId);
        if (disAndSchoolIds != null) {
            schoolId = disAndSchoolIds.getSchoolId();
            districtId = disAndSchoolIds.getDistrictId();
        }
        

        // 查询资源格式
        formats = fileFormatMapper.getDisResFormatsByMType(mtype,tfcode,fromFlag,schoolId,districtId);

        // 查询结果中增加一个 “全部”
        formats.add(0, "全部");

        return formats;
    }

    /**
     *  获得系统资源格式
     * 
     */
    @Override
    public List<String> getSysResFormats(String pTfcode,int typeId,List<Integer> sys_from) {
        List<String> formats = new ArrayList<String>();

        /*// 根据poolId和typeId，查询父类型下所有子类型及其自身
        List<Integer> typeIds = resTypeMapper.getTypesByPMTypeAndPool(poolId, typeId);

          // 参数
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("pTfcode", pTfcode);
        map.put("sys_from", sys_from);
        
        // 查询当前结点下的所有资源Id
        List<Long> resourceIds = resTypeMapper.getAllSysResIds(map);*/

        // 查询资源格式
        //formats = fileFormatMapper.getSysResFormatsByMType(resourceIds, typeIds);
        
        
        formats = fileFormatMapper.getSysResFormatsByMType(pTfcode,typeId,sys_from);
        // 查询结果中增加一个 “全部”
        formats.add(0, "全部");

        return formats;

    }
    
    //****优化前的代码*****//
    
/*    *//**
     *  查询区本校本资源格式
     *//*
    @Override
    public List<String> getDisResFormats(String tfcode, int fromFlag, int MType) {

        List<String> formats = new ArrayList<String>();
        
        // 根据poolId和typeId，查询父类型下所有子类型及其自身
        List<Integer> typeIds = resTypeMapper.getDisResTypesByPMType(MType);

        // 获得资源id
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("pTfcode", tfcode);

        List<Long> resourceIds = resTypeMapper.getAllDisResIds(map);

        // 查询资源格式
        formats = fileFormatMapper.getDisResFormatsByMType(resourceIds, fromFlag,typeIds);

        // 查询结果中增加一个 “全部”
        formats.add(0, "全部");

        return formats;
    }

    *//**
     *  获得系统资源格式
     * 
     *//*
    @Override
    public List<String> getSysResFormats(long poolId, String pTfcode, int typeId,List<Integer> sys_from) {
        List<String> formats = new ArrayList<String>();

        // 根据poolId和typeId，查询父类型下所有子类型及其自身
        List<Integer> typeIds = resTypeMapper.getTypesByPMTypeAndPool(poolId, typeId);

        // 参数
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("pTfcode", pTfcode);
        map.put("sys_from", sys_from);
        
        // 查询当前结点下的所有资源Id
        List<Long> resourceIds = resTypeMapper.getAllSysResIds(map);

        // 查询资源格式
        formats = fileFormatMapper.getSysResFormatsByMType(resourceIds, typeIds);

        // 查询结果中增加一个 “全部”
        formats.add(0, "全部");

        return formats;

    }*/
}