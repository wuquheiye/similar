package job.service;

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
}
