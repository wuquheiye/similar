package job.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
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
     * 学校名称
     */
    private String school;

    /**
     * 就读时间
     */
    private String studyingTime;

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


}
