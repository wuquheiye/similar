package swtech.pageDesignControl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import swtech.pageDesignControl.entity.Department;
import swtech.pageDesignControl.mapper.DepartmentMapper;
import swtech.pageDesignControl.mapper.PermissionMapper;
import swtech.pageDesignControl.mapper.RoleMapper;
import swtech.pageDesignControl.mapper.UsersMapper;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroWebApplicationTests {
    @Resource
    private UsersMapper usersMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private PermissionMapper permissionMapper;

    @Resource
    private DepartmentMapper departmentMapper;

    @Test
	public void contextLoads() {
        System.out.println(usersMapper.findPasswordByUsername("admin"));
        System.out.println(roleMapper.getRoleByUsername("admin"));
        System.out.println(permissionMapper.getPermissionbyRoleName("manage"));
        Department department = new Department();
        department.setDname("开发");
        departmentMapper.insert(department);
        System.out.println(departmentMapper.findAll());

	}

}
