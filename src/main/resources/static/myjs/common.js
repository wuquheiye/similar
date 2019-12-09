// 袁君选start
//flowPath连续点击事件
$(".manage").click(function () {
    if ($(this).parents().next('.flowbox').css('display') == 'none') {
        $(this).parents().next('.flowbox').slideDown();
    } else {
        $(this).parents().next('.flowbox').slideUp();
    }
})

//底部栏操作
$(".base").click(function () {
    if ($(this).attr("id") == 'newflow') {
        $(".base").css("color", "black");
        $(this).css("color", "#2b669a");
        $("#title").text("新建流程");
        $("#in_toDoList").hide();
        $("#in_doMatters").hide();
        $("#in_myHelp").hide();
        $("#in_newflow").show();
    } else if ($(this).attr("id") == 'toDoList') {
        $(".base").css("color", "black")
        $(this).css("color", "#2b669a");
        $("#title").text("待办事宜");
        $("#in_newflow").hide();
        $("#in_doMatters").hide();
        $("#in_myHelp").hide();
        $("#in_toDoList").show();
    } else if ($(this).attr("id") == 'doMatters') {
        $(".base").css("color", "black")
        $(this).css("color", "#2b669a");
        $("#title").text("已办事宜");
        $("#in_toDoList").hide();
        $("#in_newflow").hide();
        $("#in_myHelp").hide();
        $("#in_doMatters").show();
    } else if ($(this).attr("id") == 'myHelp') {
        $(".base").css("color", "black")
        $(this).css("color", "#2b669a");
        $("#title").text("我的请求");
        $("#in_toDoList").hide();
        $("#in_doMatters").hide();
        $("#in_newflow").hide();
        $("#in_myHelp").show();
    }
    // 日志底部
    else if ($(this).attr("id") == 'writeLog') {
        $(".base").css("color", "black")
        $(this).css("color", "#2b669a");
        $("#lookLogIn").hide();
        $("#logBar").hide();
        $("#writeLogIn").show();
    } else if ($(this).attr("id") == 'lookLog') {
        $(".base").css("color", "black")
        $(this).css("color", "#2b669a");
        $("#writeLogIn").hide();
        $("#logBar").show();
        $("#lookLogIn").show();
    }
    //查看日志
    else if ($(this).attr("id") == 'allLog') {
        $(this).css({"color": "#2b669a", "border-bottom": "2px solid #2aabd2"});
        $(this).siblings().css({"color": "grey", "border-bottom": "0px"})
        $("#lookLogIn").html(' <div class="row">\n' +
            '            <div class="col-xs-12" >\n' +
            '                <div class="row daily" onclick="daily(this)" style="line-height: 35px;height: 35px">\n' +
            '                    <div class="col-xs-6">\n' +
            '                        项目一\n' +
            '                    </div>\n' +
            '                    <div class="col-xs-6" style="color: grey;font-size: 10px;text-align: right;line-height: 35px">\n' +
            '                        22\n' +
            '                        <svg class="icon" aria-hidden="true" style="\n' +
            '                                        width: 20px;\n' +
            '                                        height: 20px;\n' +
            '                                        display: inline-block;\n' +
            '                                        top: 4px;\n' +
            '                                        position: relative">\n' +
            '                                <use xlink:href="#icon-arrow-right" class="iconUse"></use>\n' +
            '                        </svg>\n' +
            '                    </div>\n' +
            '                </div>\n' +
            '            </div>\n' +
            '            <div class="col-xs-12 dailybox" style="display: none;">\n' +
            '                <a href="dailyinfo.html">\n' +
            '                    <div class="row" style="margin-bottom: 3px;padding-bottom: 2px;">\n' +
            '                        <div class="col-xs-2">\n' +
            '                        <span style="border-radius: 50%; background: #2aabd2;color: white;width: 40px;height: 40px;display: block;\n' +
            '                                    line-height: 40px;\n' +
            '                                    text-align: center;">\n' +
            '                            哈哈\n' +
            '                        </span>\n' +
            '                        </div>\n' +
            '                        <div class="col-xs-8" style="padding: 0px">\n' +
            '                            <div class="row">\n' +
            '                                <div class="col-xs-12">项目一</div>\n' +
            '                                <div class="col-xs-12" style="color: grey;font-size: 10px" >11-06 10:22</div>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                    </div>\n' +
            '                </a>\n' +
            '                <a href="dailyinfo.html">\n' +
            '                <div class="row" style="margin-bottom: 3px;padding-bottom: 2px;">\n' +
            '                    <div class="col-xs-2">\n' +
            '                        <span style="border-radius: 50%; background: #2aabd2;color: white;width: 40px;height: 40px;display: block;\n' +
            '                                    line-height: 40px;\n' +
            '                                    text-align: center;">\n' +
            '                            呵呵\n' +
            '                        </span>\n' +
            '                    </div>\n' +
            '                    <div class="col-xs-8" style="padding: 0px">\n' +
            '                        <div class="row">\n' +
            '                            <div class="col-xs-12">项目-</div>\n' +
            '                            <div class="col-xs-12" style="color: grey;font-size: 10px" >11-06 10:22</div>\n' +
            '                        </div>\n' +
            '                    </div>\n' +
            '                </div>\n' +
            '                </a>\n' +
            '            </div>\n' +
            '        </div>');
    } else if ($(this).attr("id") == 'myLog') {
        $(this).css({"color": "#2b669a", "border-bottom": "2px solid #2aabd2"});
        $(this).siblings().css({"color": "grey", "border-bottom": "0px"})
        $("#lookLogIn").html('<div class="col-xs-12 dailybox" style="">\n' +
            '                <a href="dailyinfo.html">\n' +
            '                    <div class="row" style="margin-bottom: 3px;padding-bottom: 2px;">\n' +
            '                        <div class="col-xs-2">\n' +
            '                        <span style="border-radius: 50%; background: #2aabd2;color: white;width: 40px;height: 40px;display: block;\n' +
            '                                    line-height: 40px;\n' +
            '                                    text-align: center;">\n' +
            '                            我\n' +
            '                        </span>\n' +
            '                        </div>\n' +
            '                        <div class="col-xs-8" style="padding: 0px">\n' +
            '                            <div class="row">\n' +
            '                                <div class="col-xs-12">项目一</div>\n' +
            '                                <div class="col-xs-12" style="color: grey;font-size: 10px" >11-06 10:22</div>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                    </div>\n' +
            '                </a>\n' +
            '                <a href="dailyinfo.html">\n' +
            '                <div class="row" style="margin-bottom: 3px;padding-bottom: 2px;">\n' +
            '                    <div class="col-xs-2">\n' +
            '                        <span style="border-radius: 50%; background: #2aabd2;color: white;width: 40px;height: 40px;display: block;\n' +
            '                                    line-height: 40px;\n' +
            '                                    text-align: center;">\n' +
            '                            我\n' +
            '                        </span>\n' +
            '                    </div>\n' +
            '                    <div class="col-xs-8" style="padding: 0px">\n' +
            '                        <div class="row">\n' +
            '                            <div class="col-xs-12">项目-</div>\n' +
            '                            <div class="col-xs-12" style="color: grey;font-size: 10px" >11-06 10:22</div>\n' +
            '                        </div>\n' +
            '                    </div>\n' +
            '                </div>\n' +
            '                </a>\n' +
            '            </div>');
    } else if ($(this).attr("id") == 'myTeamLog') {
        $(this).css({"color": "#2b669a", "border-bottom": "2px solid #2aabd2"});
        $(this).siblings().css({"color": "grey", "border-bottom": "0px"})
        $("#lookLogIn").html(' <div class="row">\n' +
            '            <div class="col-xs-12" >\n' +
            '                <div class="row daily" onclick="daily(this)"   style="line-height: 35px;height: 35px">\n' +
            '                    <div class="col-xs-6">\n' +
            '                        项目一\n' +
            '                    </div>\n' +
            '                    <div class="col-xs-6" style="color: grey;font-size: 10px;text-align: right;line-height: 35px">\n' +
            '                        22\n' +
            '                        <svg class="icon" aria-hidden="true" style="\n' +
            '                                        width: 20px;\n' +
            '                                        height: 20px;\n' +
            '                                        display: inline-block;\n' +
            '                                        top: 4px;\n' +
            '                                        position: relative">\n' +
            '                                <use xlink:href="#icon-arrow-right" class="iconUse"></use>\n' +
            '                        </svg>\n' +
            '                    </div>\n' +
            '                </div>\n' +
            '            </div>\n' +
            '            <div class="col-xs-12 dailybox" style="display: none;">\n' +
            '                <a href="dailyinfo.html">\n' +
            '                    <div class="row" style="margin-bottom: 3px;padding-bottom: 2px;">\n' +
            '                        <div class="col-xs-2">\n' +
            '                        <span style="border-radius: 50%; background: #2aabd2;color: white;width: 40px;height: 40px;display: block;\n' +
            '                                    line-height: 40px;\n' +
            '                                    text-align: center;">\n' +
            '                            哈哈\n' +
            '                        </span>\n' +
            '                        </div>\n' +
            '                        <div class="col-xs-8" style="padding: 0px">\n' +
            '                            <div class="row">\n' +
            '                                <div class="col-xs-12">项目一</div>\n' +
            '                                <div class="col-xs-12" style="color: grey;font-size: 10px" >11-06 10:22</div>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                    </div>\n' +
            '                </a>\n' +
            '                <a href="dailyinfo.html">\n' +
            '                <div class="row" style="margin-bottom: 3px;padding-bottom: 2px;">\n' +
            '                    <div class="col-xs-2">\n' +
            '                        <span style="border-radius: 50%; background: #2aabd2;color: white;width: 40px;height: 40px;display: block;\n' +
            '                                    line-height: 40px;\n' +
            '                                    text-align: center;">\n' +
            '                            呵呵\n' +
            '                        </span>\n' +
            '                    </div>\n' +
            '                    <div class="col-xs-8" style="padding: 0px">\n' +
            '                        <div class="row">\n' +
            '                            <div class="col-xs-12">项目-</div>\n' +
            '                            <div class="col-xs-12" style="color: grey;font-size: 10px" >11-06 10:22</div>\n' +
            '                        </div>\n' +
            '                    </div>\n' +
            '                </div>\n' +
            '                </a>\n' +
            '            </div>\n' +
            '        </div>')
    }


})


