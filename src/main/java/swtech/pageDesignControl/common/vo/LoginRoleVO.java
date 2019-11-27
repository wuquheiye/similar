package swtech.pageDesignControl.common.vo;

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
 * @since 2019-11-18
 */
@Data
@Accessors(chain = true)
public class LoginRoleVO {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "rid", type = IdType.AUTO)
    private Integer rid;

    /**
     * 角色名
     */
    private String rname;


}