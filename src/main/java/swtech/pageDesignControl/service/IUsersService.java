package swtech.pageDesignControl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import swtech.pageDesignControl.common.vo.CompanyVO;
import swtech.pageDesignControl.common.vo.UsersVO;
import swtech.pageDesignControl.common.vo.LoginVO;
import swtech.pageDesignControl.entity.Permission;
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

    /**
     * 根据用户名获取所有的权限
     *
     * @param utelephonenumber
     * @return
     */
    List<Permission> getPermission(String utelephonenumber);

    /**
     * 根据角色id查询所有的用户
     *
     * @return
     */
    List<Users> selectUsersByRid();

    List<CompanyVO> getAllCompanyInformation();
}
