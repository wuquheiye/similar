package job.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 个人工作经验
 * </p>
 *
 * @author 李鸿智
 * @since 2019-12-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PersonWorkExperience implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 公司名称
     */
    private String company;

    /**
     * 所属行业
     */
    private String industry;

    /**
     * 职位名称
     */
    private String position;

    /**
     * 税前月薪
     */
    private String monthlySalary;

    /**
     * 工作描述
     */
    private String taskDescription;

    /**
     * 在职开始时间
     */
    private String workTimeStart;

    /**
     * 在职结束时间
     */
    private String workTimeEnd;

    /**
     * 所属简历
     */
    private Integer personUserId;


}
