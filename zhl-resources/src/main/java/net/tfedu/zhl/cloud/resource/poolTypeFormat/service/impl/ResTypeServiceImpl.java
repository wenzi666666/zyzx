package net.tfedu.zhl.cloud.resource.poolTypeFormat.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.dao.ResPoolTypeMapper;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.dao.ResTypeMapper;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.FirstLevelResType;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResType;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.SysFrom;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResTypeService;

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

    /**
     * 系统资源 获取当前节点下所有资源id，pTfcode为父结点的tfcode
     * 
     * @param map
     * @return
     */
    @Override
    public List<Long> getAllSysResIds(HashMap<String, Object> map) {
        return resTypeMapper.getAllSysResIds(map);
    }

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
     * 系统资源：根据资源库id，得到父类型的所有子类型及其自身
     */
    @Override
    public List<Integer> getAllTypeIdsByPool(long poolId) {
        return resPoolTypeMapper.getAllTypeIdsByPool(poolId);
    }

    @Override
    /**
     * 系统资源：当资源库选择 “全部” 或 “教学素材” 时，显示所有一级类型
     */
    public List<ResType> getSysFirstLevelType(List<Long> resourceIds, List<Integer> typeIds) {
        return resTypeMapper.getSysFirstLevelType(resourceIds, typeIds);
    }

    /**
     * 系统资源：当资源库选择 “动画焦教具”、“名师微课”、“教学案例” 时，显示所有二级类型。当资源库为“理化生实验”时，只显示“全部”
     */
    @Override
    public List<ResType> getSysSecondLevelType(List<Long> resourceIds, List<Integer> typeIds) {
        return resTypeMapper.getSysSecondLevelType(resourceIds, typeIds);
    }

    /**
     * 区本校本资源：根据资源ids和fromFlag（区本/校本），查询资源类型
     * 
     * @param resourceIds
     * @param fromFlag
     * @return
     */
    @Override
    public List<ResType> getDisResType(List<Long> resourceIds, int fromFlag) {
        return resTypeMapper.getDisResType(resourceIds, fromFlag);
    }

    /**
     * 根据资源库id，得到父类型的所有子类型及其自身
     * 
     * @param map
     * @return
     */
    @Override
    public List<Integer> getTypesByPMTypeAndPool(long poolId, int MType) {
        return resTypeMapper.getTypesByPMTypeAndPool(poolId, MType);
    }

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

    /**
     * 区本校本资源：查询资源类型
     * 
     * @return
     */
    @Override
    public List<ResType> getDisResTypes(String tfcode, int fromFlag) {
        // 定义类型结果集
        List<ResType> types = new ArrayList<ResType>();

        // 根据tfcode获得区本校本资源ids
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("pTfcode", tfcode);
        List<Long> resourceIds = getAllDisResIds(map);

        // 查询资源类型
        types = getDisResType(resourceIds, fromFlag);

        // 资源类型中增加一个“全部”
        ResType all = new ResType();
        all.setId(0);
        all.setMtype("全部");
        types.add(0,all);

        return types;
    }

    /**
     * 系统资源：查询资源类型
     */
    @Override
    public List<ResType> getSysResTypes(long poolId, String pTfcode) {
        List<ResType> types = new ArrayList<ResType>();

        // 传递参数
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("pTfcode", pTfcode);
        map.put("sys_from", SysFrom.sys_from);
        // 查询当前结点下的所有资源Id
        List<Long> resourceIds = getAllSysResIds(map);

        // 根据资源库，查询所有资源类型id
        List<Integer> typeIds = getAllTypeIdsByPool(poolId);

        /**
         * 当资源库选择 “全部” 或 “教学素材” 时 显示所有一级类型
         */
        if (poolId == 0 || poolId == 4) {
            types = getSysFirstLevelType(resourceIds, typeIds);

        } else { // 当资源库选择 “动画焦教具”、“名师微课”、“教学案例”
                 // 时，显示所有二级类型；当资源库为“理化生实验”时，只显示“全部”。
            types = getSysSecondLevelType(resourceIds, typeIds);
        }

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
    public List<ResType> getDisResType_EPrepare(String tfcode, int fromFlag,List<Integer> removeTypeIds) {
        // 定义类型结果集
        List<ResType> types = new ArrayList<ResType>();

        // 根据tfcode获得区本校本资源ids
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("pTfcode", tfcode);
        List<Long> resourceIds = getAllDisResIds(map);

        // 查询资源类型
        types = resTypeMapper.getDisResType_EPrepare(resourceIds, fromFlag, removeTypeIds);

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
    public List<ResType> getSysResTypes_EPrepare(long poolId, String pTfcode,List<Integer> removeTypeIds) {
        List<ResType> types = new ArrayList<ResType>();

        // 传递参数
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("pTfcode", pTfcode);
        map.put("sys_from", SysFrom.sys_from);
        // 查询当前结点下的所有资源Id
        List<Long> resourceIds = getAllSysResIds(map);

        // 根据资源库，查询所有资源类型id,e备课
        List<Integer> typeIds = resPoolTypeMapper.getAllTypeIdsByPool_EPrepare(poolId, removeTypeIds);

        /**
         * 当资源库选择 “全部” 或 “教学素材” 时 显示所有一级类型
         */
        if (poolId == 0 || poolId == 4) {
            types = getSysFirstLevelType(resourceIds, typeIds);

        } else { // 当资源库选择 “动画焦教具”、“名师微课”、“教学案例”
                 // 时，显示所有二级类型；当资源库为“理化生实验”时，只显示“全部”。
            types = getSysSecondLevelType(resourceIds, typeIds);
        }

        // 资源类型中增加一个“全部”
        ResType all = new ResType();
        all.setId(0);
        all.setMtype("全部");
        types.add(0,all);

        return types;
    }

    @Override
    public List<FirstLevelResType> getAllFirstLevelResType() {
        return resTypeMapper.getAllFirstLevelResType();
    }
}
