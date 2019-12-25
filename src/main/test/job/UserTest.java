package job;

import job.entity.PersonUser;
import job.entity.User;
import job.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTest {
    @Resource
    private UserMapper userMapper;

    @Test
    public void findUsersByEmail() {
        User user = userMapper.findUserByEmail("2325805994@qq.com");
        System.out.println(user);
    }

    @Test
    public void getPersonUser() {
        PersonUser personUser = userMapper.getPersonUser(1);
        System.out.println(personUser);
    }

    @Test
    public void selectByPageAndCondition() {
        User user = new User();
        user.setEmail("@qq.com");
        List<User> userVOList = userMapper.selectByPageAndCondition(user, 1, 100);
        System.out.println(userVOList.get(0));
    }

    @Test
    public void selectCount() {
        int num = userMapper.selectCount();
        System.out.println(num);
    }

    @Test
    public void updateState() {
        int num = userMapper.updateState(38,2);
        System.out.println(num);
    }
}
