package com.sxy.www;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangyusun on 2019/1/22.
 */
public class JVMStudy {

    @Test
    public void testJvm(){

        List<OOMObject> list=new ArrayList<OOMObject>();

        while(true){

            list.add(new OOMObject());

        }
    }
}
