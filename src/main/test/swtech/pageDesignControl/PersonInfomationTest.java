package swtech.pageDesignControl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import swtech.pageDesignControl.entity.PersonInfomation;
import swtech.pageDesignControl.mapper.PersonInfomationMapper;
import swtech.pageDesignControl.service.impl.PersonInfomationServiceImpl;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonInfomationTest {
    @Resource
    private PersonInfomationMapper personInfomationMapper;

    @Resource
    private PersonInfomationServiceImpl personInfomationService;

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

    @Test
    public void insert() {
        PersonInfomation personInfomation = new PersonInfomation();
        personInfomation.setPname("1");
        int num = personInfomationMapper.insert(personInfomation);
        System.out.println(num);
    }

    @Test
    public void insert1() {
        PersonInfomation personInfomation = new PersonInfomation();
        personInfomation.setPname("1");
        boolean num = personInfomationService.save(personInfomation);
        System.out.println(num);
    }
}
