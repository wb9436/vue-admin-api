package com.vue.admin.vueadminapi.controller;

import com.vue.admin.vueadminapi.dto.MenuDTO;
import com.vue.admin.vueadminapi.service.IMenuService;
import com.vue.admin.vueadminapi.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    IMenuService menuService;

    @GetMapping("/list")
    Result list(@RequestParam Map<String, Object> params) {
        List<MenuDTO> list = menuService.list(params);
        return Result.ok()
                .put("list", list);
    }

    @PostMapping("/add")
    Result add(@RequestParam Map<String, String> params) {
        try {
            MenuDTO menuDTO = new MenuDTO();
            menuDTO.setParentId(Integer.parseInt(params.get("parentId")));
            menuDTO.setName(params.get("name"));
            menuDTO.setPerms(params.get("perms"));
            menuDTO.setType(Integer.parseInt(params.get("type")));
            menuDTO.setOrderNum(Integer.parseInt(params.get("orderNum")));
            menuDTO.setGmtCreate(new Date());
            menuDTO.setGmtModified(new Date());
            menuService.add(menuDTO);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    @PostMapping("/update")
    Result update(@RequestParam Map<String, String> params) {
        try {
            int menuId = Integer.parseInt(params.get("menuId"));
            MenuDTO menuDTO = menuService.get(menuId);
            if (menuDTO == null) {
                return Result.error("数据异常");
            }
            menuDTO.setParentId(Integer.parseInt(params.get("parentId")));
            menuDTO.setName(params.get("name"));
            menuDTO.setPerms(params.get("perms"));
            menuDTO.setType(Integer.parseInt(params.get("type")));
            menuDTO.setOrderNum(Integer.parseInt(params.get("orderNum")));
            menuDTO.setGmtModified(new Date());

            menuService.update(menuDTO);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    @PostMapping("/delete")
    Result delete(@RequestParam Map<String, String> params) {
        int menuId = Integer.parseInt(params.get("menuId"));
        MenuDTO menuDTO = menuService.get(menuId);
        if (menuDTO != null) {
            menuService.delete(menuId);
        }
        return Result.ok();
    }

    @GetMapping("/all")
    Result alllist(@RequestParam Map<String, Object> params) {
        List<MenuDTO> list = menuService.allList(params);
        return Result.ok()
                .put("list", list);
    }


}
