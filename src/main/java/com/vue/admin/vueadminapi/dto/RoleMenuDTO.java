package com.vue.admin.vueadminapi.dto;

import java.io.Serializable;

/**
 * @Auther: WB
 * @Date: 2018/6/22 18:13
 * @Description:
 */
public class RoleMenuDTO implements Serializable {

    private Integer id;
    private Integer roleId;//角色ID
    private Integer menuId;//菜单ID

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
}
