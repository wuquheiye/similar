package swtech.pageDesignControl.mapper;

import org.apache.ibatis.annotations.Param;
import swtech.pageDesignControl.common.vo.UsersVO;
import swtech.pageDesignControl.entity.Department;
import swtech.pageDesignControl.entity.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-18
 */
public interface UsersMapper extends BaseMapper<Users> {
    /**
     * 通过用户名获取信息shiro
     *
     * @param username
     * @return
     */
    Users findUsersByName(String username);

    /**
     * 通过部门和角色查询用户
     *
     * @param did
     * @param rtype
     * @return
     */
    List<Users> findUsersByDepartmentAndRole(@Param("did") int did, @Param("rtype") int rtype);

    /**
     * 分页查询
     *
     * @param users
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<UsersVO> selectByPageAndCondition(@Param("users") Users users, @Param("pageStart") int pageStart, @Param("pageSize") int pageSize);

    /**
     * 查询个数
     *
     * @return
     */
    int selectCount();

    /**
     * 删除部门时将对应的用户的部门id清空
     *
     * @param did
     * @return
     */
    int updateDepartmentToNull(int did);

    /**
     * 更改用户状态
     *
     * @param uid
     * @param ustate
     * @return
     */
    int updateUustate(@Param("uid") int uid, @Param("ustate") int ustate);

    /**
     * 根据角色id查询所有的用户
     *
     * @param rtype
     * @return
     */
    List<Users> selectUsersByRid();

    /**
     * yjx
     * 获取总经理uid
     */
    List<Users> selectGM(@Param("rtype") Integer rtype);

    /**
     * 获取所有公司名称
     *
     * @return
     */
    List<String> getAllCompany();

    /**
     * 通过公司获取所属部门
     *
     * @param artsVision
     * @return
     */
    List<Department> getAllDepartmentByCompany(@Param("artsVision")String artsVision);

    /**
     * 通过部门获取所有员工
     *
     * @param did
     * @return
     */
    List<Users> getAllUsersByDepartment(int did);
}
