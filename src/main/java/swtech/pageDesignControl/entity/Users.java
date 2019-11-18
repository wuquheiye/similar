package swtech.pageDesignControl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author jobob
 * @since 2019-11-15
 */
@Data
public class Users {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    /**
     * 用户名
     */
    private String uusername;

    /**
     * 密码
     */
    private String upassword;

    /**
     * 邀请码
     */
    private String uinvitationCode;

    /**
     * 部门id
     */
    private Integer did;


}
