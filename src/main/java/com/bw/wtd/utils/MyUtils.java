package com.bw.wtd.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyUtils {

	/**
	 * 
	 * @Title:         isEmpty   
	 * @Description:   TODO
	 * @param:         @param str
	 * @param:         @return      
	 * @return:        boolean     
	 * @date:          2019年9月6日 下午1:37:48   
	 * @throws
	 */
	public static  boolean isEmpty(String str) {
		
		return (null==str||"".equals(str.trim()));
	}
	
	/**
	 * 
	 * @Title:         isHasValue   
	 * @Description:   TODO
	 * @param:         @param str
	 * @param:         @return      
	 * @return:        boolean     
	 * @date:          2019年9月6日 下午1:37:53   
	 * @throws
	 */
	public static boolean isHasValue(String str) {
		
		return (null !=str && !"".equals(str.trim()));
	}
	
	/**
	 * 
	 * @Title:         isTelephone   
	 * @Description:   TODO
	 * @param:         @param str
	 * @param:         @return      
	 * @return:        boolean     
	 * @date:          2019年9月6日 下午1:38:06   
	 * @throws
	 */
	public static boolean isTelephone(String str) {
		String pattern = "^(136|135|137)\\d{8}$";
		return str.matches(pattern);
	}
	
	/**
	 * 
	 * @Title:         isEmail   
	 * @Description:   TODO
	 * @param:         @param str
	 * @param:         @return      
	 * @return:        boolean     
	 * @date:          2019年9月6日 下午1:38:11   
	 * @throws
	 */
	public static boolean isEmail(String str) {
		
		String pattern = "^\\w+@\\w+\\.[a-zA-Z]{2,3}$";
		Pattern compile = Pattern.compile(pattern);
		Matcher matcher = compile.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 
	 * @Title:         isLetter   
	 * @Description:   TODO
	 * @param:         @param str
	 * @param:         @return      
	 * @return:        boolean     
	 * @date:          2019年9月6日 下午1:38:15   
	 * @throws
	 */
	public static boolean isLetter(String str) {
		String pattern = "^[a-zA-Z]+$";
		return str.matches(pattern);
	}
	
	/**
	 * 
	 * @Title:         randomLetterStr   
	 * @Description:   TODO
	 * @param:         @param n
	 * @param:         @return      
	 * @return:        String     
	 * @date:          2019年9月6日 下午1:38:18   
	 * @throws
	 */
	public String randomLetterStr(int n) {
		
		if(n<=0)
			return "";
		Random random = new Random();
		
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<n;i++) {
			char letter = (char)('A' + random.nextInt(26));
			sb.append(letter);
		}		
		return sb.toString();
	}
	
	/**
	 * 
	 * @Title:         randomStr   
	 * @Description:   TODO
	 * @param:         @param n
	 * @param:         @return      
	 * @return:        String     
	 * @date:          2019年9月6日 下午1:38:26   
	 * @throws
	 */
	public String randomStr(int n) {
		
		char chars[]= {'0','1','2','3','4','5','6','7','8','9',
				'A','B','C','D','E','F','G','H','I','J','K','L','M','N'};
		
		
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<n;i++) {
			
			int index = random.nextInt(chars.length);
			char c = chars[index];
			sb.append(c);
		}
		return sb.toString();
		
	}
	
	/**
	 * 
	 * @Title:         getRandonCnStr   
	 * @Description:   TODO
	 * @param:         @param n
	 * @param:         @return      
	 * @return:        String     
	 * @date:          2019年9月6日 下午1:38:31   
	 * @throws
	 */
	public static String getRandonCnStr(int n) {
		
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<n;i++) {
			sb.append(getOneCn());
		}
		return sb.toString();
		
	} 
	
	/**
	 * 
	 * @Title:         getOneCn   
	 * @Description:   TODO
	 * @param:         @return      
	 * @return:        String     
	 * @date:          2019年9月6日 下午1:38:37   
	 * @throws
	 */
	private static String getOneCn(){
		
		String str = "";
        int hightPos; 
        int lowPos;
        Random random = new Random();

        hightPos = (176 + Math.abs(random.nextInt(39)));
        lowPos = (161 + Math.abs(random.nextInt(93)));

        byte[] b = new byte[2];
        b[0] = (Integer.valueOf(hightPos)).byteValue();
        b[1] = (Integer.valueOf(lowPos)).byteValue();

        try {
            str = new String(b, "GBK");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("错误");
        }

        return str;
	}
	
}
