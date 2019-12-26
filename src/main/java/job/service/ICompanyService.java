package job.service;

import job.entity.User;
import job.vo.CompanyVO;
import job.vo.ReturnMsg;

public interface ICompanyService {
    /**
     * 录入公司信息
     *
     * @param companyVO
     * @return
     */
    ReturnMsg save(CompanyVO companyVO);

    /**
     * 获取公司信息
     *
     * @param user
     * @return
     */
    ReturnMsg getCompany(User user);
}
