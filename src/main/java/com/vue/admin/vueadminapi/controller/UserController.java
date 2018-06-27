package com.vue.admin.vueadminapi.controller;

import com.vue.admin.vueadminapi.config.ApplicationConfig;
import com.vue.admin.vueadminapi.dto.UserDTO;
import com.vue.admin.vueadminapi.redis.RedisCacheUtils;
import com.vue.admin.vueadminapi.service.IMenuService;
import com.vue.admin.vueadminapi.service.IUserService;
import com.vue.admin.vueadminapi.utils.FileUploadUtils;
import com.vue.admin.vueadminapi.utils.MD5;
import com.vue.admin.vueadminapi.utils.Query;
import com.vue.admin.vueadminapi.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Auther: WB
 * @Date: 2018/6/20 10:20
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;
    @Autowired
    IMenuService menuService;

    @PostMapping("/login")
    Result login(@RequestBody Map<String, String> loginUser) {
        String password = loginUser.get("password");
        String username = loginUser.get("username");
        if (StringUtils.isEmpty(password)) {
            return Result.error("密码不允许为空");
        }
        if (StringUtils.isEmpty(username)) {
            return Result.error("用户名不允许为空");
        }
        loginUser.put("password", MD5.encodeUTF8(password));
        UserDTO user = userService.checkUser(loginUser);
        if (user == null) {
            return Result.error("用户名或密码错误");
        }
        if (user.getStatus() == 0) {
            return Result.error("账号已经被禁用，请联系管理员");
        }
        String token = MD5.encodeUTF8("LoginToken:" + user.getUserId());
        RedisCacheUtils.putInCache(token, user, 1800);
        return Result.ok().put("token", token);
    }

    @PostMapping("/logout")
    Result logout(@RequestParam String token) {
        RedisCacheUtils.delFromCache(token);
        return Result.ok();
    }

    @GetMapping("/reLogin")
    Result reLogin() {
        return Result.error401();
    }

    @GetMapping("/info")
    Result userInfo(@RequestParam String token) {
        UserDTO user = RedisCacheUtils.getFromCache(token);
        if (user == null) {
            return Result.error401();
        }
        return Result.ok()
                .put("uploadUrl", ApplicationConfig.getUploadAction())
                .put("name", user.getUsername())
                .put("avatar", user.getAvatarUrl())
                .put("roles", menuService.userRoleList(user.getRoleKey()))
                .put("perms", menuService.userPermsList(user.getRoleKey()));
    }

    @GetMapping("/list")
    Result listByPage(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<UserDTO> list = userService.list(query);
        int total = userService.count(query);
        return Result.ok()
                .put("total", total)
                .put("list", list);
    }

    @PostMapping("/modifyStatus")
    Result modifyStatus(@RequestParam Map<String, String> params) {
        int userId = Integer.parseInt(params.get("userId"));
        UserDTO userDTO = userService.get(userId);
        if (userDTO == null) {
            return Result.error("数据异常");
        }
        userDTO.setStatus(Integer.parseInt(params.get("status")));
        userService.modifyStatus(userDTO);
        return Result.ok();
    }

    @PostMapping("/resetPwd")
    Result resetPwd(@RequestParam Map<String, String> params) {
        int userId = Integer.parseInt(params.get("userId"));
        String password = params.get("password");
        UserDTO userDTO = userService.get(userId);
        if (userDTO == null) {
            return Result.error("数据异常");
        }
        userDTO.setPassword(MD5.encodeUTF8(password));
        userService.resetPwd(userDTO);
        return Result.ok();
    }

    @PostMapping("/add")
    Result add(@RequestParam Map<String, String> params) {
        try {
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(params.get("username"));
            userDTO.setPassword(MD5.encodeUTF8(params.get("password")));
            userDTO.setNickname(params.get("nickname"));
            userDTO.setAvatar(params.get("avatar"));
            userDTO.setDesc(params.get("desc"));
            userDTO.setRoleKey(params.get("roleKey"));
            userDTO.setStatus(Integer.parseInt(params.get("status")));

            userDTO.setGmtCreate(new Date());
            userDTO.setGmtModified(new Date());

            userService.add(userDTO);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    @PostMapping("/update")
    Result update(@RequestParam Map<String, String> params) {
        int userId = Integer.parseInt(params.get("userId"));
        UserDTO userDTO = userService.get(userId);
        if (userDTO == null) {
            return Result.error("数据异常");
        }
        userDTO.setRoleKey(params.get("roleKey"));
        userDTO.setUsername(params.get("username"));
        userDTO.setNickname(params.get("nickname"));
        String avatar = params.get("avatar");
        String oldFileUrl = userDTO.getAvatar();
        if (!StringUtils.isEmpty(avatar) && !avatar.equals(oldFileUrl)) {
            userDTO.setAvatar(avatar);
        }
        userDTO.setDesc(params.get("desc"));
        userDTO.setGmtModified(new Date());
        try {
            userService.update(userDTO);
            FileUploadUtils.doDeleteOldFile(ApplicationConfig.getUploadPath(), oldFileUrl);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    @PostMapping("/delete")
    Result delete(@RequestParam Map<String, String> params) {
        int userId = Integer.parseInt(params.get("userId"));
        UserDTO userDTO = userService.get(userId);
        if (userDTO != null) {
            userService.delete(userId);
            FileUploadUtils.doDeleteOldFile(ApplicationConfig.getUploadPath(), userDTO.getAvatar());
        }
        return Result.ok();
    }


}
