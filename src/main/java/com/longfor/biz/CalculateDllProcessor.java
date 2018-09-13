package com.longfor.biz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class CalculateDllProcessor {
    public static void main(String[] args){
        System.out.println("hello world!");
        String fileName="D:/test.csv";
        String sql =  CalculateDllProcessor.generateSql(fileName);
        System.out.println(sql);
    }

    public static String generateSql(String fileName){
        File file = new File(fileName);
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader(file));
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
