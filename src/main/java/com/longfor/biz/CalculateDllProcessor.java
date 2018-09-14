package com.longfor.biz;

import java.io.*;

public class CalculateDllProcessor {
    public static void main(String[] args){
        System.out.println("hello world!");
        String fileName="D:/test1.csv";
        String sql = "";
        try {
            sql =  CalculateDllProcessor.generateSql(fileName);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        System.out.println(sql);
    }

    public static String generateSql(String fileName) throws Exception{
        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis,"GBK");
        BufferedReader reader = null;
        StringBuffer sql = new StringBuffer();
        try{
            reader = new BufferedReader(isr);
            String lineStr = null;
            int lineNum = 1;
            while((lineStr=reader.readLine())!=null){
               // System.out.println("line:" + lineNum + ",content:" + lineStr);
                if(lineStr.startsWith("table-")){
                    if(lineNum!=1){
                        sql.deleteCharAt(sql.length()-1);
                        sql.append(");\r\n");
                    }
                    String tableLine = lineStr.substring(6);
                    String[] tableLineArr = tableLine.split("\\|");
                    String tableName = tableLineArr[0].trim().toLowerCase();
                    sql.append("CREATE TABLE IF NOT EXISTS `"+ tableName+ "` (");
                    continue;
                }
                String[] dataLineArr = lineStr.split("\\|");
                if(dataLineArr.length>=4 && dataLineArr[3].equals("01")){
                    String columnName = dataLineArr[0].trim().toLowerCase();
                    sql.append("`" + columnName + "` " + dataLineArr[2]+ " COMMENT '" + dataLineArr[1] + "',");
                    sql.append("PRIMARY KEY (`" + columnName + "`),");
                }else{
                    String columnName = dataLineArr[0].trim().toLowerCase();
                    sql.append("`" + columnName + "` " + dataLineArr[2]+ " COMMENT '" + dataLineArr[1] + "',");
                }
                lineNum++;
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if(reader!=null){
                try {
                    reader.close();
                    isr.close();
                    fis.close();
                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        }
        return sql.toString();
    }




}
