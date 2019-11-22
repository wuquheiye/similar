package swtech.pageDesignControl.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import swtech.pageDesignControl.common.exception.ServiceException;

@AllArgsConstructor
@Getter
public enum Judge implements BaseEnum<Integer>{
    YES(0,"YES"),
    NO(1,"NO");

    private Integer code;
    private String desc;

    public static String getDesc(Integer code) {
        for(Judge judge : Judge.values()) {
            if(judge.code.equals(code)) {
                return judge.getDesc();
            }
        }
        throw  new ServiceException("judge  No matching constant for [" + code + "]");
    }
}
