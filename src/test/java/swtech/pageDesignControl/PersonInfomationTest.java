package swtech.pageDesignControl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import swtech.pageDesignControl.entity.PersonInfomation;
import swtech.pageDesignControl.entity.Role;
import swtech.pageDesignControl.mapper.PersonInfomationMapper;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonInfomationTest {
    @Resource
    private PersonInfomationMapper personInfomationMapper;

    @Test
    public void selectByPageAndCondition() {
        PersonInfomation personInfomation = new PersonInfomation();
//        personInfomation.setPname("");
        List<PersonInfomation> personInfomationList = personInfomationMapper.selectByPageAndCondition(personInfomation,0,8);
        System.out.println(personInfomationList);
    }

    @Test
    public void selectCount() {
        int num = personInfomationMapper.selectCount();
        System.out.println(num);
    }
}
