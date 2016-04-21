package net.tfedu.zhl.cloud.resource.poolTypeFormat.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.dao.FileFormatMapper;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.dao.ResTypeMapper;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResFormatService;

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

    /**
     *  根据资源库id，得到父类型的所有子类型及其自身
     */
    @Override
    public List<Integer> getTypesByPMTypeAndPool(long poolId, int MType) {
        return resTypeMapper.getTypesByPMTypeAndPool(poolId, MType);
    }

    /**
     *  系统资源，根据资源ids和typeIds，查询得到资源格式
     */
    @Override
    public List<String> getSysResFormatsByMType(List<Long> resourceIds, List<Integer> typeIds) {
        return fileFormatMapper.getSysResFormatsByMType(resourceIds, typeIds);
    }

    /**
     *  区本校本资源，查询资源格式
     */
    @Override
    public List<String> getDisResFormatsByMType(List<Long> resourceIds, int fromFlag) {

        return fileFormatMapper.getDisResFormatsByMType(resourceIds, fromFlag);
    }

    /**
     *  查询区本校本资源格式
     */
    @Override
    public List<String> getDisResFormats(String tfcode, int fromFlag) {

        List<String> formats = new ArrayList<String>();

        // 获得资源id
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("pTfcode", tfcode);

        List<Long> resourceIds = resTypeMapper.getAllDisResIds(map);

        // 查询资源格式
        formats = getDisResFormatsByMType(resourceIds, fromFlag);

        // 查询结果中增加一个 “全部”
        formats.add(0, "全部");

        return formats;
    }

    /**
     *  获得系统资源格式(non-Javadoc)
     * @see net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResFormatService#getSysResFormats(long, java.lang.String, int, java.util.List)
     */
    @Override
    public List<String> getSysResFormats(long poolId, String pTfcode, int typeId,List<Integer> sys_from) {
        List<String> formats = new ArrayList<String>();

        // 根据poolId和typeId，查询父类型下所有子类型及其自身
        List<Integer> typeIds = getTypesByPMTypeAndPool(poolId, typeId);

        // 参数
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("pTfcode", pTfcode);
        map.put("sys_from", sys_from);
        
        // 查询当前结点下的所有资源Id
        List<Long> resourceIds = resTypeMapper.getAllSysResIds(map);

        // 查询资源格式
        formats = getSysResFormatsByMType(resourceIds, typeIds);

        // 查询结果中增加一个 “全部”
        formats.add(0, "全部");

        return formats;

    }
}