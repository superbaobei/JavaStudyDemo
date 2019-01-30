package com.sxy.www;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import com.alibaba.fastjson.JSONObject;
import org.apache.pulsar.shade.org.apache.avro.data.Json;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by xiangyusun on 2018/11/8.
 */
public class JsonTest {



    @Test
    public void testDat(){
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(year);
        System.out.println("month = " + month);
        System.out.println("day = " + day);
    }



    @Test
    public void testBIg(){

        BigDecimal bigDecimal = new BigDecimal(12345646L);

        String amountStr = null;
            DecimalFormat df = new DecimalFormat(",##0.00");
            amountStr = df.format(bigDecimal);
        System.out.println("amountStr = " + amountStr);
    }

    @Test
    public void testLogback(){
        logger.info("test");
    }

    @Before
    public void before(){
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);
        logger.info("service start.................");
    }

    private static final Logger logger = LoggerFactory.getLogger(JsonTest.class);
}
