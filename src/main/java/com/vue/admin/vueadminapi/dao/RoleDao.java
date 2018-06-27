package com.vue.admin.vueadminapi.dao;

import com.vue.admin.vueadminapi.dto.RoleDTO;
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
public interface RoleDao {

    List<RoleDTO> list(Map<String, Object> params);

    RoleDTO get(@Param("roleId") int roleId);

    int add(RoleDTO roleDTO);

    int update(RoleDTO roleDTO);

    int delete(@Param("roleId") int roleId);

}
