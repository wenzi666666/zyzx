package net.tfedu.zhl.cloud.resource.poolTypeFormat.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SysFrom implements Serializable {
    /**
     * 序列号
     */
    private static final long serialVersionUID = 8160030266758656476L;

    /**
     *  资源来源0 平台资源 1 专家辅导资源 2三年无忧资源
     */
    public static List<Integer> sys_from = new ArrayList<Integer>();

    /**
     *  e备课，在查询系统资源、区本、校本资源时，当选择类型为全部时，排除掉哪些类型
     */
    public static List<Integer> removeTypeIds = new ArrayList<Integer>();
    
    /**
     * 最新资源为最近30天内的资源
     */
    public static int expire = 30;
    
    /**
     * 定义查询教材时的产品编码是zy
     */
    public static String proCode = "zy"; 
    

    static {
        sys_from.add(0);
        sys_from.add(1);
        sys_from.add(2);
        
        removeTypeIds.add(25);
        removeTypeIds.add(34);
        removeTypeIds.add(43);
        removeTypeIds.add(36);
        removeTypeIds.add(22);
        removeTypeIds.add(37);
        removeTypeIds.add(40);
        removeTypeIds.add(61);
    }

}
