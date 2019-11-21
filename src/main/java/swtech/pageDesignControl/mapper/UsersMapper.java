package swtech.pageDesignControl.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.apache.ibatis.annotations.Param;
import swtech.pageDesignControl.entity.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-18
 */
public interface UsersMapper extends BaseMapper<Users> {
    // 通过用户名获取密码
    String findPasswordByUsername(String username);

    @Override
    List<Map<String, Object>> selectMaps(Wrapper<Users> queryWrapper);

    /**
     * 分页查询
     *
     * @param users
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<Users> selectByPageAndCondition(@Param("users") Users users, @Param("pageStart") int pageStart, @Param("pageSize") int pageSize);

    /**
     * 查询个数
     *
     * @return
     */
    int selectCount();
}
