package org.gecedu.utils;

import java.util.Calendar;

import org.apache.commons.lang.StringUtils;


/**
 * 文件上传工具类
 * @author wzd
 */
public class FileUploadUtil {
	
	private FileUploadUtil() {
		super();
	}
	
	
	/**
	 *  获取文件扩展名
	 * @param fileName
	 * @return
	 */
	public static String getFileExt(String fileName){
		String ext="";
		if(StringUtils.isNotBlank(fileName)&&
				StringUtils.contains(fileName, ".")){
			
			ext=StringUtils.substring(fileName, fileName.lastIndexOf(".")+1);
		}
		return ext;
	}
	
	
	/**
	 * 获取年,月组成的目录
	 */
	public static String getTimeDesPath(){
		Calendar cal=Calendar.getInstance();
		
		return "/"+cal.get(Calendar.YEAR)+"/"+(cal.get(Calendar.MONTH)+1)+"/";
	}
	
}
