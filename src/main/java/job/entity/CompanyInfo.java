package job.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 公司信息
 * </p>
 *
 * @author 李鸿智
 * @since 2019-12-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CompanyInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 头像
     */
    private String portrait;

    /**
     * 公司logo
     */
    private String companyLogo;

    /**
     * 姓名
     */
    private String name;

    /**
     * 职位
     */
    private String position;

    /**
     * 工作邮箱
     */
    private String email;

    /**
     * 单位全称
     */
    private String companyFullName;

    /**
     * 公司简称
     */
    private String companyShortName;

    /**
     * 行业领域
     */
    private String industry;

    /**
     * 公司规模
     */
    private String scale;

    /**
     * 公司发展阶段
     */
    private String stage;

    /**
     * 公司简介
     */
    private String profile;

    /**
     * 公司核心价值观
     */
    private String coreValue;

    /**
     * 公司地址
     */
    private String area;

    /**
     * 关联user_id
     */
    private Integer userId;


}
