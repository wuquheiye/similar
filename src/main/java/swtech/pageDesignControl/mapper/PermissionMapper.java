package swtech.pageDesignControl.mapper;

import org.apache.ibatis.annotations.Param;
import swtech.pageDesignControl.common.vo.PermissionVo;
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
     * 根据角色查询权限
     *
     * @param roleName
     * @return
     */
    List<String> getPermissionyRoleName(String roleName);

    /**
     * 查询所有的父类
     *
     * @return
     */
    List<PermissionVo> selecGtrandfather();

    /**
     * 根据父类id查询子类
     */
    List<PermissionVo> selectChild(int ppid);
}
