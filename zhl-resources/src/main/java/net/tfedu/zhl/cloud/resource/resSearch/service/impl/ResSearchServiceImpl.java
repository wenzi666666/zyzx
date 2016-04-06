package net.tfedu.zhl.cloud.resource.resSearch.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.dao.FileFormatMapper;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.SysFrom;
import net.tfedu.zhl.cloud.resource.resSearch.dao.ResSearchMapper;
import net.tfedu.zhl.cloud.resource.resSearch.entity.ResSearchResultEntity;
import net.tfedu.zhl.cloud.resource.resSearch.service.ResSearchService;
import net.tfedu.zhl.cloud.resource.resourceList.dao.DistrictResMapper;
import net.tfedu.zhl.cloud.resource.resourceList.entity.DisAndSchoolEntity;
import net.tfedu.zhl.cloud.resource.resourceList.entity.PageInfoToPagination;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

/**
 * 资源跨库检索的service
 * 
 * @author WeiCuicui
 *
 */
@Service("resSearchService")
public class ResSearchServiceImpl implements ResSearchService {

    @Resource
    ResSearchMapper resSearchMapper;
    @Resource
    FileFormatMapper fileFormatMapper;
    @Resource DistrictResMapper districtResMapper;

    /**
     * 跨库检索资源
     */
    @Override
    public Pagination<ResSearchResultEntity> getResources(int fromFlag, List<Integer> sys_from, String searchKeyword,
            String format, int page, int perPage,long userId) {

        // 存放查询结果
        List<ResSearchResultEntity> list = new ArrayList<ResSearchResultEntity>();
        // 封装结果集
        PageInfoToPagination<ResSearchResultEntity> transfer = new PageInfoToPagination<ResSearchResultEntity>();

        // 若输入的关键字为空，则返回为空
        if (searchKeyword == null || searchKeyword.length() == 0)
            return transfer.transfer(list);
        
        long schoolId = 0;
        long districtId = 0;

        // 根据userId查询schoolId 和 districtId
        DisAndSchoolEntity disAndSchoolIds = districtResMapper.getDisAndSchool(userId);
        if (disAndSchoolIds != null) {
            schoolId = disAndSchoolIds.getSchoolId();
            districtId = disAndSchoolIds.getDistrictId();
        }
  
        // 查询满足条件的全部资源
        if (fromFlag == 0) {

            // Page插件必须放在查询语句之前紧挨的第一个位置
            PageHelper.startPage(page, perPage);
            list = resSearchMapper.getAllResources(searchKeyword, format, sys_from,schoolId, districtId);
        } else if (fromFlag == 1) { // 系统资源

            // Page插件必须放在查询语句之前紧挨的第一个位置
            PageHelper.startPage(page, perPage);
            list = resSearchMapper.getAllSysResources(searchKeyword, format, sys_from);
        } else { // 校本资源、区本资源

        	
            // Page插件必须放在查询语句之前紧挨的第一个位置
            PageHelper.startPage(page, perPage);
            list = resSearchMapper.getAllDisResources(searchKeyword, fromFlag, format,schoolId, districtId);
        }
        
        // 判断资源是否为最新
        for (int i = 0; i < list.size(); i++) {
        	
            // 最后更新日期
            Date date = list.get(i).getUpdateDT();
            // 得到当前日期的前多少天
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -SysFrom.expire);
            Date expireDate = calendar.getTime();
            // 比较
            if (date.getTime() >= expireDate.getTime())
                list.get(i).setNew(true);
        }
        //将pageIn封装为自定义的pagination
        return transfer.transfer(list);
    }
    
    /**
     * 查询资源格式
     * @param searchKeyword
     * @param fromFlag
     * @param sys_from
     * @return
     */
    @Override
	public List<String> getFileFormats(String searchKeyword,int fromFlag,List<Integer> sys_from,long userId){
    	 // 若输入的关键字为空，则返回为空
        if (searchKeyword == null || searchKeyword.length() == 0)
            return null;
        
        List<String> resultList = new ArrayList<String>();
        
        long schoolId = 0;
        long districtId = 0;

        // 根据userId查询schoolId 和 districtId
        DisAndSchoolEntity disAndSchoolIds = districtResMapper.getDisAndSchool(userId);
        if (disAndSchoolIds != null) {
            schoolId = disAndSchoolIds.getSchoolId();
            districtId = disAndSchoolIds.getDistrictId();
        }
        
        // 查询全部资源的格式
        if (fromFlag == 0) {

        	resultList = resSearchMapper.getAllFileFormats(searchKeyword, sys_from,schoolId,districtId);
        	
        } else if (fromFlag == 1) { // 系统资源的格式

        	resultList = resSearchMapper.getSysFileFormats(searchKeyword, sys_from);
        } else { // 校本资源、区本资源的格式

        	
            resultList = resSearchMapper.getDisFileFormats(searchKeyword, fromFlag,schoolId,districtId);
        }
        
        return resultList;
    }

}
