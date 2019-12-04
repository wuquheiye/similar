package swtech.pageDesignControl.common.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;
import swtech.pageDesignControl.entity.Department;
import swtech.pageDesignControl.entity.Users;

import java.util.List;

/**
 * <p>
 * 生成组织信息部门
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-21
 */
@Data
@Accessors(chain = true)
public class DepartmentVO {

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

    /**
     * 所属公司
     */
    private String artsVision;

    /**
     * 用户列表
     */
    private List<UsersVO> usersListVO;
}
