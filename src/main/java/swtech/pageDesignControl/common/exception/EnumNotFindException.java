package swtech.pageDesignControl.common.exception;

import swtech.pageDesignControl.common.vo.CodeMsg;

public class EnumNotFindException extends GlobalException{
    public EnumNotFindException(CodeMsg codeMsg) {
        super(codeMsg);
    }

    public EnumNotFindException(CodeMsg codeMsg, Throwable e) {
        super(codeMsg, e);
    }
}
