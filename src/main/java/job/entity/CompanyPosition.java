package job.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import job.utils.DateUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author 李鸿智
 * @since 2019-12-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CompanyPosition implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 职位类型
     */
    private String positionType;

    /**
     * 职位名字
     */
    private String positionName;

    /**
     * 所属部门
     */
    private String department;

    /**
     * 工作性质
     */
    private String nature;

    /**
     * 最低月薪
     */
    private Integer monthlySalaryLow;

    /**
     * 最高月薪
     */
    private Integer monthlySalaryHight;

    /**
     * 工作城市
     */
    private String areaCity;

    /**
     * 学历
     */
    private String education;

    /**
     * 工作描述
     */
    private String positionDescribe;

    /**
     * 工作地址详情
     */
    private String areaCityDetail;

    /**
     * 分布时间
     */
    private String createTime = DateUtil.getNewDate();

    /**
     * 企业id
     */
    private Integer companyInfoId;


}
