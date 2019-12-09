package swtech.pageDesignControl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import swtech.pageDesignControl.entity.Role;
import swtech.pageDesignControl.mapper.RoleMapper;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoleTest {
    @Resource
    private RoleMapper roleMapper;

    @Test
    public void getRoleByUsername() {
        Role role = roleMapper.getRoleByUtelephonenumber("admin");
        System.out.println(role);
    }
}
