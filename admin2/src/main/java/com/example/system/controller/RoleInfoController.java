package com.example.system.controller;


import com.example.frame.dto.ResponseResult;
import com.example.system.service.RoleInfoService;
import com.example.system.vo.RoleInfoVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author bobo
 * @since 2021-04-19
 */
@Controller
@RequestMapping("/system/roleInfo")
public class RoleInfoController {

    @Autowired
    private RoleInfoService roleInfoService;

    @GetMapping("init")
    public String init() {
        return "system/role_info/role_info_list";
    }

    @PostMapping("listByTable")
    @ResponseBody
    public ResponseResult listByTable(RoleInfoVo entity){
        System.out.println("进入角色查询");
        return roleInfoService.listByTable(entity);
    }

    @GetMapping("listByAll")
    @ResponseBody
    public ResponseResult listByAll() {
        return ResponseResult.success("查询成功", roleInfoService.list());
    }

    @PostMapping("save")
    @ResponseBody
    public ResponseResult save(RoleInfoVo entity) {
        return roleInfoService.saveEntity(entity);
    }

    @PostMapping("updateById")
    @ResponseBody
    public ResponseResult updateById(RoleInfoVo entity) {
        return roleInfoService.updateEntity(entity);
    }

    @PostMapping("deleteById")
    @ResponseBody
    public ResponseResult deleteById(int id) {
        roleInfoService.removeById(id);
        return ResponseResult.success("删除成功");
    }
}
