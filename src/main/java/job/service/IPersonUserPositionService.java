package job.service;

import job.entity.PersonUserPosition;
import com.baomidou.mybatisplus.extension.service.IService;
import job.vo.PersonUserPositionVO;
import job.vo.ReturnMsg;
import job.vo.ReturnMsgPage;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 李鸿智
 * @since 2020-01-06
 */
public interface IPersonUserPositionService extends IService<PersonUserPosition> {
    /**
     * 投递简历
     *
     * @param email
     * @param companyPositionId
     * @return
     */
    ReturnMsg save(String email, int companyPositionId);

    /**
     * 查询通过简历
     *
     * @param personUserId
     * @return
     */
    ReturnMsgPage findByPersonUserId(int personUserId, int companyPositionId, int state, int page, int pageSize);

    /**
     * 将简历状态改为不合适
     *
     * @param id
     * @param state
     * @return
     */
    ReturnMsg inappropriate(int id, int state);

    /**
     * 将简历状态改为邀请面试
     *
     * @param id
     * @param state
     * @return
     */
    ReturnMsg informInterview(int id, int state,String email);
}
