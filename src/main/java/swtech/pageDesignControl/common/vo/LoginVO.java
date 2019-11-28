package swtech.pageDesignControl.common.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import swtech.pageDesignControl.entity.Department;
import swtech.pageDesignControl.entity.Role;

/**
 * <p>
 * 用户缓存信息
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-18
 */
@Data
@Accessors(chain = true)
public class LoginVO {

    private static final long serialVersionUID = 1L;

    /**
     * 用户部门
     */
    private Department department;

    /**
     * 用户角色
     */
    private Role role;

    /**
     * 用户信息
     *
     */
    private LoginUsersVO users;
}