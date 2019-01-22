package com.sxy.www;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * Created by xiangyusun on 2018/11/7.
 */

public class ComplitableFutureTest {

    @Test
    public void testCompleteFuture() throws Exception{
        CompletableFuture<String> completeFuture = CompletableFuture.supplyAsync(() -> {
                System.out.println("1");
                System.out.println(2);
            return "Hello";
        });

        System.out.println("main thread");
    }

}
