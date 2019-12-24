package com.explorer.musicblog.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * zhangzhong
 * Sep 8, 201811:17:00 PM
 */
public class Play {

	public void readFile(String fileName) {
		InputStream in = null;
		try {  
            System.out.println("以字节为单位读取文件内容，一次读多个字节：");  
            // 一次读多个字节  
            byte[] tempbytes = new byte[100];  
            int byteread = 0;  
            in = new FileInputStream(fileName);  
//            ReadFromFile.showAvailableBytes(in);
            // 读入多个字节到字节数组中，byteread为一次读入的字节数  
            while ((byteread = in.read(tempbytes)) != -1) {  
                System.out.write(tempbytes, 0, byteread);  
            }  
        } catch (Exception e1) {  
            e1.printStackTrace();  
        } finally {  
            if (in != null) {  
                try {  
                    in.close();  
                } catch (IOException e1) {  
                }  
            }  
        } 
//		
//		File file = new File(fileName);
//		try {
//			System.out.println("正在读取文件...");
//			byte[] tempBytes = new byte[100];
//			int byteRead = 0;
//			in = new FileInputStream(file);
//			
//		} catch (Exception e){
//			e.printStackTrace();
//		} finally {
//			if(in != null) {
//				try {
//					in.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
	}
}
