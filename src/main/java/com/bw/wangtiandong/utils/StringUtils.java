package com.bw.wangtiandong.utils;

import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @ClassName:     StringUtils   
 * @Description:   TODO
 * @author:        WTD
 * @date:          2019年9月9日 上午9:53:03
 */
public class StringUtils {
	
	/**
	 * 
	 * @Title:         toHtml   
	 * @Description:   TODO
	 * @param:         @param src
	 * @param:         @return      
	 * @return:        String     
	 * @date:          2019年9月9日 上午9:52:59   
	 * @throws
	 */
	public static String toHtml(String src) {
		
		String[] strings = src.split("\\\\n");
		StringBuilder sb = new StringBuilder();
		for (String string : strings) {
			sb.append("<p>").append(string).append("</p>");
		}
		return sb.toString();
		
		
		
	}
	
	/**
	 * 
	 * @Title:         isEmpty   
	 * @Description:   TODO
	 * @param:         @param str
	 * @param:         @return      
	 * @return:        boolean     
	 * @date:          2019年9月9日 上午9:53:09   
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
	 * @date:          2019年9月9日 上午9:53:13   
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
	 * @date:          2019年9月9日 上午9:53:19   
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
	 * @date:          2019年9月9日 上午9:53:23   
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
	 * @date:          2019年9月9日 上午9:53:27   
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
	 * @date:          2019年9月9日 上午9:53:31   
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
	 * @date:          2019年9月9日 上午9:53:36   
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
	 * @date:          2019年9月9日 上午9:53:53   
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
	 * @date:          2019年9月9日 上午9:53:56   
	 * @throws
	 */
	private static String getOneCn(){
		
		String str = "";
        int hightPos; //
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
            System.out.println("����");
        }

        return str;
	}
	
	/**
	 * 
	 * @Title:         getPlaceholderValue   
	 * @Description:   TODO
	 * @param:         @param src
	 * @param:         @param regex
	 * @param:         @return      
	 * @return:        String     
	 * @date:          2019年9月9日 上午9:54:02   
	 * @throws
	 */
	public static String getPlaceholderValue(String src, String regex){
		//TODO ʵ�ִ���
        Pattern pattern = Pattern.compile(regex);// ƥ���ģʽ  
        Matcher m = pattern.matcher(src);  
        boolean find = m.find();
        if(find) {
        	String group = m.group(0);
        	 return group.substring(1,group.lastIndexOf('.'));
        }
        return "";
	}
	
	/**
	 * 
	 * @Title:         isNumber   
	 * @Description:   TODO
	 * @param:         @param src
	 * @param:         @return      
	 * @return:        boolean     
	 * @date:          2019年9月9日 上午9:54:05   
	 * @throws
	 */
	public static boolean isNumber(String src) {
		//String regix="[0-9]{1,}(\\.?|[0-9]*)";
		String regix="[0-9]{1,}\\.?[0-9]*";
		return src.matches(regix);
		
		
	}
	
	/**
	 * 
	 * @Title:         hasText   
	 * @Description:   TODO
	 * @param:         @param src
	 * @param:         @return      
	 * @return:        boolean     
	 * @date:          2019年9月9日 上午9:54:10   
	 * @throws
	 */
	public static boolean hasText(String src) {
		String string = src.replaceAll("\\s", "");
		return (!"".equals(string));
	}
	
	/**
	 * 
	 * @Title:         toHtml1   
	 * @Description:   TODO
	 * @param:         @param text
	 * @param:         @return      
	 * @return:        String     
	 * @date:          2019年9月9日 上午9:54:16   
	 * @throws
	 */
	public static String toHtml1(String text) {
		
		String text2 = text.replaceAll("\\\r", "<br/>");
		
		String[] strings = text2.split("(\\\n|\\\n\\\r)");
		
		StringBuilder sb= new StringBuilder();
		for (String string : strings) {
			sb.append("<p>").append(string).append("</p>");
		}
		
		return sb.toString();
	}
	
	
}
