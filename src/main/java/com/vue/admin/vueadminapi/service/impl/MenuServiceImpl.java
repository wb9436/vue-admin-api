package com.vue.admin.vueadminapi.service.impl;

import com.vue.admin.vueadminapi.dao.MenuDao;
import com.vue.admin.vueadminapi.dao.RoleMenuDao;
import com.vue.admin.vueadminapi.dto.MenuDTO;
import com.vue.admin.vueadminapi.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: WB
 * @Date: 2018/6/22 19:23
 * @Description:
 */
@Service
public class MenuServiceImpl implements IMenuService {

    @Autowired
    MenuDao menuDao;
    @Autowired
    RoleMenuDao roleMenuDao;

    @Override
    public List<MenuDTO> list(Map<String, Object> params) {
        params.put("parentId", -1);
        List<MenuDTO> parentList = menuDao.list(params);
        if (parentList != null && parentList.size() > 0) {
            for (int i = 0; i < parentList.size(); i++) {
                params.put("parentId", parentList.get(i).getMenuId());
                List<MenuDTO> childList = menuDao.list(params);

                if (childList != null && childList.size() > 0) {
                    parentList.get(i).setChildren(childList);
                    for (int j = 0; j < childList.size(); j++) {
                        params.put("parentId", childList.get(j).getMenuId());

                        List<MenuDTO> grandsonList = menuDao.list(params);
                        if (grandsonList != null && grandsonList.size() > 0) {
                            childList.get(j).setChildren(grandsonList);
                        }
                    }
                }
            }
        }
        return parentList;
    }

    @Transactional
    @Override
    public void add(MenuDTO menuDTO) {
        menuDao.add(menuDTO);
    }

    @Transactional
    @Override
    public void update(MenuDTO menuDTO) {
        menuDao.update(menuDTO);
    }

    @Override
    public MenuDTO get(int menuId) {
        return menuDao.get(menuId);
    }

    @Transactional
    @Override
    public void delete(int menuId) {
        Map<String, Object> params = new HashMap<>();
        params.put("parentId", menuId);
        List<MenuDTO> childMenuList = menuDao.list(params);
        if (childMenuList != null && childMenuList.size() > 0) {
            for (int i = 0; i < childMenuList.size(); i++) {
                MenuDTO childMenu = childMenuList.get(i);

                params.put("parentId", childMenu.getMenuId());
                List<MenuDTO> childMenuList2 = menuDao.list(params);
                if (childMenuList2 != null && childMenuList2.size() > 0) {
                    for (int j = 0; j < childMenuList2.size(); j++) {
                        MenuDTO childMenu2 = childMenuList2.get(j);
                        roleMenuDao.deleteByMenuId(childMenu2.getMenuId());
                        menuDao.delete(childMenu2.getMenuId());
                    }
                }
                roleMenuDao.deleteByMenuId(childMenu.getMenuId());
                menuDao.delete(childMenu.getMenuId());
            }
        }
        roleMenuDao.deleteByMenuId(menuId);
        menuDao.delete(menuId);
    }

    @Override
    public List<MenuDTO> allList(Map<String, Object> params) {
        params.put("parentId", -1);
        List<MenuDTO> parentList = menuDao.simpleList(params);
        if (parentList != null && parentList.size() > 0) {
            for (int i = 0; i < parentList.size(); i++) {
                params.put("parentId", parentList.get(i).getMenuId());
                List<MenuDTO> childList = menuDao.simpleList(params);

                if (childList != null && childList.size() > 0) {
                    parentList.get(i).setChildren(childList);
                    for (int j = 0; j < childList.size(); j++) {
                        params.put("parentId", childList.get(j).getMenuId());

                        List<MenuDTO> grandsonList = menuDao.simpleList(params);
                        if (grandsonList != null && grandsonList.size() > 0) {
                            childList.get(j).setChildren(grandsonList);
                        }
                    }
                }
            }
        }
        return parentList;
    }

    @Override
    public List<String> userRoleList(String roleKey) {
        Map<String, Object> params = new HashMap<>();
        params.put("roleKey", roleKey);
        params.put("type", 2);//菜单权限类型 0：目录   1：菜单   2：按钮
        return menuDao.userRoleList(params);
    }

    @Override
    public List<String> userPermsList(String roleKey) {
        Map<String, Object> params = new HashMap<>();
        params.put("roleKey", roleKey);
        params.put("type", 2);//菜单权限类型 0：目录   1：菜单   2：按钮
        return menuDao.userPermsList(params);
    }
}
