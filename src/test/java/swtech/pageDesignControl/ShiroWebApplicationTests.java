package swtech.pageDesignControl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import swtech.pageDesignControl.entity.Department;
import swtech.pageDesignControl.entity.Users;
import swtech.pageDesignControl.mapper.DepartmentMapper;
import swtech.pageDesignControl.mapper.PermissionMapper;
import swtech.pageDesignControl.mapper.RoleMapper;
import swtech.pageDesignControl.mapper.UsersMapper;
import swtech.pageDesignControl.service.IUsersService;

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

    @Resource
    private IUsersService iUsersService;

    @Test
	public void Test() {
        System.out.println(usersMapper.findPasswordByUsername("admin"));
        System.out.println(roleMapper.getRoleByUsername("admin"));
        System.out.println(permissionMapper.getPermissionbyRoleName("manage"));
        Department department = new Department();
        department.setDname("开发");
        departmentMapper.insert(department);
        System.out.println(departmentMapper.findAll());

	}

    @Test
    public void Users() {
        Users users = new Users();
        users.setUusername("张三");
        users.setUpassword("123456");
        users.setUinvitationCode("123");
        users.setDid(1);
        usersMapper.insert(users);
    }

    @Test
    public void Users1() {
        Users users = new Users();
        users.setUusername("张三");
        users.setUpassword("123456");
        users.setUinvitationCode("1231");
        users.setDid(1);
        iUsersService.insert(users);
    }
}
