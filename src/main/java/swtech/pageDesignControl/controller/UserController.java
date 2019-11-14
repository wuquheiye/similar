package swtech.pageDesignControl.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import swtech.pageDesignControl.common.vo.ReturnMsg;
import swtech.pageDesignControl.service.IUserService;

import javax.annotation.Resource;



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

    @RequestMapping(value={"/uploadImgFile"})
    @ResponseBody
    public JSONObject uploadImgFile(@RequestParam("uploadImgFile") MultipartFile uploadImgFile) {
        try{
//            return  map2Json(uploadImgFile);
            return  new JSONObject();
        }catch(Exception e){
            e.printStackTrace();
            log.error("上传-文件-失败");
            return null;
        }
    }

}
