package com.vue.admin.vueadminapi.dto;

import java.io.Serializable;

/**
 * @Auther: WB
 * @Date: 2018/6/20 10:09
 * @Description:
 */
public class UserRoleDTO implements Serializable {

    private Integer id;//
    private Integer userId;//
    private Integer roleId;//

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
