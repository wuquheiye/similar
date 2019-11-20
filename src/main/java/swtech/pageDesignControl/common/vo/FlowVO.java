package swtech.pageDesignControl.common.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class FlowVO implements Serializable {
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fapplyTime;

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
}
