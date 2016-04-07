package net.tfedu.zhl.cloud.resource.resPreview.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.asset.dao.ZAssetMapper;
import net.tfedu.zhl.cloud.resource.navigation.dao.JUserDefaultMapper;
import net.tfedu.zhl.cloud.resource.navigation.entity.JUserDefault;
import net.tfedu.zhl.cloud.resource.resPreview.entity.ResNavEntity;
import net.tfedu.zhl.cloud.resource.resPreview.entity.ResPreviewInfo;
import net.tfedu.zhl.cloud.resource.resPreview.service.ResPreviewService;
import net.tfedu.zhl.cloud.resource.resourceList.dao.DistrictResMapper;
import net.tfedu.zhl.cloud.resource.resourceList.dao.SysResourceMapper;

import org.springframework.stereotype.Service;

/**
 * 资源预览的 serviceImpl
 * 
 * @author WeiCuicui
 *
 */
@Service("resPreviewService")
public class ResPreviewServiceImpl implements ResPreviewService {

    @Resource
    SysResourceMapper sysResourceMapper;
    @Resource
    DistrictResMapper districtResMapper;
    @Resource
    JUserDefaultMapper jUserDefaultMapper;
    
    @Resource
    ZAssetMapper  assetMapper;
    

    // 根据resId和fromFlag，查询资源具体信息
    @Override
    public ResPreviewInfo getResPreviewInfo(long resId, int fromFlag) {
        ResPreviewInfo info = null;
        if (fromFlag == 0) {// 系统资源
            info = sysResourceMapper.getSysResInfo(resId);

        } else if (fromFlag == 1) {// 自建资源
        	info = assetMapper.getAssetPreviewInfo(resId);
        } else if (fromFlag == 3) {// 校本资源
            info = districtResMapper.getDisResInfo(fromFlag, resId);

        } else if (fromFlag == 4) {// 区本资源
            info = districtResMapper.getDisResInfo(fromFlag, resId);

        }

        return info;
    }

    // 对一个资源进行预览时，查询该资源所在的所有版本目录
    @Override
    public List<List<ResNavEntity>> getAllResNavs(long resId, int fromFlag, String curTfcode) {
        List<List<ResNavEntity>> info = new ArrayList<List<ResNavEntity>>();
        List<String> structCodes  = null;
        if (fromFlag == 0) {// 系统资源
            structCodes = sysResourceMapper.getAllRescodes(resId, curTfcode);
        } else if (fromFlag == 1) {// 自建资源
        	structCodes = assetMapper.getAssetNavs(resId, curTfcode);
        } else if (fromFlag == 3 || fromFlag == 4) {// 校本资源、区本资源
            structCodes = districtResMapper.getAllDisRescodes(resId, curTfcode);
        }
        
        
        if (structCodes != null) {
            for (int i = 0; i < structCodes.size(); i++) {
                List<ResNavEntity> navs = sysResourceMapper.getSysNav(structCodes.get(i));
                if (navs != null)
                    info.add(navs);
            }
        }
        return info;
    }

    // 根据当前目录结点的tfcode，查找其所在学段、学科、版本、教材等目录
    @Override
    public JUserDefault getPnodes(String tfcode) {

        return jUserDefaultMapper.getCourseContent(tfcode);
    }
}

