package net.tfedu.zhl.core.exception;

public class NoAccessTokenException extends CustomException {
    /**
     * 
     */
    private static final long serialVersionUID = -5245657609530099014L;

    public NoAccessTokenException() {
        super("NoAccessToken", "缺少token令牌");
    }

}
