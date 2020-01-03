package job.service;

import job.entity.CompanyInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 李鸿智
 * @since 2019-12-23
 */
public interface ICompanyInfoService extends IService<CompanyInfo> {
    /**
     * 根据条件查询公司
     *
     * @param companyInfo
     * @return
     */
    List<CompanyInfo> selectCompanyInfoByCondition(
            CompanyInfo companyInfo, int page, int pageSize);

    /**
     * 根据条件查询公司
     *
     * @return
     */
    int selectCount(CompanyInfo companyInfo);

}
