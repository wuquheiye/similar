package swtech.pageDesignControl.common.utils;

import swtech.pageDesignControl.common.exception.EnumNotFindException;
import swtech.pageDesignControl.common.vo.CodeMsg;
import swtech.pageDesignControl.enums.BaseEnum;
import swtech.pageDesignControl.enums.MaritalStatus;

public class EnumUtil {
    /**
     * 根据枚举类的code属性，获取对应的枚举类
     * @param code
     * @param enumClass
     * @param <T>
     * @return
     */
    public static <T extends BaseEnum> T getByCode(Object code, Class<T> enumClass){
        for (T e : enumClass.getEnumConstants()){
            if (e.getCode().equals(code)){
                return e;
            }
        }
        throw new EnumNotFindException(CodeMsg.ENUM_NOT_FIND_ERROR.msg("没有找到对应的枚举！code: " + code));
    }

    public static void main(String[] args) {
        MaritalStatus maritalStatus = org.apache.commons.lang3.EnumUtils.getEnum(MaritalStatus.class, "UNMARRIED");
        System.out.println(maritalStatus);

        getByCode(1, MaritalStatus.class);
        System.out.println(getByCode(1, MaritalStatus.class).getDesc());
    }
}
