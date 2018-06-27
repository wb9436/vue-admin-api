package com.vue.admin.vueadminapi.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: WB
 * @Date: 2018/6/20 10:30
 * @Description:
 */
@Mapper
public interface RoleMenuDao {

    int delete(@Param("roleId") int roleId);

    int deleteByMenuId(@Param("menuId") int menuId);

    int add(@Param("roleId")int roleId, @Param("menuIds")int[] menuIds);

    List<Integer> getRoleMenu(@Param("roleId") int roleId);

}
