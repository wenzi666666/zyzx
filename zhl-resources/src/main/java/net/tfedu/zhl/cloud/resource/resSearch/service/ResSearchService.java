package net.tfedu.zhl.cloud.resource.resSearch.service;

import java.util.List;

import net.tfedu.zhl.cloud.resource.resSearch.entity.ResSearchResultEntity;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;

public interface ResSearchService {

    // 跨库检索资源

    public Pagination<ResSearchResultEntity> getResources(int fromFlag, List<Integer> sys_from, String searchKeyword,
            String format, int page, int perPage);

}
