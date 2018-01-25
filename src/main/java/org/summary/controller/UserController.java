package org.summary.controller;

/**
 * Created by CharlesYang on 2018/1/23.
 */
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.summary.dto.Result;
import org.summary.dto.SignUpExecution;
import org.summary.enums.SignUpEnum;
import org.summary.exception.NoUserException;
import org.summary.model.User;
import org.summary.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/showUser.do", method = RequestMethod.POST, produces = {
            "application/json; charset=utf-8" })
    @ResponseBody
    public Result<SignUpExecution> login(@RequestParam("username") String username,
                      @RequestParam("password") String password) throws IOException {
        username = URLDecoder.decode(username,"utf-8");
        System.out.println("username is " + username);
        User user = this.userService.login(username,password);

        return new Result<>(true,new SignUpExecution(user.getUsername(), SignUpEnum.SUCCESS));
    }
}
