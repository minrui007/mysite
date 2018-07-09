package com.mr.mysite.controller;

import com.mr.mysite.domain.UserDto;
import com.mr.mysite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = {"/user"})
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/insert"},produces = {"application/json;charset=utf-8"},method= RequestMethod.GET)
    public int insertUser(HttpServletRequest request) {
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String sex = request.getParameter("sex");
        UserDto userDto = new UserDto();
        Date date = new Date();
        userDto.setName(name);
        userDto.setPassword(password);
        userDto.setPhone(phone);
        userDto.setOperator("00000000");
        userDto.setOperationDate(date);
        userDto.setUserName(username);
        userDto.setSex(sex);
        userService.insert(userDto);
        System.out.print(userDto.getId());
        return userDto.getId();
    }

    @RequestMapping(value="/findAll",produces={"application/json;charset=utf-8"},method=RequestMethod.GET)
    public List<UserDto>  findAll() {
        return userService.findAll();
    }

    @RequestMapping(value="/findById")
    public String findById(HttpServletRequest request, HttpServletResponse response) {
        String message = "";
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.print("用户id === " + id);
        UserDto userDto = userService.findById(id);
        if(userDto == null) {
            message = "用户不存在";
        } else {
            message = userDto.getName();
        }
        request.setAttribute("user",userDto);
        System.out.print(message);
        return response.encodeRedirectURL("user/success");
    }

}