//TEXT文本自适应
function textareasend() {
//拿到TextArea的DOM
    var textarea = document.getElementsByClassName('textarea');
//设置高度
    textarea.style.height = 20 + 'px';
}

//点击图片事件
function picture() {
    alert("das")
    $("#picture").show();
}

//点击删除图片
$(".removePicture").click(function () {
    $(this).parents('li').remove();
})

function daily(e) {
    if ($(e).parents().next('.dailybox').css('display') == 'none') {
        $(e).parents().next('.dailybox').slideDown();
        $(".iconUse").attr("xlink:href", "#icon-chebaba-xialakuangjiantou");

    } else {
        $(e).parents().next('.dailybox').slideUp();
        $(".iconUse").attr("xlink:href", "#icon-arrow-right");
        // $(this).children().children(".iconUse").attr("xlink:href","#icon-chebaba-xialakuangjiantou");
    }
}

//daily连续点击事件
$(".daily").click(function () {

    if ($(this).parents().next('.dailybox').css('display') == 'none') {
        $(this).parents().next('.dailybox').slideDown();
        $(".iconUse").attr("xlink:href", "#icon-chebaba-xialakuangjiantou");

    } else {
        $(this).parents().next('.dailybox').slideUp();
        $(".iconUse").attr("xlink:href", "#icon-arrow-right");
        // $(this).children().children(".iconUse").attr("xlink:href","#icon-chebaba-xialakuangjiantou");
    }
})

