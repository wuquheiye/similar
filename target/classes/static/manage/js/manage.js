/**
 * 权限start
 */
var permissionManageId = 0;
$(function () {
    // 左侧点击变色和显示隐藏并获取权限列表
    $(".manageLeft").on("click", "#permissionManage", function () {
        $("#permissionManageName").val("");
        if (!$(this).is('.manageActive')) {
            $(".manageLeft .manageClick").removeClass("manageActive");
            $(this).addClass("manageActive");
            $(".manageRight .rightManageDiv").addClass("hidden");
            $(".permissionManage").removeClass("hidden");
        }
        getPermissionList(1);
    });

    // 点击分页
    $("#permissionManagePageDiv").on("click", "a", function () {
        getPermissionList($(this).text());
    });

    // 清除
    $(".permissionManage").on("click", "#permissionManageClean", function () {
        $("#permissionManageName").val("");
    });

    // 查询
    $(".permissionManage").on("click", "#permissionManageSearch", function () {
        getPermissionList(1)
    });

    // 删除单个权限
    $(".permissionManage").on("click", ".delete", function () {
        var r = confirm("是否删除!");
        if (r == true) {
            var pid = $(this).next().attr("class");
            removePermission(pid);
        }
    });

    // 删除多个权限
    $(".permissionManage").on("click", ".permissionManageDelete", function () {
        var r = confirm("是否删除!");
        if (r == true) {
            $("input:checkbox[class=permissionManageCheckBox]:checked").each(function () {
                var pid = $(this).next().attr("class");
                removePermission(pid);
            });
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
        permissionManageId = -1;
        $(".manageRight .permissionManage").addClass("hidden");
        $(".manageRight").children(".permissionManageEdit").removeClass("hidden");
        $(".Permission").attr("id", "savePermission");
        $(".permissionManageEdit #dname").val("");
    });
    // 点击更新
    $(".manageRight").on("click", ".permissionManageUpdate", function () {
        permissionManageId = $(this).prev().attr("class");
        getPermission();
        $(".manageRight .permissionManage").addClass("hidden");
        $(".manageRight").children(".permissionManageEdit").removeClass("hidden");
        $(".Permission").attr("id", "updatePermission");
    });
    // 点击返回
    $(".manageRight").on("click", ".permissionManageBack", function () {
        $(".manageRight .permissionManage").removeClass("hidden");
        $(".manageRight").children(".permissionManageEdit").addClass("hidden");
        getPermissionList(1);
    });
})

/**
 * 获取权限列表
 */
function getPermission() {
    var pid = permissionManageId;
    $.ajax({
        url: pageDesignControl_HOST + '/manage/permission/selectbyid',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        data: {"pid": pid},
        success: function (msg) {
            var permissionList = JSON.stringify(msg.msg);
            if (msg.status == 200) {
                // $(".permissionManageEdit #dname").attr("value",msg.msg.pname);
                $(".permissionManageEdit #pname").val(msg.msg.pname);
                $(".permissionManageEdit #ptype").val(msg.msg.ptype);
            }
        }
    });
}

/**
 * 更改权限
 */
function updatePermission() {
    var pid = permissionManageId;
    var pname = $(".permissionManageEdit #pname").val();
    var ptype = $(".permissionManageEdit #ptype").val();
    $.ajax({
        url: pageDesignControl_HOST + 'manage/permission/updatebyid',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        data: {"ptype": ptype,"pname": pname, "pid": pid},
        success: function (msg) {
            var permissionList = JSON.stringify(msg.msg);
            if (msg.status == 200) {
                $(".permissionManageEdit #pname").val("");
                $(".permissionManageEdit #ptype").val("");
                alert("更改成功")
            }
        }
    });
}

/**
 * 添加权限
 */
function savePermission() {
    var pname = $(".permissionManageEdit #pname").val();
    var ptype = $(".permissionManageEdit #ptype").val();
    $.ajax({
        url: pageDesignControl_HOST + 'manage/permission/save',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        data: {"pname": pname,"ptype": ptype},
        success: function (msg) {
            var permissionList = JSON.stringify(msg.msg);
            if (msg.status == 200) {
                $(".permissionManageEdit #pname").val("");
                $(".permissionManageEdit #ptype").val("");
                alert("添加成功")
            }
        }
    });
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
                getPermissionList(1);
                alert("删除成功")
            }
        }
    });
}

