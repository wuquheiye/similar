package swtech.pageDesignControl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 袁君选
 * @since 2019-11-18
 */
@Data
@Accessors(chain = true)
public class UpdateProject  {

    private static final long serialVersionUID = 1L;

    /**
     * 项目进度主键
     */
    @TableId(value = "up_id", type = IdType.AUTO)
    private Integer upId;

    /**
     * 绑定项目id
     */
    private Integer pid;

    /**
     * 项目进度
     */
    private Integer upProgress;

    /**
     * 项目描述
     */
    private String upDescribe;

    /**
     * 更新时间
     */
    private String upTime;

    /**
     * 项目名
     */
    private String pname;


}
