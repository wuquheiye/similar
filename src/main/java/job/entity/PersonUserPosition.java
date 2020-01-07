package job.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import job.utils.DateUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 李鸿智
 * @since 2020-01-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PersonUserPosition implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 简历id
     */
    private Integer personUserId;

    /**
     * 职位id
     */
    private Integer companyPositionId;

    /**
     * 创建时间
     */
    private String createTime = DateUtil.getNewDate();

    /**
     * 更改时间
     */
    private String updateTime = DateUtil.getNewDate();

    /**
     * 状态（1.投递成功，2.邀请面试，3.不合适）
     */
    private int state = 1;


}
