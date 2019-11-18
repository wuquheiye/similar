// 用户管理启用
$(function() {
	$(".manageRight").on("click",".manageEdit",function() {
		$(".manageLeft .manageClick").removeClass("manageActive");
		$(".manageRight").children("." + $(this).next().attr("class")).addClass("manageActive");
		$(".manageRight .rightManageDiv").addClass("hidden");
		$(".manageRight").children("." + $(this).next().attr("class")).removeClass("hidden");
	});
})
// 用户管理启用
$(function() {
	$(".userManage").on("click", ".button", function() {
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
$(function() {
	// 左侧点击变色和显示隐藏
	$(".manageLeft").on("click", ".manageClick", function() {
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
$(function() {
	// 左侧手风琴显示隐藏start
	$(".manageLeft").on("click", ".organizationHeaderOrganizationClick",function() {
		$(this).find("span").toggleClass("glyphicon-chevron-down");
		$(this).find("span").toggleClass("glyphicon-chevron-up");
		$(this).parent().children("ul").toggle();
	});
	// 左侧手风琴显示隐藏end
})

// 日期插件
$(document).ready(function() {
	$('#userManageDateBegin').bootstrapMaterialDatePicker({
		time : false
	});
	$('#userManageDateEnd').bootstrapMaterialDatePicker({
		time : false
	});
	$('#time').bootstrapMaterialDatePicker({
		date : false,
		shortTime : true,
		format : 'HH:mm'
	});
	$('#date-format').bootstrapMaterialDatePicker({
		format : 'dddd DD MMMM YYYY - HH:mm'
	});
	$('#date-fr').bootstrapMaterialDatePicker({
		format : 'DD/MM/YYYY HH:mm',
		lang : 'fr',
		weekStart : 1,
		cancelText : 'ANNULER'
	});
	$('#date-end').bootstrapMaterialDatePicker({
		weekStart : 0,
		format : 'DD/MM/YYYY HH:mm'
	});
	$('#date-start').bootstrapMaterialDatePicker({
		weekStart : 0,
		format : 'DD/MM/YYYY HH:mm'
	}).on('change', function(e, date) {
		$('#date-end').bootstrapMaterialDatePicker('setMinDate', date);
	});
	$('#min-date').bootstrapMaterialDatePicker({
		format : 'DD/MM/YYYY HH:mm',
		minDate : new Date()
	});
});
