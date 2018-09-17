package com.sxy.www.controller;

import com.sxy.www.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by user on 2018/9/17.
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @RequestMapping(value="/login/{userid}/{passwd}")
    public Result login(@PathVariable("userid")String userid, @PathVariable("passwd")String passwd){

        log.info("userid = {},passwd = {}",userid,passwd);
        return null;
    }

    @Value("${md5.key}")
    private String md5key;
}
