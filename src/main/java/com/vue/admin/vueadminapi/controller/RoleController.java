package com.vue.admin.vueadminapi.controller;

import com.vue.admin.vueadminapi.dto.RoleDTO;
import com.vue.admin.vueadminapi.service.IRoleService;
import com.vue.admin.vueadminapi.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Auther: WB
 * @Date: 2018/6/22 19:21
 * @Description:
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    IRoleService roleService;

    @GetMapping("/list")
    Result list(@RequestParam Map<String, Object> params) {
        List<RoleDTO> list = roleService.list(params);
        return Result.ok()
                .put("list", list);
    }

    @PostMapping("/add")
    Result add(@RequestParam Map<String, String> params) {
        try {
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setRoleKey(params.get("roleKey"));
            roleDTO.setRoleName(params.get("roleName"));
            roleDTO.setRemark(params.get("remark"));
            roleDTO.setGmtCreate(new Date());
            roleDTO.setGmtModified(new Date());

            String[] menuIdsStr = new String[0];
            if (!StringUtils.isEmpty(params.get("menuIds"))) {
                menuIdsStr = params.get("menuIds").split(",");
            }
            int[] menuIds = new int[0];
            if (menuIdsStr != null && menuIdsStr.length > 0) {
                menuIds = new int[menuIdsStr.length];
                for (int i = 0; i < menuIdsStr.length; i++) {
                    menuIds[i] = Integer.parseInt(menuIdsStr[i]);
                }
            }
            roleService.add(roleDTO, menuIds);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    @PostMapping("/update")
    Result update(@RequestParam Map<String, String> params) {
        try {
            int roleId = Integer.parseInt(params.get("roleId"));
            RoleDTO roleDTO = roleService.get(roleId);
            if(roleDTO == null){
                return Result.error("数据异常");
            }
            roleDTO.setRoleKey(params.get("roleKey"));
            roleDTO.setRoleName(params.get("roleName"));
            roleDTO.setRemark(params.get("remark"));
            roleDTO.setGmtModified(new Date());

            String[] menuIdsStr = new String[0];
            if (!StringUtils.isEmpty(params.get("menuIds"))) {
                menuIdsStr = params.get("menuIds").split(",");
            }
            int[] menuIds = new int[0];
            if (menuIdsStr != null && menuIdsStr.length > 0) {
                menuIds = new int[menuIdsStr.length];
                for (int i = 0; i < menuIdsStr.length; i++) {
                    menuIds[i] = Integer.parseInt(menuIdsStr[i]);
                }
            }
            roleService.update(roleDTO, menuIds);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    @PostMapping("/delete")
    Result delete(@RequestParam Map<String, String> params) {
        int roleId = Integer.parseInt(params.get("roleId"));
        RoleDTO roleDTO = roleService.get(roleId);
        if (roleDTO != null) {
            roleService.delete(roleId);
        }
        return Result.ok();
    }

    @GetMapping("/roleMenu")
    Result getRoleMenu(@RequestParam Map<String, String> params){
        int roleId = Integer.parseInt(params.get("roleId"));
        List<Integer> roleMenu = roleService.getRoleMenu(roleId);
        return Result.ok()
                .put("roleMenu", roleMenu);
    }

}
