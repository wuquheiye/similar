package job.mapper;

import job.entity.CompanyPosition;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import job.vo.CompanyPositionVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 李鸿智
 * @since 2019-12-26
 */
public interface CompanyPositionMapper extends BaseMapper<CompanyPosition> {

    /**
     * 查询职位
     *
     * @param companyPositionVO
     * @return
     */
    List<CompanyPosition> selectCompanyPositionByCondition(
            @Param("companyPositionVO")CompanyPositionVO companyPositionVO, @Param("pageStart") int pageStart, @Param("pageSize") int pageSize);

    /**
     * 查询个数
     *
     * @return
     */
    int selectCount(@Param("companyPositionVO")CompanyPositionVO companyPositionVO);
}
