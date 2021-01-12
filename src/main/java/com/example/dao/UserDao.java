package com.example.dao;

import com.example.entity.Perms;
import com.example.entity.Role;
import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

@Mapper
@Repository
public interface UserDao {

    void save(User user);

    User login(User user);

    User findUserByUserName(String userName);

    //根据用户查询角色
    User findRolesByUserName(String name);
    //根据用户查询角色
    List<Perms> findPermsByRid(String id);
}
