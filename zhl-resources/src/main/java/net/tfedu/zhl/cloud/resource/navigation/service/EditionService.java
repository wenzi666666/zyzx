package net.tfedu.zhl.cloud.resource.navigation.service;

import java.util.List;
import java.util.Map;

import net.tfedu.zhl.cloud.resource.navigation.entity.JSyscourse;

/**
 * 根据学段、学科获得所有版本
 * 
 * @author WeiCuicui
 *
 */
public interface EditionService {

    /**
     * 根据学段、学科，获得所有版本
     */
    public List<JSyscourse> getAllEditionsByTermAndSub(long termId,long subjectId);
    
    
    /**
     * 获取指定学段学科下的版本
     * @param proCode  产品编码code 如 ‘zy’
     * @param termId
     * @param subjectId
     * @return
     */
    public List<Map<String,Object>> getSyscourseVersion(String proCode,Long termId,Long subjectId);
    
    
}
