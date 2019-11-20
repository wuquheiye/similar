//李鸿智start
/**
 * 注册点击事件
 */
function regist() {
    $.ajax({
        url: pageDesignControl_HOST+'/doregist',
        type: 'Post',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify({
            "uusername" : $("#registUusername").val(),
            "upassword" : $("#registUpassword").val(),
            "uinvitationCode" : $("#registDid option:selected").val(),
            "did" : $("#registUinvitationCode").val()
        }),
        success: function (result) {
            alert(result.result);
        }
    });
}
/**
 * 登录点击事件
 */
function login() {
    $.ajax({
        url: '/dologin',
        type: 'Post',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify({
            "uusername" : $("#loginUusername").val(),
            "upassword" : $("#loginUpassword").val()
        }),
        success: function (result) {
            alert(result.result);
        }
    });
}

$(function () {
    $(".loginAndRegist").on("click", ".backLogin", function () {
        $("#regist").addClass("hidden");
        $("#login").removeClass("hidden");
    });
    $(".loginAndRegist").on("click", ".regist", function () {
        $("#login").addClass("hidden");
        $("#regist").removeClass("hidden");
    });
})
$(function () {
    /*
    // 点击跳转群组详情页面start
    $(".organizationContain").on("click",".clickToGroupDetail", function(){
        location.href='groupDetails.html';
    });
    // 点击跳转群组详情页面end
    // 点击跳转个人详情页面start
    $(".organizationContain").on("click",".clickToPersondetail", function(){
        location.href='personalDetails.html';
    });
    // 点击跳转个人详情页面end
    */
})
$(function () {
    // 组织中多级手风琴显示隐藏start
    $(".organizationHeaderOrganization").on("click", ".organizationHeaderOrganizationClick", function () {
        $(this).parent().children("ul").toggle();
        $(this).find("span").toggleClass("glyphicon-chevron-down");
        $(this).find("span").toggleClass("glyphicon-chevron-up");
    });
    // 组织中多级手风琴显示隐藏end
})
$(function () {
    // 组织顶部点击变色和显示隐藏
    $(".centerContain").on("click", ".organizationHeaderLi", function () {
        if (!$(this).is('.active')) {
            // 显示隐藏start
            $(".centerContain .organizationCenter").addClass("hidden");
            $("." + $(this).attr("id")).removeClass("hidden");
            // 显示隐藏end
            // 底部点击变色start
            $(".centerContain .organizationHeaderLi").removeClass("active");
            $(this).addClass("active");
            // 底部点击变色end
        }
    });
})

/**
 * 袁君选
 * 获取监听值
 * 根据name值监听
 */
$(".organizationHeaderOrganization").on("click", ".getallname", function () {
    if ($(this).is(':checked')) {
        var showdata = '<button type="button" class="btn btn-primary button"  id="bun_' + $(this).attr("id") + '" value=' + this.value + '>' + this.value + '</button>';
        $("#getname").append(showdata);
    } else {
        $("#bun_" + $(this).attr("id")).remove();
    }
})

/**
 * 确认按钮
 */
function affirm() {
    var value = [];
    var id = []
    $("#getname .button").each(function (e) {
        value.push(this.value);
        id.push(this.id.substring(4));
    })
    alert(value);
    alert(id);
}

/**
 * 清除人员
 */
function clean() {
    $("#getname .button").remove();
}

//李鸿智end
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
// 袁君选end