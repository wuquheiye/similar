package job.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 个人信息
 * </p>
 *
 * @author 李鸿智
 * @since 2019-12-20
 */
@Data
@Accessors(chain = true)
public class PersonInfo  {

    private static final long serialVersionUID = 1L;

    /**
     * 姓名
     */
    private String name;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 0:男，1：女
     */
    private Integer sex;

    /**
     * 婚姻状态
     */
    private String maritalStatus;

    /**
     * 政治面貌
     */
    private String politicsStatus;

    /**
     * 海外经历
     */
    private Integer overseasExperience;

    /**
     * 出生日期
     */
    private String birthday;

    /**
     * 参加工作时间
     */
    private String workTime;

    /**
     * 户口所在地市
     */
    private String householdCity;

    /**
     * 户口所在地省
     */
    private String householdProvince;

    /**
     * 现居住城市省
     */
    private String currentProvince;

    /**
     * 现居住城市市
     */
    private String currentCity;

    /**
     * 现居住城市县
     */
    private String currentCounty;

    /**
     * 关联user
     */
    private Integer userId;

    /**
     * 电子邮箱
     */
    private String email;


}
