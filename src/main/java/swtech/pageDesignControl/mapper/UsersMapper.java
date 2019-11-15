package swtech.pageDesignControl.mapper;

import org.springframework.stereotype.Repository;
import swtech.pageDesignControl.entity.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-11-15
 */
@Repository("userDao")
public interface UsersMapper {
    // 通过用户名获取密码
    String findUserByUsername(String username);
}
