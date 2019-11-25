/**
 * 用户start
 */
var usersManageId = 0;
$(function () {
    // 左侧显示隐藏并获取用户列表
    $(".manageLeft").on("click", "#usersManage", function () {
        $("#usersManageName").val("");
        $(".manageRight .rightManageDiv").addClass("hidden");
        $(".usersManage").removeClass("hidden");
        getUsersList(1);
    });

    // 点击分页
    $("#usersManagePageDiv").on("click", "a", function () {
        getUsersList($(this).text());
    });

    // 清除
    $(".usersManage").on("click", "#usersManageClean", function () {
        $("#usersManageName").val("");
    });

    // 查询
    $(".usersManage").on("click", "#usersManageSearch", function () {
        getUsersList(1)
    });

    // 删除单个用户
    $(".usersManage").on("click", ".delete", function () {
        var r = confirm("是否删除!");
        if (r == true) {
            var uid = $(this).next().attr("class");
            removeUsers(uid);
        }
    });

    // 删除多个用户
    $(".usersManage").on("click", ".usersManageDelete", function () {
        var r = confirm("是否删除!");
        if (r == true) {
            $("input:checkbox[class=usersManageCheckBox]:checked").each(function () {
                var uid = $(this).next().attr("class");
                removeUsers(uid);
            });
        }
    });

    // 增加用户
    $(".usersManageEdit").on("click", "#saveUsers", function () {
        saveUsers();
    });

    // 更改用户
    $(".usersManageEdit").on("click", "#updateUsers", function () {
        updateUsers();
    });

})

// 用户管理
$(function () {
    // 点击添加
    $(".manageRight").on("click", ".usersManageAdd", function () {
        usersManageId = -1;
        $(".manageRight .usersManage").addClass("hidden");
        $(".manageRight").children(".usersManageEdit").removeClass("hidden");
        $(".Users").attr("id", "saveUsers");
        $(".usersManageEdit #uusername").val("");
        $(".usersManageEdit #did").val("");
        $(".usersManageEdit #utelephonenumber").val("");
        $(".usersManageEdit #upassword").val("");
    });
    // 点击更新
    $(".manageRight").on("click", ".usersManageUpdate", function () {
        usersManageId = $(this).prev().attr("class");
        getUsers();
        $(".manageRight .usersManage").addClass("hidden");
        $(".manageRight").children(".usersManageEdit").removeClass("hidden");
        $(".Users").attr("id", "updateUsers");
    });
    // 点击返回
    $(".manageRight").on("click", ".usersManageBack", function () {
        $(".manageRight .usersManage").removeClass("hidden");
        $(".manageRight").children(".usersManageEdit").addClass("hidden");
        getUsersList(1);
    });
})

/**
 * 获取用户列表
 */
function getUsers() {
    var uid = usersManageId;
    $.ajax({
        url: pageDesignControl_HOST + '/manage/users/selectbyid',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        data: {"uid": uid},
        success: function (msg) {
            // var usersList = JSON.stringify(msg.msg);
            if (msg.status == 200) {
                // $(".usersManageEdit #rname").attr("value",msg.msg.rname);
                $(".usersManageEdit #uusername").val(msg.msg.uusername);
                $(".usersManageEdit #did").val(msg.msg.did);
                $(".usersManageEdit #utelephonenumber").val(msg.msg.utelephonenumber);
                $(".usersManageEdit #upassword").val(msg.msg.upassword);
            }
        }
    });
}

/**
 * 更改用户
 */
function updateUsers() {
    var uid = usersManageId;
    var uusername = $(".usersManageEdit #uusername").val();
    var did = $(".usersManageEdit #did").val();
    var utelephonenumber = $(".usersManageEdit #utelephonenumber").val();
    var upassword = $(".usersManageEdit #upassword").val();
    $.ajax({
        url: pageDesignControl_HOST + 'manage/users/updatebyid',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        data: {
            "uid": uid,
            "uusername": uusername,
            "did": did,
            "utelephonenumber": utelephonenumber,
            "upassword": upassword
        },
        success: function (msg) {
            // var usersList = JSON.stringify(msg.msg);
            if (msg.status == 200) {
                alert("更改成功")
            }
        }
    });
}

/**
 * 添加用户
 */
function saveUsers() {
    var uusername = $(".usersManageEdit #uusername").val();
    var did = $(".usersManageEdit #did").val();
    var utelephonenumber = $(".usersManageEdit #utelephonenumber").val();
    var upassword = $(".usersManageEdit #upassword").val();
    $.ajax({
        url: pageDesignControl_HOST + 'manage/users/save',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        data: {
            "uusername": uusername,
            "did": did,
            "utelephonenumber": utelephonenumber,
            "upassword": upassword
        },
        success: function (msg) {
            // var usersList = JSON.stringify(msg.msg);
            if (msg.status == 200) {
                alert("添加成功")
            }
        }
    });
}

/**
 * 删除单个用户
 */
function removeUsers(uid) {
    $.ajax({
        url: pageDesignControl_HOST + 'manage/users/removebyid',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        data: {"uid": uid},
        success: function (msg) {
            if (msg.status == 200) {
                getUsersList(1);
                alert("删除成功")
            }
        }
    });
}

/**
 * 用户管理所有checkbox全选
 */
