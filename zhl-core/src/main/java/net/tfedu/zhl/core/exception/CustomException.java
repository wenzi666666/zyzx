package net.tfedu.zhl.core.exception;

/**
 * 服务基础异常
 * 
 * @author bruce
 *
 */
public class CustomException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -2264593730959108392L;
    
    protected String code;

    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CustomException() {
        super("service层异常");
    }

    public CustomException(String msg) {
        super(msg);
    }

    public CustomException(Exception e) {
        super("service层异常", e);
    }

    public CustomException(String msg, Exception e) {
        super(msg, e);
    }

    public CustomException(String code, String msg) {
        super(msg);
        setCode(code);
    }
}
