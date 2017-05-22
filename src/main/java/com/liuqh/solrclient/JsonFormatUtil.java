package com.liuqh.solrclient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.solr.common.StringUtils;

public class JsonFormatUtil {
	 /**
     * 格式化json
     * @param content
     * @return
     */
    public static String formatJson(String content) {

        StringBuffer sb = new StringBuffer();
        int index = 0;
        int count = 0;
        while(index < content.length()){
            char ch = content.charAt(index);
            char befor=' ';
            if(index>0){
            	befor= content.charAt(index-1);
            }
            char after=' ';
            if(index<content.length()-1){
            	 after=content.charAt(index+1);
            }
          
            
            if(ch == '{' || ch == '['){
                sb.append(ch);
                sb.append('\n');
                count++;
                for (int i = 0; i < count; i++) {                   
                    sb.append('\t');
                }
            }
            else if(ch == '}' || ch == ']'){
                sb.append('\n');
                count--;
                for (int i = 0; i < count; i++) {                   
                    sb.append('\t');
                }
                sb.append(ch);
            } 
            
            else if(ch == ','&&(after=='{'||after=='['||befor=='}'||befor==']'||after=='"')){
                sb.append(ch);
                sb.append('\n');
                for (int i = 0; i < count; i++) {                   
                    sb.append('\t');
                }
            } 
            else {
                sb.append(ch);              
            }
            index ++;
        }
        return sb.toString();
    }
    /**
     * 把格式化的json紧凑
     * @param content
     * @return
     */
    public static String compactJson(String content) {
        String regEx="[\t\n]"; 
        Pattern p = Pattern.compile(regEx); 
        Matcher m = p.matcher(content);
        return m.replaceAll("").trim();
    }
    public static void main(String[] args) throws IOException {
    	File f=new File("json.txt");
    	BufferedReader br=new BufferedReader(new FileReader(f));
    	String s=br.readLine();
    	String result="";
    	if(!StringUtils.isEmpty(s)){
    		result=formatJson(s);
    	}
    	//String s="[items:[{name:\"liuqh\"},{name:\"abc123,ddd\"}],age:100]";
    	System.out.println(result);
	}

}
