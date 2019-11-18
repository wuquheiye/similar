var localHost = window.location.host;
// localHost = '106.54.231.23';
localHost = '127.0.0.1';
var pageDesignControl_HOST = 'http://' + localHost + ':43008/';

//枚举  fstatus 申请状态
var fstatus={

}
// 申请类型
var ftype ={
    LEAVE :{code:1,desc:"请假申请"},
    OVERTIME : {code:2,desc:"加班申请"}
}

//请假类型
var fleaveType={
    ANNUAL :{code:1,desc:"年假"},
    CASUAL :{code:2,desc:"事假"},
    SICK   :{code:3,desc:"病假"},
    MARRIAGE:{code:4,desc:"婚假"},
    MATERNITYANDBREAST:{code:5,desc:"产假及哺乳假"},
    PATERNITY:{code:6,desc:"陪产假"},
    FUNERAL:{code:7,desc:"丧假"},
    INLIEU :{code:8,desc:"调休假"}
}
