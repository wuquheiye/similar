package swtech.pageDesignControl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import swtech.pageDesignControl.entity.RolePermission;
import swtech.pageDesignControl.mapper.RolePermissionMapper;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RolePermissionTest {
    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Test
    public void getPermissionByRoleId() {
        List<String> list = rolePermissionMapper.getPermissionByRoleId(33);
        System.out.println(list);
    }

    @Test
    public void deleteByRoleId() {
        int num = rolePermissionMapper.deleteByRoleId(37);
        System.out.println(num);
    }

    @Test
    public void deleteByPermissionId() {
        int num = rolePermissionMapper.deleteByPermissionId(26);
        System.out.println(num);
    }

    @Test
    public void insertList() {
        List<RolePermission> rolePermissionList = new ArrayList<>();
        RolePermission rolePermission1 = new RolePermission();
        rolePermission1.setPid(1);
        rolePermission1.setRid(1);
        RolePermission rolePermission2 = new RolePermission();
        rolePermission2.setPid(2);
        rolePermission2.setRid(2);
        RolePermission rolePermission3 = new RolePermission();
        rolePermission3.setPid(3);
        rolePermission3.setRid(3);
        RolePermission rolePermission4 = new RolePermission();
        rolePermission4.setPid(4);
        rolePermission4.setRid(4);
        rolePermissionList.add(rolePermission1);
        rolePermissionList.add(rolePermission2);
        rolePermissionList.add(rolePermission3);
        rolePermissionList.add(rolePermission4);
        int num = rolePermissionMapper.insertList(rolePermissionList);
        System.out.println(num);
    }
}
