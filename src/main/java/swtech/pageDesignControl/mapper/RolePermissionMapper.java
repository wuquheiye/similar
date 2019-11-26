package swtech.pageDesignControl.mapper;

import swtech.pageDesignControl.entity.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-21
 */
public interface RolePermissionMapper extends BaseMapper<RolePermission> {
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
