package com.ju.web.controller;


import com.ju.biz.manager.UserService;
import com.ju.model.domain.UserDomain;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by tao on 2017/5/28.
 */
@Controller
@RequestMapping("user")
public class UserController {
    private Logger log = Logger.getLogger(UserController.class);
    @Autowired
    UserService userService;


    @RequestMapping("showUser")
    public String showUser(HttpServletRequest request, Model model){
        log.info("查询所有1用户信息");
        List<UserDomain> userList = userService.listUser();
        model.addAttribute("userList",userList);
        return "user";
    }
    @RequestMapping("showUserList")
    @ResponseBody
    public List<UserDomain> showUserList(HttpServletRequest request){
        log.info("查询所有1用户信息");
        List<UserDomain> userList = userService.listUser();
        return userList;
    }
}
