package swtech.pageDesignControl.mapper;

import swtech.pageDesignControl.entity.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-11-18
 */
public interface UsersMapper extends BaseMapper<Users> {
    // 通过用户名获取密码
    String findPasswordByUsername(String username);
}
