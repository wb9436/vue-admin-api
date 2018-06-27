package com.vue.admin.vueadminapi.service.impl;

import com.vue.admin.vueadminapi.dao.UserDao;
import com.vue.admin.vueadminapi.dto.UserDTO;
import com.vue.admin.vueadminapi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Auther: WB
 * @Date: 2018/6/20 10:29
 * @Description:
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDTO checkUser(Map<String, String> loginUser) {
        return userDao.checkUser(loginUser);
    }

    @Override
    public List<UserDTO> list(Map<String, Object> params) {
        return userDao.list(params);
    }

    @Override
    public int count(Map<String, Object> params) {
        return userDao.count(params);
    }

    @Override
    public UserDTO get(int userId) {
        return userDao.get(userId);
    }

    @Transactional
    @Override
    public void modifyStatus(UserDTO userDTO) {
        userDao.modifyStatus(userDTO);
    }

    @Transactional
    @Override
    public void resetPwd(UserDTO userDTO) {
        userDao.resetPwd(userDTO);
    }

    @Transactional
    @Override
    public void add(UserDTO userDTO) {
        userDao.add(userDTO);
    }

    @Transactional
    @Override
    public void update(UserDTO userDTO) {
        userDao.update(userDTO);
    }

    @Transactional
    @Override
    public void delete(int userId) {
        userDao.delete(userId);
    }
}
