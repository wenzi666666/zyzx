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
    public List<String> getSysResFormats(String pTfcode,int typeId,List<Integer> sys_from,long poolId) {
        List<String> formats = new ArrayList<String>();
        
        List<Integer> typeIds = resTypeMapper.getTypesByPMTypeAndPool(poolId, typeId);
        
        formats = fileFormatMapper.getSysResFormatsByMType(pTfcode,typeId,sys_from,poolId,typeIds);
        // 查询结果中增加一个 “全部”
        formats.add(0, "全部");

        return formats;

    }
}