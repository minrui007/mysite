package com.mr.mysite.service;

import com.mr.mysite.domain.UserDto;

import java.util.List;

public interface UserService {
    public int insert(UserDto userDto);

    public List<UserDto>  findAll();

    public UserDto findById(int id);
}
