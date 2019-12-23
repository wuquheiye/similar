package job.service;

import job.vo.Person;
import job.vo.ReturnMsg;

public interface IPersionService {
    /**
     * 录入个人信息
     *
     * @param person
     * @return
     */
    ReturnMsg save(Person person);
}
