package com.bw.wangtiandong.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


/**
 * 
 * @ClassName:     FileUtil   
 * @Description:   TODO
 * @author:        WTD
 * @date:          2019年9月9日 上午9:52:02
 */
public class FileUtil {
	
	public static Logger log = Logger.getLogger(FileUtil.class); 
	
	/**
	 * 
	 * @Title:         delFilePath   
	 * @Description:   TODO
	 * @param:         @param fileName      
	 * @return:        void     
	 * @date:          2019年9月9日 上午9:52:06   
	 * @throws
	 */
	public static void delFilePath(String fileName) {
		
		File file = new File(fileName);
		if(!file.exists()) {
			return;
		}
		
		if(file.isFile()) {
			log.info(" ɾ���ļ� " + fileName);
			file.delete();
			return;
		}
		
		if(file.isDirectory()) {
			 String[] list = file.list();
			 for (String subPath : list) {
				 delFilePath(fileName + "/" + subPath);
			}
			 log.info(" ------------ ɾ���ļ� �� �� " + fileName); 
			file.delete(); 
		}
		
		
	}
	
	/**
	 * 
	 * @Title:         getSuffix   
	 * @Description:   TODO
	 * @param:         @param fileName
	 * @param:         @return      
	 * @return:        String     
	 * @date:          2019年9月9日 上午9:52:19   
	 * @throws
	 */
	public static String getSuffix(String fileName) {
		int dotIndex = fileName.lastIndexOf('.');
		if(dotIndex<0) {
			return "";
		}
		if(dotIndex>=fileName.length()) {
			return "";
		}
		//
		return fileName.substring(dotIndex+1);
	}
	/**
	 * 
	 * @Title:         getSystemProp   
	 * @Description:   TODO
	 * @param:         @param key
	 * @param:         @return      
	 * @return:        String     
	 * @date:          2019年9月9日 上午9:52:27   
	 * @throws
	 */
	public static String getSystemProp(String key) {
		
		String propValue = System.getProperty(key);
		return propValue;
		
	}
	
	/**
	 * 
	 * @Title:         getSize   
	 * @Description:   TODO
	 * @param:         @param fileName
	 * @param:         @param fileUnit
	 * @param:         @return      
	 * @return:        long     
	 * @date:          2019年9月9日 上午9:52:30   
	 * @throws
	 */
	public long  getSize(String fileName,FileUnit fileUnit) {
		File file = new File(fileName);
		
		
		long  size = file.length();
		switch (fileUnit) {
			case B:
				return size;
			case KB:
				return size/1024;
			case MB:
				return size/1024/1024;
			case GB:
				return size/1024/1024/1024;
			case TB:
				return size/1024/1024/1024/1024;
			case PB:
				return size/1024/1024/1024/1024/1024;
			default:
				return 0;
		}
		
	}
	
	
	public static List<Object> fileToBean(String fileName,Constructor constructor,String regex) throws Exception{
		
		File file=new File(fileName);
		
		BufferedReader br=new BufferedReader(new FileReader(file));
		
		String str=null;
		
		List<Object> list= new ArrayList<Object>();
		
		while((str=br.readLine())!=null) {
			String[] strings = str.split(regex);
			Object object = constructor.newInstance(strings);
			list.add(object);
		}
		
		return list;
	}
	
	public static List<Object> fileToBeanExam(String fileName,Constructor constructor) throws Exception{
		
		File file=new File(fileName);
		
		BufferedReader br=new BufferedReader(new FileReader(file));
		
		String str=null;
		
		List<Object> list= new ArrayList<Object>();
		
		while((str=br.readLine())!=null) {
			String[] strings = str.split("\\|\\|");
			Object object = constructor.newInstance(strings);
			list.add(object);
		}
		
		return list;
	}
	
	
}
