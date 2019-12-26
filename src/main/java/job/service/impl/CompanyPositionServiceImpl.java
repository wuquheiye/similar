package job.service.impl;

import job.entity.CompanyPosition;
import job.mapper.CompanyInfoMapper;
import job.mapper.CompanyPositionMapper;
import job.service.ICompanyPositionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import job.vo.CompanyPositionVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 李鸿智
 * @since 2019-12-26
 */
@Service
public class CompanyPositionServiceImpl extends ServiceImpl<CompanyPositionMapper, CompanyPosition> implements ICompanyPositionService {

    @Resource
    private CompanyPositionMapper companyPositionMapper;

    @Resource
    private CompanyInfoMapper companyInfoMapper;

    @Transactional
    @Override
    public List<CompanyPositionVO> selectCompanyPositionByCondition(CompanyPositionVO companyPositionVOCondition, int page, int pageSize) {
        int pageStart = (page - 1) * pageSize;
        // 1.通过查询条件查出所有满足条件的职位
        List<CompanyPosition> companyPositionList = companyPositionMapper.selectCompanyPositionByCondition(companyPositionVOCondition, pageStart, pageSize);
        List<CompanyPositionVO> companyPositionVOList = new ArrayList<>();
        CompanyPositionVO companyPositionVO = new CompanyPositionVO();
        // 2.循环遍历所有的职位，查出它所属公司
        for (CompanyPosition companyPosition : companyPositionList) {
            if (companyPosition != null) {
                companyPositionVO.setCompanyPosition(companyPosition);
                companyPositionVO.setCompanyInfo(companyInfoMapper.selectById(companyPosition.getCompanyInfoId()));
            }
            // 3.包装
            companyPositionVOList.add(companyPositionVO);
        }
        return companyPositionVOList;
    }

    @Override
    public int selectCount(CompanyPositionVO companyPositionVO) {
        return companyPositionMapper.selectCount(companyPositionVO);
    }

}
