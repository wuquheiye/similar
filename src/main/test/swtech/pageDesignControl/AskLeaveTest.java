package swtech.pageDesignControl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import swtech.pageDesignControl.entity.AskLeave;
import swtech.pageDesignControl.mapper.AskLeaveMapper;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AskLeaveTest {

    @Resource
    private AskLeaveMapper leaveMapper;


    @Test
    public void getPermission() {
        List<AskLeave> users = leaveMapper.selectByPageAndCondition(null,1,10);
        System.out.println(users);
    }

    @Test
    public void selectCount() {
        int users = leaveMapper.selectCount();
        System.out.println(users);
    }
}
