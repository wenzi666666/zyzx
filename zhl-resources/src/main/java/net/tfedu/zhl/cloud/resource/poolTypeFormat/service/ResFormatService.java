package net.tfedu.zhl.cloud.resource.poolTypeFormat.service;

import java.util.List;

/**
 * 资源格式 service
 * 
 * @author WeiCuicui
 *
 */
public interface ResFormatService {

    // 根据资源库id，得到父类型的所有子类型及其自身
    public List<Integer> getTypesByPMTypeAndPool(long poolId, int MType);

    // 系统资源，根据资源ids和typeIds，查询得到资源格式
    public List<String> getSysResFormatsByMType(List<Long> resourceIds, List<Integer> typeIds);

    // 区本校本资源，根据资源ids和typeIds，查询得到资源格式
    public List<String> getDisResFormatsByMType(List<Long> resourceIds, int fromFlag);

    // 查询区本校本资源格式
    public List<String> getDisResFormats(String tfcode, int fromFlag);

    // 获得系统资源格式
    public List<String> getSysResFormats(long poolId, String pTfcode, int typeId,List<Integer> sys_from);
}
