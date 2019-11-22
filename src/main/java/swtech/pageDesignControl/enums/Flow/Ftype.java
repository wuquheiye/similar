package swtech.pageDesignControl.enums.Flow;

import lombok.AllArgsConstructor;
import lombok.Getter;
import swtech.pageDesignControl.common.exception.ServiceException;
import swtech.pageDesignControl.enums.BaseEnum;

@AllArgsConstructor
@Getter
public enum Ftype implements BaseEnum<Integer> {
    LEAVE(1, "请假申请"),
    OVERTIME(2, "加班申请"),
    SERVE(3,"招待申请"),
    GOOUT(4,"外出申请"),
    ONBUSINESS(5,"出差申请"),
    SEALUSE(6,"印章使用申请"),
    FINANCEPAY(7,"财务付款申请");

    private Integer code;
    private String desc;

    public static String getDesc(Integer code) {
        for (Ftype role : Ftype.values()) {
            if (role.code.equals(code)) {
                return role.getDesc();
            }
        }
        throw new ServiceException("ftype  No matching constant for [" + code + "]");
    }
    }
