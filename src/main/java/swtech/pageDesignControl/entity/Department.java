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
 * @since 2019-11-19
 */
@Data
@Accessors(chain = true)
public class Department {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "did", type = IdType.AUTO)
    private Integer did;

    /**
     * 部门名称
     */
    private String dname;

    /**
     * 创建时间
     */
    private String dcreateTime;


}
