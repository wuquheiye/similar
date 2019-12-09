package swtech.pageDesignControl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import swtech.pageDesignControl.entity.AskLeave;
import swtech.pageDesignControl.entity.Tardy;
import swtech.pageDesignControl.mapper.AskLeaveMapper;
import swtech.pageDesignControl.mapper.TardyMapper;
import swtech.pageDesignControl.service.impl.TardyServiceImpl;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TardyTest {

    @Resource
    private TardyMapper tardyMapper;

    @Resource
    private TardyServiceImpl tardyService;

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

    @Test
    public void insertList() {
        List<Tardy> tardyList = new ArrayList<>();
        Tardy tardy1 = new Tardy();
        tardy1.setTname("11");
        tardy1.setDate("12");
        tardy1.setDate("13");
        Tardy tardy2 = new Tardy();
        tardy2.setTname("21");
        tardy2.setDate("22");
        tardy2.setDate("23");
        Tardy tardy3 = new Tardy();
        tardy3.setTname("31");
        tardy3.setDate("32");
        tardy3.setDate("33");
        tardyList.add(tardy1);
        tardyList.add(tardy2);
        tardyList.add(tardy3);
        int users = tardyMapper.insertList(tardyList);
        System.out.println(users);
    }
}
