package job;

import job.entity.PersonUser;
import job.mapper.PersonUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonUserTest {
    @Resource
    private PersonUserMapper personUserMapper;

    @Test
    public void getPersonUserByUserId() {
        PersonUser personUser = personUserMapper.getPersonUserByUserId(61);
        System.out.println(personUser);
    }

    @Test
    public void getRoleByEmail() {
        PersonUser personUser = new PersonUser();
        personUser.setName("1");
        personUser.setUserId(1);
        int num = personUserMapper.insert(personUser);
        System.out.println(personUser.getId());
    }

    @Test
    public void findAll() {
        List<PersonUser> personUserList = personUserMapper.findAll(1,10);
        System.out.println(personUserList);
    }

    @Test
    public void selectCount() {
        int num = personUserMapper.selectCount();
        System.out.println(num);
    }
}
