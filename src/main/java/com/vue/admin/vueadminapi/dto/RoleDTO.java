package com.vue.admin.vueadminapi.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: WB
 * @Date: 2018/6/20 10:09
 * @Description:
 */
public class RoleDTO implements Serializable {

    private Integer roleId;//
    private String roleKey;//角色标识
    private String roleName;//角色名称
    private String remark;//备注
    private Date gmtCreate;//创建时间
    private Date gmtModified;//修改时间

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
