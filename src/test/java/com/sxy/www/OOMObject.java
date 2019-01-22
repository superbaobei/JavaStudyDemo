package com.sxy.www;

/**
 * Created by xiangyusun on 2018/12/7.
 */
public class OOMObject {

    static class Test{

    }

    public static void main(String[] args) {
        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);

        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);



        String str1=new StringBuilder("计算机").append("软件").toString();

        System.out.println(str1.intern()==str1);

        String str2=new StringBuilder("jaa").append("va").toString();

        System.out.println(str2.intern()==str2);
        String str3=new StringBuilder("ja").append("va").toString();

        System.out.println(str3.intern()==str3);

        String str4=new StringBuilder("ja").append("va").toString();

        System.out.println(str4 ==str3);


    }

}
