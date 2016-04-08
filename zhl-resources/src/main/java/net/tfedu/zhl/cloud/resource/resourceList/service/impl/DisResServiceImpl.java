package net.tfedu.zhl.cloud.resource.resourceList.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.dao.ResTypeMapper;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.SysFrom;
import net.tfedu.zhl.cloud.resource.resourceList.dao.DistrictResMapper;
import net.tfedu.zhl.cloud.resource.resourceList.entity.DisAndSchoolEntity;
import net.tfedu.zhl.cloud.resource.resourceList.entity.DisResourceEntity;
import net.tfedu.zhl.cloud.resource.resourceList.entity.PageInfoToPagination;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;
import net.tfedu.zhl.cloud.resource.resourceList.service.DisResService;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

/**
 * 区本、校本资源的serviceImpl
 * 
 * @author WeiCuicui
 *
 */
@Service("disResService")
public class DisResServiceImpl implements DisResService {

    @Resource
    DistrictResMapper districtResMapper;
    @Resource
    ResTypeMapper resTypeMapper;

    // 获得区、校id
    @Override
    public DisAndSchoolEntity getDisAndSchool(long userId) {
        return districtResMapper.getDisAndSchool(userId);
    }

    // 查询区本、校本资源信息
    @Override
    public Pagination<DisResourceEntity> selectDisRes(int fromFlag, String fileFormat, List<Integer> typeIds,
            String tfcode, int orderBy, long schoolId, long districtId, int page, int perPage) {
        // Page插件必须放在查询语句之前紧挨的第一个位置
        PageHelper.startPage(page, perPage);

        // 查询资源
        List<DisResourceEntity> list = districtResMapper.selectDisRes(fromFlag, fileFormat, typeIds, tfcode, orderBy,
                schoolId, districtId);

        // 判断资源是否为最新
        for (int i = 0; i < list.size(); i++) {
        	
        	
        	//将 / 替换为 \
        	String thumbnailpath = list.get(i).getThumbnailpath();
        	list.get(i).setThumbnailpath(thumbnailpath.replaceAll("/", "\\"));
        	
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

        // 封装结果集
        PageInfoToPagination<DisResourceEntity> transfer = new PageInfoToPagination<DisResourceEntity>();

        return transfer.transfer(list);

    }

    // 查询区本、校本资源信息
    @Override
    public Pagination<DisResourceEntity> selectAllDisRes(long userId, int mTypeId, String fileFormat, String tfcode,
            int orderBy, int page, int perPage, int fromFlag) {

        // 根据父类型，查询所有的子类型
        List<Integer> typeIds = resTypeMapper.getDisResTypesByPMType(mTypeId);

        long schoolId = 0;
        long districtId = 0;

        // 根据userId查询schoolId 和 districtId
        DisAndSchoolEntity disAndSchoolIds = getDisAndSchool(userId);
        if (disAndSchoolIds != null) {
            schoolId = disAndSchoolIds.getSchoolId();
            districtId = disAndSchoolIds.getDistrictId();
        }

        return selectDisRes(fromFlag, fileFormat, typeIds, tfcode, orderBy, schoolId, districtId, page, perPage);

    }
    
    // 查询区本、校本资源信息，资源预览页面的推荐
    @Override
	public Pagination<DisResourceEntity> selectDisRes_Preview(int fromFlag, String fileFormat, List<Integer> typeIds,
            String tfcode, int orderBy, long schoolId, long districtId, int page, int perPage,long resId){
    	 // Page插件必须放在查询语句之前紧挨的第一个位置
        PageHelper.startPage(page, perPage);

        // 查询资源
        List<DisResourceEntity> list = districtResMapper.selectDisRes_Preview(fromFlag, fileFormat, typeIds, tfcode, orderBy, schoolId, districtId, resId);
        // 判断资源是否为最新
        for (int i = 0; i < list.size(); i++) {
        	
        	
        	//将 / 替换为 \
        	String thumbnailpath = list.get(i).getThumbnailpath();
        	list.get(i).setThumbnailpath(thumbnailpath.replaceAll("/", "\\"));
        	
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

        // 封装结果集
        PageInfoToPagination<DisResourceEntity> transfer = new PageInfoToPagination<DisResourceEntity>();

        return transfer.transfer(list);
    }

    // 查询区本、校本资源信息，资源预览页面的推荐
    @Override
	public Pagination<DisResourceEntity> selectAllDisRes_Preview(long userId, int mTypeId, String fileFormat, String tfcode,
            int orderBy, int page, int perPage, int fromFlag,long resId){
    	 // 根据父类型，查询所有的子类型
        List<Integer> typeIds = resTypeMapper.getDisResTypesByPMType(mTypeId);

        long schoolId = 0;
        long districtId = 0;

        // 根据userId查询schoolId 和 districtId
        DisAndSchoolEntity disAndSchoolIds = getDisAndSchool(userId);
        if (disAndSchoolIds != null) {
            schoolId = disAndSchoolIds.getSchoolId();
            districtId = disAndSchoolIds.getDistrictId();
        }

        return selectDisRes_Preview(fromFlag, fileFormat, typeIds, tfcode, orderBy, schoolId, districtId, page, perPage, resId);
    }
    
    // 查询区本、校本资源信息，e备课
    @Override
	public Pagination<DisResourceEntity> selectDisRes_EPrepare(int fromFlag, String fileFormat, List<Integer> typeIds,
            String tfcode, int orderBy, long schoolId, long districtId, int page, int perPage,String searchWord){
    	 // Page插件必须放在查询语句之前紧挨的第一个位置
        PageHelper.startPage(page, perPage);

        // 查询资源
        List<DisResourceEntity> list = districtResMapper.selectDisRes_EPrepare(fromFlag, fileFormat, typeIds, tfcode, orderBy, schoolId, districtId, searchWord);
        // 判断资源是否为最新
        for (int i = 0; i < list.size(); i++) {
        	
        	
        	//将 / 替换为 \
        	String thumbnailpath = list.get(i).getThumbnailpath();
        	list.get(i).setThumbnailpath(thumbnailpath.replaceAll("/", "\\"));
        	
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

        // 封装结果集
        PageInfoToPagination<DisResourceEntity> transfer = new PageInfoToPagination<DisResourceEntity>();

        return transfer.transfer(list);
    }

    // 查询区本、校本资源信息，e备课
    @Override
	public Pagination<DisResourceEntity> selectAllDisRes_EPrepare(long userId, int mTypeId, String fileFormat, String tfcode,
            int orderBy, int page, int perPage, int fromFlag,String searchWord,List<Integer> removeTypeIds){
    	 // 根据父类型，查询所有的子类型
        List<Integer> typeIds = resTypeMapper.getDisResTypesByPMType_EPrepare(mTypeId, removeTypeIds);

        long schoolId = 0;
        long districtId = 0;

        // 根据userId查询schoolId 和 districtId
        DisAndSchoolEntity disAndSchoolIds = getDisAndSchool(userId);
        if (disAndSchoolIds != null) {
            schoolId = disAndSchoolIds.getSchoolId();
            districtId = disAndSchoolIds.getDistrictId();
        }

        return selectDisRes_EPrepare(fromFlag, fileFormat, typeIds, tfcode, orderBy, schoolId, districtId, page, perPage, searchWord);
    }

}