package com.example.service;

import com.example.entity.Perms;
import com.example.entity.Role;
import com.example.entity.User;

import java.util.List;

public interface UserService {

    void register(User user);

    User login(User user);

    User findUserByUserName(String userName);

    //根据用户查询角色
    User findRolesByUserName(String name);

    List<Perms> findPermsByRid(String id);
}
