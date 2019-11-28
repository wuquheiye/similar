package swtech.pageDesignControl.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import swtech.pageDesignControl.common.exception.ServiceException;

@AllArgsConstructor
@Getter
public enum Role implements BaseEnum<Integer>{
    MANAGE(1,"经理"),
    GOVERNOR(2,"主管"),
    ADMINISTRATIVE(3,"人事"),
    EMPLOYEES(4,"职工");

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
