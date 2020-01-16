package job;

import job.entity.PersonEducationExperience;
import job.entity.PersonInfo;
import job.mapper.PersonEducationExperienceMapper;
import job.mapper.PersonInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonInfoTest {
    @Resource
    private PersonInfoMapper personInfoMapper;

    @Test
    public void selectByPersonUserId() {
        PersonInfo personInfo = personInfoMapper.selectByPersonUserId(1);
        System.out.println(personInfo);
    }

    @Test
    public void insert() {
        PersonInfo personInfo = new PersonInfo();
        personInfo.setFresh(1);
        personInfo.setPersonUserId(1);
        int num = personInfoMapper.insert(personInfo);
        System.out.println(num);
    }
}