// 权限管理所有checkbox全选
function permissionManageCheckbox() {
    if ($(".permissionManage .permissionManageCheckBoxManage").is(':checked')) {
        $(".permissionManage .permissionManageCheckBox").prop("checked", true);
    } else {
        $(".permissionManage .permissionManageCheckBox").prop("checked", false);
    }
}

/**
 * 获取权限列表
 */
function getPermissionList(page) {
    var pname = $("#permissionManageName").val();
    $.ajax({
        url: pageDesignControl_HOST + 'manage/permission/selectbypageandcondition',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        data: {"pname": pname, "page": page},
        success: function (msg) {
            var permissionList = JSON.stringify(msg.msg);
            if (msg.status == 200) {
                // 添加列表
                var permissionListStr = "";
                for (var i = 0; i < msg.msg.length; i++) {
                    permissionListStr += '<tr class="active">' +
                        '<td class="text-center">' +
                        '<input type="checkbox" class="permissionManageCheckBox">' + msg.msg[i].pname +
                        '<input type="hidden" class="' + msg.msg[i].pid + '" />' +
                        '</td>' +
                        '<td class="text-center">' +
                        '<input type="checkbox" class="permissionManageCheckBox">' + msg.msg[i].ptype +
                        '</td>' +
                        '<td class="text-center">' +
                        '<button type="button" class="btn btn-info delete" >删除</button>' +
                        '<input type="hidden" class="' + msg.msg[i].pid + '" />' +
                        '<button type="button" class="btn btn-warning manageEdit permissionManageUpdate">修改</button>' +
                        '<input type="hidden" class="permissionManageEdit">' +
                        '</td>' +
                        '</tr>'
                }
                $("#permissionManageTable").empty();
                $("#permissionManageTable").append(permissionListStr);
                // 回显搜索名
                $("#permissionManageName").val(pname);
                // 是否显示分页
                if (msg.totalSize <= 10) {
                    $("#permissionManagePageDiv").addClass("hidden");
                } else {
                    $("#permissionManagePageDiv").removeClass("hidden");
                }
                // 分页具体信息
                var pageSpanStr = "第 " + msg.pageStart + " 条 到 " + (msg.pageEnd < msg.totalSize ? msg.pageEnd : msg.totalSize) + " 条 ，共 " + msg.totalSize + " 条记录, 当前第 " + msg.currentPage + "页";
                $("#permissionManagePageSpan").text(pageSpanStr);
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
                $("#permissionManagePageUl").empty();
                $("#permissionManagePageUl").append(pageUlStr);
            }
        }
    });
}

/**
 * 权限end
 */

/**
 * 角色start
 */
