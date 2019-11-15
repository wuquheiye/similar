package swtech.pageDesignControl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import swtech.pageDesignControl.mapper.RolesPermissionsMapper;
import swtech.pageDesignControl.mapper.UserMapper;
import swtech.pageDesignControl.mapper.UserRoleMapper;
import swtech.pageDesignControl.mapper.UsersMapper;
import swtech.pageDesignControl.service.IUserService;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroWebApplicationTests {
    @Autowired
    private UsersMapper userMapper;

    @Autowired
    private RolesPermissionsMapper rolesPermissionsMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Test
	public void contextLoads() {
        System.out.println(userMapper.findUserByUsername("admin"));
        System.out.println(rolesPermissionsMapper.getPermissionbyRoleName("user"));
        System.out.println(userRoleMapper.getRolesByUsername("admin"));
	}

}
