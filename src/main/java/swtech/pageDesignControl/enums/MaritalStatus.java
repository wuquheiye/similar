package swtech.pageDesignControl.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import swtech.pageDesignControl.common.exception.ServiceException;

@Getter
@AllArgsConstructor
public enum MaritalStatus implements BaseEnum<Integer>{
    SINGLE(0,"已婚"),
    UNMARRIED(1,"未婚")
    ;
    private Integer code;
    private String desc;

    public static String getDesc(Integer code) {
        for(MaritalStatus MS: MaritalStatus.values()) {
            if(MS.code.equals(code)) {
                return MS.getDesc()+","+MS.getCode();
            }
        }
       throw  new ServiceException("MaritalStatus  No matching constant for [" + code + "]");
    }

    public static void main(String[] args) {
        System.out.println(getDesc(1));
    }
}