var roleManageId = 0;
$(function () {
    // 左侧点击变色和显示隐藏并获取角色列表
    $(".manageLeft").on("click", "#roleManage", function () {
        $("#roleManageName").val("");
        if (!$(this).is('.manageActive')) {
            $(".manageLeft .manageClick").removeClass("manageActive");
            $(this).addClass("manageActive");
            $(".manageRight .rightManageDiv").addClass("hidden");
            $(".roleManage").removeClass("hidden");
        }
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
            var roleList = JSON.stringify(msg.msg);
            if (msg.status == 200) {
                // $(".roleManageEdit #rname").attr("value",msg.msg.rname);
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
    $.ajax({
        url: pageDesignControl_HOST + 'manage/role/updatebyid',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        data: {"rname": rname, "rid": rid},
        success: function (msg) {
            var roleList = JSON.stringify(msg.msg);
            if (msg.status == 200) {
                $(".roleManageEdit #rname").val("");
                alert("更改成功")
            }
        }
    });
}

/**
 * 添加角色
 */
function saveRole() {
    var rname = $(".roleManageEdit #rname").val();
    $.ajax({
        url: pageDesignControl_HOST + 'manage/role/save',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        data: {"rname": rname},
        success: function (msg) {
            var roleList = JSON.stringify(msg.msg);
            if (msg.status == 200) {
                $(".roleManageEdit #rname").val("");
                alert("添加成功")
            }
        }
    });
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

// 角色管理所有checkbox全选
function roleManageCheckbox() {
    if ($(".roleManage .roleManageCheckBoxManage").is(':checked')) {
        $(".roleManage .roleManageCheckBox").prop("checked", true);
    } else {
        $(".roleManage .roleManageCheckBox").prop("checked", false);
    }
}

/**
 * 获取角色列表
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
            var rolementList = JSON.stringify(msg.msg);
            if (msg.status == 200) {
                // 添加列表
                var roleListStr = "";
                for (var i = 0; i < msg.msg.length; i++) {
                    roleListStr += '<tr class="active">' +
                        '<td class="text-center">' +
                        '<input type="checkbox" class="roleManageCheckBox">' + msg.msg[i].rname +
                        '<input type="hidden" class="' + msg.msg[i].rid + '" />' +
                        '</td>' +
                        '<td class="text-center">' +
                        '<button type="button" class="btn btn-info delete">删除</button>' +
                        '<input type="hidden" class="' + msg.msg[i].rid + '" />' +
                        '<button type="button" class="btn btn-warning manageEdit roleManageUpdate">修改</button>' +
                        '<input type="hidden" class="roleManageEdit">' +
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
 * 部门start
 */
var departmentManageId = 0;
$(function () {
    // 左侧点击变色和显示隐藏并获取部门列表
    $(".manageLeft").on("click", "#departmentManage", function () {
        $("#departmentManageName").val("");
        if (!$(this).is('.manageActive')) {
            $(".manageLeft .manageClick").removeClass("manageActive");
            $(this).addClass("manageActive");
            $(".manageRight .rightManageDiv").addClass("hidden");
            $(".departmentManage").removeClass("hidden");
        }
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
            var departmentList = JSON.stringify(msg.msg);
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
            var departmentList = JSON.stringify(msg.msg);
            if (msg.status == 200) {
                $(".departmentManageEdit #dname").val("");
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
            var departmentList = JSON.stringify(msg.msg);
            if (msg.status == 200) {
                $(".departmentManageEdit #dname").val("");
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

// 部门管理所有checkbox全选
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
            var departmentList = JSON.stringify(msg.msg);
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

// // 用户管理启用
// $(function () {
//     $(".manageRight").on("click", ".manageEdit", function () {
//         $(".manageLeft .manageClick").removeClass("manageActive");
//         $(".manageRight").children("." + $(this).next().attr("class")).addClass("manageActive");
//         $(".manageRight .rightManageDiv").addClass("hidden");
//         $(".manageRight").children("." + $(this).next().attr("class")).removeClass("hidden");
//     });
// })
// 用户管理启用
$(function () {
    $(".userManage").on("click", ".button", function () {
        if ($(this).val() == "true") {
            $(this).val("false");
            $(this).text("启用");
        } else {
            $(this).val("true");
            $(this).text("停用");
        }
    });
})

// 用户管理所有checkbox全选
function userManageCheckbox() {
    if ($(".userManage .userManageCheckBoxManage").is(':checked')) {
        $(".userManage .userManageCheckBox").prop("checked", true);
    } else {
        $(".userManage .userManageCheckBox").prop("checked", false);
    }
}

// 用户管理清除
function userManageClean() {
    $("#userManageName").val("");
    $("#userManageDateBegin").val("");
    $("#userManageDateEnd").val("");
}

// $(function () {
//     // 左侧点击变色和显示隐藏
//     $(".manageLeft").on("click", ".manageClick", function () {
//         if (!$(this).is('.manageActive')) {
//             // 显示隐藏start
//             $(".manageLeft .manageClick").removeClass("manageActive");
//             $(this).addClass("manageActive");
//             $(".manageRight .rightManageDiv").addClass("hidden");
//             $("." + $(this).attr("id")).removeClass("hidden");
//             // 显示隐藏end
//         }
//     });
// })
$(function () {
    // 左侧手风琴显示隐藏start
    $(".manageLeft").on("click", ".organizationHeaderOrganizationClick", function () {
        $(this).find("span").toggleClass("glyphicon-chevron-down");
        $(this).find("span").toggleClass("glyphicon-chevron-up");
        $(this).parent().children("ul").toggle();
    });
    // 左侧手风琴显示隐藏end
})

// 日期插件
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
