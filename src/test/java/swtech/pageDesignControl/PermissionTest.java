package swtech.pageDesignControl;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import swtech.pageDesignControl.entity.Permission;
import swtech.pageDesignControl.mapper.PermissionMapper;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PermissionTest {
    @Resource
    private PermissionMapper permissionMapper;

    @Test
    public void insert() {
        Permission permission = new Permission();
        permission.setPname("1");
        permission.setPtype("2");
        int num = permissionMapper.insert(permission);
        System.out.println(num);
    }

    @Test
    public void deleteById() {
        int did = 1;
        int num = permissionMapper.deleteById(1);
        System.out.println(num);
    }

    @Test
    public void updateById() {
        Permission permission = new Permission();
        permission.setPid(1);
        permission.setPname("1");
        permission.setPtype("2");
        int num = permissionMapper.updateById(permission);
        System.out.println(num);
    }

    @Test
    public void selectById() {
        Permission permission = permissionMapper.selectById(2);
        System.out.println(permission);
    }

//    @Test
//    public void selectAll() {
//        Integer pageNo = 0;
//        Integer pageSize = 8;
//        IPage<Department> page = new Page<>(pageNo, pageSize);
//        QueryWrapper<Department> wrapper = new QueryWrapper<>();
//        Department department = new Department();
////        department.setDid(1);
//        department.setDname("开发");
//        wrapper.setEntity(department);
////        return studentService.page(page,wrapper);
//        IPage<Department> departmentList = departmentMapper.selectPage(page, wrapper);
//        System.out.println(departmentList.getRecords().size());
//        System.out.println(departmentList.getTotal());
//        System.out.println(departmentList.getSize());
//    }
//
    @Test
    public void selectByPageAndCondition() {
        Permission permission = new Permission();
        permission.setPname("1");
        List<Permission> permissionmentList = permissionMapper.selectByPageAndCondition(permission, 0, 8);
        System.out.println(permissionmentList.size());
    }
//
//    @Test
//    public void selectCount() {
//        int num = departmentMapper.selectCount();
//        System.out.println(num);
//    }
}
