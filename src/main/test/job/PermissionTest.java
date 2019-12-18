package job;

import job.entity.Permission;
import job.mapper.PermissionMapper;
import job.vo.PermissionVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PermissionTest {
    @Resource
    private PermissionMapper permissionMapper;

    @Test
    public void getPermissionByRoleName() {
        List<Permission> permissionList = permissionMapper.getPermissionByRoleName("主管");
        System.out.println(permissionList);
    }

    @Test
    public void selecGtrandfather() {
        List<PermissionVO> permissionVOList = permissionMapper.selecGtrandfather();
        getChild(permissionVOList);
        System.out.println(permissionVOList);
    }

    public List<PermissionVO> getChild(List<PermissionVO> permissionVOList){
        for (PermissionVO permissionVo : permissionVOList){
            List<PermissionVO> childPermissionVO = permissionMapper.selectChild(permissionVo.getId());
            if(childPermissionVO !=null&& childPermissionVO.size()>0){
                getChild(childPermissionVO);
            }
            permissionVo.setChildrenPermission(childPermissionVO);
        }
        return permissionVOList;
    }

}