function usersManageCheckbox() {
    if ($(".usersManage .usersManageCheckBoxManage").is(':checked')) {
        $(".usersManage .usersManageCheckBox").prop("checked", true);
    } else {
        $(".usersManage .usersManageCheckBox").prop("checked", false);
    }
}

/**
 * 获取用户列表
 */
function getUsersList(page) {
    var uusername = $("#usersManageName").val();
    $.ajax({
        url: pageDesignControl_HOST + 'manage/users/selectbypageandcondition',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        data: {"uusername": uusername, "page": page},
        success: function (msg) {
            // var usersmentList = JSON.stringify(msg.msg);
            if (msg.status == 200) {
                // 添加列表
                var usersListStr = "";
                for (var i = 0; i < msg.msg.length; i++) {
                    usersListStr += '<tr class="active">' +
                        '<td class="text-center">' +
                        '<input type="checkbox" class="usersManageCheckBox">' + msg.msg[i].uid +
                        '<input type="hidden" class="' + msg.msg[i].uid + '" />' +
                        '</td>' +
                        '<td class="text-center">' + msg.msg[i].uusername + '</td>' +
                        '<td class="text-center">' + msg.msg[i].did + '</td>' +
                        '<td class="text-center">' + msg.msg[i].utelephonenumber + '</td>' +
                        '<td class="text-center">' +
                        '<ul class="Switch"><li><input type="checkbox" name="Storage" id="users' + msg.msg[i].uid + '" /><label for="users' + msg.msg[i].uid + '"><em></em></label></li></ul>' +
                        '</td>' +
                        '<td class="text-center">' + msg.msg[i].ucreationtime + '</td>' +
                        '<td class="text-center">' +
                        '<button type="button" class="btn btn-info delete">删除</button>' +
                        '<input type="hidden" class="' + msg.msg[i].uid + '" />' +
                        '<button type="button" class="btn btn-warning manageEdit usersManageUpdate">修改</button>' +
                        '<input type="hidden" class="usersManageEdit">' +
                        '</td>' +
                        '</tr>'
                }
                $("#usersManageTable").empty();
                $("#usersManageTable").append(usersListStr);
                // 回显搜索名
                $("#usersManageName").val(uusername);
                // 是否显示分页
                if (msg.totalSize <= 10) {
                    $("#usersManagePageDiv").addClass("hidden");
                } else {
                    $("#usersManagePageDiv").removeClass("hidden");
                }
                // 分页具体信息
                var pageSpanStr = "第 " + msg.pageStart + " 条 到 " + (msg.pageEnd < msg.totalSize ? msg.pageEnd : msg.totalSize) + " 条 ，共 " + msg.totalSize + " 条记录, 当前第 " + msg.currentPage + "页";
                $("#usersManagePageSpan").text(pageSpanStr);
                var pageUlStr = "";
                if (parseInt(msg.currentPage) - 2 > 0) {
                    pageUlStr += '<li><a href="#">' + (parseInt(msg.currentPage) - 2) + '</a></li>';
                }
                if (parseInt(msg.currentPage) - 1 > 0) {
                    pageUlStr += '<li><a href="#">' + (parseInt(msg.currentPage) - 1) + '</a></li>';
                }
                pageUlStr += '<li><a href="#">' + msg.currentPage + '</a></li>';
                if (parseInt(msg.currentPage) + 1 <= parseInt(msg.totalPage)) {
                    pageUlStr += '<li><a href="#">' + (parseInt(msg.currentPage) + 1) + '</a></li>';
                }
                if (parseInt(msg.currentPage) + 2 <= parseInt(msg.totalPage)) {
                    pageUlStr += '<li><a href="#">' + (parseInt(msg.currentPage) + 2) + '</a></li>';
                }
                $("#usersManagePageUl").empty();
                $("#usersManagePageUl").append(pageUlStr);
            }
        }
    });
}

/**
 * 用户end
 */

/**
 * 部门start
 */
var departmentManageId = 0;
$(function () {
    // 左侧显示隐藏并获取部门列表
    $(".manageLeft").on("click", "#departmentManage", function () {
        $("#departmentManageName").val("");
        $(".manageRight .rightManageDiv").addClass("hidden");
        $(".departmentManage").removeClass("hidden");
        getDepartmentList(1);
    });

    // 点击分页
    $("#departmentManagePageDiv").on("click", "a", function () {
        getDepartmentList($(this).text());
    });

    // 清除
    $(".departmentManage").on("click", "#departmentManageClean", function () {
        $("#departmentManageName").val("");
    });

    // 查询
    $(".departmentManage").on("click", "#departmentManageSearch", function () {
        getDepartmentList(1)
    });

    // 删除单个部门
    $(".departmentManage").on("click", ".delete", function () {
        var r = confirm("是否删除!");
        if (r == true) {
            var did = $(this).next().attr("class");
            removeDepartment(did);
        }
    });

    // 删除多个部门
    $(".departmentManage").on("click", ".departmentManageDelete", function () {
        var r = confirm("是否删除!");
        if (r == true) {
            $("input:checkbox[class=departmentManageCheckBox]:checked").each(function () {
                var did = $(this).next().attr("class");
                removeDepartment(did);
            });
        }
    });

    // 增加部门
    $(".departmentManageEdit").on("click", "#saveDepartment", function () {
        saveDepartment();
    });

    // 更改部门
    $(".departmentManageEdit").on("click", "#updateDepartment", function () {
        updateDepartment();
    });
})

