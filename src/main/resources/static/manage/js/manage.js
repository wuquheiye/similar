/**
 * 部门start
 */
var departManageId = -1;
$(function () {
    getDepartmentList();
    $("#departManagePageDiv").on("click", "a", function () {
        getDepartmentList($(this).text());
    });
    $(".departManage").on("click", "#departManageClean", function () {
        $("#departManageName").val("");
    });
    $(".departManage").on("click", "#departManageSearch", function () {
        getDepartmentList(1)
    });
    $(".departManage").on("click", ".delete", function () {
        var did = $(this).next().attr("class");
        removeDepartment(did);
    });
    $(".departManageEdit").on("click", "#saveDepartment", function () {
        saveDepartment();
    });

    $(".departManageEdit").on("click", "#updateDepartment", function () {
        updateDepartment();
    });
})

// 部门管理
$(function () {
    $(".manageRight").on("click", ".departManageAdd", function () {
        departManageId = -1;
        $(".manageRight .departManage").addClass("hidden");
        $(".manageRight").children(".departManageEdit").removeClass("hidden");
        $(".Department").attr("id","saveDepartment");
    });
    $(".manageRight").on("click", ".departManageUpdate", function () {
        departManageId=$(this).prev().attr("class");
        getDepartment();
        $(".manageRight .departManage").addClass("hidden");
        $(".manageRight").children(".departManageEdit").removeClass("hidden");
        $(".Department").attr("id","updateDepartment");
    });
})

/**
 * 获取部门列表
 */
function getDepartment(){
    var did = departManageId;
    $.ajax({
        url: pageDesignControl_HOST + '/manage/department/selectbyid',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        data: {"did": did},
        success: function (msg) {
            var departmentList = JSON.stringify(msg.msg);
            if (msg.status == 200) {
                $("#dname").val(msg.msg.dname);
            }
        }
    });
}

/**
 * 更改部门
 */
function updateDepartment(){
    var did = departManageId;
    var dname = $(".departManageEdit #dname").val();
    $.ajax({
        url: pageDesignControl_HOST + 'manage/department/save',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        data: {"dname": dname,"did":did},
        success: function (msg) {
            var departmentList = JSON.stringify(msg.msg);
            if (msg.status == 200) {
                alert("更改成功")
            }
        }
    });
}

/**
 * 添加部门
 */
function saveDepartment(){
    var dname = $(".departManageEdit #dname").val();
    $.ajax({
        url: pageDesignControl_HOST + 'manage/department/save',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        data: {"dname": dname},
        success: function (msg) {
            var departmentList = JSON.stringify(msg.msg);
            if (msg.status == 200) {
                alert("添加成功")
            }
        }
    });
}

/**
 * 删除单个部门
 */
function removeDepartment(did){
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
 * 获取部门列表
 */
function getDepartmentList(page){
    var dname = $("#departManageName").val();
    $.ajax({
        url: pageDesignControl_HOST + 'manage/department/selectbypageandcondition',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        data: {"dname": dname,"page":page},
        success: function (msg) {
            var departmentList = JSON.stringify(msg.msg);
            if (msg.status == 200) {
                var departmentListStr = "";
                for(var i = 0; i<msg.msg.length;i++){
                    departmentListStr +=  '<tr class="active">' +
                        '<td class="text-center">' +
                        '<input type="checkbox" class="userManageCheckBox">' +msg.msg[i].dname+
                        '</td>' +
                        '<td class="text-center">'+msg.msg[i].dcreateTime+'</td>'+
                        '<td class="text-center">' +
                        '<button type="button" class="btn btn-info delete" >删除</button>' +
                        '<input type="hidden" class="'+msg.msg[i].did+'" />'+
                        '<button type="button" class="btn btn-warning manageEdit departManageUpdate">修改</button>' +
                        '<input type="hidden" class="departManageEdit">' +
                        '</td>' +
                        '</tr>'
                }
                $("#departManageTable").empty();
                $("#departManageTable").append(departmentListStr);
                $("#departManageName").val(dname);
                if(msg.totalSize<=10){
                    $("#departManagePageDiv").addClass("hidden");
                }else{
                    $("#departManagePageDiv").removeClass("hidden");
                }
                var pageSpanStr= "第 "+msg.pageStart+ " 条 到 "+msg.pageEnd+" 条 ，共 "+msg.totalSize+" 条记录, 当前第 "+msg.currentPage+"页" ;
                $("#departManagePageSpan").text(pageSpanStr);
                var pageUlStr ="";
                if(parseInt(msg.currentPage)-2>0){
                    pageUlStr += '<li><a href="#">'+(parseInt(msg.currentPage)-2)+'</a></li>';
                }
                if(parseInt(msg.currentPage)-1>0){
                    pageUlStr += '<li><a href="#">'+(parseInt(msg.currentPage)-1)+'</a></li>';
                }
                pageUlStr += '<li><a href="#">'+msg.currentPage+'</a></li>';
                if(parseInt(msg.currentPage)+1<=parseInt(msg.totalPage)){
                    pageUlStr += '<li><a href="#">'+(parseInt(msg.currentPage)+1)+'</a></li>';
                }
                if(parseInt(msg.currentPage)+2<=parseInt(msg.totalPage)){
                    pageUlStr +=  '<li><a href="#">'+(parseInt(msg.currentPage)+2)+'</a></li>';
                }
                $("#departManagePageUl").empty();
                $("#departManagePageUl").append(pageUlStr);
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

// 用户管理所以checkbox全选
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

$(function () {
    // 左侧点击变色和显示隐藏
    $(".manageLeft").on("click", ".manageClick", function () {
        if (!$(this).is('.manageActive')) {
            // 显示隐藏start
            $(".manageLeft .manageClick").removeClass("manageActive");
            $(this).addClass("manageActive");
            $(".manageRight .rightManageDiv").addClass("hidden");
            $("." + $(this).attr("id")).removeClass("hidden");
            // 显示隐藏end
        }
    });
})
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
