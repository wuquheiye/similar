package job.service.impl;

import job.entity.CompanyInfo;
import job.mapper.CompanyInfoMapper;
import job.service.ICompanyInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 李鸿智
 * @since 2019-12-23
 */
@Service
public class CompanyInfoServiceImpl extends ServiceImpl<CompanyInfoMapper, CompanyInfo> implements ICompanyInfoService {

    @Resource
    private CompanyInfoMapper companyInfoMapper;

    @Override
    public List<CompanyInfo> selectCompanyInfoByCondition(CompanyInfo companyInfo, int page, int pageSize) {
        int pageStart = (page - 1) * pageSize;
        return companyInfoMapper.selectCompanyInfoByCondition(companyInfo,pageStart,pageSize);
    }

    @Override
    public int selectCount(CompanyInfo companyInfo) {
        return companyInfoMapper.selectCount(companyInfo);
    }
}
