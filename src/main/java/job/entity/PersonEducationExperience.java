package job.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 个人教育经验
 * </p>
 *
 * @author 李鸿智
 * @since 2019-12-20
 */
@Data
@Accessors(chain = true)
public class PersonEducationExperience  {

    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 学校名称
     */
    private String school;

    /**
     * 就读开始时间
     */
    private String studyingTimeStart;

    /**
     * 就读结束时间
     */
    private String studyingTimeEnd;

    /**
     * 所学专业
     */
    private String major;

    /**
     * 学历
     */
    private String education;

    /**
     * 是否统招 0:否 1:是
     */
    private Integer recruitment;

    /**
     * 所属简历
     */
    private Integer personUserId;
}
