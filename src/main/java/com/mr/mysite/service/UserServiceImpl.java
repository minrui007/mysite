package com.mr.mysite.service;

import com.mr.mysite.dao.UserDao;
import com.mr.mysite.domain.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public int insert(UserDto userDto) {
        return userDao.insert(userDto);
    }

    @Override
    public List<UserDto>  findAll() {
        return userDao.findAll();
    }

    @Override
    public UserDto findById(int id) {
        return userDao.findById(id);
    }
}
