package job.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-21
 */
@Data
@Accessors(chain = true)
public class User {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    private String verificationCode;

    /**
     * 电话号码
     */
    private String telephonenumber;

    /**
     * 创建时间
     */
    private String creationtime;

    /**
     * 用户状态
     */
    private String state;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户余额
     */
    private String money;
}
