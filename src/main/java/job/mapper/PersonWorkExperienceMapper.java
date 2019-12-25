package job.mapper;

import job.entity.PersonWorkExperience;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 李鸿智
 * @since 2019-12-20
 */
public interface PersonWorkExperienceMapper extends BaseMapper<PersonWorkExperience> {

    /**
     * 根据简历ID查询个人工作经验
     *
     * @param personUserId
     * @return
     */
    PersonWorkExperience selectByPersonUserId(Integer personUserId);
}
