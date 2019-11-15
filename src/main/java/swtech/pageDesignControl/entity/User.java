package swtech.pageDesignControl.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author 袁君选
 * @since 2019-10-24
 */
@Data
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer Id;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 登陆密码//可以为空
     */
    private String password;

    /**
     * 用户角色
     */
    private Integer userRole;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 身份证号
     */
    private String userCard;

    /**
     * 智联币
     */
    private  String userZLB;

    public User(String phone, String password, Integer userRole) {
        this.phone = phone;
        this.password = password;
        this.userRole = userRole;
    }

}
