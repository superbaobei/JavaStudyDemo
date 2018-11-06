package com.sxy.www;

import java.util.*;

/**
 * Created by xiangyusun on 2018/11/6.
 */
public class MainTest {

    public static String handleAmount(String amountStr){
        String subStr = amountStr.substring(0,amountStr.indexOf("."));
        List<Character> queue = new ArrayList<>();
        int sum = 0;
        for (int i = subStr.length()-1; i >=0 ;i--){
            if( sum % 3 == 0 &&  i != subStr.length() - 1) {
                queue.add(',');
            }
            sum++;
            queue.add(subStr.charAt(i));
        }
        StringBuffer sb  = new StringBuffer();
        for (int i = queue.size() - 1 ; i>= 0 ;i--) {
            sb.append(queue.get(i));
        }
        sb.append(amountStr.substring(amountStr.indexOf('.'),amountStr.length()));
        System.out.println(sb.toString());
        return null;
    }

    public static void main(String[] args) {

        handleAmount("2151412.02");
    }
}
