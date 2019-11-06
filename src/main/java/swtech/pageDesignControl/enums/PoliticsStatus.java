package swtech.pageDesignControl.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import swtech.pageDesignControl.common.exception.ServiceException;

@AllArgsConstructor
@Getter
public enum PoliticsStatus implements BaseEnum<Integer> {
    PARTTYMEMBER(1,"党员"),
    MEMBER(2,"团员"),
    MASSES(3,"群众"),
    DEMOCRATS(4,"民主党"),
    NONPARTY(5,"无党派"),
    ;
    private Integer code;
    private String desc;


    public static String getDesc(Integer code) {
        for(PoliticsStatus PS: PoliticsStatus.values()) {
            if(PS.code.equals(code)) {
                return PS.getDesc();
            }
        }
        throw  new ServiceException("PoliticsStatus  No matching constant for [" + code + "]");
    }
    @AllArgsConstructor
    @Getter
    public enum OverseasExperience {
        YES(0,"yes"),
        NO(1,"no"),
        ;
        private Integer code;
        private String desc;

        public static String getDesc(Integer code) {
            for(OverseasExperience OE: OverseasExperience.values()) {
                if(OE.code.equals(code)) {
                    return OE.getDesc();
                }
            }
            throw  new ServiceException("OverseasExperience  No matching constant for [" + code + "]");
        }
    }


}

