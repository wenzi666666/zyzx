package net.tfedu.zhl.core.exception;


/**
 
  
    @author wangwr
    @date 2017年8月3日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
public class Custom500Exception extends CustomException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Custom500Exception(){
		super("500", "服务器内部错误");
	}
	
	public Custom500Exception(String message){
		super("500", message);
	}
	
	

}
