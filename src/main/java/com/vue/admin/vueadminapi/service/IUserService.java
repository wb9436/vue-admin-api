package com.vue.admin.vueadminapi.service;

import com.vue.admin.vueadminapi.dto.UserDTO;
import com.vue.admin.vueadminapi.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * @Auther: WB
 * @Date: 2018/6/20 10:28
 * @Description:
 */
public interface IUserService {
    UserDTO checkUser(Map<String, String> loginUser);

    List<UserDTO> list(Map<String, Object> params);

    int count(Map<String, Object> params);

    UserDTO get(int userId);

    void modifyStatus(UserDTO userDTO);

    void resetPwd(UserDTO userDTO);

    void add(UserDTO userDTO);

    void update(UserDTO userDTO);

    void delete(int userId);
}
