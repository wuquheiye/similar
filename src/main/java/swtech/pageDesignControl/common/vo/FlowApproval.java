package swtech.pageDesignControl.common.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class FlowApproval implements Serializable {
     private Integer status;//拒绝or同意
     private String hand;//反馈意见
     private Integer fstatus;//流程状态
     private Integer fid; //流程id
     private Integer rtype; //所属公司
     private Integer fuidManager;//获取领导uid
     private Integer uid;//当前用户uid  人事财务  批注后 写入数据库
     private String uusername;//当前用户姓名
}
