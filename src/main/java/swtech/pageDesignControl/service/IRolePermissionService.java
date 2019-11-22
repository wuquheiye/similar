package swtech.pageDesignControl.service;

import swtech.pageDesignControl.entity.RolePermission;
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
}
