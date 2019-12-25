package job.mapper;

import job.entity.PersonJobWanted;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 李鸿智
 * @since 2019-12-20
 */
public interface PersonJobWantedMapper extends BaseMapper<PersonJobWanted> {

    /**
     * 根据简历ID查询个人工作期望
     *
     * @param personUserId
     * @return
     */
    PersonJobWanted selectByPersonUserId(Integer personUserId);

}
