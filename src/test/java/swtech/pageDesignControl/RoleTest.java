package swtech.pageDesignControl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import swtech.pageDesignControl.common.utils.DateUtil;
import swtech.pageDesignControl.entity.Department;
import swtech.pageDesignControl.mapper.DepartmentMapper;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleTest {
    @Resource
    private DepartmentMapper departmentMapper;

    @Test
    public void insert() {
        Department department = new Department();
        department.setDname("开发");
        department.setDcreateTime(DateUtil.getNewDate());
        int num = departmentMapper.insert(department);
        System.out.println(num);
    }

    @Test
    public void deleteById() {
        int did = 1;
        int num = departmentMapper.deleteById(did);
        System.out.println(num);
    }

    @Test
    public void updateById() {
        Department department = new Department();
        department.setDid(2);
        department.setDname("1");
        department.setDcreateTime(DateUtil.getNewDate());
        int num = departmentMapper.updateById(department);
        System.out.println(num);
    }

    @Test
    public void selectById() {
        Department department = departmentMapper.selectById(2);
        System.out.println(department.toString());
    }

    @Test
    public void selectAll() {
        Integer pageNo = 0;
        Integer pageSize = 8;
        IPage<Department> page = new Page<>(pageNo, pageSize);
        QueryWrapper<Department> wrapper = new QueryWrapper<>();
        Department department = new Department();
//        department.setDid(1);
        department.setDname("开发");
        wrapper.setEntity(department);
//        return studentService.page(page,wrapper);
        IPage<Department> departmentList = departmentMapper.selectPage(page, wrapper);
        System.out.println(departmentList.getRecords().size());
        System.out.println(departmentList.getTotal());
        System.out.println(departmentList.getSize());
    }

    @Test
    public void selectByPageAndCondition() {
        Department department = new Department();
        department.setDname("发");
        List<Department> departmentList = departmentMapper.selectByPageAndCondition(department, 0, 8);
        System.out.println(departmentList.size());
    }

    @Test
    public void selectCount() {
        int num = departmentMapper.selectCount();
        System.out.println(num);
    }
}
