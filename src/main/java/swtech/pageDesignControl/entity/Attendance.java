package swtech.pageDesignControl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author 袁君选
 * @since 2019-12-04
 */
@Data
@Accessors(chain = true)
public class Attendance {

    private static final long serialVersionUID = 1L;

    /**
     * 年
     */
    private String ayear;

    /**
     * 月
     */
    private String amonth;

    /**
     * 部门
     */
    private String adepartment;

    /**
     * 姓名
     */
    private String aname;

    /**
     * 第一号
     */
    private Integer aday1;

    private Integer aday2;

    private Integer aday3;

    private Integer aday4;

    private Integer aday5;

    private Integer aday6;

    private Integer aday7;

    private Integer aday8;

    private Integer aday9;

    private Integer aday10;

    private Integer aday11;

    private Integer aday12;

    private Integer aday13;

    private Integer aday14;

    private Integer aday15;

    private Integer aday16;

    private Integer aday17;

    private Integer aday18;

    private Integer aday19;

    private Integer aday20;

    private Integer aday21;

    private Integer aday22;

    private Integer aday23;

    private Integer aday24;

    private Integer aday25;

    private Integer aday26;

    private Integer aday27;

    private Integer aday28;

    private Integer aday29;

    private Integer aday30;

    private Integer aday31;

    /**
     * 实际出勤天数
     */
    private Integer aattendanceDays;

    /**
     * 加班天数
     */
    private Integer aworkOvertimeDays;

    /**
     * 事假
     */
    private Integer aleavePersonalAffairs;

    /**
     * 病假
     */
    private Integer asickLeave;

    /**
     * 年休假
     */
    private Integer aannualLeave;

    /**
     * 补休假
     */
    private Integer aforVacation;

    /**
     * 公差
     */
    private Integer atolerance;

    /**
     * 公伤假
     */
    private Integer amaleInjuryLeave;

    /**
     * 丧假
     */
    private Integer afuneralLeave;

    /**
     * 产前检查
     */
    private Integer aantenatalExamination;

    /**
     * 哺乳假
     */
    private Integer abreastfeedingLeave;

    /**
     * 产假
     */
    private Integer amaternityLeave;

    /**
     * 技生假
     */
    private Integer afamilyPlanningLeave;

    /**
     * 看护假
     */
    private Integer acareLeave;

    /**
     * 婚假
     */
    private Integer amarriageLeave;

    /**
     * 其他假
     */
    private Integer aotherLeave;

    /**
     * 合计
     */
    private Integer atotalLeave;

    /**
     * 迟到
     */
    private Integer atardy;

    /**
     * 早退
     */
    private Integer aleaveEarly;

    /**
     * 旷工
     */
    private Integer absenteeism;

    /**
     * 备注
     */
    private Integer remark;

    @TableId(value = "aid", type = IdType.AUTO)
    private Integer aid;

    private Integer uid;


}
