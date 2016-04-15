package net.tfedu.zhl.cloud.resource.prepare.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.tfedu.zhl.cloud.resource.downloadrescord.dao.ResDownRecordMapper;
import net.tfedu.zhl.cloud.resource.downloadrescord.entity.ResDownRecord;
import net.tfedu.zhl.cloud.resource.prepare.dao.JPrepareContentMapper;
import net.tfedu.zhl.cloud.resource.prepare.dao.JPrepareMapper;
import net.tfedu.zhl.cloud.resource.prepare.entity.FirstNavigationInfo;
import net.tfedu.zhl.cloud.resource.prepare.entity.JPrepare;
import net.tfedu.zhl.cloud.resource.prepare.entity.JPrepareContent;
import net.tfedu.zhl.cloud.resource.prepare.entity.JPrepareContentView;
import net.tfedu.zhl.cloud.resource.prepare.entity.JPrepareContentViewUtil;
import net.tfedu.zhl.cloud.resource.prepare.entity.JPrepareView;
import net.tfedu.zhl.cloud.resource.prepare.entity.ResourceSimpleInfo;
import net.tfedu.zhl.cloud.resource.prepare.entity.UserPrepareStatisInfo;
import net.tfedu.zhl.cloud.resource.prepare.service.JPrepareService;
import net.tfedu.zhl.cloud.resource.prepare.util.JPrepareConstant;
import net.tfedu.zhl.cloud.resource.resourceList.dao.DistrictResMapper;
import net.tfedu.zhl.cloud.resource.resourceList.dao.SysResourceMapper;
import net.tfedu.zhl.cloud.resource.resourceList.entity.DistrictRes;
import net.tfedu.zhl.cloud.resource.resourceList.entity.PageInfoToPagination;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.sso.userlog.dao.JUserlogMapper;
import net.tfedu.zhl.sso.userlog.entity.JUserlog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("jPrepareService")
public class JPrepareServiceImpl implements JPrepareService {

	/**
	 * 备课夹
	 */
    @Autowired
    JPrepareMapper mapper;

	/**
	 * 备课夹内容
	 */
    @Autowired
    JPrepareContentMapper contMapper;
    
	/**
	 * 区校本资源
	 */    
    @Autowired
    DistrictResMapper disResMapper; 
    
    
    /**
     * 系统资源
     */
    @Autowired
    SysResourceMapper sysResMapper;
    
    /**
     * 下载
     */
    @Autowired
    ResDownRecordMapper downMapper;
    
    
    @Autowired
    JUserlogMapper logMapper;

    @Override
    public JPrepare addPrepare(JPrepare obj) {
        // TODO Auto-generated method stub
        mapper.insert(obj);
        // 更新排序字段
        mapper.update_default_prepare_order();
        return obj;
    }

    @Override
    public void editPrepare(JPrepare obj) {
        mapper.updateByPrimaryKeySelective(obj);
    }

    @Override
    public void deletePrepareById(Long id) {
    	JPrepare p = new JPrepare();
    	p.setFlag(true);
    	p.setId(id);
        mapper.updateByPrimaryKeySelective(p);
    }

    @Override
    public JPrepareContent addPrepareContent(JPrepareContent content) {
        // TODO Auto-generated method stub
        contMapper.insert(content);
        mapper.update_default_prepare_content_order();
        return content;
    }

    @Override
    public void editPrepareContent(JPrepareContent content) {
        contMapper.updateByPrimaryKeySelective(content);
    }

