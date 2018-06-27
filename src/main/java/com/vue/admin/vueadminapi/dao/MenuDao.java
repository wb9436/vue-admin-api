package com.vue.admin.vueadminapi.dao;

import com.vue.admin.vueadminapi.dto.MenuDTO;
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
public interface MenuDao {

    List<MenuDTO> list(Map<String, Object> params);

    MenuDTO get(@Param("menuId") int menuId);

    int add(MenuDTO menuDTO);

    int update(MenuDTO menuDTO);

    int delete(@Param("menuId") int menuId);

    List<MenuDTO> simpleList(Map<String, Object> params);

    List<String> userRoleList(Map<String, Object> params);

    List<String> userPermsList(Map<String, Object> params);
}
