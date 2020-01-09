package job.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import job.entity.PersonUser;
import job.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-18
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 通过邮箱获取信息shiro
     *
     * @param email
     * @return
     */
    User findUserByEmail(String email);

    /**
     * 根据用户id查询对应的简历
     *
     * @param id
     * @return
     */
    PersonUser getPersonUser(Integer id);

    /**
     * 分页查询
     *
     * @param user
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<User> selectByPageAndCondition(@Param("user") User user, @Param("pageStart") int pageStart, @Param("pageSize") int pageSize);

    /**
     * 查询个数
     *
     * @return
     */
    int selectCount(@Param("user") User user);

    /**
     * 更改用户状态
     *
     * @param id
     * @param state
     * @return
     */
    int updateState(@Param("id") int id, @Param("state") int state);

}
