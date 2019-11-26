package swtech.pageDesignControl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-21
 */
@Data
@Accessors(chain = true)
public class RolePermission {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "rpid", type = IdType.AUTO)
    private Integer rpid;

    /**
     * 角色id
     */
    private Integer rid;

    /**
     * 权限id
     */
    private Integer pid;

    /**
     * 权限
     */
    private String ppermission;
}
