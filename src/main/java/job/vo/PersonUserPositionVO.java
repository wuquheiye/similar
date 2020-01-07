package job.vo;

import job.entity.CompanyInfo;
import job.entity.CompanyPosition;
import job.entity.PersonUser;
import job.entity.PersonUserPosition;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 个人职位信息
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-18
 */
@Data
@Accessors(chain = true)
public class PersonUserPositionVO {
    /**
     * 投递职位
     */
    private PersonUserPosition personUserPosition;

    /**
     * 投递简历
     */
    private PersonUser personUser;

    /**
     * 简历信息
     */
    private PersonVO personVO;

    /**
     * 公司职位
     */
    private CompanyPosition companyPosition;

    /**
     * 公司信息
     */
    private CompanyInfo companyInfo;


}
