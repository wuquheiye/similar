package job;

import job.entity.PersonUserPosition;
import job.mapper.PersonUserPositionMapper;
import job.service.IPersonUserPositionService;
import job.vo.ReturnMsg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonUserPositionTest {
    @Resource
    private PersonUserPositionMapper personUserPositionMapper;

    @Resource
    private IPersonUserPositionService iPersonUserPositionService;

    @Test
    public void iPersonUserPositionService() {
        ReturnMsg returnMsg = iPersonUserPositionService.save("3477221592@qq.com", 12);
        iPersonUserPositionService.save("3477221592@qq.com", 22);
        iPersonUserPositionService.save("3477221592@qq.com", 21);
        iPersonUserPositionService.save("3477221592@qq.com", 20);
        iPersonUserPositionService.save("3477221592@qq.com", 19);
        iPersonUserPositionService.save("3477221592@qq.com", 18);
        System.out.println(returnMsg);
    }

    @Test
    public void findByPersonUserIdAndCompanyPositionId() {
        PersonUserPosition personUserPosition = personUserPositionMapper.findByPersonUserIdAndCompanyPositionId(1, 1);
        System.out.println(personUserPosition);
    }

    @Test
    public void findByPersonUserId() {
//        List<PersonUserPosition> byPersonUserId = personUserPositionMapper.findByPersonUserIdAndState(1,null,0,1,10);
//        System.out.println(byPersonUserId);

        List<PersonUserPosition> byPersonUserId = personUserPositionMapper.findByPersonUserIdAndState(null,2,0,0,10);
        System.out.println(byPersonUserId);
    }

}
