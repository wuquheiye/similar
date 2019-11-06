package swtech.pageDesignControl.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import swtech.pageDesignControl.common.vo.ReturnMsg;
import swtech.pageDesignControl.entity.User;
import swtech.pageDesignControl.service.IUserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 袁君选
 * @since 2019-10-24
 */
@RestController
@RequestMapping("/userControl")
@CrossOrigin //跨域
@Slf4j
public class UserController  {

    @Resource
    private IUserService iUserService;
    /**
     * 查询用户是否有简历
     */
    @GetMapping("/selectJobWanted")
    public  ReturnMsg selectJobWanted(@RequestParam("id") Integer id){
        return iUserService.selectJobWanted(id);
    }



}
