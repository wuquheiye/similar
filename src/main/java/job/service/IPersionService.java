package job.service;

import job.entity.User;
import job.vo.PersonVO;
import job.vo.ReturnMsg;

public interface IPersionService {
    /**
     * 录入个人信息
     *
     * @param personVO
     * @return
     */
    ReturnMsg save(PersonVO personVO);

    /**
     * 获取个人信息
     *
     * @param user
     * @return
     */
    ReturnMsg getPserson(User user);
}
