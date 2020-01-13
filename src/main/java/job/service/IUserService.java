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
     * 注册
     *
     * @param user
     * @param roleId
     * @return
     */
    boolean regist(User user,int roleId);

    /**
     * 修改密码
     *
     * @param user
     * @return
     */
    int forgetpassword(User user);

    /**
     * 通过邮箱获取信息
     *
     * @param telephonenumber
     * @return
     */
    User findUserByTelephonenumber(String telephonenumber);

    /**
     * 根据邮箱获取用户登录缓存信息
     *
     * @param telephonenumber
     * @return
     */
    LoginVO getLoginVO(String telephonenumber);

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
    int selectCount(User user);

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
     * @param telephonenumber
     * @return
     */
    List<Permission> getPermission(String telephonenumber);

    /**
     * 发送邮箱验证码(待用)
     *
     * @param email
     * @return
     */
    String sendEmail(String email);

    /**
     * 发送手机验证码
     *
     * @param telephonenumber
     * @return
     */
    String sendTelephonenumberVerificationCode(String telephonenumber) throws Exception;
}
