package com.example.util;

import java.util.Random;

public class SaltUtils {

    /**
     * 生成salt的方法
     * @param n
     * @return
     */
    public static String gerSalt(int n){
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~!@#$%^".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<n;i++){
            char aChar = chars[new Random().nextInt(chars.length)];
            stringBuilder.append(aChar);
        }
        return stringBuilder.toString();
    }
}
