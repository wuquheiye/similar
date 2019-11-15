package swtech.pageDesignControl.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author jobob
 * @since 2019-11-15
 */
@Data
public class Users {
    // 姓名
    private String username;

    // 密码
    private String password;

}
