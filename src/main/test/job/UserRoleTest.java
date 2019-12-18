package job;

import job.mapper.UserRoleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRoleTest {
    @Resource
    private UserRoleMapper userRoleMapper;

    @Test
    public void getRoleByUserId() {
        List<String> list = userRoleMapper.getRoleByUserId(32);
        System.out.println(list);
    }

    @Test
    public void deleteByRoleId() {
        int num = userRoleMapper.deleteByRoleId(35);
        System.out.println(num);
    }

    @Test
    public void deleteByPermissionId() {
        int num = userRoleMapper.deleteByUserId(35);
        System.out.println(num);
    }


}
