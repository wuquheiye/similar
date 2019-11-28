package swtech.pageDesignControl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import swtech.pageDesignControl.common.vo.PermissionVO;
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
        List<PermissionVO> permissionVOList = permissionMapper.selecGtrandfather();
        getChild(permissionVOList);
        System.out.println(permissionVOList);
    }

    public List<PermissionVO> getChild(List<PermissionVO> permissionVOList){
        for (PermissionVO permissionVo : permissionVOList){
            List<PermissionVO> childPermissionVO = permissionMapper.selectChild(permissionVo.getPid());
            if(childPermissionVO !=null&& childPermissionVO.size()>0){
                getChild(childPermissionVO);
            }
            permissionVo.setChildrenPermission(childPermissionVO);
        }
        return permissionVOList;
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

    @Test
    public void getPermissionByRoleName() {
        List<Permission> permissionList = permissionMapper.getPermissionByRoleName("admin");
        System.out.println(permissionList);
    }
}
