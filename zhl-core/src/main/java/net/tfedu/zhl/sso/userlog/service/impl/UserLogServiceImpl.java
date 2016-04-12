package net.tfedu.zhl.sso.userlog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.tfedu.zhl.sso.userlog.dao.JUserlogMapper;
import net.tfedu.zhl.sso.userlog.entity.ResourceViewLog;
import net.tfedu.zhl.sso.userlog.service.UserLogService;

/**
 * 用户日志的记录log
 * 
 * @author wangwr
 *
 */
@Service("userLogService")
public class UserLogServiceImpl implements UserLogService {

    @Autowired
    JUserlogMapper mapper;

    @Override
    public PageInfo getMyViewLogFroResource(Long userId, Long unifyTypeId, String fileFormat, Integer page, Integer prePage) {

        PageHelper.startPage(page, prePage);

        List<ResourceViewLog> list = mapper.getMyViewForResource(userId, unifyTypeId, fileFormat);

        return new PageInfo(list);
    }

}
