package swtech.pageDesignControl.enums.Flow;

import lombok.AllArgsConstructor;
import lombok.Getter;
import swtech.pageDesignControl.common.exception.ServiceException;
import swtech.pageDesignControl.enums.BaseEnum;

@AllArgsConstructor
@Getter
public enum  Fstatus implements BaseEnum<Integer> {
    UNTREATED(0,"未处理"),
    CHARGEPASS(1,"主管审批通过"),
    CHARGEREFUSE(2,"主管审批拒绝"),
    MANAGERPASS(3,"经理审批通过"),
    MANAGERREFUSE(4,"经理审批拒绝"),
    STAFFINGAFFIRM(5,"行政部门已确认"),
    STAFFINGAREFUSE(6,"行政部门拒绝"),
    FINANCE(7,"财务部门确认"),
    FINANCEREFUSE(8,"财务部门复核");
//    OVER(10,"结束");

    private Integer code;
    private String desc;

    public static String getDesc(Integer code) {
        for(Fstatus fstatus: Fstatus.values()) {
            if(fstatus.code.equals(code)) {
                return fstatus.getDesc();
            }
        }
        throw  new ServiceException("Sex  No matching constant for [" + code + "]");
    }
}
