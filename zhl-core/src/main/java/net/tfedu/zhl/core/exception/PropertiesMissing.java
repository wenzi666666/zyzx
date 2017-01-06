package net.tfedu.zhl.core.exception;


/**
 
 	缺少配置信息
  
    @author wangwr
    @date 2017年1月5日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
public class PropertiesMissing extends CustomException {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PropertiesMissing(){
		super("PropertiesMissing", "缺少配置信息");
	}

	public PropertiesMissing(String configname){
		super("PropertiesMissing", "缺少配置信息:"+configname);
	}
	
}
