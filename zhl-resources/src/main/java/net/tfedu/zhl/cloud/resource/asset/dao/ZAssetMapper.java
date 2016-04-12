package net.tfedu.zhl.cloud.resource.asset.dao;

import java.util.List;

import net.tfedu.zhl.cloud.resource.asset.entity.ZAsset;
import net.tfedu.zhl.cloud.resource.asset.entity.ZAssetView;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.FirstLevelResType;
import net.tfedu.zhl.cloud.resource.resPreview.entity.ResPreviewInfo;
import net.tfedu.zhl.helper.CoreMapper;

import org.apache.ibatis.annotations.Param;

public interface ZAssetMapper extends CoreMapper<ZAsset> {

    /**
     * 获取资源
     * 
     * @param userId
     * @param resPath
     * @return
     */
    public ZAsset getAssetByUserIdAndPath(@Param("userId") Long userId, @Param("resPath") String resPath);

    /**
     * 更新 asset资源中的 finished标示为true
     * 
     * @param userId
     * @param resPath
     */
    public void updateAssetFinished(@Param("userId") Long userId, @Param("resPath") String resPath);

    /**
     * 查询我的上传
     * 
     * @param userId
     * @param unifyTypeId
     * @param fileFormat
     * @return
     */
    public List<ZAsset> queryMyAssets(@Param("userId") Long userId, @Param("unifyTypeId") Long unifyTypeId,
            @Param("fileFormat") String fileFormat);
    
    
    
    /**
	 * 获取用户的课件
	 * @param userId
	 * @param tfcode
	 * @param title
	 * @param orderby
	 * @return
	 */
	public List<ZAssetView> queryUserCourseware(@Param("userId") Long userId,@Param("tfcode") String tfcode
				,@Param("title") String title,@Param("orderby") String orderby);
	
	
	/**
	 * 重命名课件
	 */
	public void renameAsset(@Param("id")Long id,@Param("name")String name);
	
	
	
	
	/**
	 * 查询 自建资源的详细信息
	 * @param resId
	 * @return
	 */
	public  ResPreviewInfo getAssetPreviewInfo(Long resId);
	
	/**
	 * 返回自建资源的资源导航信息
	 * @param resId
	 * @return
	 */
	public List<String>  getAssetNavs(@Param("resId")Long resId,@Param("curTfcode")String curTfcode);
	
	
	
	/**
	 * 查询我的备课资源中的资源类型
	 * @param userId
	 * @return
	 */
	public  List<FirstLevelResType> getResTypeForMyPrepareRes(Long userId);
	
	/**
	 *  查询我的上传中的资源类型
	 * @param userId
	 * @return
	 */
	public  List<FirstLevelResType> getResTypeForMyUpload(Long userId);
	
	/**
	 * 查询我的下载中的资源类型
	 * @param userId
	 * @return
	 */	
	public  List<FirstLevelResType> getResTypeForMyDownload(Long userId);
	/**
	 * 查询我的最近浏览资源中的资源类型
	 * @param userId
	 * @return
	 */
	public  List<FirstLevelResType> getResTypeForMyView(Long userId);
	
	
	/**
	 * 复制一条自建资源
	 * @param resId
	 * @param tfcode
	 */
	public void copyAsset(@Param("resId")long resId,@Param("des_tfcode")String tfcode);
	
	/**
	 * 删除一条自建资源
	 * @param resId
	 * @param tfcode
	 */
	public void delAsset(@Param("resId")long resId,@Param("tfcode")String tfcode);
}


