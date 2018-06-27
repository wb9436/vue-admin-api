package com.vue.admin.vueadminapi.service.impl;

import com.vue.admin.vueadminapi.dao.RoleDao;
import com.vue.admin.vueadminapi.dao.RoleMenuDao;
import com.vue.admin.vueadminapi.dto.RoleDTO;
import com.vue.admin.vueadminapi.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Auther: WB
 * @Date: 2018/6/23 16:13
 * @Description:
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    RoleDao roleDao;
    @Autowired
    RoleMenuDao roleMenuDao;

    @Override
    public List<RoleDTO> list(Map<String, Object> params) {
        return roleDao.list(params);
    }

    @Transactional
    @Override
    public void add(RoleDTO roleDTO, int[] menuIds) {
        roleDao.add(roleDTO);
        if (menuIds != null && menuIds.length > 0) {
            roleMenuDao.add(roleDTO.getRoleId(), menuIds);
        }
    }

    @Transactional
    @Override
    public void update(RoleDTO roleDTO, int[] menuIds) {
        roleDao.update(roleDTO);
        roleMenuDao.delete(roleDTO.getRoleId());
        if (menuIds != null && menuIds.length > 0) {
            roleMenuDao.add(roleDTO.getRoleId(), menuIds);
        }
    }

    @Override
    public void delete(int roleId) {
        roleDao.delete(roleId);
        roleMenuDao.delete(roleId);
    }

    @Override
    public RoleDTO get(int roleId) {
        return roleDao.get(roleId);
    }

    @Override
    public List<Integer> getRoleMenu(int roleId) {
        return roleMenuDao.getRoleMenu(roleId);
    }
}
