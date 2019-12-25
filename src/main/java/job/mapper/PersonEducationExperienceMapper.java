package job.mapper;

import job.entity.PersonEducationExperience;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 李鸿智
 * @since 2019-12-20
 */
public interface PersonEducationExperienceMapper extends BaseMapper<PersonEducationExperience> {

    /**
     * 根据简历ID查询个人教育经验
     *
     * @param personUserId
     * @return
     */
    PersonEducationExperience selectByPersonUserId(Integer personUserId);

}
