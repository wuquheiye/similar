package swtech.pageDesignControl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import swtech.pageDesignControl.entity.AskLeave;
import swtech.pageDesignControl.entity.Tardy;
import swtech.pageDesignControl.mapper.AskLeaveMapper;
import swtech.pageDesignControl.mapper.TardyMapper;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TardyTest {

    @Resource
    private TardyMapper tardyMapper;

    @Test
    public void getPermission() {
        List<Tardy> users = tardyMapper.selectByPageAndCondition(null,1,10);
        System.out.println(users);
    }

    @Test
    public void selectCount() {
        int users = tardyMapper.selectCount();
        System.out.println(users);
    }
}
