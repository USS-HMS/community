package com.blhx.community.controller;

import com.blhx.community.dto.QuestionDTO;
import com.blhx.community.mapper.UserMapper;
import com.blhx.community.model.User;
import com.blhx.community.service.QuestionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String hello( HttpServletRequest request
                        ,Model model){
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
        List<QuestionDTO> questionList =questionService.list();
        model.addAttribute("questions",questionList);
        return "index";
    }
}
