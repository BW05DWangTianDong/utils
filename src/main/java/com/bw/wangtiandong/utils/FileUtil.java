package com.bw.wangtiandong.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * 
 * @ClassName:     FileUtil   
 * @Description:   TODO
 * @author:        WTD
 * @date:          2019年9月9日 上午9:52:02
 */
public class FileUtil {
	
	public static Logger log = Logger.getLogger(FileUtil.class); 
	
	public static void MyJsoup(String src) throws IOException {
		// 记录文章数
		int count = 0;
		// 获取连接对象
		Connection connect = Jsoup.connect(src);
		// 获取文档对象
		Document document = connect.get();
		// 获取当前文档的所有超链接
		Elements ahrefs = document.select("a[href]");
		// 遍历元素对象
		for (Element href : ahrefs) {
			// 超链接的url地址
			String url = href.attr("href");
			// 定义表达式 https://news.163.com ***** html
			String regex = "https://news\\.163\\.com.*html$";
			
			//以https://news.163.com开头，以html结尾
//			url.startsWith("https://news\\\\.163\\\\.com");
//			url.endsWith("html");
			
			// 特殊要求
			if (url != null && Pattern.matches(regex, url)) {
				// 连接的文本内容
				String title = href.text();
				System.out.println(url + "@@@@@@@@@" + title);
				count++;
				
				// 获取文章的文档对象
				Document articleDoc = Jsoup.connect(url).get();
				// 获取文章的内容元素对象
				Element articleContentElement = articleDoc.getElementById("endText");
				// 判断元素是否为空
				if (articleContentElement != null) {
					// 获取纯文本内容
					String content = articleContentElement.text();
					
					//去除标题中的特殊符号
					title = title.replace("?", "").replace("\"", "").replace(":", "");
					
					//写入到文件中
					FileUtil.writeFile("D:\\大纲\\小二\\jsoup\\" + title + ".txt", content, "utf8");
				}
			}
		}
		System.out.println("首页中找到了复合条件的网址有：" + count + "篇文章");
	}
	
	/**
	 * 
	 * @Title:         writeFile   
	 * @Description:   按照指定的编码把内容写入指定的文件中
	 * @param:         @param path
	 * @param:         @param content
	 * @param:         @param charset
	 * @param:         @throws IOException      
	 * @return:        void     
	 * @date:          2019年10月22日 上午11:29:37   
	 * @throws
	 */
	public static void writeFile(String path, String content, String charset) throws IOException {
		// 创建写入的文件
		File file = new File(path);
		// 创建输出流对象
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
		bw.write(content);
		bw.flush();
		bw.close();

	}
	
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
			String[] strings = str.split("==");
			Object object = constructor.newInstance(strings);
			list.add(object);
		}
		
		return list;
	}
	
	/**
	 * 
	 * @Title:         getFileList   
	 * @Description:   遍历文件夹
	 * @param:         @param pathName
	 * @param:         @return      
	 * @return:        List<String>     
	 * @date:          2019年9月23日 下午2:35:40   
	 * @throws
	 */
	public static List<String> getFileList(String pathName){
		String[] list = new File(pathName).list();
		 List<String> fileList = new ArrayList<String>();
		for (String string : list) {
			File subFile = new File(pathName + "\\" + string);
			if(subFile!=null && subFile.exists() && subFile.isFile())
				fileList.add(pathName + "\\" + string);
		}
		return fileList;
		
	}
	
	/**
	 * 
	 * @Title:         readFile   
	 * @Description:   读取文件
	 * @param:         @param fileName
	 * @param:         @return
	 * @param:         @throws IOException      
	 * @return:        String     
	 * @date:          2019年9月23日 下午2:35:55   
	 * @throws
	 */
	public static String readFile(String fileName) throws IOException {

		StringBuilder sb = new StringBuilder();
		
		File file = new File(fileName);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String lineString = null;
		
		while ((lineString = bufferedReader.readLine()) != null) {
			sb.append(lineString).append("\n");
		}
		return sb.toString();
	}
	
	
}
