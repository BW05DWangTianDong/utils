package com.bw.wangtiandong.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 
 * @ClassName:     StreamUtils   
 * @Description:   TODO
 * @author:        WTD
 * @date:          2019年9月9日 上午9:52:42
 */
public class StreamUtils {
	
	/**
	 * 
	 * @Title:         closeStream   
	 * @Description:   TODO
	 * @param:         @param closeables
	 * @param:         @throws IOException      
	 * @return:        void     
	 * @date:          2019年9月9日 上午9:52:45   
	 * @throws
	 */
	public static void closeStream(Closeable ...closeables) throws IOException {
		for (Closeable closeable : closeables) {
			closeable.close();
		}
		
	}
	
	/**
	 * 
	 * @Title:         copyStream   
	 * @Description:   TODO
	 * @param:         @param is
	 * @param:         @param os
	 * @param:         @throws IOException      
	 * @return:        void     
	 * @date:          2019年9月9日 上午9:52:51   
	 * @throws
	 */
	public static void copyStream(InputStream is,OutputStream os) throws IOException {
		
		byte b[] = new byte[1024];
		while(is.read(b)>0) {
			os.write(b);
		}
		os.flush();
		
	}

}
