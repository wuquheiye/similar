package swtech.pageDesignControl.mapper;

import org.springframework.stereotype.Repository;
import swtech.pageDesignControl.entity.UserRole;
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
@Repository("userRoleMapper")
public interface UserRoleMapper {
    List<String> getRolesByUsername(String username);
}