// 部门管理
$(function () {
    // 点击添加
    $(".manageRight").on("click", ".departmentManageAdd", function () {
        departmentManageId = -1;
        $(".manageRight .departmentManage").addClass("hidden");
        $(".manageRight").children(".departmentManageEdit").removeClass("hidden");
        $(".Department").attr("id", "saveDepartment");
        $(".departmentManageEdit #dname").val("");
    });
    // 点击更新
    $(".manageRight").on("click", ".departmentManageUpdate", function () {
        departmentManageId = $(this).prev().attr("class");
        getDepartment();
        $(".manageRight .departmentManage").addClass("hidden");
        $(".manageRight").children(".departmentManageEdit").removeClass("hidden");
        $(".Department").attr("id", "updateDepartment");
    });
    // 点击返回
    $(".manageRight").on("click", ".departmentManageBack", function () {
        $(".manageRight .departmentManage").removeClass("hidden");
        $(".manageRight").children(".departmentManageEdit").addClass("hidden");
        getDepartmentList(1);
    });
})

/**
 * 获取部门列表
 */
function getDepartment() {
    var did = departmentManageId;
    $.ajax({
        url: pageDesignControl_HOST + '/manage/department/selectbyid',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        data: {"did": did},
        success: function (msg) {
            // var departmentList = JSON.stringify(msg.msg);
            if (msg.status == 200) {
                // $(".departmentManageEdit #dname").attr("value",msg.msg.dname);
                $(".departmentManageEdit #dname").val(msg.msg.dname);
            }
        }
    });
}

/**
 * 更改部门
 */
function updateDepartment() {
    var did = departmentManageId;
    var dname = $(".departmentManageEdit #dname").val();
    $.ajax({
        url: pageDesignControl_HOST + 'manage/department/updatebyid',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        data: {"dname": dname, "did": did},
        success: function (msg) {
            // var departmentList = JSON.stringify(msg.msg);
            if (msg.status == 200) {
                alert("更改成功")
            }
        }
    });
}

/**
 * 添加部门
 */
function saveDepartment() {
    var dname = $(".departmentManageEdit #dname").val();
    $.ajax({
        url: pageDesignControl_HOST + 'manage/department/save',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        data: {"dname": dname},
        success: function (msg) {
            // var departmentList = JSON.stringify(msg.msg);
            if (msg.status == 200) {
                alert("添加成功")
            }
        }
    });
}

/**
 * 删除单个部门
 */
function removeDepartment(did) {
    $.ajax({
        url: pageDesignControl_HOST + 'manage/department/removebyid',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        data: {"did": did},
        success: function (msg) {
            if (msg.status == 200) {
                getDepartmentList(1);
                alert("删除成功")
            }
        }
    });
}

/**
 * 部门管理所有checkbox全选
 */
function departmentManageCheckbox() {
    if ($(".departmentManage .departmentManageCheckBoxManage").is(':checked')) {
        $(".departmentManage .departmentManageCheckBox").prop("checked", true);
    } else {
        $(".departmentManage .departmentManageCheckBox").prop("checked", false);
    }
}

/**
 * 获取部门列表
 */
function getDepartmentList(page) {
    var dname = $("#departmentManageName").val();
    $.ajax({
        url: pageDesignControl_HOST + 'manage/department/selectbypageandcondition',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        data: {"dname": dname, "page": page},
        success: function (msg) {
            // var departmentList = JSON.stringify(msg.msg);
            if (msg.status == 200) {
                // 添加列表
                var departmentListStr = "";
                for (var i = 0; i < msg.msg.length; i++) {
                    departmentListStr += '<tr class="active">' +
                        '<td class="text-center">' +
                        '<input type="checkbox" class="departmentManageCheckBox">' + msg.msg[i].dname +
                        '<input type="hidden" class="' + msg.msg[i].did + '" />' +
                        '</td>' +
                        '<td class="text-center">' + msg.msg[i].dcreateTime + '</td>' +
                        '<td class="text-center">' +
                        '<button type="button" class="btn btn-info delete" >删除</button>' +
                        '<input type="hidden" class="' + msg.msg[i].did + '" />' +
                        '<button type="button" class="btn btn-warning manageEdit departmentManageUpdate">修改</button>' +
                        '<input type="hidden" class="departmentManageEdit">' +
                        '</td>' +
                        '</tr>'
                }
                $("#departmentManageTable").empty();
                $("#departmentManageTable").append(departmentListStr);
                // 回显搜索名
                $("#departmentManageName").val(dname);
                // 是否显示分页
                if (msg.totalSize <= 10) {
                    $("#departmentManagePageDiv").addClass("hidden");
                } else {
                    $("#departmentManagePageDiv").removeClass("hidden");
                }
                // 分页具体信息
                var pageSpanStr = "第 " + msg.pageStart + " 条 到 " + (msg.pageEnd < msg.totalSize ? msg.pageEnd : msg.totalSize) + " 条 ，共 " + msg.totalSize + " 条记录, 当前第 " + msg.currentPage + "页";
                $("#departmentManagePageSpan").text(pageSpanStr);
                var pageUlStr = "";
                if (parseInt(msg.currentPage) - 2 > 0) {
                    pageUlStr += '<li><a href="#">' + (parseInt(msg.currentPage) - 2) + '</a></li>';
                }
                if (parseInt(msg.currentPage) - 1 > 0) {
                    pageUlStr += '<li><a href="#">' + (parseInt(msg.currentPage) - 1) + '</a></li>';
                }
                pageUlStr += '<li><a href="#">' + msg.currentPage + '</a></li>';
                if (parseInt(msg.currentPage) + 1 <= parseInt(msg.totalPage)) {
                    pageUlStr += '<li><a href="#">' + (parseInt(msg.currentPage) + 1) + '</a></li>';
                }
                if (parseInt(msg.currentPage) + 2 <= parseInt(msg.totalPage)) {
                    pageUlStr += '<li><a href="#">' + (parseInt(msg.currentPage) + 2) + '</a></li>';
                }
                $("#departmentManagePageUl").empty();
                $("#departmentManagePageUl").append(pageUlStr);
            }
        }
    });
}

