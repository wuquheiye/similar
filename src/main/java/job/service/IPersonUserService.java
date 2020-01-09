package job.service;

import job.entity.PersonUser;
import com.baomidou.mybatisplus.extension.service.IService;
import job.vo.ReturnMsg;
import job.vo.ReturnMsgPage;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 李鸿智
 * @since 2019-12-24
 */
public interface IPersonUserService extends IService<PersonUser> {
    /**
     * 查询所有简历
     *
     * @return
     */
    ReturnMsgPage findAll(Integer page, Integer size);

    /**
     * 根据条件查询简历
     *
     * @param personUser
     * @return
     */
    ReturnMsgPage selectPersonUserByCondition(PersonUser personUser, int page, int pageSize);

    /**
     * 根据条件查询简历
     *
     * @return
     */
    int selectCount(PersonUser personUser);
}
