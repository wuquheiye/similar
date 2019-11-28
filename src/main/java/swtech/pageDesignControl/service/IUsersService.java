package swtech.pageDesignControl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import swtech.pageDesignControl.common.vo.UsersVO;
import swtech.pageDesignControl.common.vo.LoginVO;
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
     * 获取用户登录缓存信息
     *
     * @param uusername
     * @return
     */
    LoginVO getLoginVO(String uusername);

    /**
     * 分页查询
     *
     * @param users
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<UsersVO> selectByPageAndCondition(Users users, int pageStart, int pageSize);

    /**
     * 查询个数
     *
     * @return
     */
    int selectCount();

    /**
     * 更改用户状态
     *
     * @param uid
     * @param ustate
     * @return
     */
    boolean updateUustate(int uid, int ustate);
}
