package com.vue.admin.vueadminapi.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Auther: WB
 * @Date: 2018/6/22 18:13
 * @Description:
 */
public class MenuDTO implements Serializable {

    private Integer menuId;//
    private Integer parentId;//父菜单ID，一级菜单为0
    private String name;//菜单名称
    private String perms;//授权(多个用逗号分隔，如：user:list,user:create)
    private Integer type;//类型   0：目录   1：菜单   2：按钮
    private Integer orderNum;//排序
    private Date gmtCreate;//创建时间
    private Date gmtModified;//修改时间

    private List<MenuDTO> children;

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
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

    public List<MenuDTO> getChildren() {
        return children;
    }

    public void setChildren(List<MenuDTO> children) {
        this.children = children;
    }
}
