package com.sxy.www.controller;

import com.sxy.www.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by user on 2018/9/17.
 */
@RestController
@Slf4j
public class TestController {

    @RequestMapping(value = "/test/{userid}",method = RequestMethod.GET)
    public Result testController(@PathVariable("userid") String userid){
        log.info("userid = {}",userid);
//        Result rs = new Result();
        return null;
    }
}
