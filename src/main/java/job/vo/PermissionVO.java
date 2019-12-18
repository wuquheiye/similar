package job.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 生成权限树（父类中包含子类）
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-18
 */
@Data
@Accessors(chain = true)
public class PermissionVO {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 权限名
     */
    private String name;

    /**
     * 权限类型
     */
    private String type;

    /**
     * 父类Id
     */
    private String pid;

    /**
     * 权限
     */
    private String permission;

    /**
     * url
     */
    private String url;


    /**
     * 子元素
     */
    private List<PermissionVO> childrenPermission = new ArrayList<>();
}
