package job;

import job.entity.PersonEducationExperience;
import job.mapper.PersonEducationExperienceMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonEducationExperienceTest {
    @Resource
    private PersonEducationExperienceMapper personEducationExperienceMapper;

    @Test
    public void selectByPersonUserId() {
        PersonEducationExperience personEducationExperience = personEducationExperienceMapper.selectByPersonUserId(1);
        System.out.println(personEducationExperience);
    }

}
