/**
 * 权限控制显示隐藏
 */
$(function () {
    var login = JSON.parse(sessionStorage.getItem('login'));
    var utelephonenumber = login.users.utelephonenumber;
    $.ajax({
        url: pageDesignControl_HOST + 'manage/users/getpermission',
        type: 'get',
        contentType: 'application/json',
        dataType: 'json',
        data: {
            "utelephonenumber": utelephonenumber
        },
        success: function (msg) {
            if (msg.status == "200") {
                for (var i = 0; i < msg.msg.length; i++) {
                    if (msg.msg[i].ppermission != "" && msg.msg[i].ppermission != " ") {
                        $("#" + msg.msg[i].ppermission.replace("//s/g", "")).removeClass("hidden");
                    }
                }
            }
        }
    });
})


$(function () {
    var login = JSON.parse(sessionStorage.getItem('login'));
    var str =
        '<div style="background-color: #0ab871;">' +
        '<div class="text-center" style="height:44px;line-height:44px;font-size:20px;padding-top:10px;color:#ffffff">' +
        login.users.uusername +
        '</div>' +
        '<div class="text-center">' +
        '<img src="../publicBottom/images/head.png" class="img-rounded" style="width:70px;padding-top:10px;">' +
        '</div>' +
        '<div class="text-center" style="height:20px;line-height:20px;font-size:16px;margin-bottom:2px;color:#ffffff;margin-top:5px;">' +
        login.department.dname + login.role.rname+
        '</div>' +
        '<div class="text-center" style="height:20px;line-height:20px;font-size:14px;padding-bottom:45px;color:#ffffff">' +
        login.users.utelephonenumber +
        '</div>' +
        '</div>' +
        '<div>' +
        '<table class="table">' +
        '<tbody style="font-size:16px;">' +
        '<tr style="height:50px;line-height:50px;">' +
        '<td style="height:50px;line-height:50px;margin:0;padding:0;text-align:center;">姓名</td>' +
        '<td style="height:50px;line-height:50px;margin:0;padding:0;text-align:center;">'+login.department.dname+'</td>' +
        '<td style="height:50px;line-height:50px;margin:0;padding:0;text-align:center;">' +
        '</td>' +
        '</tr>' +
        '<tr style="height:35px;line-height:35px;">' +
        '<td style="height:50px;line-height:50px;margin:0;padding:0;text-align:center;">职位</td>' +
        '<td style="height:50px;line-height:50px;margin:0;padding:0;text-align:center;">'+login.role.rname+'</td>' +
        '<td style="height:50px;line-height:50px;margin:0;padding:0;text-align:center;">' +
        '</td>' +
        '</tr>' +
        '<tr style="height:35px;line-height:35px;">' +
        '<td style="height:50px;line-height:50px;margin:0;padding:0;text-align:center;">部门</td>' +
        '<td style="height:50px;line-height:50px;margin:0;padding:0;text-align:center;">'+login.department.dname+'</td>' +
        '<td style="height:50px;line-height:50px;margin:0;padding:0;text-align:center;">' +
        '</td>' +
        '</tr>' +
        '<tr style="height:35px;line-height:35px;">' +
        '<td style="height:50px;line-height:50px;margin:0;padding:0;text-align:center;">电话号码</td>' +
        '<td style="height:50px;line-height:50px;margin:0;padding:0;text-align:center;">'+login.users.utelephonenumber+'</td>' +
        '<td style="height:50px;line-height:50px;margin:0;padding:0;text-align:center;">' +
        '</td>' +
        '</tr>' +
        '</tbody>' +
        '</table>' +
        '</div>'
    $("#publicBottomMyDiv").empty();
    $("#publicBottomMyDiv").append(str);
})