package job.vo;

import job.entity.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户缓存信息
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-18
 */
@Data
@Accessors(chain = true)
public class Company {

    private static final long serialVersionUID = 1L;

    /**
     * 用户信息
     *
     */
    private User user;

    /**
     * 公司信息
     */
    private CompanyInfo companyInfo;

}