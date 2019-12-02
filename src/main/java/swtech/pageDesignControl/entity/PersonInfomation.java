package swtech.pageDesignControl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author 袁君选
 * @since 2019-11-28
 */
@Data
@Accessors(chain = true)
public class PersonInfomation {

    private static final long serialVersionUID = 1L;

    /**
     * 部门：
     */
    private String pdepartment;

    /**
     * 岗位：
     */
    private String pposition;

    /**
     * 填表日期:
     */
    private String pcreatedate;

    /**
     * 姓   名
     */
    private String pname;

    /**
     * 性    别
     */
    private String psex;

    /**
     * 民     族
     */
    private String pnation;

    /**
     * 婚姻状况
     */
    private String pmarriage;

    /**
     * 政治面貌
     */
    private String ppolitics;

    /**
     * 出生年月
     */
    private String pbirthday;

    /**
     * 最高学历
     */
    private String pacademic;

    /**
     * 专  业
     */
    private String pmajor;

    /**
     * 毕业学校
     */
    private String pschool;

    /**
     * 毕业时间
     */
    private String pgraduationdate;

    /**
     * 学  位
     */
    private String pdegree;

    /**
     * 职   称
     */
    private String pprofessional;

    /**
     * 照片
     */
    private String pimg;

    /**
     * 电话号码
     */
    private String ptelephonenumber;

    /**
     * 入职时间
     */
    private String phiredate;

    /**
     * 身份证地址
     */
    private String paddress;

    /**
     * 身份证号码
     */
    private String pidcard;

    /**
     * 银行卡账号
     */
    private String pbankaccount;

    /**
     * 银行卡开户行
     */
    private String popenbank;

    /**
     * 教育经历起止时间
     */
    private String peducationdate1;

    private String peducationdate2;

    private String peducationdate3;

    /**
     * 教育经历学校
     */
    private String peducationschool1;

    private String peducationschool2;

    private String peducationschool3;

    /**
     * 教育经历专业
     */
    private String peducationmajor1;

    private String peducationmajor2;

    private String peducationmajor3;

    /**
     * 教育经历所获学位
     */
    private String peducationdegree1;

    private String peducationdegree2;

    private String peducationdegree3;

    /**
     * 教育经历所获证书
     */
    private String peducationcertificate1;

    private String peducationcertificate2;

    private String peducationcertificate3;

    /**
     * 专业资格证书
     */
    private String pqualification;

    /**
     * 获取资格时间
     */
    private String pqualificationdate;

    /**
     * cad
     */
    private String pprofessionalcad;

    /**
     * ps
     */
    private String pprofessionalps;

    /**
     * 3dmax
     */
    private String pprofessional3dmax;

    /**
     * 天正建筑
     */
    private String pprofessionaltarch;

    /**
     * 职业技能其他
     */
    private String pprofessionalother;

    /**
     * 工作经历起止时间
     */
    private String pexperiencedate1;

    private String pexperiencedate2;

    private String pexperiencedate3;

    /**
     * 工作经历起止时间
     */
    private String pexperienceunit1;

    private String pexperienceunit2;

    private String pexperienceunit3;

    /**
     * 工作经历工作岗位
     */
    private String pexperiencepost1;

    private String pexperiencepost2;

    private String pexperiencepost3;

    /**
     * 工作经历薪资水平
     */
    private String pexperiencepay1;

    private String pexperiencepay2;

    private String pexperiencepay3;

    /**
     * 工作经历离职原因
     */
    private String pexperiencedimission1;

    private String pexperiencedimission2;

    private String pexperiencedimission3;

    /**
     * 工作经历证明人及电话
     */
    private String pexperiencedimissionnumber1;

    private String pexperiencedimissionnumber2;

    private String pexperiencedimissionnumber3;

    /**
     * 家庭成员姓名
     */
    private String pfamilyfamilyname1;

    private String pfamilyfamilyname2;

    /**
     * 家庭成员年龄
     */
    private String pfamilyfamilyage1;

    private String pfamilyfamilyage2;

    /**
     * 家庭成员工作单位
     */
    private String pfamilyfamilyunit1;

    private String pfamilyfamilyunit2;

    /**
     * 家庭成员职位
     */
    private String pfamilyfamilypost1;

    private String pfamilyfamilypost2;

    /**
     * 家庭成员联系电话
     */
    private String pfamilyfamilynumber1;

    private String pfamilyfamilynumber2;

    /**
     * 应急联系人
     */
    private String pemergencycontactname;

    /**
     * 联系电话
     */
    private String pemergencycontactnumber;

    /**
     * 向公司提供的资料身份证
     */
    private String pinformationid;

    /**
     * 向公司提供的资料银行卡
     */
    private String pinformationbank;

    /**
     * 向公司提供的资料学历
     */
    private String pinformationeducation;

    /**
     * 向公司提供的资料其他
     */
    private String pinformationother;

    /**
     * 是否有离职证明文件
     */
    private String pemploymentseparation;

    /**
     * 是否有犯罪记录
     */
    private String pcriminalrecord;

    /**
     * 自增id
     */
    @TableId(value = "pid", type = IdType.AUTO)
    private Integer pid;

    /**
     * 用户id
     */
    private Integer uid;


}
