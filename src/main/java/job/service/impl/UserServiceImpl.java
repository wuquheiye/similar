package job.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import job.entity.Permission;
import job.entity.Role;
import job.entity.User;
import job.entity.UserRole;
import job.mapper.PermissionMapper;
import job.mapper.RoleMapper;
import job.mapper.UserMapper;
import job.mapper.UserRoleMapper;
import job.service.IUserService;
import job.utils.MailUTil;
import job.utils.MessageUtil;
import job.utils.RandomUtil;
import job.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private PermissionMapper permissionMapper;

    @Autowired
    private MailUTil mailUTil;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private MessageUtil messageUtil;

    @Override
    @Transactional
    public boolean regist(User user, int roleId) {
        // 1.生成用户
        int num = userMapper.insert(user);
        if (num <= 0) {
            return false;
        } else {
            UserRole userRole = new UserRole();
            User userByEmail = userMapper.findUserByTelephonenumber(user.getTelephonenumber());
            userRole.setUid(userByEmail.getId());
            userRole.setRid(roleId);
            // 2.生成用户角色关系
            int num1 = userRoleMapper.insert(userRole);
            if (num1 > 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    @Transactional
    public int forgetpassword(User user) {
        // 1.查询邮箱是否存在
        User userByEmail = userMapper.findUserByTelephonenumber(user.getTelephonenumber());
        userByEmail.setPassword(user.getPassword());
        // 2.修改
        int num = userMapper.updateById(userByEmail);
        return num;
    }

    @Override
    public User findUserByTelephonenumber(String telephonenumber) {
        return userMapper.findUserByTelephonenumber(telephonenumber);
    }

    @Override
    public LoginVO getLoginVO(String telephonenumber) {
        if ("".equals(telephonenumber)) {
            return null;
        }
        LoginVO loginVO = new LoginVO();
        // 1.根据邮箱名查询用户
        User user = userMapper.findUserByTelephonenumber(telephonenumber);
        // 2.根据邮箱查询角色
        Role role = roleMapper.getRoleByTelephonenumber(telephonenumber);
        if (user == null) {
            return null;
        }
        if(role == null){
            return null;
        }
        loginVO.setUser(user);
        loginVO.setRole(role);
        return loginVO;
    }

    @Override
    public List<User> selectByPageAndCondition(User user, int page, int pageSize) {
        int pageStart = (page - 1) * pageSize;
        return userMapper.selectByPageAndCondition(user, pageStart, pageSize);
    }

    @Override
    public int selectCount(User user) {
        return userMapper.selectCount(user);
    }

    @Override
    public boolean updateState(int id, int state) {
        int num = userMapper.updateState(id, state);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Permission> getPermission(String telephonenumber) {
        List<Permission> permissions = new ArrayList<>();
        // 根据邮箱获取角色
        Role role = roleMapper.getRoleByTelephonenumber(telephonenumber);
        if (role == null) {
            return null;
        }
        // 根据角色名获取权限
        List<Permission> permissionList = permissionMapper.getPermissionByRoleName(role.getName());
        for (Permission permission : permissionList) {
            permissions.add(permission);
        }
        return permissions;
    }

    @Override
    @Transactional
    public String sendEmail(String email) {
        // 1.生成随机数
        String verificationCode = RandomUtil.getRandom(6);
        // 2.获取邮件模板
        Context context = new Context();
        // 3.设置随机数
        context.setVariable("verificationCode", verificationCode);
        // 4.设置模板文件
        String emailContent = templateEngine.process("registerEmailTemplate", context);
        // 5.发送模板邮件
        mailUTil.sendHtmlMail(email, "注册验证邮件", emailContent);
        return verificationCode;
    }

    @Override
    @Transactional
    public String sendTelephonenumberVerificationCode(String telephonenumber) throws Exception {
        // 1.生成随机数
        String verificationCode = RandomUtil.getRandom(6);
        // 2.发送手机验证码
        messageUtil.sendMessage(telephonenumber,verificationCode);
        return verificationCode;
    }
}
