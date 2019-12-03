package swtech.pageDesignControl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import swtech.pageDesignControl.common.utils.DateUtil;
import swtech.pageDesignControl.common.vo.UsersVO;
import swtech.pageDesignControl.entity.Users;
import swtech.pageDesignControl.mapper.UsersMapper;
import swtech.pageDesignControl.service.IUsersService;
import swtech.pageDesignControl.service.impl.UsersServiceImpl;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsersTest {
    @Resource
    private UsersMapper usersMapper;

    @Resource
    private IUsersService iUsersService;

    @Test
    public void insert() {
        Users users = new Users();
        users.setUinvitationCode("1");
        users.setUusername("2");
        users.setUcreationtime(DateUtil.getNewDate().toString());
        users.setDid(1);
        users.setUtelephonenumber("1111111111");
        users.setUpassword("13333333");
        int num = usersMapper.insert(users);
        System.out.println(num);
    }

    @Test
    public void deleteById() {
        int uid = 8;
        int num = usersMapper.deleteById(uid);
        System.out.println(num);
    }

    @Test
    public void updateById() {
        Users users = new Users();
        users.setUid(9);
        users.setUinvitationCode("1");
        users.setUusername("2");
        users.setUcreationtime(DateUtil.getNewDate());
        users.setDid(1);
        users.setUtelephonenumber("1111111111");
        users.setUpassword("13333333");
        int num = usersMapper.updateById(users);
        System.out.println(num);
    }

    @Test
    public void selectById() {
        Users users = usersMapper.selectById(11);
        System.out.println(users.toString());
    }

//    @Test
//    public void selectAll() {
//        Integer pageNo = 0;
//        Integer pageSize = 8;
//        IPage<users> page = new Page<>(pageNo, pageSize);
//        QueryWrapper<users> wrapper = new QueryWrapper<>();
//        users users = new users();
////        users.setDid(1);
//        users.setDname("开发");
//        wrapper.setEntity(users);
////        return studentService.page(page,wrapper);
//        IPage<users> usersList = usersMapper.selectPage(page, wrapper);
//        System.out.println(usersList.getRecords().size());
//        System.out.println(usersList.getTotal());
//        System.out.println(usersList.getSize());
//    }

    @Test
    public void selectByPageAndCondition() {
        Users users = new Users();
        users.setUusername("");
        List<UsersVO> usersList = usersMapper.selectByPageAndCondition(users, 0, 8);
        System.out.println(usersList.size());
    }

    @Test
    public void selectCount() {
        int num = usersMapper.selectCount();
        System.out.println(num);
    }

    @Test
    public void updateDepartmentToNull() {
        int num = usersMapper.updateDepartmentToNull(115);
        System.out.println(num);
    }

    @Test
    public void updateUustate() {
        int num = usersMapper.updateUustate(27,0);
        System.out.println(num);
    }

    @Test
    public void findUsersByName() {
        Users users = usersMapper.findUsersByName("2");
        System.out.println(users);
    }

    @Test
    public void findUsersByDepartmentAndRole() {
        List<Users> users = usersMapper.findUsersByDepartmentAndRole(117,1);
        System.out.println(users);
    }

    @Test
    public void getPermission() {
        List<String> users = iUsersService.getPermission("立捷总经理");
        System.out.println(users);
    }
}
