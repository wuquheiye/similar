package swtech.pageDesignControl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import swtech.pageDesignControl.entity.Role;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-18
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 通过用户名查询角色shiro
     *
     * @param username
     * @return
     */
    Role getRoleByUsername(String username);

    /**
     * 分页查询
     *
     * @param role
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<Role> selectByPageAndCondition(@Param("role") Role role, @Param("pageStart") int pageStart, @Param("pageSize") int pageSize);

    /**
     * 查询个数
     *
     * @return
     */
    int selectCount();
}
