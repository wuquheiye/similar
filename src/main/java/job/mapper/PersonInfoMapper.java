package job.mapper;

import job.entity.PersonInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 李鸿智
 * @since 2019-12-20
 */
public interface PersonInfoMapper extends BaseMapper<PersonInfo> {

    /**
     * 根据简历ID查询个人信息
     *
     * @param personUserId
     * @return
     */
    PersonInfo selectByPersonUserId(Integer personUserId);

}
