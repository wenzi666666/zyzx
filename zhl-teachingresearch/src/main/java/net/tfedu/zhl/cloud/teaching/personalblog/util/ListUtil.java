package net.tfedu.zhl.cloud.teaching.personalblog.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 
  
    @author wangwr
    @date 2017年6月27日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
public class ListUtil {
	
	
	/**
	 * 生成List范型集合(key全部小写)
	 * @param rowset
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, Object>> CreateList(List<Object> sourceList)throws Exception{
		List<Map<String, Object>> list = null;

		if(sourceList != null && sourceList.size()>0){
			Map<String, Object> map = null ;
			list = new ArrayList<Map<String, Object>>();
			
			for (Iterator<Object> iterator = sourceList.iterator(); iterator.hasNext();) {
				Object bean = iterator.next();
				list.add(convertBean2Map(bean,false));
			}
		}
		return list;
	}
	/**
	 * 生成List范型集合(key全部小写)
	 * @param rowset
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, Object>> CreateListToLowerCase(List<Object> sourceList)throws Exception{
		List<Map<String, Object>> list = null;
		
		if(sourceList != null && sourceList.size()>0){
			Map<String, Object> map = null ;
			list = new ArrayList<Map<String, Object>>();
			
			for (Iterator<Object> iterator = sourceList.iterator(); iterator.hasNext();) {
				Object bean = iterator.next();
				list.add(convertBean2Map(bean,true));
			}
		}
		return list;
	}

	
	
	   /**
     * 将一个 JavaBean 对象转化为一个 Map 
     * <一句话功能简述>
     * <功能详细描述>
     * @param bean
     * @return
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @see [类、类#方法、类#成员]
     */
    public static Map<String, Object> convertBean2Map(Object bean,Boolean isLower)
        throws IntrospectionException, IllegalAccessException, InvocationTargetException
    {
        Class<? extends Object> type = bean.getClass();
        Map<String, Object> returnMap = new HashMap<String, Object>();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++)
        {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!"class".equals(propertyName))
            {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null)
                {
                    returnMap.put(isLower?propertyName.toLowerCase():propertyName, result);
                }
                else
                {
                    returnMap.put(isLower?propertyName.toLowerCase():propertyName, null);
                }
            }
        }
        return returnMap;
    }
}
