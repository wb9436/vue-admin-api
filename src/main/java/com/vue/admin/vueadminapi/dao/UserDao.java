package com.vue.admin.vueadminapi.dao;

import com.vue.admin.vueadminapi.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Auther: WB
 * @Date: 2018/6/20 10:30
 * @Description:
 */
@Mapper
public interface UserDao {

    UserDTO checkUser(Map<String,String> loginUser);

    List<UserDTO> list(Map<String,Object> params);

    int count(Map<String,Object> params);

    UserDTO get(@Param("userId") int userId);

    int modifyStatus(UserDTO userDTO);

    int resetPwd(UserDTO userDTO);

    int add(UserDTO userDTO);

    int update(UserDTO userDTO);

    int delete(@Param("userId") int userId);
}
