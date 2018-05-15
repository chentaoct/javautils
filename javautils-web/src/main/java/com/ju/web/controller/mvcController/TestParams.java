package com.ju.web.controller.mvcController;


import com.ju.model.domain.UserDomain;
import com.ju.model.req.Person;
import com.ju.model.req.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 陈涛 on 2017/6/14.
 */
@Controller
@RequestMapping("testParams")
public class TestParams {
    @RequestMapping(value = "testRequestBody", method = RequestMethod.POST)
    @ResponseBody
    public Person testRequestBody(@RequestBody Person person){
        return person;
    }
    @RequestMapping(value = "testRequestBody2", method = RequestMethod.POST)
    @ResponseBody
    public UserDomain testRequestBody2(@RequestBody UserDomain userDomain){
        return userDomain;
    }

    @RequestMapping(value = "testModelAttribute", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Student  testModelAttribute(@ModelAttribute Student student){
        return student;
    }
}
