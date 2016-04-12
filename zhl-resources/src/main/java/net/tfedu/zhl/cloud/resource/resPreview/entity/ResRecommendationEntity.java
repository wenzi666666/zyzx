package net.tfedu.zhl.cloud.resource.resPreview.entity;

import java.io.Serializable;

/**
 * 资源预览页面的资源推荐列表信息
 * @author WeiCuicui
 *
 */
public class ResRecommendationEntity implements Serializable{

	private static final long serialVersionUID = 8160030266758656476L;
	
	 /**
     * 资源id
     */
    private Long id;

    /**
     * 资源标题
     */
    private String Title;
    
    /**
     * 资源来源
     */
    private int fromflag;
    
    /**
     * 文件缩略图地址
     */
    private String thumbnailpath;
}