//获取当前时间
function getCurrentTime() {
    var timeStr = '-';
    var curDate = new Date();
    var curYear = curDate.getFullYear();  //获取完整的年份(4位,1970-????)
    var curMonth = curDate.getMonth() + 1;  //获取当前月份(0-11,0代表1月)
    var curDay = curDate.getDate();       //获取当前日(1-31)
    var curWeekDay = curDate.getDay();    //获取当前星期X(0-6,0代表星期天)
    var curHour = curDate.getHours();      //获取当前小时数(0-23)
    var curMinute = curDate.getMinutes();   // 获取当前分钟数(0-59)
    var curSec = curDate.getSeconds();      //获取当前秒数(0-59)
    var Current = curYear + timeStr + curMonth + timeStr + curDay + " " + curHour + ":" + curMinute;
    console.log(Current);
    // this.datetime=Current;
    if (curSec < 10) {
        curSec = '0' + curSec;
    }
    if (curHour < 10) {
        curHour = '0' + curHour;
    }
    if (curDay < 10) {
        curDay = '0' + curDay;
    }
    return Current;
}

//字符串转日期格式，strDate要转为日期格式的字符串
function getDate(strDate) {
    var st = strDate;
    var a = st.split(" ");
    var b = a[0].split("-");
    var c = a[1].split(":");
    var date = new Date(b[0], b[1] - 1, b[2], c[0], c[1], c[2]);
    return date;
}

