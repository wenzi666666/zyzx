package net.tfedu.zhl.core.exception;

public class WithoutUserException extends CustomException {
    /**
     * 
     */
    private static final long serialVersionUID = -5245657609530099014L;

    public WithoutUserException() {
        super("WithoutUser", "用户没有权限");
    }
}
