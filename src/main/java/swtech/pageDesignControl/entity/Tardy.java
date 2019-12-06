package swtech.pageDesignControl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
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
public class Tardy {

    private static final long serialVersionUID = 1L;

    /**
     * 迟到人
     */
    private String tname;

    /**
     * 迟到日期
     */
    private String tdate;

    /**
     * 迟到年月
     */
    private String date;

    /**
     * 所属公司
     */
    private String tcompany;

    /**
     * 自增id
     */
    @TableId(value = "tid", type = IdType.AUTO)
    private Integer tid;


}
