package job.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户角色更新
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-21
 */
@Data
@Accessors(chain = true)
public class UserRole {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "urid", type = IdType.AUTO)
    private Integer urid;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 角色id
     */
    private Integer rid;


}