/**
 * 部门end
 */

/**
 * 角色start
 */
var roleManageId = 0;
$(function () {
    // 左侧显示隐藏并获取角色列表
    $(".manageLeft").on("click", "#roleManage", function () {
        $("#roleManageName").val("");
        $(".manageRight .rightManageDiv").addClass("hidden");
        $(".roleManage").removeClass("hidden");
        getRoleList(1);
    });

    // 点击分页
    $("#roleManagePageDiv").on("click", "a", function () {
        getRoleList($(this).text());
    });

    // 清除
    $(".roleManage").on("click", "#roleManageClean", function () {
        $("#roleManageName").val("");
    });

    // 查询
    $(".roleManage").on("click", "#roleManageSearch", function () {
        getRoleList(1)
    });

    // 删除单个角色
    $(".roleManage").on("click", ".delete", function () {
        var r = confirm("是否删除!");
        if (r == true) {
            var rid = $(this).next().attr("class");
            removeRole(rid);
        }
    });

    // 删除多个角色
    $(".roleManage").on("click", ".roleManageDelete", function () {
        var r = confirm("是否删除!");
        if (r == true) {
            $("input:checkbox[class=roleManageCheckBox]:checked").each(function () {
                var rid = $(this).next().attr("class");
                removeRole(rid);
            });
        }
    });

    // 增加角色
    $(".roleManageEdit").on("click", "#saveRole", function () {
        saveRole();
    });

    // 更改角色
    $(".roleManageEdit").on("click", "#updateRole", function () {
        updateRole();
    });
})

// 角色管理
$(function () {
    // 点击添加
    $(".manageRight").on("click", ".roleManageAdd", function () {
        roleManageId = -1;
        $(".manageRight .roleManage").addClass("hidden");
        $(".manageRight").children(".roleManageEdit").removeClass("hidden");
        $(".Role").attr("id", "saveRole");
        $(".roleManageEdit #name").val("");
    });
    // 点击更新
    $(".manageRight").on("click", ".roleManageUpdate", function () {
        roleManageId = $(this).prev().attr("class");
        getRole();
        $(".manageRight .roleManage").addClass("hidden");
        $(".manageRight").children(".roleManageEdit").removeClass("hidden");
        $(".Role").attr("id", "updateRole");
    });
    // 点击返回
    $(".manageRight").on("click", ".roleManageBack", function () {
        $(".manageRight .roleManage").removeClass("hidden");
        $(".manageRight").children(".roleManageEdit").addClass("hidden");
        getRoleList(1);
    });
})

/**
 * 获取角色列表
 */
function getRole() {
    var rid = roleManageId;
    $.ajax({
        url: pageDesignControl_HOST + '/manage/role/selectbyid',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        data: {"rid": rid},
        success: function (msg) {
            // var roleList = JSON.stringify(msg.msg);
            if (msg.status == 200) {
                $(".roleManageEdit #rname").val(msg.msg.rname);
            }
        }
    });
}

/**
 * 更改角色
 */
function updateRole() {
    var rid = roleManageId;
    var rname = $(".roleManageEdit #rname").val();
    if (!rname) {
        alert("角色名称不能为空");
    } else if (isRnameHendiadysRole(rname)) {
        alert("角色名称不能重复");
    }else {
        $.ajax({
            url: pageDesignControl_HOST + 'manage/role/updatebyid',
            type: 'Get',
            contentType: 'application/json',
            dataType: 'json',
            data: {"rname": rname, "rid": rid},
            success: function (msg) {
                // var roleList = JSON.stringify(msg.msg);
                if (msg.status == 200) {
                    alert("更改成功")
                }
            }
        });
    }
}

/**
 * 添加角色
 */
function saveRole() {
    var rname = $(".roleManageEdit #rname").val();
    if (!rname) {
        alert("角色名称不能为空");
    } else if (isRnameHendiadysRole(rname)) {
        alert("角色名称不能重复");
    } else {
        $.ajax({
            url: pageDesignControl_HOST + 'manage/role/save',
            type: 'Get',
            contentType: 'application/json',
            dataType: 'json',
            data: {"rname": rname},
            success: function (msg) {
                // var roleList = JSON.stringify(msg.msg);
                if (msg.status == 200) {
                    alert("添加成功")
                }
            }
        });
    }
}

/**
 * 判断角色名是否重名
 */