    @Override
    public void deletePrepareContentById(Long id) {
    	JPrepareContent record = new JPrepareContent();
    	record.setId(id);
    	record.setFlag(true);
        contMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void clearPrepareContentByPrepareId(Long prepareId) {
        mapper.clearPrepareContentByPrepareId(prepareId);
    }

    @Override
    public List<JPrepareView> queryPrepareList(String tfcode, Long userId) {
        // TODO Auto-generated method stub
        return mapper.queryPrepareList(tfcode + "%", userId);
    }

    @Override
    public List<JPrepareView> queryPrepareAndTimeScopeList(String tfcode,String title, Long userId) {
        // TODO Auto-generated method stub
        return mapper.queryPrepareAndTimeScopeList(tfcode + "%",("%"+title+"%"), userId);
    }

    @Override
    public List<JPrepareContentView> queryPrepareContentList(Long prepareId) {
        // TODO Auto-generated method stub

        List<JPrepareContentView> list = mapper.queryPrepareContentList(prepareId);
        return list;
    }

    @Override
    public void removeResourceFromPrepare(Long prepareId, Integer contType, Long contId) {
        // TODO Auto-generated method stub
        mapper.removeResourceFromPrepare(prepareId, contType, contId);
    }

    @Override
    public void addToPrepareWithOnlySysResource(String resCode, Long userId) {
        // TODO Auto-generated method stub
        // 获取资源的第一个导航节点
        FirstNavigationInfo nav = mapper.getFirstNavigationForSysResource(resCode);
        if (nav != null) {
            String tfcode = nav.getTfcode();
            // 获取节点下的备课夹
            List<JPrepareView> list = mapper.queryPrepareList(tfcode, userId);
            // 如果有则 加入到备课夹
            // 没有则 新建备课夹 加入
            Date currentDate = Calendar.getInstance().getTime();
            if (list != null && list.size() > 0) {
                JPrepareView prepare = list.get(0);
                // 将资源加入备课夹
                JPrepareContent cont = new JPrepareContent();
                cont.setPreid(prepare.getId());
                cont.setContid(nav.getResId());
                cont.setConttype(JPrepareConstant.CONTTYPE_SYSRES);
                cont.setCreatetime(currentDate);
                contMapper.insert(cont);
                mapper.update_default_prepare_content_order();
            } else {
                String title = nav.getTitle();
                // 新建备课夹
                JPrepare prepare = new JPrepare();
                prepare.setUserid(userId);
                prepare.setCreatetime(currentDate);
                prepare.setTfcode(tfcode);
                prepare.setTitle(title);
                mapper.insert(prepare);

                // 将资源加入备课夹
                JPrepareContent cont = new JPrepareContent();
                cont.setPreid(prepare.getId());
                cont.setContid(nav.getResId());
                cont.setConttype(JPrepareConstant.CONTTYPE_SYSRES);
                cont.setCreatetime(currentDate);
                contMapper.insert(cont);
                mapper.update_default_prepare_content_order();
            }
        }
    }

    @Override
    public void exchangeContOrderIdx(long prevId, long nextId) {
        JPrepareContent p = contMapper.selectByPrimaryKey(prevId);
        JPrepareContent n = contMapper.selectByPrimaryKey(nextId);

        int prev_idx = p.getOrderidx();
        int next_idx = n.getOrderidx();

        p.setOrderidx(next_idx);
        n.setOrderidx(prev_idx);

        contMapper.updateByPrimaryKey(p);
        contMapper.updateByPrimaryKey(n);

    }

    @Override
    public List<ResourceSimpleInfo> getResourceSimpleInfo(String[] ids, String[] fromFlags) {
        List<ResourceSimpleInfo> list = new ArrayList<ResourceSimpleInfo>();
        for (int i = 0; i < fromFlags.length; i++) {
            ResourceSimpleInfo info = null;
            switch (Integer.parseInt(fromFlags[i])) {
            case JPrepareConstant.fromFlag_localRes:
            case JPrepareConstant.fromFlag_sharedRes:
                info = mapper.getAssetInfo(Long.parseLong(ids[i]));
                break;

            case JPrepareConstant.fromFlag_sysRes:

                info = mapper.getResourceInfo(Long.parseLong(ids[i]));
                break;

            case JPrepareConstant.fromFlag_districtRes:
            case JPrepareConstant.fromFlag_schoolRes:
                info = mapper.getDistrictORSchoolResInfo(Long.parseLong(ids[i]));
                break;
            }
            list.add(info);
        }
        return list;
    }

    /**
     * 分页获取我的备课资源
     * 
     * @param userId
     * @param unifyTypeId
     * @param fileFormat
     * @param page
     * @param prePage
     * @return
     */
    @Override
    public Pagination getPrepareContentListByUserId(Long userId, Long unifyTypeId, String fileFormat, Integer page,
            Integer prePage) {
        PageHelper.startPage(page, prePage);
        List<JPrepareContentView> list = contMapper.getPrepareContentListByUserId(userId, unifyTypeId, fileFormat);
        Pagination p = new PageInfoToPagination().transfer(list);
        return p;
    }

    /**
     * 将指定资源从我的备课夹中清除
     * 
     * @param userId
     * @param resIds
     * @param fromFlags
     */
    @Override
    public void removeMyPrepareContentResource(Long userId, String resIds, String fromFlags) {
        // 非空判断
        if (StringUtils.isEmpty(resIds) || StringUtils.isEmpty(fromFlags)) {
            throw new RuntimeException(CustomException.PARAMSERROR.getCode());
        }
        String[] resId = resIds.split(",");
        String[] fromFlag = fromFlags.split(",");
        if (resId.length != fromFlag.length) {
            throw new RuntimeException(CustomException.PARAMSERROR.getCode());
        }
        for (int i = 0; i < fromFlag.length; i++) {
            long _resId = Long.parseLong(resId[i]);
            int _contType = JPrepareConstant.getContTypeByFromFlag(Integer.parseInt(fromFlag[i]));
            contMapper.removeMyPrepareContentResource(userId, _resId, _contType);
        }
    }

    @Override
    public List<UserPrepareStatisInfo> getMyPrepareStatis(Long userId) {

        List<UserPrepareStatisInfo> list = mapper.getUserPrepareStatisButPartResult(userId);
        for (UserPrepareStatisInfo info : list) {
            UserPrepareStatisInfo temp = mapper.getBookPrepareStatis(userId, info.getTfcode() + "%");
            info.setNodeFinishedNums(temp.getNodeFinishedNums());
            info.setNodeOmitNums(temp.getNodeOmitNums());
            info.setPrepareNums(temp.getPrepareNums());
            info.setResourceNums(temp.getResourceNums());
        }
        return list;
    }

	@Override
	public List<ResourceSimpleInfo> getResourceSimpleInfoForView(String[] ids,
			String[] fromFlags,Long userId) {
		// TODO Auto-generated method stub		
		List<ResourceSimpleInfo>  list = this.getResourceSimpleInfo(ids, fromFlags);
		
		List<JUserlog> logList = new ArrayList<JUserlog>();
		
		Date time = Calendar.getInstance().getTime();
		
		String logTypeCode = "";
		
		//增加浏览记录、点击数  自建资源不处理
		for (ResourceSimpleInfo resourceSimpleInfo : list) {

			if(null == resourceSimpleInfo){
				continue;
			}
			
			
			
			String rescode = resourceSimpleInfo.getRescode();
			switch(resourceSimpleInfo.getFromflag()){
			
			
			//校本资源
			case JPrepareConstant.fromFlag_schoolRes:
				disResMapper.updateClickTime(rescode);
				
				break;
			//区本资源
			case JPrepareConstant.fromFlag_districtRes:
				disResMapper.updateClickTime(rescode);
				break;	
			//系统资源
			case JPrepareConstant.fromFlag_sysRes:
				sysResMapper.updateClickTime(rescode);
				break;	
			}			
			
			
			String  opertypecode = "view";
			
			JUserlog log = new JUserlog();
			log.setUserid(userId);
			log.setDownflag(false);
			log.setCreatetime(time);
			log.setAlltestnum(0);
			log.setCorrtestnum(0);
			log.setDuration("");
			log.setFlag(false);
			log.setIsflag(0);
			log.setLogtypecode(JPrepareConstant.getLogTypeByFromflag(resourceSimpleInfo.getFromflag()));
			log.setObjid(resourceSimpleInfo.getResid());
			log.setObjname(resourceSimpleInfo.getTitle());
			log.setOpertypecode(opertypecode);
			log.setSubjectid(0l);
			logList.add(log);
		}
		
		if(logList.size()>0){
			//批量插入浏览日志
			logMapper.insertList(logList);
		}
		return list;
	}

	@Override
	public List<ResourceSimpleInfo> getResourceSimpleInfoForDownload(
			String[] ids, String[] fromFlags ,Long userId) {
		// TODO Auto-generated method stub
		List<ResourceSimpleInfo>  list = this.getResourceSimpleInfo(ids, fromFlags);		
		List<ResDownRecord> downList =new ArrayList<ResDownRecord>();
		Date time = Calendar.getInstance().getTime();
		//增加下载记录、下载数   自建资源不处理
		for (ResourceSimpleInfo resourceSimpleInfo : list) {
			if(null == resourceSimpleInfo){
				continue;
			}
			String rescode = resourceSimpleInfo.getRescode();

			switch(resourceSimpleInfo.getFromflag()){			
			//校本资源
			case JPrepareConstant.fromFlag_schoolRes:
				disResMapper.updateDownloadTime(rescode);
				break;
			//区本资源
			case JPrepareConstant.fromFlag_districtRes:
				disResMapper.updateDownloadTime(rescode);
				break;	
			//系统资源
			case JPrepareConstant.fromFlag_sysRes:
				sysResMapper.updateDownloadTime(rescode);				
				break;					
			}	
			
			//自建资源不处理
			if(JPrepareConstant.fromFlag_localRes!=resourceSimpleInfo.getFromflag()){
				//准备添加下载记录
				ResDownRecord  record = new ResDownRecord();
				record.setUserid(userId);
				record.setFromflag(resourceSimpleInfo.getFromflag());
				record.setResid(resourceSimpleInfo.getResid());
				record.setDowndate(time);
				record.setDowntime(time);
				downList.add(record);
			}
		}
		if(downList.size()>0){
			//批量插入下载记录
			downMapper.insertList(downList);
		}
		
		return list;
	}

	@Override
	public List<JPrepareView> getLatestPrepare(Long userId) {
		// TODO Auto-generated method stub
		return mapper.getLatestPrepare(userId);
	}

}