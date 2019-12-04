/**
 * 个人档案start
 */
$(function () {

    var str = window.location.href;
    var uid = str.substring(str.indexOf("=")+1);
    alert(uid)
    getPersonInfomation(uid);
})

/**
 * 获取单个个人档案
 */
function getPersonInfomation(uid) {
    $.ajax({
        url: pageDesignControl_HOST + '/manage/personinfomation/selectbyuid',
        type: 'Get',
        contentType: 'application/json',
        dataType: 'json',
        data: {"uid": uid},
        async: false,
        success: function (msg) {
            if (msg.status == 200) {
                $("#img_area").html('<img id="pimg" src="'+msg.msg.pimg+'"/>') ;
                $(".personInfomationManageEdit #pname").val(msg.msg.pname);
                $(".personInfomationManageEdit #uid").val(msg.msg.uid);
                $(".personInfomationManageEdit #pdepartment").val(msg.msg.pdepartment);
                $(".personInfomationManageEdit #pposition").val(msg.msg.pposition);
                $(".personInfomationManageEdit #pcreatedate").val(msg.msg.pcreatedate);
                $(".personInfomationManageEdit #personInfomationname").val(msg.msg.pname);
                $(".personInfomationManageEdit #psex").val(msg.msg.psex);
                $(".personInfomationManageEdit #pnation").val(msg.msg.pnation);
                $(".personInfomationManageEdit #pmarriage").val(msg.msg.pmarriage);
                $(".personInfomationManageEdit #ppolitics").val(msg.msg.ppolitics);
                $(".personInfomationManageEdit #pbirthday").val(msg.msg.pbirthday);
                $(".personInfomationManageEdit #pacademic").val(msg.msg.pacademic);
                $(".personInfomationManageEdit #pmajor").val(msg.msg.pmajor);
                $(".personInfomationManageEdit #pschool").val(msg.msg.pschool);
                $(".personInfomationManageEdit #pgraduationdate").val(msg.msg.pgraduationdate);
                $(".personInfomationManageEdit #pdegree").val(msg.msg.pdegree);
                $(".personInfomationManageEdit #pprofessional").val(msg.msg.pprofessional);
                $(".personInfomationManageEdit #ptelephonenumber").val(msg.msg.ptelephonenumber);
                $(".personInfomationManageEdit #phiredate").val(msg.msg.phiredate);
                $(".personInfomationManageEdit #paddress").val(msg.msg.paddress);
                $(".personInfomationManageEdit #pidcard").val(msg.msg.pidcard);
                $(".personInfomationManageEdit #pbankaccount").val(msg.msg.pbankaccount);
                $(".personInfomationManageEdit #popenbank").val(msg.msg.popenbank);
                $(".personInfomationManageEdit #peducationdate1").val(msg.msg.peducationdate1);
                $(".personInfomationManageEdit #peducationdate2").val(msg.msg.peducationdate2);
                $(".personInfomationManageEdit #peducationdate3").val(msg.msg.peducationdate3);
                $(".personInfomationManageEdit #peducationschool1").val(msg.msg.peducationschool1);
                $(".personInfomationManageEdit #peducationschool2").val(msg.msg.peducationschool2);
                $(".personInfomationManageEdit #peducationschool3").val(msg.msg.peducationschool3);
                $(".personInfomationManageEdit #peducationmajor1").val(msg.msg.peducationmajor1);
                $(".personInfomationManageEdit #peducationmajor2").val(msg.msg.peducationmajor2);
                $(".personInfomationManageEdit #peducationmajor3").val(msg.msg.peducationmajor3);
                $(".personInfomationManageEdit #peducationdegree1").val(msg.msg.peducationdegree1);
                $(".personInfomationManageEdit #peducationdegree2").val(msg.msg.peducationdegree2);
                $(".personInfomationManageEdit #peducationdegree3").val(msg.msg.peducationdegree3);
                $(".personInfomationManageEdit #peducationcertificate1").val(msg.msg.peducationcertificate1);
                $(".personInfomationManageEdit #peducationcertificate2").val(msg.msg.peducationcertificate2);
                $(".personInfomationManageEdit #peducationcertificate3").val(msg.msg.peducationcertificate3);
                $(".personInfomationManageEdit #pqualification").val(msg.msg.pqualification);
                $(".personInfomationManageEdit #pqualificationdate").val(msg.msg.pqualificationdate);
                $('.personInfomationManageEdit #pprofessionalcad').attr('checked', msg.msg.pprofessionalcad);
                $('.personInfomationManageEdit #pprofessionalps').attr('checked', msg.msg.pprofessionalps);
                $('.personInfomationManageEdit #pprofessional3dmax').attr('checked', msg.msg.pprofessional3dmax);
                $('.personInfomationManageEdit #pprofessionaltarch').attr('checked', msg.msg.pprofessionaltarch);
                $('.personInfomationManageEdit #pprofessionalother').attr('checked', msg.msg.pprofessionalother);
                $(".personInfomationManageEdit #pexperiencedate1").val(msg.msg.pexperiencedate1);
                $(".personInfomationManageEdit #pexperiencedate2").val(msg.msg.pexperiencedate2);
                $(".personInfomationManageEdit #pexperiencedate3").val(msg.msg.pexperiencedate3);
                $(".personInfomationManageEdit #pexperienceunit1").val(msg.msg.pexperienceunit1);
                $(".personInfomationManageEdit #pexperienceunit2").val(msg.msg.pexperienceunit2);
                $(".personInfomationManageEdit #pexperienceunit3").val(msg.msg.pexperienceunit3);
                $(".personInfomationManageEdit #pexperiencepost1").val(msg.msg.pexperiencepost1);
                $(".personInfomationManageEdit #pexperiencepost2").val(msg.msg.pexperiencepost2);
                $(".personInfomationManageEdit #pexperiencepost3").val(msg.msg.pexperiencepost3);
                $(".personInfomationManageEdit #pexperiencepay1").val(msg.msg.pexperiencepay1);
                $(".personInfomationManageEdit #pexperiencepay2").val(msg.msg.pexperiencepay2);
                $(".personInfomationManageEdit #pexperiencepay3").val(msg.msg.pexperiencepay3);
                $(".personInfomationManageEdit #pexperiencedimission1").val(msg.msg.pexperiencedimission1);
                $(".personInfomationManageEdit #pexperiencedimission2").val(msg.msg.pexperiencedimission2);
                $(".personInfomationManageEdit #pexperiencedimission3").val(msg.msg.pexperiencedimission3);
                $(".personInfomationManageEdit #pexperiencedimissionnumber1").val(msg.msg.pexperiencedimissionnumber1);
                $(".personInfomationManageEdit #pexperiencedimissionnumber2").val(msg.msg.pexperiencedimissionnumber2);
                $(".personInfomationManageEdit #pexperiencedimissionnumber3").val(msg.msg.pexperiencedimissionnumber3);
                $(".personInfomationManageEdit #pfamilyfamilyname1").val(msg.msg.pfamilyfamilyname1);
                $(".personInfomationManageEdit #pfamilyfamilyname2").val(msg.msg.pfamilyfamilyname2);
                $(".personInfomationManageEdit #pfamilyfamilyname3").val(msg.msg.pfamilyfamilyname3);
                $(".personInfomationManageEdit #pfamilyfamilyage1").val(msg.msg.pfamilyfamilyage1);
                $(".personInfomationManageEdit #pfamilyfamilyage2").val(msg.msg.pfamilyfamilyage2);
                $(".personInfomationManageEdit #pfamilyfamilyage3").val(msg.msg.pfamilyfamilyage3);
                $(".personInfomationManageEdit #pfamilyfamilyunit1").val(msg.msg.pfamilyfamilyunit1);
                $(".personInfomationManageEdit #pfamilyfamilyunit2").val(msg.msg.pfamilyfamilyunit2);
                $(".personInfomationManageEdit #pfamilyfamilyunit3").val(msg.msg.pfamilyfamilyunit3);
                $(".personInfomationManageEdit #pfamilyfamilypost1").val(msg.msg.pfamilyfamilypost1);
                $(".personInfomationManageEdit #pfamilyfamilypost2").val(msg.msg.pfamilyfamilypost2);
                $(".personInfomationManageEdit #pfamilyfamilypost3").val(msg.msg.pfamilyfamilypost3);
                $(".personInfomationManageEdit #pfamilyfamilynumber1").val(msg.msg.pfamilyfamilynumber1);
                $(".personInfomationManageEdit #pfamilyfamilynumber2").val(msg.msg.pfamilyfamilynumber2);
                $(".personInfomationManageEdit #pfamilyfamilynumber3").val(msg.msg.pfamilyfamilynumber3);
                $(".personInfomationManageEdit #pemergencycontactname").val(msg.msg.pemergencycontactname);
                $(".personInfomationManageEdit #pemergencycontactnumber").val(msg.msg.pemergencycontactnumber);
                $(".personInfomationManageEdit #pinformationid").attr('checked', msg.msg.pinformationid);
                $(".personInfomationManageEdit #pinformationbank").attr('checked', msg.msg.pinformationbank);
                $(".personInfomationManageEdit #pinformationeducation").attr('checked', msg.msg.pinformationeducation);
                $(".personInfomationManageEdit #pinformationother").attr('checked', msg.msg.pinformationother);
                $("input[name='pemploymentseparation'][id = " + msg.msg.pemploymentseparation + "]").prop("checked", "checked");
                $("input[name='pcriminalrecord'][id = " + msg.msg.pcriminalrecord + "]").prop("checked", "checked");
                $(".personInfomationManageEdit #uid").val(msg.msg.uid);
            }
        }
    });
}
/**
 * 个人档案end
 */

