package job.mapper;

import job.entity.PersonUserPosition;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 李鸿智
 * @since 2020-01-06
 */
public interface PersonUserPositionMapper extends BaseMapper<PersonUserPosition> {
    /**
     * 查询是否投递简历
     *
     * @param personUserId
     * @param companyPositionId
     * @return
     */
    PersonUserPosition findByPersonUserIdAndCompanyPositionId(@Param("personUserId") int personUserId, @Param("companyPositionId") int companyPositionId);

    /**
     * 更改投递了简历状态
     *
     * @param state
     * @param id
     * @return
     */
    boolean updateState(@Param("id")int id,@Param("state")int state);

    /**
     * 查询通过简历
     *
     * @param personUserId
     * @param state
     * @return
     */
    List<PersonUserPosition> findByPersonUserIdAndState
        (@Param("personUserId") Integer personUserId, @Param("companyPositionId") Integer companyPositionId,
         @Param("state") Integer state, @Param("pageStart") Integer pageStart, @Param("pageSize") Integer pageSize);

    /**
     * 查询个数
     *
     * @return
     */
    int selectCount(@Param("personUserId") int personUserId, @Param("companyPositionId") Integer companyPositionId, @Param("state") int state);
}
