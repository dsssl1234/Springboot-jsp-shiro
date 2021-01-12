package com.example.service.impl;

import com.example.dao.UserDao;
import com.example.entity.Perms;
import com.example.entity.User;
import com.example.service.UserService;
import com.example.shiro.salt.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserIServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public void register(User user) {


        //生成盐
        String salt = SaltUtils.gerSalt(8);
        //对明文的密码进行md5+salt+hash散列
        Md5Hash md5Hash = new Md5Hash(user.getPassWord(),salt,1024);
        user.setPassWord(md5Hash.toHex());
        user.setSalt(salt);
        userDao.save(user);
    }

    @Override
    public User login(User user) {
        return null;
    }

    @Override
    public User findUserByUserName(String userName) {
        User user = userDao.findUserByUserName(userName);
        return user;
    }

    @Override
    public User findRolesByUserName(String name) {
        return userDao.findRolesByUserName(name);
    }

    @Override
    public List<Perms> findPermsByRid(String id) {
        return userDao.findPermsByRid(id);
    }
}
