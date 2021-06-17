package com.example.system.controller;


import com.example.frame.consts.SessionConst;
import com.example.frame.dto.ResponseResult;
import com.example.frame.shiro.util.ShiroUtils;
import com.example.system.service.UserInfoService;
import com.example.system.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author bobo
 * @since 2021-04-19
 */
@Controller
@RequestMapping("/system/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("init")
    public String init(){
        System.out.println("进入方法init");
        return "system/user_Info/user_Info_list";
    }

    @PostMapping("listByTable")
    @ResponseBody
    public ResponseResult listByTable(UserInfoVo entity){
        return userInfoService.listByTable(entity);
    }

    @PostMapping("save")
    @ResponseBody
    public ResponseResult save(UserInfoVo entity){
        return userInfoService.saveEntity(entity);
    }

    @PostMapping("updateById")
    @ResponseBody
    public ResponseResult updateById(UserInfoVo entity){
        return userInfoService.updateEntity(entity);
    }

    @PostMapping("deleteById")
    @ResponseBody
    public ResponseResult deleteById(int id){
        userInfoService.removeById(id);
        return ResponseResult.success("删除成功");
    }

    @GetMapping("updateSetting")
    public String updateSetting(Model model){
        UserInfoVo userInfoVo = (UserInfoVo) ShiroUtils.getSessionAttribute(SessionConst.USER_INFO_SESSION);
        System.out.println(userInfoVo.getName());
        model.addAttribute("userInfoVo", userInfoVo);
        return "system/user_Info/user_Info_setting";
    }

    @PostMapping("updateInfoOrPwdById")
    @ResponseBody
    public ResponseResult updateInfoOrPwdById(UserInfoVo entity){
        System.out.println("进入基本信息方法");
        return userInfoService.updateInfoOrPwdById(entity);
    }
}
