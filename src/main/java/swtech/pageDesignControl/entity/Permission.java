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
 * @author 李鸿智
 * @since 2019-11-18
 */
@Data
@Accessors(chain = true)
public class Permission {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "pid", type = IdType.AUTO)
    private Integer pid;

    /**
     * 权限名
     */
    private String pname;

    /**
     * 权限类型
     */
    private String ptype;

    /**
     * 父类Id
     */
    private String ppid;

    /**
     * 权限
     */
    private String ppermission;

    /**
     * url
     */
    private String purl;
}
