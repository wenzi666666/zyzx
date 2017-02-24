package net.tfedu.zhl.helper;

import java.io.Serializable;

import net.tfedu.zhl.core.exception.CustomException;

/**
 * 新的api标准 返回的json 对象
 * 
 * @author wangwr
 *
 *
 *         JSON格式： { "code":"ok", "message":"", "data":"", "sign":"57edf4a22be3c955ac49da2e2107b67a" }
 */
public class ResultJSON implements Serializable{
	

    /**
	 * 
	 */
	private static final long serialVersionUID = -2467433315357529553L;

	/**
     * 成功或失败的编码
     */
    String code;

    /**
     * 失败的信息
     */
    String message;

    /**
     * 成功后返回的数据
     */
    Object data;

    /**
     * 签名字符串
     */
    String sign;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public ResultJSON(){
        super();
    }
    
    public ResultJSON(String code, String message, Object data, String sign) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
        this.sign = sign;
    }

    
    /**
     * 返回成功
     * @param data
     * @return
     */
    public static ResultJSON getSuccess(Object data){
    	return new ResultJSON("OK", "成功", data==null?"":data, "");
    	
    }
    /**
     * 缺省错误处理
     * 
     * @param data
     * @param e
     * @return
     */
    public static ResultJSON defaultError(CustomException e ) {
    	ResultJSON result = new ResultJSON(e.getCode(), e.getMessage(), "", "");
        e.printStackTrace();
        return result;
    }

    
    
    
    @Override
    public String toString() {
//        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).toString();
        return "[code :" + code + "  ;message:" + message + ";data:" + data.toString() + ";sign:" + sign + "]";
    }

}
