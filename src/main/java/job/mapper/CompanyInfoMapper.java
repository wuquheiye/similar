package job.mapper;

import job.entity.CompanyInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 李鸿智
 * @since 2019-12-23
 */
public interface CompanyInfoMapper extends BaseMapper<CompanyInfo> {

    /**
     * 通过用户ID查询公司信息
     *
     * @param userId
     * @return
     */
    CompanyInfo findByUserId(Integer userId);

    /**
     * 根据条件查询公司
     *
     * @param companyInfo
     * @return
     */
    List<CompanyInfo> selectCompanyInfoByCondition(
            @Param("companyInfo")CompanyInfo companyInfo, @Param("pageStart") int pageStart, @Param("pageSize") int pageSize);

    /**
     * 根据条件查询公司
     *
     * @return
     */
    int selectCount(@Param("companyInfo")CompanyInfo companyInfo);
}
