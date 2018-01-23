package net.tfedu.zhl.core.exception;


/**
 
 	md5 签名校验失败
  
    @author wangwr
    @date 2017年1月5日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
public class MD5SignError extends CustomException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MD5SignError(){
		super("MD5SignError", "md5 签名校验失败");
		
	}
	
	
}
