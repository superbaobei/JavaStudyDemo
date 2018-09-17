package com.sxy.www.controller;

import com.sxy.www.result.ResultBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by user on 2018/9/17.
 */
@RestController
@Slf4j
//@Controller
public class TestController {

    @RequestMapping(value = "/test/{userid}",method = RequestMethod.GET)
    public ResultBean testController(@PathVariable("userid") String userid){
        log.info("userid = {}",userid);
        ResultBean rs = new ResultBean();
        rs.setData("success");
        return rs;
    }
}
