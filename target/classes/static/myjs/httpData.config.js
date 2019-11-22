var localHost = window.location.host;
// localHost = '106.54.231.23';
localHost = '127.0.0.1';
var pageDesignControl_HOST = 'http://' + localHost + ':43008/';

//枚举  fstatus 申请状态
var fstatusFlow = {
    UNTREATED:{code:0,desc:"未处理"},
    CHARGEPASS:{code:1,desc:"主管审批通过"},
    CHARGEREFUSE:{code:2,desc:"主管审批拒绝"},
    MANAGERPASS:{code:3,desc:"经理审批通过"},
    MANAGERREFUSE:{code:4,desc:"经理审批拒绝"},
    STAFFINGAFFIRM:{code:5,desc:"人事确认"}
}
// 申请类型
var ftypeFlow = {
    LEAVE: {code: 1, desc: "请假申请"},
    OVERTIME: {code: 2, desc: "加班申请"},
    SERVE:{code:3,desc :"招待申请"},
    GOOUT:{code:4,desc: "外出申请"},
    ONBUSINESS:{code:5,desc: "出差申请"},
    SEALUSE:{code:6,desc: "印章使用申请"},
    FINANCEPAY:{code:7,desc: "财务付款申请"}
}

//请假类型
var fleaveTypeFlow={
    ANNUAL :{code:1,desc:"年假"},
    CASUAL :{code:2,desc:"事假"},
    SICK   :{code:3,desc:"病假"},
    MARRIAGE:{code:4,desc:"婚假"},
    MATERNITYANDBREAST:{code:5,desc:"产假及哺乳假"},
    PATERNITY:{code:6,desc:"陪产假"},
    FUNERAL:{code:7,desc:"丧假"},
    INLIEU :{code:8,desc:"调休假"}
}
