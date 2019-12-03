$(function () {
    alert(1)
    /**
     * 权限控制显示隐藏
     */
    console.log(JSON.stringify(sessionStorage.getItem("login").users.uusername));
    // $.ajax({
    //     url: pageDesignControl_HOST + 'manage/users/getpermission',
    //     type: 'get',
    //     contentType: 'application/json',
    //     dataType: 'json',
    //     data: {
    //         "uusername": sessionStorage.getItem("login").users.uusername
    //     },
    //     success: function (result) {
    //         console.log(result)
    //         alert(result.result);
    //     }
    // });
})