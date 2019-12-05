/**
 * 李鸿智start
 */

/**
 * 点击进入个人档案页面
 */
$(function () {
    $(".publicBottomOrganization").on("click", ".clickToPersondetail", function () {
        var personInfomationManageId = $(this).children("input").val();
        alert(personInfomationManageId);
        window.location.href = "./personInfomation.html?personInfomationManageId="+personInfomationManageId;
    });
})

/**
 * 公司组织页面
 */
$(function () {
    $.ajax({
        url: pageDesignControl_HOST + 'manage/users/getallcompanyinformation',
        type: 'get',
        contentType: 'application/json',
        dataType: 'json',
        data: {},
        success: function (msg) {
            if (msg.status == "200") {
                var str = "";
                $("#organizationHeaderOrganizationUiContain").empty();
                for (var i = 0; i < msg.msg.length; i++) {
                    str +=
                        '<li class="">' +
                        '<div class="organizationHeaderOrganizationClick" >' +
                        '<span class="glyphicon glyphicon-chevron-right organizationHeaderOrganizationSpan"></span>' +
                        msg.msg[i].companyVOName +
                        '</div>' +
                        '<ul class="organizationHeaderOrganizationUl" id="companyVOId' + msg.msg[i].companyVOId + '">' +
                        '</ul>'
                    '</li>'
                    $("#organizationHeaderOrganizationUiContain").append(str);
                    str = "";
                    for (var j = 0; j < msg.msg[i].departmentVOList.length; j++) {
                        str +=
                            '<li class="">' +
                            '<div class="organizationHeaderOrganizationClick" >' +
                            '<span class="glyphicon glyphicon-chevron-right organizationHeaderOrganizationSpan"></span>' +
                            msg.msg[i].departmentVOList[j].dname +
                            '</div>' +
                            '<ul class="organizationHeaderOrganizationUl" id="companyVOIdUsers' + msg.msg[i].departmentVOList[j].did + '">' +
                            '</ul>' +
                            '</li>'
                        $("#companyVOId" + msg.msg[i].companyVOId).append(str);
                        str = "";
                        for (var k = 0; k < msg.msg[i].departmentVOList[j].usersListVO.length; k++) {
                            str +=
                                '<li class="organizationHeaderOrganizationLi clickToPersondetail">' +
                                '<input type="hidden" value="'+msg.msg[i].departmentVOList[j].usersListVO[k].uid+'" />'+
                                '<div class="" style="float:left;padding-top:5px;padding-right:20px;"><img src="../publicBottom/images/head.png" style="width: 50px; "/></div>' +
                                '<div class="" style="float:left;">' +
                                '<div style="padding-top: 8px;font-size: 16px;">' +
                                 msg.msg[i].departmentVOList[j].usersListVO[k].uusername +'&nbsp;&nbsp;['+ msg.msg[i].departmentVOList[j].usersListVO[k].rname + ']' +
                                '</div>' +
                                '<div style="font-size: 14px;color: #A9A9A9">' + msg.msg[i].departmentVOList[j].usersListVO[k].utelephonenumber + '</div>' +
                                '</div>' +
                                '</li>'
                            $("#companyVOIdUsers" + msg.msg[i].departmentVOList[j].did).append(str);
                            str = "";
                        }
                    }
                }
            } else {
                alert(msg.statusMsg)
            }
        }
    });
})

/**
 * 注册点击事件
 */
function regist() {
    $.ajax({
        url: pageDesignControl_HOST + '/doregist',
        type: 'Post',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify({
            "uusername": $("#registUusername").val(),
            "upassword": $("#registUpassword").val(),
            "uinvitationCode": $("#registDid option:selected").val(),
            "did": $("#registUinvitationCode").val()
        }),
        success: function (msg) {
            if (msg.status == "200") {
                alert(msg.statusMsg)
            } else {
                alert(msg.statusMsg)
            }
        }
    });
}

/**
 * 登录点击事件
 */
function login() {
    $.ajax({
        url: '/dologin',
        type: 'get',
        contentType: 'application/json',
        dataType: 'json',
        data: {
            "utelephonenumber": $("#loginUtelephonenumber").val(),
            "upassword": $("#loginUpassword").val()
        },
        success: function (msg) {
            if (msg.status == "200") {
                window.sessionStorage.removeItem("login");
                window.sessionStorage.setItem("login", JSON.stringify(msg.msg));
                // var loginUser = JSON.parse(sessionStorage.getItem('login'));
                // console.log(loginUser.users.uusername)
                window.location.href = pageDesignControl_HOST + "use/index.html";
            } else {
                alert(msg.statusMsg)
            }
        }
    });
}

/**
 * 登录注册显示隐藏
 */
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

/**
 * 组织中多级手风琴显示隐藏
 */
$(function () {
    $(".organizationHeaderOrganization").on("click", ".organizationHeaderOrganizationClick", function () {
        $(this).parent().children("ul").toggle();
        $(this).find("span").toggleClass("glyphicon-chevron-down");
        $(this).find("span").toggleClass("glyphicon-chevron-up");
    });
})

/**
 * 组织顶部点击变色和显示隐藏
 */
$(function () {
    $(".centerContain").on("click", ".organizationHeaderLi", function () {
        if (!$(this).is('.active')) {
            $(".centerContain .organizationCenter").addClass("hidden");
            $("." + $(this).attr("id")).removeClass("hidden");
            $(".centerContain .organizationHeaderLi").removeClass("active");
            $(this).addClass("active");
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

/**
 * 李鸿智end
 */
