package swtech.pageDesignControl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import swtech.pageDesignControl.common.utils.DateUtil;
import swtech.pageDesignControl.common.vo.PermissionVo;
import swtech.pageDesignControl.entity.Department;
import swtech.pageDesignControl.entity.Permission;
import swtech.pageDesignControl.mapper.DepartmentMapper;
import swtech.pageDesignControl.mapper.PermissionMapper;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PermissionTest {
    @Resource
    private PermissionMapper permissionMapper;

    @Test
    public void selecGtrandfather() {
        List<PermissionVo> permissionVoList = permissionMapper.selecGtrandfather();
        getChild(permissionVoList);
        System.out.println(permissionVoList);
    }

    public List<PermissionVo> getChild(List<PermissionVo> permissionVoList){
        for (PermissionVo permissionVo : permissionVoList){
            List<PermissionVo> childPermissionVo = permissionMapper.selectChild(permissionVo.getPid());
            if(childPermissionVo!=null&&childPermissionVo.size()>0){
                getChild(childPermissionVo);
            }
            permissionVo.setChildrenPermission(childPermissionVo);
        }
        return permissionVoList;
    }
}