function isRnameHendiadysRole(rname) {
    var result = 0;
    $.ajax({
        url: pageDesignControl_HOST + 'manage/role/selectbypageandcondition',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        data: {"rname" : "","pageSize" : 10000},
        async: false,
        success: function (msg) {
            if (msg.status == 200) {
                var roleList = msg.msg;
                for (var i = 0; i < roleList.length; i++) {
                    if (roleList[i].rname == rname) {
                        result = 1;
                    }
                }
            }
        }
    });
    return result;
}

/**
 * 删除单个角色
 */
function removeRole(rid) {
    $.ajax({
        url: pageDesignControl_HOST + 'manage/role/removebyid',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        data: {"rid": rid},
        success: function (msg) {
            if (msg.status == 200) {
                getRoleList(1);
                alert("删除成功")
            }
        }
    });
}

/**
 * 角色管理所有checkbox全选
 */
function roleManageCheckbox() {
    if ($(".roleManage .roleManageCheckBoxManage").is(':checked')) {
        $(".roleManage .roleManageCheckBox").prop("checked", true);
    } else {
        $(".roleManage .roleManageCheckBox").prop("checked", false);
    }
}

// 权限树点击隐藏
$(function () {
    $("#tree").on("click", "li span", function () {
        $(this).parent().find("span").toggleClass("glyphicon-chevron-down");
        $(this).parent().find("span").toggleClass("glyphicon-chevron-up");
        $(this).parent().next("ul").toggle();
    });
})

/**
 * 权限树所有checkbox全选
 */
$(function () {
    $("#tree").on("click", ".treeCheckBox", function () {
        if ($(this).is(':checked')) {
            $(this).parent().next("ul").find(".treeCheckBox").prop("checked", true);
        } else {
            $(this).parent().next("ul").find(".treeCheckBox").prop("checked", false);
        }
    });
})

/**
 * 权限树回显
 *
 * @param rid
 */
function getPermissionByRoleId(rid) {
    $.ajax({
        url: pageDesignControl_HOST + 'manage/rolepermission/getpermissionbyroleid',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        data: {"rid": rid},
        success: function (msg) {
            if (msg.status == 200) {
                var list = msg.msg;
                for (var i = 0; i < list.length; i++) {
                    $(".tree" + list[i]).prop("checked", true);
                }
            }
        }
    });
}

// var permissionListStr = "";
// createTree(msg.msg, 0);
// function createTree(permissionList, num) {
//     for (var j = 0; j < permissionList.length; j++) {
//         var display = "";
//         if (num > 0) {
//             display = 'style="display: none;"';
//         }
//         var menu = "";
//         if (isMenuPermission(permissionList[j].ptype)) {
//             menu = '<span class="glyphicon glyphicon-chevron-right"></span>'
//         }
//         var width = num + 1;
//         permissionListStr += '<tr class="permission' + permissionList[j].ppid + '" id="permission' + permissionList[j].pid + '" ' + display + '>' +
//             '<td class="" style="padding-left: ' + width * 40 + 'px">' + menu + permissionList[j].pname +
//             '<input type="hidden" class="' + permissionList[j].pid + '" />' +
//             '</td>' +
//             '<td class="text-center">' + nullToEmptyPermission(permissionList[j].purl) + '</td>' +
//             '<td class="text-center">' + getMenuOrButtonPermission(permissionList[j].ptype) + '</td>' +
//             '<td class="text-center">' + nullToEmptyPermission(permissionList[j].ppermission) + '</td>' +
//             '<td class="text-center">' +
//             '<button type="button" class="btn btn-info delete" >删除</button>' +
//             '<input type="hidden" class="' + permissionList[j].pid + '" />' +
//             '<button type="button" class="btn btn-warning manageEdit permissionManageUpdate">修改</button>' +
//             '<input type="hidden" class="permissionManageEdit">' +
//             '</td>' +
//             '</tr>'
//         createTree(permissionList[j].childrenPermission, width)
//     }
// }
// $("#permissionManageTable").empty();
// $("#permissionManageTable").append(permissionListStr);

/**
 * 获取权限列表
 */
function getTreeList() {
    $.ajax({
        url: pageDesignControl_HOST + 'manage/permission/selecttree',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        data: {},
        success: function (msg) {
            if (msg.status == 200) {
                $("#tree").empty();
                tree(msg.msg, "tree" ,0);

                function tree(permissionList, parent ,num) {
                    for (var j = 0; j < permissionList.length; j++) {
                        var width = num + 1;
                        var menu = "";
                        var display = "";
                        if (num > 0) {
                            display = 'style="display: none; "';
                        }
                        if (isMenuPermission(permissionList[j].ptype)) {
                            menu = '<span class="glyphicon glyphicon-chevron-right"></span>'
                         }

                        var str = '<li class="tree' + permissionList[j].ppid + '" style="padding-left: ' + width * 40 + 'px" + display +>'
                            +menu
                            + '<input type="checkbox" class="treeCheckBox tree' + permissionList[j].pid + '" >'
                            + permissionList[j].pname
                            + '</li>'
                            + '<ul id="tree' + permissionList[j].pid + '" ></ul>'
                        $("#" + parent).append(str);
                        tree(permissionList[j].childrenPermission, "tree" + permissionList[j].pid ,width)
                    }
                }
            }
        }
    });
}

// 编辑角色权限信息
$(function () {
    // 点击添加
    $(".roleManage").on("click", ".rolePermissionEdit", function () {
        getTreeList();
        getPermissionByRoleId($(this).next().attr("class"));
    });
})

