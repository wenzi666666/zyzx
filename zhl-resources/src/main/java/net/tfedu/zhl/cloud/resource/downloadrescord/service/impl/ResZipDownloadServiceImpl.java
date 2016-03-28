package net.tfedu.zhl.cloud.resource.downloadrescord.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.tfedu.zhl.cloud.resource.downloadrescord.dao.ResZipDownRecordMapper;
import net.tfedu.zhl.cloud.resource.downloadrescord.entity.ResZipDownRecord;
import net.tfedu.zhl.cloud.resource.downloadrescord.service.ResZipDownloadService;

@Service("resZipDownloadService")
public class ResZipDownloadServiceImpl implements ResZipDownloadService {

	
	@Autowired
	ResZipDownRecordMapper mapper ;
	
	
	@Override
	public void addZipDownRecord(ResZipDownRecord obj) {
		mapper.insert(obj);
	}

	@Override
	public void updateZipDownRecord(ResZipDownRecord obj) {
		mapper.updateByPrimaryKeySelective(obj);
	}

	@Override
	public ResZipDownRecord getZipDownRecord(Long id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

}
