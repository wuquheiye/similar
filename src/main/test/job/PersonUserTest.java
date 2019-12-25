package job;

import job.entity.Permission;
import job.mapper.PermissionMapper;
import job.mapper.PersonUserMapper;
import job.vo.PermissionVO;
import job.vo.PersonVO;
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

}
