package swtech.pageDesignControl.common.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class FlowApproval implements Serializable {
     private Integer status;
     private String hand;
     private Integer fstatus;
     private Integer fid; //
     private Integer ArtsVision; //所属公司
     private Integer fuidManager;
     private Integer uid;//当前用户uid  人事财务  批注后 写入数据库
}
