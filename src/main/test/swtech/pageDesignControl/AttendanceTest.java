package swtech.pageDesignControl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import swtech.pageDesignControl.entity.Attendance;
import swtech.pageDesignControl.mapper.AttendanceMapper;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AttendanceTest {

    @Resource
    private AttendanceMapper attendanceMapper;


    @Test
    public void getPermission() {
        List<Attendance> users = attendanceMapper.selectByPageAndCondition(null,1,10);
        System.out.println(users);
    }

    @Test
    public void deleteById() {
        int users = attendanceMapper.deleteById(3);
        System.out.println(users);
    }
}
