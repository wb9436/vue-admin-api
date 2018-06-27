package com.vue.admin.vueadminapi.service;

import com.vue.admin.vueadminapi.dto.RoleDTO;

import java.util.List;
import java.util.Map;

/**
 * @Auther: WB
 * @Date: 2018/6/23 16:13
 * @Description:
 */
public interface IRoleService {
    List<RoleDTO> list(Map<String,Object> params);

    void add(RoleDTO roleDTO, int[] menuIds);

    void update(RoleDTO roleDTO, int[] menuIds);

    void delete(int roleId);

    RoleDTO get(int roleId);

    List<Integer> getRoleMenu(int roleId);
}
