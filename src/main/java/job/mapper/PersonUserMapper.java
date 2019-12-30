package job.mapper;

import job.entity.PersonUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import job.vo.PersonVO;

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
     * 插入简历
     *
     * @param personUser
     * @return
     */
    int insert(PersonUser personUser);
}
