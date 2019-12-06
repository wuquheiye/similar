package swtech.pageDesignControl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import swtech.pageDesignControl.entity.UserRole;
import swtech.pageDesignControl.mapper.UserRoleMapper;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRoleTest {
    @Resource
    private UserRoleMapper userRoleMapper;

    @Test
    public void getRoleByUserId() {
        List<String> list = userRoleMapper.getRoleByUserId(27);
        System.out.println(list);
    }

    @Test
    public void deleteByRoleId() {
        int num = userRoleMapper.deleteByRoleId(34);
        System.out.println(num);
    }

    @Test
    public void deleteByPermissionId() {
        int num = userRoleMapper.deleteByUserId(27);
        System.out.println(num);
    }

    @Test
    public void insertList() {
        List<UserRole> userRoleArrayList = new ArrayList<>();
        UserRole userRole1 = new UserRole();
        userRole1.setRid(1);
        userRole1.setUid(1);
        UserRole userRole2 = new UserRole();
        userRole2.setRid(1);
        userRole2.setUid(1);
        UserRole userRole3 = new UserRole();
        userRole3.setRid(1);
        userRole3.setUid(1);
        UserRole userRole4 = new UserRole();
        userRole4.setRid(1);
        userRole4.setUid(1);
        userRoleArrayList.add(userRole1);
        userRoleArrayList.add(userRole2);
        userRoleArrayList.add(userRole3);
        userRoleArrayList.add(userRole4);
//        int num = userRoleMapper.insertList(userRoleArrayList);
//        System.out.println(num);
    }
}
