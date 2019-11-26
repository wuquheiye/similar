package swtech.pageDesignControl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class OnbusInessFlow implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "oif_id", type = IdType.AUTO)
    private Integer oifId;

    /**
     * 出差申请表关联到流程表
     */
    private Integer fid;

    /**
     * 同行人
     */
    private String oifPeer;

    /**
     * 到达地点
     */
    private String oifSite;

    /**
     * 0 yes  1 no 是否提供交通工具
     */
    private Integer oifIfVehicle;

    /**
     * 0 yes 1 no 伙食费是否自理
     */
    private Integer oifIfBoard;
    /**
     * 0 yes  1 no 是否提供住宿
     */
    private Integer oifIfPutup;

    /**
     * 接待单位
     */
    private String oifHost;

    /**
     * 关联到流程类型
     */
    private Integer oifFtype;


}
