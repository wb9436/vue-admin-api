package com.vue.admin.vueadminapi.service;

import com.vue.admin.vueadminapi.dto.MenuDTO;

import java.util.List;
import java.util.Map;

/**
 * @Auther: WB
 * @Date: 2018/6/22 19:23
 * @Description:
 */
public interface IMenuService {
    List<MenuDTO> list(Map<String,Object> params);

    void add(MenuDTO menuDTO);

    void update(MenuDTO menuDTO);

    MenuDTO get(int menuId);

    void delete(int menuId);

    List<MenuDTO> allList(Map<String,Object> params);

    List<String> userRoleList(String roleKey);

    List<String> userPermsList(String roleKey);
}
