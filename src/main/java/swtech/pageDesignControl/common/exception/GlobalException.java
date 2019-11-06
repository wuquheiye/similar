package swtech.pageDesignControl.common.exception;

import swtech.pageDesignControl.common.vo.CodeMsg;

public class GlobalException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private CodeMsg codeMsg;

    public GlobalException(CodeMsg codeMsg) {
        super(codeMsg.toString());
        this.codeMsg = codeMsg;
    }

    public GlobalException(CodeMsg codeMsg, Throwable e) {
        super(codeMsg.toString(), e);
        this.codeMsg = codeMsg;
    }

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }
}
