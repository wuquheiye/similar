package job.service;

import com.baomidou.mybatisplus.extension.service.IService;
import job.entity.Permission;
import job.entity.User;
import job.vo.LoginVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-15
 */
public interface IUserService extends IService<User> {

    /**
     * 通过邮箱获取信息
     *
     * @param email
     * @return
     */
    User findUserByEmail(String email);

    /**
     * 根据邮箱获取用户登录缓存信息
     *
     * @param email
     * @return
     */
    LoginVO getLoginVO(String email);

    /**
     * 分页查询
     *
     * @param user
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<User> selectByPageAndCondition(User user, int pageStart, int pageSize);

    /**
     * 查询个数
     *
     * @return
     */
    int selectCount();

    /**
     * 更改用户状态
     *
     * @param id
     * @param state
     * @return
     */
    boolean updateState(int id, int state);

    /**
     * 根据用邮箱获取所有的权限
     *
     * @param email
     * @return
     */
    List<Permission> getPermission(String email);

}