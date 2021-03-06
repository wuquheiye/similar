
/**
 * 用户start
 */
var usersManageId = 0;
var usersUusername = "";
$(function () {
    /**
     * 左侧显示隐藏并获取用户列表
     */
    $(".manageLeft").on("click", "#usersManage", function () {
        $("#usersManageName").val("");
        $(".manageRight .rightManageDiv").addClass("hidden");
        $(".usersManage").removeClass("hidden");
        getUsersList(1);
    });

    /**
     * 点击分页
     */
    $("#usersManagePageDiv").on("click", "personInfomation.css", function () {
        getUsersList($(this).text());
    });

    /**
     * 清除
     */
    $(".usersManage").on("click", "#usersManageClean", function () {
        $("#usersManageName").val("");
    });

    /**
     * 查询
     */
    $(".usersManage").on("click", "#usersManageSearch", function () {
        getUsersList(1)
    });

    /**
     * 删除单个用户
     */
    $(".usersManage").on("click", ".delete", function () {
        var r = confirm("是否删除!");
        if (r == true) {
            var uid = $(this).next().attr("class");
            removeUsers(uid);
        }
    });

    /**
     * 删除多个用户
     */
    $(".usersManage").on("click", ".usersManageDelete", function () {
        var r = confirm("是否删除!");
        if (r == true) {
            $("input:checkbox[class=usersManageCheckBox]:checked").each(function () {
                var uid = $(this).next().attr("class");
                removeUsers(uid);
            });
        }
    });

    /**
     * 增加用户
     */
    $(".usersManageEdit").on("click", "#saveUsers", function () {
        saveUsers();
    });

    /**
     * 更改用户
     */
    $(".usersManageEdit").on("click", "#updateUsers", function () {
        updateUsers();
    });

})

/**
 * 用户管理
 */
$(function () {
    /**
     * 点击添加
     */
    $(".manageRight").on("click", ".usersManageAdd", function () {
        $(".manageRight .usersManage").addClass("hidden");
        $(".manageRight").children(".usersManageEdit").removeClass("hidden");
        $(".Users").attr("id", "saveUsers");
        $(".usersManageEdit #uusername").val("");
        $(".usersManageEdit #utelephonenumber").val("");
        $(".usersManageEdit #upassword").val("");
    });

    /**
     * 点击更新
     */
    $(".manageRight").on("click", ".usersManageUpdate", function () {
        usersManageId = $(this).prev().attr("class");
        getUsers();
        $(".manageRight .usersManage").addClass("hidden");
        $(".manageRight").children(".usersManageEdit").removeClass("hidden");
        $(".Users").attr("id", "updateUsers");
    });

    /**
     * 点击返回
     */
    $(".manageRight").on("click", ".usersManageBack", function () {
        $(".manageRight .usersManage").removeClass("hidden");
        $(".manageRight").children(".usersManageEdit").addClass("hidden");
        getUsersList(1);
    });

    /**
     * 点击控制状态
     */
    $(".manageRight").on("click", "#usersUstate", function () {
        uid = $(this).next().val();
        ustate = $(this).next().attr("class");
        if (ustate == 0) {
            ustate = 1;
            $(this).text("停用");
            $(this).next().attr("class", 1);
        } else {
            ustate = 0;
            $(this).text("启用");
            $(this).next().attr("class", 0);
        }
        updateUustate(uid, ustate);
    });
})

/**
 * 获取单个用户
 */
