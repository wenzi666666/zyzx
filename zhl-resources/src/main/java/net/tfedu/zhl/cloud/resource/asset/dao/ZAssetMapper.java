package net.tfedu.zhl.cloud.resource.asset.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.tfedu.zhl.cloud.resource.asset.entity.ZAsset;
import net.tfedu.zhl.helper.CoreMapper;

public interface ZAssetMapper extends CoreMapper<ZAsset> {
	
	
	
	/**
	 * 获取资源
	 * @param userId
	 * @param resPath
	 * @return
	 */
	public ZAsset getAssetByUserIdAndPath(@Param("userId")Long userId,@Param("resPath")String resPath);
	
	
	
	/**
	 * 更新 asset资源中的 finished标示为true
	 * @param userId
	 * @param resPath
	 */
	public void updateAssetFinished(@Param("userId")Long userId,@Param("resPath")String resPath);



	/**
	 * 查询我的上传
	 * @param userId
	 * @param unifyTypeId
	 * @param fileFormat
	 * @return
	 */
	public List<ZAsset> queryMyAssets(@Param("userId")Long userId,@Param("unifyTypeId") Long unifyTypeId
								,@Param("fileFormat")String fileFormat);

}
