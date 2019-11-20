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
public class Project  {

    private static final long serialVersionUID = 1L;

    /**
     * 项目表主键
     */
    @TableId(value = "pid", type = IdType.AUTO)
    private Integer pid;

    /**
     * 关联用户id
     */
    private Integer uid;

    /**
     * 项目名
     */
    private String pname;

    /**
     * 项目创建时间
     */
    private String pstartTime;

    /**
     * 项目结束时间
     */
    private String pendTime;

    /**
     * 项目团队
     */
    private String pteam;

    /**
     * 关联进度更新id 备用
     */
    private Integer upId;


}
