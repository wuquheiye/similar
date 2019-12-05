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
            for (var i = 0; i < msg.msg.length; i++) {
                if(msg.msg[i].ppermission!="" && msg.msg[i].ppermission!=" "){
                    $("#"+msg.msg[i].ppermission.replace("//s/g", "")).removeClass("hidden");
                }
            }
        }
    });
})