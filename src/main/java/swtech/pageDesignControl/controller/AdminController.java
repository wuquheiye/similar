package swtech.pageDesignControl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-19
 */
@Controller
public class AdminController {

    /**
     * 跳转登录页面
     *
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "use/login";
    }

    @RequestMapping("/index")
    public String index(){
        return "use/index";
    }
    @RequestMapping("/manage")
    public String manage(){
        return "manage/manage";
    }

    @RequestMapping("/403")
    public String index403(){
        return "use/403";
    }

    @RequestMapping("/organization")
    public String organization(){
        return "organization/organization";
    }

//
//
//    @RequiresRoles("user")
//    @GetMapping("/u")
//    @ResponseBody
//    public String testRoles(){
//        return  "testRole success";
//    }
//
//    @RequiresRoles("manage")
//    @GetMapping("/m")
//    @ResponseBody
//    public String testRoles1(){
//        return  "testRole success";
//    }
//
//    @RequiresPermissions("user:add")
//    @GetMapping("/a")
//    @ResponseBody
//    public String testPermissions(){
//        return  "testPermissions success";
//    }
//
//
//    @RequiresPermissions("user:add1")
//    @GetMapping("/b")
//    @ResponseBody
//    public String testPermissions1(){
//        return  "testPermissions success";
//    }
//
//
//    @GetMapping("/c")
//    @ResponseBody
//    public String testPermissions2(){
//        return  "testPermissions success";
//    }
}

