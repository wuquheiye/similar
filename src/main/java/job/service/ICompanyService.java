package job.service;

import job.vo.Company;
import job.vo.ReturnMsg;

public interface ICompanyService {
    /**
     * 录入公司信息
     *
     * @param company
     * @return
     */
    ReturnMsg save(Company company);
}
