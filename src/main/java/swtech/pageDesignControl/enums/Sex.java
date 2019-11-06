package swtech.pageDesignControl.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import swtech.pageDesignControl.common.exception.ServiceException;

@AllArgsConstructor
@Getter
public enum Sex implements BaseEnum<Integer>{
    MAN(0,"男"),
    GIRL(1,"女");

    private Integer code;
    private String desc;

    public static String getDesc(Integer code) {
        for(Sex sex: Sex.values()) {
            if(sex.code.equals(code)) {
                return sex.getDesc();
            }
        }
        throw  new ServiceException("Sex  No matching constant for [" + code + "]");
    }
}
