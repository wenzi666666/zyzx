package net.tfedu.zhl.cloud.teaching.videoCourses.entity;

/**
 * 一条视频课程的预览信息
 * @author WeiCuicui
 *
 */
public class VideoPreviewEntity {

	private long id;
	/**
	 * 视频课程标题
	 */
	private String title;
	
	/**
	 * 当前用户对该视频的评分
	 */
	private Integer score;
	
	/**
	 * 视频文件名称
	 */
	private String fname;
	
	/**
	 * 视频课程缩略图路径
	 */
	private String thumbnailpath;
	
	public String getThumbnailpath() {
		return thumbnailpath;
	}
	public void setThumbnailpath(String thumbnailpath) {
		this.thumbnailpath = thumbnailpath;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	
}
