package net.tfedu.zhl.cloud.resource.poolTypeFormat.entity;

import java.util.ArrayList;
import java.util.List;


public class SysFrom {

	//资源来源0 平台资源 1 专家辅导资源 2三年无忧资源
	public static List<Integer> sys_from = new ArrayList<Integer>();
	
	static {
		sys_from.add(0);
		sys_from.add(1);
		sys_from.add(2);      
    }
}
