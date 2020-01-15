package job.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 0:男，1：女
     */
    private Integer sex;

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
     * 电子邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String portrait;

    /**
     * 是否应届生（0，否，1.，是）
     */
    private int freshGraduate;
    /**
     * 所属简历
     */
    private Integer personUserId;


}
