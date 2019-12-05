var localHost = window.location.host;
// localHost = '127.0.0.1';
localHost = '192.168.1.154';
// localHost = '106.54.231.23';
var pageDesignControl_HOST = 'http://' + localHost + ':43008/';

//枚举  fstatus 申请状态
var fstatusFlow = {
    UNTREATED:{code:0,desc:"未处理"},
    CHARGEPASS:{code:1,desc:"主管审批通过"},
    CHARGEREFUSE:{code:2,desc:"主管审批拒绝"},
    MANAGERPASS:{code:3,desc:"经理审批通过"},
    MANAGERREFUSE:{code:4,desc:"经理审批拒绝"},
    STAFFINGAFFIRM:{code:5,desc:"人事确认"},
    STAFFINGREFUSE:{code:6,desc:"人事反馈"},

}

var roleType={
    GM:{code:6,desc:"总经理"},
    FINANCE:{code:5,desc:"财务"},
    MANAGE:{code:4,desc:"经理"},
    GOVERNOR:{code:3,desc:"主管"},
    ADMINISTRATIVE:{code:1,desc:"人事"},
    EMPLOYEES:{code:2,desc:"职工"}
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

//加班类型
var overtimeTypeS={
    WORKDAY:{code: 1,desc:"工作日加班"},
    WEEKEND:{code: 2,desc:"周末假日加班"},
    HOLIDAYS:{code: 3,desc:"法定节假日加班"}
}

//yes or no
var yesOrNo={
    YES:{code:0,desc:"是"},
    NO:{code:1,desc:"否"}
}

//印章使用类型
var sealsUseType={
    COMPANYSEAL:{code:1,desc:"公司公章"},
    LEGALPERSONSEAL:{code:2,desc:"公司法人章"},
    FINANCESEAL:{code:3,desc:"财务专用章"},
    PACTSEAL:{code:4,desc:"合同专用章"},
    ELSESEAL:{code:5,desc:"其他"}
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
