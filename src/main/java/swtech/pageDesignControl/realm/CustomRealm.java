package swtech.pageDesignControl.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import swtech.pageDesignControl.entity.Users;
import swtech.pageDesignControl.mapper.RolesPermissionsMapper;
import swtech.pageDesignControl.mapper.UserRoleMapper;
import swtech.pageDesignControl.mapper.UsersMapper;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Administrator on 2018/6/24 0024.
 */
public class CustomRealm extends AuthorizingRealm{
    @Autowired
    private UsersMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RolesPermissionsMapper rolesPermissionsMapper;

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 1.从主体传过来的认证信息中，获取用户名
        String username = (String) authenticationToken.getPrincipal();
        // 2.通过用户名去到数据库中获取凭证
        String password = getPasswordByUsername(username);
        if(password == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, "costomRealm");
        return authenticationInfo;
    }
    
    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        // 从数据库中根据用户名获取角色数据
        Set<String> roles = getRolesByUsername(username);
        // 从数据库中根据用户名获取权限数据
        Set<String> permissions = getPermissionbyUsername(roles);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);
        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }

    /**
     * 获取数据库查询凭证
     * @param username
     * @return
     */
    private String getPasswordByUsername(String username) {
        String password = userMapper.findUserByUsername(username);
        if(password == null){
            return null;
        }
        return password;
    }

    /**
     * 根据用户名获取数据库中的角色数据
     * @param username
     * @return
     */
    private Set<String> getRolesByUsername(String username) {
        List<String> list = userRoleMapper.getRolesByUsername(username);
        Set<String> sets = new HashSet<>(list);
        return sets;
    }
    
    /**
     * 获取用户权限信息
     * @return
     */
    private Set<String> getPermissionbyUsername(Set<String> rolenameList) {
        List<String> permissions = new ArrayList<>();
        List<String> roleList = new ArrayList<>(rolenameList);
        for(String role : roleList){
            List<String> persimmion = rolesPermissionsMapper.getPermissionbyRoleName(role);
            if(persimmion!=null){
                permissions.addAll(persimmion);
            }
        }
        Set<String> sets = new HashSet<String>(permissions);
        return sets;
    }
}
