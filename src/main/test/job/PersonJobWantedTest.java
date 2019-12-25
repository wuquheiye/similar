package job;

import job.entity.PersonInfo;
import job.entity.PersonJobWanted;
import job.mapper.PersonInfoMapper;
import job.mapper.PersonJobWantedMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonJobWantedTest {
    @Resource
    private PersonJobWantedMapper personJobWantedMapper;

    @Test
    public void selectByPersonUserId() {
        PersonJobWanted personJobWanted = personJobWantedMapper.selectByPersonUserId(1);
        System.out.println(personJobWanted);
    }

}
