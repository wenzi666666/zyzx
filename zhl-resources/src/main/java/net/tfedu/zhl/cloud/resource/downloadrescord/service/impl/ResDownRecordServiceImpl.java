package net.tfedu.zhl.cloud.resource.downloadrescord.service.impl;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.tfedu.zhl.cloud.resource.constant.ResourcePlatformWebConstant;
import net.tfedu.zhl.cloud.resource.downloadrescord.dao.ResDownRecordMapper;
import net.tfedu.zhl.cloud.resource.downloadrescord.entity.ResDownRecord;
import net.tfedu.zhl.cloud.resource.downloadrescord.service.ResDownRecordService;
import net.tfedu.zhl.cloud.resource.prepare.util.JPrepareConstant;
import net.tfedu.zhl.cloud.resource.resourceList.dao.DistrictResMapper;
import net.tfedu.zhl.cloud.resource.resourceList.dao.SysResourceMapper;
import net.tfedu.zhl.cloud.resource.resourceList.entity.DistrictRes;
import net.tfedu.zhl.cloud.resource.resourceList.entity.SysResource;
import net.tfedu.zhl.cloud.resource.share.dao.XPlatFormShareMapper;
import net.tfedu.zhl.cloud.resource.userlog.entity.JUserlog;
import net.tfedu.zhl.core.service.impl.BaseServiceImpl;

/**
 * 
 * 
 * @author wangwr
 * @date 2017年8月7日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 */
@Transactional("transactionManager")
@Service("resDownRecordService")
public class ResDownRecordServiceImpl extends BaseServiceImpl<ResDownRecord> implements ResDownRecordService {

	@Autowired
	ResDownRecordMapper mapper;

	@Autowired
	XPlatFormShareMapper shareMapper;

	@Autowired
	DistrictResMapper districtResMapper;

	@Autowired
	SysResourceMapper syscourseMapper;

	@Override
	public Boolean addResourceDownloadRecordAndUpdateDownloadNumber(Long userId, Long resId, Integer fromflag) {
		//// 下载资源的来源 0 系统资源 1 自建资源 2 共享资源 3校本资源,4区本资源

		Date date = Calendar.getInstance().getTime();

		ResDownRecord record = new ResDownRecord();

		record.setDowndate(date);
		record.setDowntime(date);
		record.setFromflag(fromflag);
		record.setResid(resId);
		record.setUserid(userId);

		mapper.insert(record);

		updateDownloadNumber(resId, fromflag);
		
		
/*String  opertypecode = "view";
		
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
*/
		return true;
	}

	/**
	 * 更新下载次数
	 * 
	 * @param resId
	 * @param fromflag
	 *            //下载资源的来源 0 系统资源 1 自建资源 2 共享资源 3校本资源,4区本资源
	 */
	private void updateDownloadNumber(Long resId, Integer fromflag) {
		switch (fromflag) {
		case 1:// 自建资源共享之后，才能被其他人点击下载，创建人的下载更新下载次数
			break;
		case 2:
			shareMapper.updateDownloadTimes(ResourcePlatformWebConstant.SHAREDTYPE_FOR_ASSET, resId);
			break;

		case 3:
		case 4:
			DistrictRes res = districtResMapper.selectByPrimaryKey(resId);
			districtResMapper.updateDownloadTime(res.getRescode());

			break;
		default:
			// 系统资源
			SysResource sRes = syscourseMapper.selectByPrimaryKey(resId);
			Integer downloadTimes = syscourseMapper.getDownloadTimes(sRes.getRescode());
			if (null == downloadTimes) {
				syscourseMapper.insertDownloadRecord(sRes.getRescode());
			} else {
				syscourseMapper.updateDownloadTime(sRes.getRescode());
			}

			break;
		}
	}

}