//日期相减
function leadTime(e) {
    getDate(e);
    let databaseTime = Date.parse(getDate(e));
    let date = Date.parse(new Date());
    let lead = date - databaseTime;
    let leadTime = Math.floor(lead / 86400000) + "天";
    if (leadTime <= 0) {
        leadTime = "项目未开始";
    }
    return leadTime;
}

//hh:mm  dd/MM/yyyy 格式字符串转日期
function getDateSprit(strDate) {
    var st = strDate;
    var a = st.split(" ");
    var b = a[0].split("/");
    var c = a[1].split(":");
    var date = new Date(b[2], b[1], b[0], c[0], c[1]);
    return date;
}


//两个时间计算时间差
function leadTimeTwo(s, e) {
    let databaseTime = Date.parse(getDateSprit(s));
    let date = Date.parse(getDateSprit(e));
    let lead = date - databaseTime;
    let leadDate = Math.floor(lead / 86400000) + "天";
    let leadTime = Math.floor((lead % 86400000) / (1000 * 60 * 60)) + "小时";
    return leadDate + leadTime;
}

/**
 *  获取状态
 *  e fstatus
 */
function getStatus(e) {
    let desc = '';
    if (e == fstatusFlow.UNTREATED.code) {
        desc = fstatusFlow.UNTREATED.desc;
    } else if (e == fstatusFlow.CHARGEPASS.code) {
        desc = fstatusFlow.CHARGEPASS.desc;
    } else if (e == fstatusFlow.CHARGEREFUSE.code) {
        desc = fstatusFlow.CHARGEREFUSE.desc;
    } else if (e == fstatusFlow.MANAGERPASS.code) {
        desc = fstatusFlow.MANAGERPASS.desc;
    } else if (e == fstatusFlow.MANAGERREFUSE.code) {
        desc = fstatusFlow.MANAGERREFUSE.desc;
    } else if (e == fstatusFlow.STAFFINGAFFIRM.code) {
        desc = fstatusFlow.STAFFINGAFFIRM.desc;
    }
    return desc;
}

/**
 * 申请类型
 */
function flowType(e) {
    let desc = '';
    if (e == ftypeFlow.LEAVE.code) {
        desc = ftypeFlow.LEAVE.desc;
    } else if (e == ftypeFlow.OVERTIME.code) {
        desc = ftypeFlow.OVERTIME.desc;
    } else if (e == ftypeFlow.FINANCEPAY.code) {
        desc = ftypeFlow.FINANCEPAY.desc;
    } else if (e == ftypeFlow.GOOUT.code) {
        desc = ftypeFlow.GOOUT.desc;
    } else if (e == ftypeFlow.SEALUSE.code) {
        desc = ftypeFlow.SEALUSE.desc;
    } else if (e == ftypeFlow.SERVE.code) {
        desc = ftypeFlow.SERVE.desc;
    } else if (e == ftypeFlow.ONBUSINESS.code) {
        desc = ftypeFlow.ONBUSINESS.desc;
    }

    return desc;
}

