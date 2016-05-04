package net.tfedu.zhl.cloud.resource.navigation.dao;

import java.util.HashMap;
import java.util.List;

import net.tfedu.zhl.cloud.resource.navigation.entity.JSyscourse;
import net.tfedu.zhl.cloud.resource.navigation.entity.TreeNode;
import net.tfedu.zhl.helper.CoreMapper;

import org.apache.ibatis.annotations.Param;

/**
 * 根据学段、学科，获得所有教材版本
 * 
 * @author WeiCuicui
 *
 */
public interface JSyscourseMapper extends CoreMapper<JSyscourse> {

    /**
     *  根据学段、学科，获得所有教材版本
     * @param map
     * @return
     */
    public List<JSyscourse> getAllEditions(HashMap<String, Object> map);

    /**
     *  根据所属版本和产品编码，查询所有的教材
     * @param map
     * @return
     */
    public List<JSyscourse> getAllBooks(HashMap<String, Object> map);

    /**
     *  根据父结点id，查询子结点
     * @param pnodeId
     * @return
     */
    public List<TreeNode> getTopChildrenResultMap(long pnodeId);
    
    /**
     * 根据课程结点id，获取课程的详细信息
     * @param id
     * @return
     */
    public List<TreeNode> getOneCourseInfo(@Param("id")long id,@Param("proCode")String proCode);
}