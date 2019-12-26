package job.service;

import job.entity.CompanyPosition;
import com.baomidou.mybatisplus.extension.service.IService;
import job.vo.CompanyPositionVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 李鸿智
 * @since 2019-12-26
 */
public interface ICompanyPositionService extends IService<CompanyPosition> {

    /**
     * 查询职位
     *
     * @param companyPositionVOCondition
     * @return
     */
    public List<CompanyPositionVO> selectCompanyPositionByCondition(CompanyPositionVO companyPositionVOCondition, int page, int pageSize);

    /**
     * 查询个数
     *
     * @return
     */
    int selectCount(CompanyPositionVO companyPositionVO);
}
