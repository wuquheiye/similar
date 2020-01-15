package job.mapper;

import job.entity.PersonUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 李鸿智
 * @since 2019-12-24
 */
public interface PersonUserMapper extends BaseMapper<PersonUser> {

    /**
     * 根据用户id查询对应的简历
     *
     * @param id
     * @return
     */
    PersonUser getPersonUser(Integer id);

    /**
     * 插入简历
     *
     * @param personUser
     * @return
     */
    int insert(PersonUser personUser);

    /**
     * 查询所有简历
     *
     * @return
     */
    List<PersonUser> findAll( @Param("pageStart") Integer pageStart, @Param("pageSize") Integer pageSize);

    /**
     * 查询所有简历个数
     *
     * @return
     */
    int selectCount();

    /**
     * 根据条件查询简历
     *
     * @param personUser
     * @return
     */
    List<PersonUser> selectPersonUserByCondition(
            @Param("personUser")PersonUser personUser, @Param("pageStart") int pageStart, @Param("pageSize") int pageSize);

    /**
     * 根据条件查询简历
     *
     * @return
     */
    int selectCount(@Param("personUser")PersonUser personUser);
}
