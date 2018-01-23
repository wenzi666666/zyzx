package net.tfedu.zhl.cloud.resource.portal.module;

/**
 * 
 * 
 * 
 * 学科下的系统资源排行信息
 * 
 * @author wangwr
 * @date 2016年11月12日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 * 
 */
public class SubjectResourceUpdate {
	String rescode;// 资源编码
	String title;// 资源名称
	String imgPath;// 缩略图地址
	String viewPath;// 浏览地址
	int clicktime;// 浏览次数
	boolean isDwj;//是否為多文件
	boolean isEbook;//是否為ebook
    String fileExt;// 文件扩展名
	
    
	public boolean isEbook() {
		return isEbook;
	}

	public void setEbook(boolean isEbook) {
		this.isEbook = isEbook;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public boolean isDwj() {
		return isDwj;
	}

	public void setDwj(boolean isDwj) {
		this.isDwj = isDwj;
	}

	public String getRescode() {
		return rescode;
	}

	public void setRescode(String rescode) {
		this.rescode = rescode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getViewPath() {
		return viewPath;
	}

	public void setViewPath(String viewPath) {
		this.viewPath = viewPath;
	}

	public int getClicktime() {
		return clicktime;
	}

	public void setClicktime(int clicktime) {
		this.clicktime = clicktime;
	}

}
