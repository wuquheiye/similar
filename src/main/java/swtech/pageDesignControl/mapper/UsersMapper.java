package swtech.pageDesignControl.mapper;

import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-11-15
 */
@Repository("usersDao")
public interface UsersMapper {
    // 通过用户名获取密码
    String findPasswordByUsername(String username);
}