/**
 * 编辑角色权限信息
 */
function getRoleList(page) {
    var rname = $("#roleManageName").val();
    $.ajax({
        url: pageDesignControl_HOST + 'manage/role/selectbypageandcondition',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        data: {"rname": rname, "page": page},
        success: function (msg) {
            // var rolementList = JSON.stringify(msg.msg);
            if (msg.status == 200) {
                // 添加列表
                var roleListStr = "";
                for (var i = 0; i < msg.msg.length; i++) {
                    roleListStr += '<tr class="">' +
                        '<td class="text-center">' +
                        '<input type="checkbox" class="roleManageCheckBox">&nbsp;&nbsp;&nbsp;' + msg.msg[i].rname +
                        '<input type="hidden" class="' + msg.msg[i].rid + '" />' +
                        '</td>' +
                        '<td class="text-center">' +
                        '<button type="button" class="btn btn-info delete">删除</button>' +
                        '<input type="hidden" class="' + msg.msg[i].rid + '" />' +
                        '<button type="button" class="btn btn-warning manageEdit roleManageUpdate">修改</button>' +
                        '<input type="hidden" class="roleManageEdit">' +
                        '<button type="button" class="btn btn-warning manageEdit rolePermissionEdit" data-toggle="modal" data-target="#myModal">分配权限</button>' +
                        '<input type="hidden" class="' + msg.msg[i].rid + '" />' +
                        '</td>' +
                        '</tr>'
                }
                $("#roleManageTable").empty();
                $("#roleManageTable").append(roleListStr);
                // 回显搜索名
                $("#roleManageName").val(rname);
                // 是否显示分页
                if (msg.totalSize <= 10) {
                    $("#roleManagePageDiv").addClass("hidden");
                } else {
                    $("#roleManagePageDiv").removeClass("hidden");
                }
                // 分页具体信息
                var pageSpanStr = "第 " + msg.pageStart + " 条 到 " + (msg.pageEnd < msg.totalSize ? msg.pageEnd : msg.totalSize) + " 条 ，共 " + msg.totalSize + " 条记录, 当前第 " + msg.currentPage + "页";
                $("#roleManagePageSpan").text(pageSpanStr);
                var pageUlStr = "";
                if (parseInt(msg.currentPage) - 2 > 0) {
                    pageUlStr += '<li><a href="#">' + (parseInt(msg.currentPage) - 2) + '</a></li>';
                }
                if (parseInt(msg.currentPage) - 1 > 0) {
                    pageUlStr += '<li><a href="#">' + (parseInt(msg.currentPage) - 1) + '</a></li>';
                }
                pageUlStr += '<li><a href="#">' + msg.currentPage + '</a></li>';
                if (parseInt(msg.currentPage) + 1 <= parseInt(msg.totalPage)) {
                    pageUlStr += '<li><a href="#">' + (parseInt(msg.currentPage) + 1) + '</a></li>';
                }
                if (parseInt(msg.currentPage) + 2 <= parseInt(msg.totalPage)) {
                    pageUlStr += '<li><a href="#">' + (parseInt(msg.currentPage) + 2) + '</a></li>';
                }
                $("#roleManagePageUl").empty();
                $("#roleManagePageUl").append(pageUlStr);
            }
        }
    });
}

/**
 * 角色end
 */

/**
 * 权限start
 */
var permissionManageId = 0;
$(function () {
    // 左侧显示隐藏并获取权限列表
    $(".manageLeft").on("click", "#permissionManage", function () {
        $(".manageRight .rightManageDiv").addClass("hidden");
        $(".permissionManage").removeClass("hidden");
        getPermissionList();
    });

    // 删除单个权限
    $(".permissionManage").on("click", ".delete", function () {
        var r = confirm("是否删除!");
        if (r == true) {
            var pid = $(this).next().attr("class");
            removePermission(pid);
        }
    });

    // 增加权限
    $(".permissionManageEdit").on("click", "#savePermission", function () {
        savePermission();
    });

    // 更改权限
    $(".permissionManageEdit").on("click", "#updatePermission", function () {
        updatePermission();
    });
})

// 权限管理
$(function () {
    // 点击添加
    $(".manageRight").on("click", ".permissionManageAdd", function () {
        $(".manageRight .permissionManage").addClass("hidden");
        $(".manageRight").children(".permissionManageEdit").removeClass("hidden");
        $(".Permission").attr("id", "savePermission");
        $(".permissionManageEdit #pname").val("");
        $(".permissionManageEdit #purl").val("");
        $(".permissionManageEdit #ptype").val("1");
        $(".permissionManageEdit #ppermission").val("");
        $(".permissionManageEdit #ppid").attr("disabled", false);
        getPermissionMuneList();
    });
    // 点击更新
    $(".manageRight").on("click", ".permissionManageUpdate", function () {
        permissionManageId = $(this).prev().attr("class");
        $(".manageRight .permissionManage").addClass("hidden");
        $(".manageRight").children(".permissionManageEdit").removeClass("hidden");
        $(".Permission").attr("id", "updatePermission");
        getPermissionMuneList();
        getPermission();
    });
    // 点击返回
    $(".manageRight").on("click", ".permissionManageBack", function () {
        $(".manageRight .permissionManage").removeClass("hidden");
        $(".manageRight").children(".permissionManageEdit").addClass("hidden");
        getPermissionList();
    });
})