/**
 * 获取同行人总人数
 */
function headcount (e) {
    let headcount=e.split(",");
    return headcount.length+1;
}

/**
 *  获取历史申请记录
 */
function selectLeaveAll(uid) {
    let showdata = '';
    $.ajax({
        url: pageDesignControl_HOST + "flow/selectLeaveAll?uid=" + uid + "&ftype=" + ftypeFlow.LEAVE.code,
        type: "get",
        dataType: "json",
        contentType: 'application/json; charset=UTF-8',
        async: false,
        success: function (res) {
            if (res.msg != null) {
                $.each(res.msg, function (index, value) {
                    showdata += '<div class="row" style="height: 30px">\n' +
                        '                        <div class="col-xs-6" style="line-height: 30px;">事假</div>\n' +
                        '                        <div class="col-xs-6" style="line-height: 30px;text-align: right">\n' +
                        value.fapplyTime +
                        '                        </div>\n' +
                        '                    </div>\n' +
                        '                    <hr style="margin-top: 10px;margin-bottom: 10px;height:1px;width: 90%;background: #F4F4F4;margin: auto">';
                })
                $("#history_flow").html(showdata);
            } else {
                alert("申请发出失败");
            }
        },
        error: function () {
            alert("网络信号不好，请重试");
        }

    })
}


/**
 * 加班时间段
 */
function overtimeType(e) {
    let desc = '';
    // alert("overtimeTypeS.WORKDAY.code"+overtimeTypeS.WORKDAY.code)
    // alert("e"+e)
    if (e == overtimeTypeS.WORKDAY.code) {
        desc = overtimeTypeS.WORKDAY.desc;
    } else if (e == overtimeTypeS.WEEKEND.code) {
        desc = overtimeTypeS.WEEKEND.code;
    } else if (e == overtimeTypeS.HOLIDAYS.code) {
        desc = overtimeTypeS.HOLIDAYS.desc;
    }
    return desc;
}

/**
 * 获取总人数
 */
function getNumProper(e) {
    let numProper = e.split(",").length;
    return numProper;
}

/**
 * yes or no
 */
function yesOrNofun(e) {
    let desc = '';
    if (e == yesOrNo.YES.code) {
        desc = yesOrNo.YES.desc;
    } else if (e == yesOrNo.NO.code) {
        desc = yesOrNo.NO.desc;
    }
    return desc;
}

/**
 * 印章使用
 */
function sealsUseDesc(e) {
    let desc = '';
    if (e == sealsUseType.COMPANYSEAL.code) {
        desc = sealsUseType.COMPANYSEAL.desc;
    } else if (e == sealsUseType.ELSESEAL.code) {
        desc = sealsUseType.ELSESEAL.desc;
    } else if (e == sealsUseType.FINANCESEAL.code) {
        desc = sealsUseType.FINANCESEAL.desc;
    } else if (e == sealsUseType.LEGALPERSONSEAL.code) {
        desc = sealsUseType.LEGALPERSONSEAL.desc;
    } else if (e == sealsUseType.PACTSEAL.code) {
        desc = sealsUseType.PACTSEAL.desc;
    }
    return desc;
}

/**
 * 获取全部经理和利捷总经理
 */
function selectusersbyrid() {
    $.ajax({
        url: pageDesignControl_HOST + "manage/users/selectusersbyrid",
        type: "get",
        dataType: "json",
        // data: JSON.stringify(leaveflow),
        contentType: 'application/json; charset=UTF-8',
        async: false,
        success: function (res) {
            let showdata='';
            showdata+='<option value="0">选择推送领导</option>\n' ;
            if(res.msg!=""){
                $.each(res.msg,function (index,value) {
                    showdata+=' <option value="'+value.uid+'">'+value.uusername+'</option>';
                })
            }
            $("#fuidManager").html(showdata);
        },
        error:function () {
            alert("网络异常")
        }
    })
}

// 袁君选end