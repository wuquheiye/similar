package job.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import job.utils.DateUtil;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-21
 */
@Data
@Accessors(chain = true)
public class User {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 电话号码
     */
    private String telephonenumber;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建时间
     */
    private String creationtime = DateUtil.getNewDate();

    /**
     * 用户状态（0，被加入黑名单，1，未审核，2，审核通过）
     */
    private int state = 1;

    /**
     * 用户余额
     */
    private int money = 0;
}
