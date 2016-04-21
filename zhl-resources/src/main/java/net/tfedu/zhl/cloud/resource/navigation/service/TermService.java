package net.tfedu.zhl.cloud.resource.navigation.service;

import net.tfedu.zhl.core.service.BaseService;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.term.entity.JTerm;

/**
 * 查询所有学段 service
 */
public interface TermService extends BaseService<JTerm> {

    // 查询所有学段
    public ResultJSON selectAll();
}
