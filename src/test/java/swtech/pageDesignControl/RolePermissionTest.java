package swtech.pageDesignControl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import swtech.pageDesignControl.mapper.RolePermissionMapper;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RolePermissionTest {
    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Test
    public void selecGtrandfather() {
        List<String> list = rolePermissionMapper.getPermissionByRoleId(33);
        System.out.println(list);
    }

}
