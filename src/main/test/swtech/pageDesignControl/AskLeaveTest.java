package swtech.pageDesignControl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import swtech.pageDesignControl.entity.AskLeave;
import swtech.pageDesignControl.mapper.AskLeaveMapper;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Test
    public void insertList() {
        List<AskLeave> tardyList = new ArrayList<>();
        AskLeave askLeave1 = new AskLeave();
        askLeave1.setLname("11");
        askLeave1.setLtype("12");
        askLeave1.setLdate("13");
        askLeave1.setLtime("14");
        askLeave1.setLtotal("15");
        askLeave1.setDate("16");
        AskLeave askLeave2 = new AskLeave();
        askLeave2.setLname("21");
        askLeave2.setLtype("22");
        askLeave2.setLdate("23");
        askLeave2.setLtime("24");
        askLeave2.setLtotal("25");
        askLeave2.setDate("26");
        tardyList.add(askLeave1);
        tardyList.add(askLeave2);
        int users = leaveMapper.insertList(tardyList);
        System.out.println(users);
    }
}
