package swtech.pageDesignControl.mapper;

import org.apache.ibatis.annotations.Param;
import swtech.pageDesignControl.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-18
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    /**
     * 根据角色查询权限
     *
     * @param roleName
     * @return
     */
    List<String> getPermissionbyRoleName(String roleName);

    /**
     * 分页查询
     *
     * @param permission
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<Permission> selectByPageAndCondition(@Param("permission") Permission permission, @Param("pageStart") int pageStart, @Param("pageSize") int pageSize);

    /**
     * 查询个数
     *
     * @return
     */
    int selectCount();
}
