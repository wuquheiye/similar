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
 * @since 2019-12-06
 */
@Data
@Accessors(chain = true)
public class AskLeave {

    private static final long serialVersionUID = 1L;

    /**
     * 请假人
     */
    private String lname;

    /**
     * 请假类型
     */
    private String ltype;

    /**
     * 请假日期
     */
    private String ldate;

    /**
     * 请假具体时间
     */
    private String ltime;

    /**
     * 请假总计
     */
    private String ltotal;

    /**
     * 考勤日期
     */
    private String date;

    /**
     * 所属公司
     */
    private String lcompany;

    /**
     * 自增id
     */
    @TableId(value = "lid", type = IdType.AUTO)
    private Integer lid;


}
