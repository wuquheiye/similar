package job;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import job.entity.Role;
import job.mapper.RoleMapper;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoleTest {
    @Resource
    private RoleMapper roleMapper;

    @Test
    public void getRoleByEmail() {
        Role role = roleMapper.getRoleByEmail("2325805991@qq.com");
        System.out.println(role);
    }

    @Test
    public void selectByPageAndCondition() {
        Role role = new Role();
        role.setName("主管");
        List<Role> roleList = roleMapper.selectByPageAndCondition(role,0,100);
        System.out.println(roleList);
    }

    @Test
    public void selectCount() {
        int num = roleMapper.selectCount();
        System.out.println(num);
    }
}
