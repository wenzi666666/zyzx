package net.tfedu.zhl.core.exception;

public class WithoutAuthorizationException extends CustomException {
    /**
     * 
     */
    private static final long serialVersionUID = -5245657609530099014L;

    public WithoutAuthorizationException() {
        super("WithoutAuthorization", "用户不存在");
    }
    public WithoutAuthorizationException(String userName) {
        super("WithoutAuthorization", "用户"+userName+"不存在");
    }

}
