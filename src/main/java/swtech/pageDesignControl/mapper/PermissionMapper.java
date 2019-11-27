package swtech.pageDesignControl.mapper;

import swtech.pageDesignControl.common.vo.PermissionVO;
import swtech.pageDesignControl.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-18
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    /**
     * 根据角色查询权限shiro
     *
     * @param roleName
     * @return
     */
    List<Permission> getPermissionByRoleName(String roleName);

    /**
     * 查询所有的父类
     *
     * @return
     */
    List<PermissionVO> selecGtrandfather();

    /**
     * 根据父类id查询子类
     */
    List<PermissionVO> selectChild(int ppid);
}
