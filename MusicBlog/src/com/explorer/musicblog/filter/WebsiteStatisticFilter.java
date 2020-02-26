package com.explorer.musicblog.filter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.explorer.musicblog.util.StringUtils;

/**
 * zhangzhong
 * Oct 22, 2019 7:05:52 PM
 * 统计站点访问次数
 */
@WebFilter(filterName = "WebsiteStatisticFilter",urlPatterns = "*.jsp")
public class WebsiteStatisticFilter implements Filter {

	private static String fileName = null;
	
	// 读取本地记录并存储的"站点访问次数"文件
	private static Integer statistic = 0;
	
	public WebsiteStatisticFilter() {
		InputStream rs = getClass().getResourceAsStream("../util/driver.properties");
		Properties p = new Properties();
		try {
			p.load(rs);
			String visitNumber = p.getProperty("visitNumber");
			if (!visitNumber.isEmpty()) {
				fileName = visitNumber;
			}
		} catch (IOException e) {
			throw new RuntimeException("加载配置文件失败！"+e.getMessage());
		}
		if (StringUtils.isNotBlank(fileName)) {
			statistic = readFile(fileName);
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 设置支持HTTP的request和response
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		if (statistic == null || statistic == 0) {
			statistic = 1;
		} else {
			statistic++;
		}
		
		// 获取servlet容器
		ServletContext context = req.getServletContext();
		
		// 将请求数量存储到ServletContext里
		context.setAttribute("count", statistic);
		chain.doFilter(req, resp);
	}
	
	@Override
	public void destroy() {
		// 容器销毁时将访问次数写入到本地文件
		writeFile(statistic);
	}
	
	/**
	 * 写入站点访问次数文件
	 * @param fileName
	 * @param count
	 */
	private static void writeFile(int count) {
		try {
			// 创建File对象
			File file = new File(fileName);
			if (StringUtils.isLegal(fileName) == null) {
				// 文件不存在则创建
				if (!file.exists()) {
					file.createNewFile();
				}
			}
			
			// 写入文件
			FileWriter fw = new FileWriter(file);
			fw.write(String.valueOf(count));
			fw.close();
		} catch (IOException e) {
			throw new RuntimeException("写入文件失败！"+e.getMessage());
		}
	}
	
	/**
	 * 读取站点访问次数文件
	 * @param fileName
	 * @return
	 */
	private static int readFile(String fileName) {
		if (StringUtils.isNotBlank(fileName)) {
			File file = new File(fileName);
			if(file.exists()) {
				BufferedReader br = null;
				try {
					br = new BufferedReader(new FileReader(fileName));
					String readLine = br.readLine();
					System.out.println("readLine:"+readLine);
					if (StringUtils.isLegal(readLine) != null) {
						statistic = Integer.parseInt(readLine);
					}
				} catch (FileNotFoundException e) {
					throw new RuntimeException("获取文件失败！"+e.getMessage());
				} catch (NumberFormatException e) {
					throw new RuntimeException("转换类型错误！"+e.getMessage());
				} catch (IOException e) {
					throw new RuntimeException("I/O错误！"+e.getMessage());
				} finally {
					try {
						br.close();
					} catch (IOException e) {
						throw new RuntimeException("关闭缓冲流失败！"+e.getMessage());
					}
				}
			} else {
				writeFile(statistic);
			}
		}
		return statistic;
	}
}
