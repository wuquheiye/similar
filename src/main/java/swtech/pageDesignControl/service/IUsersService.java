package swtech.pageDesignControl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import swtech.pageDesignControl.entity.Users;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-15
 */
public interface IUsersService extends IService<Users> {

    /**
     * 查询单个
     *
     * @param uid
     * @return
     */
    Users selectById(int uid);

    /**
     * 分页查询
     *
     * @param users
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<Users> selectByPageAndCondition(Users users, int pageStart, int pageSize);

    /**
     * 查询个数
     *
     * @return
     */
    int selectCount();
}
