package swtech.pageDesignControl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import swtech.pageDesignControl.mapper.PermissionMapper;
import swtech.pageDesignControl.mapper.RoleMapper;
import swtech.pageDesignControl.mapper.UsersMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroWebApplicationTests {
    @Autowired
    private UsersMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Test
	public void contextLoads() {
        System.out.println(userMapper.findPasswordByUsername("admin"));
        System.out.println(roleMapper.getRoleByUsername("admin"));
        System.out.println(permissionMapper.getPermissionbyRoleName("manage"));
	}

}
