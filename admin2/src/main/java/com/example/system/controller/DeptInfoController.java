package com.example.system.controller;


import com.example.frame.dto.ResponseResult;
import com.example.system.entity.DeptInfo;
import com.example.system.service.DeptInfoService;
import com.example.system.vo.DeptInfoVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author bobo
 * @since 2021-04-19
 */
@Controller
@RequestMapping("/system/deptInfo")
public class DeptInfoController {

    @Autowired
    DeptInfoService deptInfoService;

    @GetMapping("init")
    public String init(){
        return "system/dept_Info/dept_Info_list";
    }

    @PostMapping("listByDTree")
    @ResponseBody
    public Map<String, Object> listByDTree(){
        return deptInfoService.listByDTree();
    }

    @GetMapping("listByTable")
    @ResponseBody
    public ResponseResult listByTable(DeptInfoVo entity) {
        List<DeptInfoVo> list = deptInfoService.listByTable(entity);
        return ResponseResult.table(list.size(), list);
    }

    @PostMapping("save")
    @ResponseBody
    public ResponseResult save(DeptInfo entity){
        return deptInfoService.saveEntity(entity);
    }

    @PostMapping("updateById")
    @ResponseBody
    public ResponseResult updateById(DeptInfo entity){
        return deptInfoService.updateEntity(entity);
    }

    @PostMapping("deleteById")
    @ResponseBody
    public ResponseResult deleteById(int id){
        deptInfoService.removeById(id);
        return ResponseResult.success("删除成功");
    }

}
