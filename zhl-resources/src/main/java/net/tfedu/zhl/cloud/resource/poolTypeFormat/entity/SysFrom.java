package net.tfedu.zhl.cloud.resource.poolTypeFormat.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class SysFrom implements Serializable{
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 8160030266758656476L;

	//资源来源0 平台资源 1 专家辅导资源 2三年无忧资源
	public static List<Integer> sys_from = new ArrayList<Integer>();
	
	//规定，最新资源是距离当前时间在30天之内的那些资源
	public static int expire = 30;
	
	static {
		sys_from.add(0);
		sys_from.add(1);
		sys_from.add(2);      
    }
}
