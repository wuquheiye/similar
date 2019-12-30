package job.vo;

import job.entity.CompanyInfo;
import job.entity.CompanyPosition;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 李鸿智
 * @since 2019-12-26
 */
@Data
@Accessors(chain = true)
public class CompanyPositionVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公司信息
     */
    private CompanyInfo companyInfo;

    /**
     * 公司职位
     */
    private CompanyPosition companyPosition;

    /**
     * 默认页码
     */
    private Integer page = 1;

    /**
     * 默认页数
     */
    private Integer pageSize = 10;

}
