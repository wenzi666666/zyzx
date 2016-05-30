package net.tfedu.zhl.cloud.resource.productUpdate.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.productUpdate.dao.ProductVersionMapper;
import net.tfedu.zhl.cloud.resource.productUpdate.dao.VersionFilesMapper;
import net.tfedu.zhl.cloud.resource.productUpdate.entity.ProductVersion;
import net.tfedu.zhl.cloud.resource.productUpdate.entity.VersionFiles;
import net.tfedu.zhl.cloud.resource.productUpdate.service.ProductUpdateService;
import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.stereotype.Service;

/**
 * 产品升级service
 * @author WeiCuicui
 *
 */
@Service("productUpdateService")
public class ProductUpdateServiceImpl implements ProductUpdateService{
	
	@Resource ProductVersionMapper productVersionMapper;
	
	@Resource VersionFilesMapper versionFilesMapper;

	/**
	 * 根据产品编码、版本编码，查询升级文件及升级说明
	 */
	@Override
	public ResultJSON selectVersionsByCode(String productCode,long versionCode,int productType){
		
		ProductVersion version = productVersionMapper.latestVersionInfoByCode(productCode, versionCode, productType);
		List<VersionFiles> files = versionFilesMapper.allUpdatedFilesByCode(productCode, versionCode, productType);

	    //存储path
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		//删除重复的文件
		for(int i = 0; i < files.size(); i++){
			VersionFiles file = files.get(i);
			String path = file.getLocation() + file.getFilename(); // 文件路径
			if(map.containsKey(path)){ //若已经存在相同的path，则删除重复的、升级时间早的文件
				files.remove(i);
			} else { //不存在相同的path
				map.put(path, 1);
			}
		}
		
		List<Object> result = new ArrayList<Object>();
		result.add(version);
		result.add(files);
		
		return ResultJSON.getSuccess(result);
		
	}

	
	/**
	 * 根据产品名称、版本编码，查询升级文件及升级说明
	 */
	@Override
	public ResultJSON selectVersionsByName(String productName,long versionCode,int productType){
		ProductVersion version = productVersionMapper.latestVersionInfoByName(productName, versionCode, productType);
		List<VersionFiles> files = versionFilesMapper.allUpdatedFilesByName(productName, versionCode, productType);
		
		//存储path
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		//删除重复的文件
		for(int i = 0; i < files.size(); i++){
			VersionFiles file = files.get(i);
			String path = file.getLocation() + file.getFilename(); // 文件路径
			if(map.containsKey(path)){ //若已经存在相同的path，则删除重复的、升级时间早的文件
				files.remove(i);
			} else { //不存在相同的path
				map.put(path, 1);
			}
		}
		
		List<Object> result = new ArrayList<Object>();
		result.add(version);
		result.add(files);
		
		return ResultJSON.getSuccess(result);
	}
}
