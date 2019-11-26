package swtech.pageDesignControl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import swtech.pageDesignControl.common.vo.PermissionVo;
import swtech.pageDesignControl.entity.Permission;
import swtech.pageDesignControl.mapper.PermissionMapper;
import swtech.pageDesignControl.service.IPermissionService;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PermissionTest {
    @Resource
    private PermissionMapper permissionMapper;

    @Autowired
    private IPermissionService iPermissionService;

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

    @Test
    public void selectAll() {
        List<Permission> permissionList = permissionMapper.selectList(null);
        System.out.println(permissionList);
    }

    @Test
    public void removeById() {
        boolean isTrue = iPermissionService.removeById(95);
        System.out.println(isTrue);
    }
}
