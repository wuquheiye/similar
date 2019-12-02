package swtech.pageDesignControl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 袁君选
 * @since 2019-11-18
 */
@Data
@Accessors(chain = true)
public class Flow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 流程表主键
     */
    @TableId(value = "fid", type = IdType.AUTO)
    private Integer fid;

    /**
     * 申请类型
     */
    private Integer ftype;

    /**
     * 关联用户表id、
     */
    private Integer uid;

    /**
     * 申请日期
     */

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private String fapplyTime;

    /**
     * 休假类型
     */
    private Integer fleaveType;

    /**
     * 申请开始时间
     */

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm")
    private LocalDateTime fstartTime;

    /**
     * 申请结束时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm")
    private LocalDateTime fendTime;


    /**
     * 申请理由
     */
    private String fapplyReason;

    /**
     * 申请状态
     */
    private Integer fstatus;

    /**
     * 获取审批主管id
     */
    private Integer fuidCharge;

    /**
     * 审批主管拒绝理由
     */
    private String fuidChargeRefuse;

    /**
     * 主管处理时间
     */
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private String fuidChargeHand;

    /**
     * 获取审批经理id
     */
    private Integer fuidManager;

    /**
     * 审批经理拒绝理由
     */
    private String fuidManagerRefuse;

    /**
     * 经理审批时间
     */
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private String fuidManagerHand;

    /**
     * 获取确认人事id
     */
    private Integer fuidStaffing;
    /**
     * 人事意见反馈
     */
    private String fuidStaffingRefuse;

    /**
     * 人事确认时间
     */
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private String fuidStaffingHand;

    /**
     * 获取确认财务id
     */
    private Integer fuidFinance;
    /**
     * 财务意见反馈
     */
    private String fuidFinanceRefuse;

    /**
     * 财务确认时间
     */
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private String fuidFinanceHand;

    /**
     * 申请人
     */
    private String uusername;

    /**
     * 所属部门
     */
    private  String  dname;

    /**
     * 变化字段
     */
    private String fchange;

    /**
     * 发送给谁处理
     */
    private Integer frid;

    /**
     * 所属公司
     */
    private Integer artsVision;

    /**
     * 已读，未读
     */
    private Integer managerRead;

}
