package job.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 个人期望工作
 * </p>
 *
 * @author 李鸿智
 * @since 2019-12-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PersonJobWanted implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 工作性质
     */
    private String nature;

    /**
     * 期望地点省
     */
    private String placeProvince;

    /**
     * 期望地点市
     */
    private String placeCity;

    /**
     * 期望地点县
     */
    private String placeCounty;

    /**
     * 期望行业
     */
    private String bank;

    /**
     * 期望月薪
     */
    private String monthSalary;

    /**
     * 求职状态
     */
    private String jobStatus;

    /**
     * 关联用户ID
     */
    private Integer userId;

    /**
     * 关联用户phone
     */
    private String userPhone;

    /**
     * 期望职业
     */
    private String profession;


}
