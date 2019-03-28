package com.sxy.www.controller;

import com.sxy.www.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.response.SpellCheckResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by user on 2018/9/17.
 */
@RestController
@Slf4j
public class TestController {

    @Autowired
    ConcurrentMapCacheManager map;
    @RequestMapping(value = "/test/{userid}",method = RequestMethod.GET)
    public Result testController(@PathVariable("userid") String userid){
        log.info("userid = {}",userid);
//        Result rs = new Result();
        return null;
    }

    @GetMapping("health/check")
    public String testSpring(){
        log.info("success");
        return map.getCacheNames().toString();
    }

    @GetMapping("testCache/{key}")
    @Cacheable(value = "testCache",key = "#key")
    public String testCache(@PathVariable("key") String key){
        log.info("testCache");
        map.getCacheNames();
        return key;
    }

    @GetMapping("queryCacheNum")
    public int queryCacheNum(){
        log.info("queryCacheNum");
        Collection<String> coll = map.getCacheNames();
        Iterator<String> iterator = coll.iterator();
        while(iterator.hasNext()){
            String name = iterator.next();
            ConcurrentMapCache cache = (ConcurrentMapCache)map.getCache(name);
        }
        return coll.size();
    }
}
