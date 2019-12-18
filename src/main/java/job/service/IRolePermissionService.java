package job.service;

import job.entity.RolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-21
 */
public interface IRolePermissionService extends IService<RolePermission> {
    /**
     * 根据角色查询权限
     *
     * @param rid
     * @return
     */
    List<String> getPermissionByRoleId(int rid);

    /**
     * 插入列表
     *
     * @param rolePermissionList
     * @return
     */
    int insertList(List<RolePermission> rolePermissionList);

    /**
     * 根据角色id删除角色权限关系
     *
     * @param rid
     * @return
     */
    int deleteByRoleId(int rid);

    /**
     * 根据权限id删除角色权限关系
     *
     * @param pid
     * @return
     */
    int deleteByPermissionId(int pid);

}
