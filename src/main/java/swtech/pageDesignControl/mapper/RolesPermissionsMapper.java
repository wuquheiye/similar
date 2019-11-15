package swtech.pageDesignControl.mapper;

import org.springframework.stereotype.Repository;
import swtech.pageDesignControl.entity.RolesPermissions;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-11-15
 */
@Repository("rolesPermissionsMapper")
public interface RolesPermissionsMapper{
    List<String> getPermissionbyRoleName(String roleName);
}
