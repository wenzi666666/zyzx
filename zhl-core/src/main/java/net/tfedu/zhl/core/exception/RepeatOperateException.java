package net.tfedu.zhl.core.exception;

/**
 * 
 * 
 * @author wangwr
 * @date 2017年7月6日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 */
public class RepeatOperateException extends CustomException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RepeatOperateException() {
		super("RepeatOperateException", "当前用户正在进行一个重复操作");
	}

	public RepeatOperateException(String operName) {
		super("RepeatOperateException", "用户不能重复进行" + operName + "操作");
	}
}
