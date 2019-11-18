package swtech.pageDesignControl.mapper;

import swtech.pageDesignControl.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-11-18
 */
public interface RoleMapper extends BaseMapper<Role> {
    List<String> getRoleByUsername(String username);
}