function getUsers() {
    var uid = usersManageId;
    $.ajax({
        url: pageDesignControl_HOST + '/manage/users/getbyid',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        async: false,
        data: {"uid": uid},
        success: function (msg) {
            if (msg.status == 200) {
                usersUusername = msg.msg.uusername;
                $(".usersManageEdit #uusername").val(msg.msg.uusername);
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
    var utelephonenumber = $(".usersManageEdit #utelephonenumber").val();
    var upassword = $(".usersManageEdit #upassword").val();
    if (!uusername) {
        alert("权限名称不能为空");
    } else if (isUnameHendiadysUsers(uusername) && usersUusername != uusername) {
        alert("权限名称不能重复");
    } else {
        $.ajax({
            url: pageDesignControl_HOST + 'manage/users/updatebyid',
            type: 'Get',
            contentType: 'application/json',
            dataType: 'json',
            data: {
                "uid": uid,
                "uusername": uusername,
                "utelephonenumber": utelephonenumber,
                "upassword": upassword
            },
            success: function (msg) {
                if (msg.status == 200) {
                    alert("更改成功")
                } else {
                    alert(msg.statusMsg)
                }
            }
        });
    }
}

/**
 * 更改用户状态
 */
function updateUustate(uid, ustate) {
    var r = confirm("是否更改用户状态!");
    if (r == true) {
        $.ajax({
            url: pageDesignControl_HOST + 'manage/users/updateuustate',
            type: 'Get',
            contentType: 'application/json',
            dataType: 'json',
            data: {
                "uid": uid,
                "ustate": ustate
            },
            success: function (msg) {
                if (msg.status == 200) {
                    alert("更改成功")
                } else {
                    alert(msg.statusMsg)
                }
            }
        });
    }
}

/**
 * 判断用户是否重名
 */
function isUnameHendiadysUsers(uusername) {
    var result = 0;
    $.ajax({
        url: pageDesignControl_HOST + 'manage/users/selectbypageandcondition',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        data: {"uusername": "", "pageSize": 10000},
        async: false,
        success: function (msg) {
            if (msg.status == 200) {
                var usersList = msg.msg;
                for (var i = 0; i < usersList.length; i++) {
                    if (usersList[i].uusername == uusername) {
                        result = 1;
                    }
                }
            }
        }
    });
    return result;
}

/**
 * 添加用户
 */
function saveUsers() {
    var uusername = $(".usersManageEdit #uusername").val();
    var utelephonenumber = $(".usersManageEdit #utelephonenumber").val();
    var upassword = $(".usersManageEdit #upassword").val();
    if (!uusername) {
        alert("权限名称不能为空");
    } else if (isUnameHendiadysUsers(uusername)) {
        alert("权限名称不能重复");
    } else {
        $.ajax({
            url: pageDesignControl_HOST + 'manage/users/save',
            type: 'Get',
            contentType: 'application/json',
            dataType: 'json',
            data: {
                "uusername": uusername,
                "utelephonenumber": utelephonenumber,
                "upassword": upassword
            },
            success: function (msg) {
                if (msg.status == 200) {
                    alert("添加成功")
                } else {
                    alert(msg.statusMsg)
                }
            }
        });
    }
}

/**
 * 删除单个用户
 */
function removeUsers(uid) {
    var r = confirm("是否删除!");
    if (r == true) {
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
                } else {
                    alert(msg.statusMsg)
                }
            }
        });
    }
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
            if (msg.status == 200) {
                // 添加列表
                var usersListStr = "";
                for (var i = 0; i < msg.msg.length; i++) {
                    usersListStr += '<tr class="">' +
                        '<td class="text-center">' +
                        '<input type="checkbox" class="usersManageCheckBox">' + msg.msg[i].uid +
                        '<input type="hidden" class="' + msg.msg[i].uid + '" />' +
                        '</td>' +
                        '<td class="text-center">' + msg.msg[i].uusername + '</td>' +
                        '<td class="text-center">' + msg.msg[i].utelephonenumber + '</td>' +
                        '<td class="text-center">' +
                        '<button type="button" class="btn btn-danger" id = "usersUstate">' + (msg.msg[i].ustate == 0 ? "启用" : "停用") + '</button>' +
                        '<input type="hidden" value="' + msg.msg[i].uid + '" class="' + msg.msg[i].ustate + '"/>' +
                        '</td>' +
                        '<td class="text-center">' + msg.msg[i].ucreationtime + '</td>' +
                        '<td class="text-center">' +
                        '<button type="button" class="btn btn-info delete">删除</button>' +
                        '<input type="hidden" class="' + msg.msg[i].uid + '" />' +
                        '<button type="button" class="btn btn-warning manageEdit usersManageUpdate">修改</button>' +
                        '<input type="hidden" class="usersManageEdit">' +
                        '<button type="button" class="btn btn-warning manageEdit usersRoleEdit" data-toggle="modal" data-target="#usersRoleEdit">分配角色</button>' +
                        '<input type="hidden" class="' + msg.msg[i].uid + '" />' +
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
 * 用户角色关系start
 */
/**
 * 用户角色关系生成权限树并回显
 */
var userRoleId = 0;
$(function () {
    $(".usersManage").on("click", ".usersRoleEdit", function () {
        getUserRoleList();
        var uid = $(this).next().attr("class");
        userRoleId = uid;
        getRoleByUserId(userRoleId);
    });
})

/**
 * 获取权限树Role列表
 */
function getUserRoleList() {
    $.ajax({
        url: pageDesignControl_HOST + 'manage/role/selectbypageandcondition',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        data: {"rname": "", "pageSize": 10000},
        async: false,
        success: function (msg) {
            if (msg.status == 200) {
                $("#usersRole").empty();
                var str = "";
                for (var i = 0; i < msg.msg.length; i++) {
                    str += '<button type="button" class="button btn btn-default usersRole' + msg.msg[i].rid + '">' + msg.msg[i].rname + '</button>'
                        + '<input type="hidden" value="' + msg.msg[i].rid + '" />'
                }
                $("#usersRole").append(str);
            }
        }
    });
}

/**
 * 用户角色关系权限树回显
 */
function getRoleByUserId(uid) {
    $.ajax({
        url: pageDesignControl_HOST + 'manage/userrole/getrolebyuserid',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        data: {"uid": uid},
        async: false,
        success: function (msg) {
            if (msg.status == 200) {
                var list = msg.msg;
                for (var i = 0; i < list.length; i++) {
                    $(".usersRole" + list[i]).removeClass("btn-default");
                    $(".usersRole" + list[i]).addClass("btn-warning");
                }
            }
        }
    });
}

/**
 * 点击是否取消角
 */
$(function () {
    $("#usersRole").on("click", ".button", function () {
        if (!$(this).hasClass("btn-warning")) {
            $("#usersRole .button").removeClass("btn-warning");
            $(this).addClass("btn-warning");
        }
    });
})

/**
 * 获取角色权限关系列表被选中的CheckBox值，先根据rid删除所有，在重新生成
 */
$(function () {
    $("#usersRoleEdit").on("click", "#usersRoleSubmit", function () {
        $.ajax({
            url: pageDesignControl_HOST + 'manage/userrole/deletebyuserid',
            type: 'Get',
            contentType: 'application/json',
            dataType: 'json',
            data: {"uid": userRoleId},
            async: false,
            success: function (msg) {
                $.ajax({
                    url: pageDesignControl_HOST + 'manage/userrole/save',
                    type: 'get',
                    contentType: 'application/json',
                    dataType: 'json',
                    async: false,
                    data: {"uid": userRoleId, "rid": $("#usersRole .btn-warning").next().val()},
                    success: function (msg) {
                        if (msg.status == 200) {
                            getRoleByUserId(userRoleId);
                            alert("修改成功")
                        }
                    }
                });
            }
        });
    });
})
/**
 * 用户角色关系end
 */


/**
 * 角色start
 */
var roleManageId = 0;
var roleRname = 0;
$(function () {
    /**
     * 左侧显示隐藏并获取角色列表
     */
    $(".manageLeft").on("click", "#roleManage", function () {
        $("#roleManageName").val("");
        $(".manageRight .rightManageDiv").addClass("hidden");
        $(".roleManage").removeClass("hidden");
        getRoleList(1);
    });

    /**
     * 点击分页
     */
    $("#roleManagePageDiv").on("click", "personInfomation.css", function () {
        getRoleList($(this).text());
    });

    /**
     * 清除
     */
    $(".roleManage").on("click", "#roleManageClean", function () {
        $("#roleManageName").val("");
    });

    /**
     * 查询
     */
    $(".roleManage").on("click", "#roleManageSearch", function () {
        getRoleList(1)
    });

    /**
     * 删除单个角色
     */
    $(".roleManage").on("click", ".delete", function () {
        var r = confirm("是否删除!");
        if (r == true) {
            var rid = $(this).next().attr("class");
            removeRole(rid);
        }
    });

    /**
     * 增加角色
     */
    $(".roleManageEdit").on("click", "#saveRole", function () {
        saveRole();
    });

    /**
     * 更改角色
     */
    $(".roleManageEdit").on("click", "#updateRole", function () {
        updateRole();
    });
})

/**
 * 角色管理
 */
$(function () {
    /**
     * 点击添加
     */
    $(".manageRight").on("click", ".roleManageAdd", function () {
        $(".manageRight .roleManage").addClass("hidden");
        $(".manageRight").children(".roleManageEdit").removeClass("hidden");
        $(".Role").attr("id", "saveRole");
        $(".roleManageEdit #name").val("");
    });

    /**
     * 点击更新
     */
    $(".manageRight").on("click", ".roleManageUpdate", function () {
        roleManageId = $(this).prev().attr("class");
        getRole();
        $(".manageRight .roleManage").addClass("hidden");
        $(".manageRight").children(".roleManageEdit").removeClass("hidden");
        $(".Role").attr("id", "updateRole");
    });

    /**
     * 点击返回
     */
    $(".manageRight").on("click", ".roleManageBack", function () {
        $(".manageRight .roleManage").removeClass("hidden");
        $(".manageRight").children(".roleManageEdit").addClass("hidden");
        getRoleList(1);
    });
})

/**
 * 获取单个角色
 */
function getRole() {
    var rid = roleManageId;
    $.ajax({
        url: pageDesignControl_HOST + '/manage/role/getbyid',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        data: {"rid": rid},
        success: function (msg) {
            if (msg.status == 200) {
                roleRname = msg.msg.rname;
                $(".roleManageEdit #rname").val(msg.msg.rname);
                $(".roleManageEdit #rtype").val(msg.msg.rtype);
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
    var rtype = $(".roleManageEdit #rtype option:selected").val();
    if (!rname) {
        alert("角色名称不能为空");
    } else if (isRnameHendiadysRole(rname) && roleRname != rname) {
        alert("角色名称不能重复");
    } else {
        $.ajax({
            url: pageDesignControl_HOST + 'manage/role/updatebyid',
            type: 'Get',
            contentType: 'application/json',
            dataType: 'json',
            data: {"rname": rname, "rid": rid, "rtype": rtype},
            success: function (msg) {
                if (msg.status == 200) {
                    alert("更改成功")
                } else {
                    alert(msg.statusMsg)
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
    var rtype = $(".roleManageEdit #rtype option:selected").val();
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
            data: {"rname": rname, "rtype": rtype},
            success: function (msg) {
                if (msg.status == 200) {
                    alert("添加成功")
                } else {
                    alert(msg.statusMsg)
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
        data: {"rname": "", "pageSize": 10000},
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
            } else {
                alert(msg.statusMsg)
            }
        }
    });
}

function getRtype(rtype) {
    if (rtype == 1) {
        return "人事";
    } else if (rtype == 2) {
        return "员工";
    } else if (rtype == 3) {
        return "主管";
    } else if (rtype == 4) {
        return "经理";
    } else if (rtype == 5) {
        return "财务";
    } else if (rtype == 6) {
        return "总经理";
    } else if (rtype == 7) {
        return "立捷总经理";
    } else {
        return "未分配";
    }
}

/**
 * 生成角色列表
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
            if (msg.status == 200) {
                // 添加列表
                var roleListStr = "";
                for (var i = 0; i < msg.msg.length; i++) {
                    roleListStr += '<tr class="">' +
                        '<td class="text-center">' +
                        msg.msg[i].rname +
                        '<input type="hidden" class="' + msg.msg[i].rid + '" />' +
                        '</td>' +
                        '<td class="text-center">' +
                        getRtype(msg.msg[i].rtype) +
                        '</td>' +
                        '<td class="text-center">' +
                        '<button type="button" class="btn btn-info delete">删除</button>' +
                        '<input type="hidden" class="' + msg.msg[i].rid + '" />' +
                        '<button type="button" class="btn btn-warning manageEdit roleManageUpdate">修改</button>' +
                        '<input type="hidden" class="roleManageEdit">' +
                        '<button type="button" class="btn btn-warning manageEdit rolePermissionEdit" data-toggle="modal" data-target="#rolePermission">分配权限</button>' +
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
 * 角色权限关系start
 */
/**
 * 角色权限关系生成权限树并回显
 */
var rolePermissionId = 0;
$(function () {
    $(".roleManage").on("click", ".rolePermissionEdit", function () {
        getTreeList();
        var rid = $(this).next().attr("class");
        rolePermissionId = rid;
        getPermissionByRoleId(rid);
    });
})

/**
 * 角色权限关系权限树点击隐藏
 */
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
 */
function getPermissionByRoleId(rid) {
    $.ajax({
        url: pageDesignControl_HOST + 'manage/rolepermission/getpermissionbyroleid',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        data: {"rid": rid},
        async: false,
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

/**
 * 获取权限树列表
 */
function getTreeList() {
    $.ajax({
        url: pageDesignControl_HOST + 'manage/permission/selecttree',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        data: {},
        async: false,
        success: function (msg) {
            if (msg.status == 200) {
                $("#tree").empty();
                tree(msg.msg, "tree", 0);

                function tree(permissionList, parent, num) {
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
                            + menu
                            + '<input type="checkbox" class="treeCheckBox tree' + permissionList[j].pid + '" >'
                            + '<input type="hidden" value="' + permissionList[j].pid + '" />'
                            + permissionList[j].pname
                            + '</li>'
                            + '<ul id="tree' + permissionList[j].pid + '" ></ul>'
                        $("#" + parent).append(str);
                        tree(permissionList[j].childrenPermission, "tree" + permissionList[j].pid, width)
                    }
                }
            }
        }
    });
}

/**
 * 获取角色权限关系列表被选中的CheckBox值，先根据rid删除所有，在重新生成
 */
$(function () {
    $("#rolePermission").on("click", "#rolePermissionSubmit", function () {
        var rolePermissionList = [];
        $.each($('.treeCheckBox:checkbox:checked'), function () {
            rolePermissionList.push({rid: rolePermissionId, pid: $(this).next().val()});
        });
        $.ajax({
            url: pageDesignControl_HOST + 'manage/rolepermission/deletebyroleid',
            type: 'Get',
            contentType: 'application/json',
            dataType: 'json',
            data: {"rid": rolePermissionId},
            async: false,
            success: function (msg) {
                if (rolePermissionList.length > 0) {
                    $.ajax({
                        url: pageDesignControl_HOST + 'manage/rolepermission/insertlist',
                        type: 'post',
                        contentType: 'application/json',
                        dataType: 'json',
                        async: false,
                        data: JSON.stringify(rolePermissionList),
                        success: function (msg) {
                            if (msg.status == 200) {
                                getPermissionByRoleId(rolePermissionId);
                                alert("修改成功")
                            }
                        }
                    });
                }
            }
        });
    });
})
/**
 * 角色权限关系end
 */

/**
 * 权限start
 */
var permissionManageId = 0;
var permissionPname = "";
$(function () {
    /**
     * 左侧显示隐藏并获取权限列表
     */
    $(".manageLeft").on("click", "#permissionManage", function () {
        $(".manageRight .rightManageDiv").addClass("hidden");
        $(".permissionManage").removeClass("hidden");
        getPermissionList();
    });

    /**
     * 删除单个权限
     */
    $(".permissionManage").on("click", ".delete", function () {
        var r = confirm("是否删除!");
        if (r == true) {
            var pid = $(this).next().attr("class");
            removePermission(pid);
        }
    });

    /**
     * 增加权限
     */
    $(".permissionManageEdit").on("click", "#savePermission", function () {
        savePermission();
    });

    /**
     * 更改权限
     */
    $(".permissionManageEdit").on("click", "#updatePermission", function () {
        updatePermission();
    });
})

/**
 * 权限管理
 */
$(function () {
    /**
     * 点击添加
     */
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

    /**
     * 点击更新
     */
    $(".manageRight").on("click", ".permissionManageUpdate", function () {
        permissionManageId = $(this).prev().attr("class");
        $(".manageRight .permissionManage").addClass("hidden");
        $(".manageRight").children(".permissionManageEdit").removeClass("hidden");
        $(".Permission").attr("id", "updatePermission");
        getPermissionMuneList();
        getPermission();
    });

    /**
     * 点击返回
     */
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
        url: pageDesignControl_HOST + 'manage/permission/getbyid',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        async: false,
        data: {"pid": pid},
        success: function (msg) {
            if (msg.status == 200) {
                permissionPname = msg.msg.pname;
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
    } else if (isPnameHendiadysPermission(pname) && permissionPname != pname) {
        alert("权限名称不能重复");
    } else {
        $.ajax({
            url: pageDesignControl_HOST + 'manage/permission/updatebyid',
            type: 'Get',
            contentType: 'application/json',
            dataType: 'json',
            data: {"ppid": ppid, "pname": pname, "purl": purl, "ptype": ptype, "ppermission": ppermission, "pid": pid},
            success: function (msg) {
                if (msg.status == 200) {
                    alert("更改成功")
                } else {
                    alert(msg.statusMsg)
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
                if (msg.status == 200) {
                    alert("添加成功")
                } else {
                    alert(msg.statusMsg)
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
            } else {
                alert(msg.statusMsg)
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

