package swtech.pageDesignControl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 袁君选
 * @since 2019-11-26
 */
@Data
@Accessors(chain = true)
public class ServeFlow implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "sf_id", type = IdType.AUTO)
    private Integer sfId;

    private Integer fid;

    /**
     * 招待地点
     */
    private String sfSite;

    /**
     * 客户单位
     */
    private String sfUnit;

    /**
     * 客户人数
     */
    private Integer sfNum;

    /**
     * 主要联系人
     */
    private String sfLinkman;

    /**
     * 联系电话
     */
    private String sfPhone;

    /**
     * 参与人
     */
    private String sfPlayers;

    /**
     * 预计费用
     */
    @TableField("sf_estimated_Cost")
    private String sfEstimatedCost;

    /**
     * 实际费用
     */
    @TableField("sf_actual_Costs")
    private String sfActualCosts;


}
