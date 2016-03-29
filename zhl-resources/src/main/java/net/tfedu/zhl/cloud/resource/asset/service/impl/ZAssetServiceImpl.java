package net.tfedu.zhl.cloud.resource.asset.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.tfedu.zhl.cloud.resource.asset.service.ZAssetService;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.dao.FileFormatMapper;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.dao.ResTypeMapper;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.FirstLevelResType;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResType;

@Service("zAssetService")
public class ZAssetServiceImpl implements ZAssetService {
	
	@Autowired
	ResTypeMapper typeMapper;
	
	@Autowired
	FileFormatMapper formatMapper;
	
	
	

	@Override
	public List<FirstLevelResType> getAllFirstLevelResType() {
		// TODO Auto-generated method stub
		return typeMapper.getAllFirstLevelResType();
	}




	@Override
	public List<String> getAllFileFormat() {
		// TODO Auto-generated method stub
		return formatMapper.getAllFileFormat();
	}

}
