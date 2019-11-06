package swtech.pageDesignControl.common.vo;

public class CodeMsg {
    private int code;
    private String msg;



    public static CodeMsg ENUM_NOT_FIND_ERROR = new CodeMsg(500701, "没有找到对应的枚举！");

    private CodeMsg(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    private CodeMsg(int code) {
        this.code = code;
    }

    public CodeMsg msg(String msg) {
        this.msg = msg;
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "CodeMsg [code=" + code + ", msg=" + msg + "]";
    }

}
