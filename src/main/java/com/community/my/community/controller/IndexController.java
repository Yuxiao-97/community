package com.community.my.community.controller;

import com.community.my.community.mapper.UserMapper;
import com.community.my.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length>0) {
           for(Cookie cookie : cookies) {
               if("token".equals(cookie.getName())) {
                   String token = cookie.getValue();
                   User user = userMapper.findByToken(token);
                   request.getSession().setAttribute("user",user);
               }
           }
        }
        return "index";
    }

}
