package com.pcc;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author : 彭聪
 * @date : 2020-09-19 10:17
 **/
public class ShiroMD5Test {
    public static void main(String[] args) {
        //使用MD5
        Md5Hash md5Hash =new Md5Hash("123456");
        System.out.println(md5Hash.toHex());
        //使用MD5+salt
        Md5Hash md5Hash1 =new Md5Hash("123456","adc*kill");
        System.out.println(md5Hash1.toHex());
        //使用MD5 + salt + Hash散列
        Md5Hash md5Hash2=new Md5Hash("123456","adc*kill",1024);
        System.out.println(md5Hash2.toHex());


    }
}
