package swtech.pageDesignControl.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import swtech.pageDesignControl.common.exception.ServiceException;

@AllArgsConstructor
@Getter
public enum Role implements BaseEnum<Integer>{
    LJGM(7,"利捷总经理"),
    GM(6,"总经理"),
    FINANCE(5,"财务"),
    MANAGE(4,"经理"),
    GOVERNOR(3,"主管"),
    ADMINISTRATIVE(1,"人事"),
    EMPLOYEES(2,"职工");

    private Integer code;
    private String desc;
    public static String getDesc(Integer code) {
        for(Role role: Role.values()) {
            if(role.code.equals(code)) {
                return role.getDesc();
            }
        }
        throw  new ServiceException("Sex  No matching constant for [" + code + "]");
    }

    public static Role getByCode(Integer value){
        for( Role  role : values()){
            if (role.getCode() == value) {
                return role;
            }
        }
        return null;
    }

}
