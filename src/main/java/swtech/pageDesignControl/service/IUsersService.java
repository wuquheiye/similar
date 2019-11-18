package swtech.pageDesignControl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import swtech.pageDesignControl.entity.Users;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2019-11-15
 */
public interface IUsersService extends IService<Users> {
    int insert(Users users);
}
