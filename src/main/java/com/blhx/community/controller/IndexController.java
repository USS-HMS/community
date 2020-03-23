package com.blhx.community.controller;

import com.blhx.community.dto.PageinationDTO;
import com.blhx.community.mapper.UserMapper;
import com.blhx.community.model.User;
import com.blhx.community.service.QuestionService;
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
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String hello( HttpServletRequest request
                        ,Model model
                        ,@RequestParam(value="page",defaultValue="1") Integer page
                        ,@RequestParam(value="size",defaultValue="5") Integer size){
        Cookie[] cookies=request.getCookies();
        if (cookies !=null && cookies.length !=0)
        for (Cookie cookie :cookies){
            if (cookie.getName().equals("token")){
                String token=cookie.getValue();
                User user=userMapper.findBYToken(token);
                if (token !=null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        PageinationDTO pageination =questionService.list(page,size);
        model.addAttribute("pageination",pageination);
        return "index";
    }
}
