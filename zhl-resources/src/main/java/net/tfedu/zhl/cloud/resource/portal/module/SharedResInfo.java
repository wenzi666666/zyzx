package net.tfedu.zhl.cloud.resource.portal.module;

import java.io.Serializable;

/**
 * 
 * 
 * 共享资源信息
 * 
 * @author wangwr
 * @date 2016年11月12日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 * 
 */
public class SharedResInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2841006899505386182L;
	
	String resTitle;// 资源标题
	String createMan;// 资源创建者
	int clickTime;// 浏览次数

	public String getResTitle() {
		return resTitle;
	}

	public void setResTitle(String resTitle) {
		this.resTitle = resTitle;
	}

	public String getCreateMan() {
		return createMan;
	}

	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}

	public int getClickTime() {
		return clickTime;
	}

	public void setClickTime(int clickTime) {
		this.clickTime = clickTime;
	}

}
