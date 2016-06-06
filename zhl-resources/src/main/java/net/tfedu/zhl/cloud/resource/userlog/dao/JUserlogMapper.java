package net.tfedu.zhl.cloud.resource.userlog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.sso.userlog.entity.JUserlog;
import net.tfedu.zhl.sso.userlog.entity.ResourceViewLog;

/**
 * 用户日志
 * 
 * @author wangwr
 *
 */
public interface JUserlogMapper extends CoreMapper<JUserlog> {

    /**
     * 分页获取用户的资源浏览记录
     * 
     * @param userId
     * @param unifyTypeId
     * @param fileFormat
     * @return
     */
    public List<ResourceViewLog> getMyViewForResource(@Param("userId") Long userId, @Param("unifyTypeId") Long unifyTypeId, @Param("fileFormat") String fileFormat);

}