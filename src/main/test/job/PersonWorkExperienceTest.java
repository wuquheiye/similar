package job;

import job.entity.PersonInfo;
import job.entity.PersonWorkExperience;
import job.mapper.PersonInfoMapper;
import job.mapper.PersonWorkExperienceMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonWorkExperienceTest {
    @Resource
    private PersonWorkExperienceMapper personWorkExperienceMapper;

    @Test
    public void selectByPersonUserId() {
        PersonWorkExperience personWorkExperience = personWorkExperienceMapper.selectByPersonUserId(1);
        System.out.println(personWorkExperience);
    }

}