/**
 * 获取单个权限
 */
function getPermission() {
    var pid = permissionManageId;
    $.ajax({
        url: pageDesignControl_HOST + '/manage/permission/selectbyid',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        async: false,
        data: {"pid": pid},
        success: function (msg) {
            // var permissionList = JSON.stringify(msg.msg);
            if (msg.status == 200) {
                $(".permissionManageEdit #pname").val(msg.msg.pname);
                $(".permissionManageEdit #purl").val(msg.msg.purl);
                $(".permissionManageEdit #ptype").val(msg.msg.ptype);
                $(".permissionManageEdit #ppermission").val(msg.msg.ppermission);
                $(".permissionManageEdit #ppid").val(msg.msg.ppid);
                $(".permissionManageEdit #ppid").attr("disabled", "disabled");
            }
        }
    });
}

/**
 * 权限类型改变事件
 */
$(function () {
    // 点击添加
    $(".permissionManageEdit").on("change", "#ppid", function () {
        $(".permissionManageEdit #ptype").val(parseInt($(".permissionManageEdit #ppid option:selected").attr("class")) + 1)
    });
})

/**
 * 更改权限
 */
function updatePermission() {
    var pid = permissionManageId;
    var pname = $(".permissionManageEdit #pname").val();
    var ppid = $(".permissionManageEdit #ppid option:selected").val();
    var purl = $(".permissionManageEdit #purl").val();
    var ptype = $(".permissionManageEdit #ptype").val();
    var ppermission = $(".permissionManageEdit #ppermission").val();
    if (!pname) {
        alert("权限名称不能为空");
    } else if (isPnameHendiadysPermission(pname)) {
        alert("权限名称不能重复");
    } else {
        $.ajax({
            url: pageDesignControl_HOST + 'manage/permission/updatebyid',
            type: 'Get',
            contentType: 'application/json',
            dataType: 'json',
            data: {"ppid": ppid, "pname": pname, "purl": purl, "ptype": ptype, "ppermission": ppermission, "pid": pid},
            success: function (msg) {
                // var permissionList = JSON.stringify(msg.msg);
                if (msg.status == 200) {
                    alert("更改成功")
                }
            }
        });
    }
}

/**
 * 添加权限
 */
function savePermission() {
    var pname = $(".permissionManageEdit #pname").val();
    var ppid = $(".permissionManageEdit #ppid option:selected").val();
    var purl = $(".permissionManageEdit #purl").val();
    var ptype = $(".permissionManageEdit #ptype").val();
    var ppermission = $(".permissionManageEdit #ppermission").val();
    if (!pname) {
        alert("权限名称不能为空");
    } else if (isPnameHendiadysPermission(pname)) {
        alert("权限名称不能重复");
    } else {
        $.ajax({
            url: pageDesignControl_HOST + 'manage/permission/save',
            type: 'Get',
            contentType: 'application/json',
            dataType: 'json',
            data: {"ppid": ppid, "pname": pname, "purl": purl, "ptype": ptype, "ppermission": ppermission},
            success: function (msg) {
                // var permissionList = JSON.stringify(msg.msg);
                if (msg.status == 200) {
                    alert("添加成功")
                }
            }
        });
    }
}

/**
 * 删除单个权限
 */
function removePermission(pid) {
    $.ajax({
        url: pageDesignControl_HOST + 'manage/permission/removebyid',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        data: {"pid": pid},
        success: function (msg) {
            if (msg.status == 200) {
                getPermissionList();
                alert("删除成功")
            }
        }
    });
}

/**
 * 权限列表点击隐藏
 */
$(function () {
    $("html").on("click", "tr", function () {
        $(this).find("span").toggleClass("glyphicon-chevron-down");
        $(this).find("span").toggleClass("glyphicon-chevron-up");
        var isTrue = $('.' + $(this).attr("id")).eq(0).is(':visible');
        hiddenTree($(this).attr("id"), isTrue);

        function hiddenTree(list, isture) {
            for (var j = 0; j < $("." + list).length; j++) {
                if ($("#" + $("." + list).eq(j).attr("id")).length > 0) {
                    if (isture) {
                        $("." + $("." + list).eq(j).attr("class")).hide();
                    } else {
                        $("." + $("." + list).eq(j).attr("class")).show();
                    }
                    hiddenTree($("." + list).eq(j).attr("id"), isture);
                } else {
                    if (isture) {
                        $("." + $("." + list).eq(j).attr("class")).hide();
                    } else {
                        $("." + $("." + list).eq(j).attr("class")).show();
                    }
                }
            }
        }
    });
})

/**
 * 获取权限列表
 */
