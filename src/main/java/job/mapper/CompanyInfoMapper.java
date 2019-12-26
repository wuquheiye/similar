package job.mapper;

import job.entity.CompanyInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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
}
