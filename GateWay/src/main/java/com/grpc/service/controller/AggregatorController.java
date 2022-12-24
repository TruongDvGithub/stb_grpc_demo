package com.grpc.service.controller;

import com.grpc.service.service.UserService;
import com.stb.user.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class AggregatorController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUserInfo/{loginId}")
    public String getUserGenre(@PathVariable String loginId){
        UserResponse userInfo = this.userService.getUserInfo(loginId);
        if(userInfo != null){
            return userInfo.getName();
        }
        return "Fail";
    }
}
