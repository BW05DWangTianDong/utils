package com.bw.wtd.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @ClassName:     DateUtils   
 * @Description:   TODO
 * @author:        WTD
 * @date:          2019年9月6日 下午2:19:37
 */
public class DateUtils {
	
	/**
	 * 
	 * @Title:         getAge   
	 * @Description:   TODO
	 * @param:         @param Birthday
	 * @param:         @return      
	 * @return:        int     
	 * @date:          2019年9月6日 下午2:06:29   
	 * @throws
	 */
	public static int getAge(Date Birthday) {
		
		Calendar instance = Calendar.getInstance();
		
		instance.setTime(Birthday);
		
		int birthYear = instance.get(Calendar.YEAR);
		
		int birthMonth = instance.get(Calendar.MONTH);
		
		int birthDay = instance.get(Calendar.DAY_OF_MONTH);
		
		instance.setTime(new Date());
		
		int currentYear = instance.get(Calendar.YEAR);
		
		int currentMonth = instance.get(Calendar.MONTH);
		
		int currentDay = instance.get(Calendar.DAY_OF_MONTH);
		
		int age = currentYear-birthYear;
		
		if(currentMonth<birthMonth) {
			age--;
		}else if(currentMonth==birthMonth && currentDay<birthDay) {
			age--;
		}
		
		return age;
	}

}
