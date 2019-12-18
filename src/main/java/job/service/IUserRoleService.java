package job.service;

import job.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-21
 */
public interface IUserRoleService extends IService<UserRole> {

    /**
     * 根据用户查询角色
     *
     * @param uid
     * @return
     */
    List<String> getRoleByUserId(int uid);

    /**
     * 根据用户id删除用户角色关系
     *
     * @param uid
     * @return
     */
    int deleteByUserId(int uid);

    /**
     * 根据角色id删除用户角色关系
     *
     * @param rid
     * @return
     */
    int deleteByRoleId(int rid);
}