function getPermissionList() {
    $.ajax({
        url: pageDesignControl_HOST + 'manage/permission/selecttree',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        data: {},
        success: function (msg) {
            if (msg.status == 200) {
                var permissionListStr = "";
                createTree(msg.msg, 0);

                function createTree(permissionList, num) {
                    for (var j = 0; j < permissionList.length; j++) {
                        var display = "";
                        if (num > 0) {
                            display = 'style="display: none;"';
                        }
                        var menu = "";
                        if (isMenuPermission(permissionList[j].ptype)) {
                            menu = '<span class="glyphicon glyphicon-chevron-right"></span>'
                        }
                        var width = num + 1;
                        permissionListStr += '<tr class="permission' + permissionList[j].ppid + '" id="permission' + permissionList[j].pid + '" ' + display + '>' +
                            '<td class="" style="padding-left: ' + width * 40 + 'px">' + menu + permissionList[j].pname +
                            '<input type="hidden" class="' + permissionList[j].pid + '" />' +
                            '</td>' +
                            '<td class="text-center">' + nullToEmptyPermission(permissionList[j].purl) + '</td>' +
                            '<td class="text-center">' + getMenuOrButtonPermission(permissionList[j].ptype) + '</td>' +
                            '<td class="text-center">' + nullToEmptyPermission(permissionList[j].ppermission) + '</td>' +
                            '<td class="text-center">' +
                            '<button type="button" class="btn btn-info delete" >删除</button>' +
                            '<input type="hidden" class="' + permissionList[j].pid + '" />' +
                            '<button type="button" class="btn btn-warning manageEdit permissionManageUpdate">修改</button>' +
                            '<input type="hidden" class="permissionManageEdit">' +
                            '</td>' +
                            '</tr>'
                        createTree(permissionList[j].childrenPermission, width)
                    }
                }

                $("#permissionManageTable").empty();
                $("#permissionManageTable").append(permissionListStr);
            }
        }
    });
}

/**
 * 生成权限菜单
 */
function getPermissionMuneList() {
    $.ajax({
        url: pageDesignControl_HOST + 'manage/permission/selecttree',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        data: {},
        async: false,
        success: function (msg) {
            if (msg.status == 200) {
                var permissionListMuneStr = '<option value="-1" selected = "selected" class="0">无父类菜单</option>';
                createTreetMune(msg.msg);

                function createTreetMune(permissionList) {
                    for (var j = 0; j < permissionList.length; j++) {
                        if (permissionList[j].ptype == 1) {
                            permissionListMuneStr += '<option value="' + permissionList[j].pid + '" class="' + permissionList[j].ptype + '">' + permissionList[j].pname + '(一级菜单)</option>'
                            createTreetMune(permissionList[j].childrenPermission)
                        } else if (permissionList[j].ptype == 2) {
                            permissionListMuneStr += '<option value="' + permissionList[j].pid + '" class="' + permissionList[j].ptype + '">' + permissionList[j].pname + '(二级菜单)</option>'
                            createTreetMune(permissionList[j].childrenPermission)
                        }
                    }
                }

                console.log(permissionListMuneStr)
                $(".permissionManageEdit #ppid").empty();
                $(".permissionManageEdit #ppid").append(permissionListMuneStr);
            }
        }
    });
}

/**
 * 判断权限名是否重名
 */
function isPnameHendiadysPermission(pname) {
    var result = 0;
    $.ajax({
        url: pageDesignControl_HOST + 'manage/permission/list',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        data: {},
        async: false,
        success: function (msg) {
            if (msg.status == 200) {
                var permissionList = msg.msg;
                for (var i = 0; i < permissionList.length; i++) {
                    if (permissionList[i].pname == pname) {
                        result = 1;
                    }
                }
            }
        }
    });
    return result;
}

/**
 * 是否为菜单（权限）
 */
function isMenuPermission(str) {
    if (str == 1 || str == 2) {
        return 1;
    } else {
        return 0;
    }
}

/**
 * 获得菜单还是按钮（权限）
 */
function getMenuOrButtonPermission(str) {
    if (str == 1 || str == 2) {
        return "菜单";
    } else if (str == 3) {
        return "按钮";
    } else {
        return "异常";
    }
}

/**
 * 将null改成空(权限)
 */
function nullToEmptyPermission(str) {
    if (str) {
        return str;
    } else {
        return "";
    }
}

/**
 * 权限end
 */

/**
 * 左侧手风琴显示隐藏
 */
$(function () {
    $(".manageLeft").on("click", ".organizationHeaderOrganizationClick", function () {
        $(this).find("span").toggleClass("glyphicon-chevron-down");
        $(this).find("span").toggleClass("glyphicon-chevron-up");
        $(this).parent().children("ul").toggle();
    });
})

/**
 * 日期插件
 */
$(document).ready(function () {
    $('#userManageDateBegin').bootstrapMaterialDatePicker({
        time: false
    });
    $('#userManageDateEnd').bootstrapMaterialDatePicker({
        time: false
    });
    $('#time').bootstrapMaterialDatePicker({
        date: false,
        shortTime: true,
        format: 'HH:mm'
    });
    $('#date-format').bootstrapMaterialDatePicker({
        format: 'dddd DD MMMM YYYY - HH:mm'
    });
    $('#date-fr').bootstrapMaterialDatePicker({
        format: 'DD/MM/YYYY HH:mm',
        lang: 'fr',
        weekStart: 1,
        cancelText: 'ANNULER'
    });
    $('#date-end').bootstrapMaterialDatePicker({
        weekStart: 0,
        format: 'DD/MM/YYYY HH:mm'
    });
    $('#date-start').bootstrapMaterialDatePicker({
        weekStart: 0,
        format: 'DD/MM/YYYY HH:mm'
    }).on('change', function (e, date) {
        $('#date-end').bootstrapMaterialDatePicker('setMinDate', date);
    });
    $('#min-date').bootstrapMaterialDatePicker({
        format: 'DD/MM/YYYY HH:mm',
        minDate: new Date()
    });
});

/**
 * 公共方法end
 */