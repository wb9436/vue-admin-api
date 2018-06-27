package com.vue.admin.vueadminapi.dto;

import com.vue.admin.vueadminapi.config.ApplicationConfig;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: WB
 * @Date: 2018/6/20 10:09
 * @Description:
 */
public class UserDTO implements Serializable {

    private Integer userId;
    private String username;//用户名
    private String nickname;//
    private String password;//密码
    private String avatar;//头像
    private Integer status;//状态 0:禁用，1:正常
    private String roleKey;//角色标识
    private String desc;//描述
    private Date gmtCreate;//创建时间
    private Date gmtModified;//修改时间

    private String avatarUrl;

    public String getAvatarUrl() {
        if(!StringUtils.isEmpty(avatar)){
            return ApplicationConfig.getUploadVisit() + avatar;
        }
        return "";
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
