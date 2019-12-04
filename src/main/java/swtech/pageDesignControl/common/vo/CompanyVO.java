package swtech.pageDesignControl.common.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 生成组织信息公司
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-21
 */
@Data
@Accessors(chain = true)
public class CompanyVO {
    /**
     * 公司名称
     */
    private String companyVOName;

    /**
     * 公司id
     */
    private int companyVOId;

    /**
     * 部门列表
     */
    private List<DepartmentVO> departmentVOList;
}
