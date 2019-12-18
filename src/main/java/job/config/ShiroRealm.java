package job.config;

import job.mapper.PermissionMapper;
import job.mapper.RoleMapper;
import job.mapper.UserMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import job.entity.Permission;
import job.entity.Role;
import job.entity.User;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Administrator on 2018/6/24 0024.
 */
public class ShiroRealm extends AuthorizingRealm {
    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private PermissionMapper permissionMapper;

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 1.从主体传过来的认证信息中，获取用户名
        String username = (String) authenticationToken.getPrincipal();
        // 2.通过用户名去到数据库中获取凭证
        String password = getPasswordByUtelephonenumber(username);
        if (password == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, "costomRealm");
        return authenticationInfo;
    }

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String utelephonenumber = (String) principalCollection.getPrimaryPrincipal();
        // 从数据库中根据用户名获取角色数据
        Set<String> roles = getRolesByUtelephonenumber(utelephonenumber);
        // 从数据库中根据用户名获取权限数据
        Set<String> permissions = getPermissionbyUsername(roles);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);
        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }

    /**
     * 获取数据库查询凭证
     *
     * @param email
     * @return
     */
    private String getPasswordByUtelephonenumber(String email) {
        User user = userMapper.findUserByEmail(email);
        if (user == null) {
            return null;
        }
        return user.getPassword();
    }

    /**
     * 根据用户名获取数据库中的角色数据
     *
     * @param email
     * @return
     */
    private Set<String> getRolesByUtelephonenumber(String email) {
        Role role = roleMapper.getRoleByEmail(email);
        Set<String> sets = new HashSet<>();
        if(role!=null){
            sets.add(role.getName());
        }
        return sets;
    }

    /**
     * 获取用户权限信息
     *
     * @return
     */
    private Set<String> getPermissionbyUsername(Set<String> rolenameList) {
        List<String> permissions = new ArrayList<>();
        List<String> roleList = new ArrayList<>(rolenameList);
        for (String role : roleList) {
            List<Permission> permissionList = permissionMapper.getPermissionByRoleName(role);
            List<String> permissionLists = new ArrayList<>();
            for (Permission permission : permissionList) {
                if (permission != null) {
                    permissionLists.add(permission.getName());
                }
            }
            if (permissionList != null) {
                permissions.addAll(permissionLists);
            }
        }
        Set<String> sets = new HashSet<String>(permissions);
        return sets;
    }
}
