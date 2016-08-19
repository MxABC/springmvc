package com.lbx.myapp;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/trade")
public class TradeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
//		model.addAttribute("serverTime", "fdsajlfdjsalfjdsa" );
		
		return "home";
	}
	
//	@RequestMapping(value = "/",method = RequestMethod.POST)
//	public void create(HttpServletRequest request,HttpServletResponse response) throws IOException
//	{
//		response.getWriter().write("3ks:" );
//	}
	
	@RequestMapping(value = "/originPostData",method = RequestMethod.POST)
	public void originPostData(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
//		logger.info("Welcome post login, The userid: "+ userInfo.getId());
//		response.getWriter().write("3ks:" + userInfo.getName() + "for login" );
		
//		response.getWriter().write("3ks:" + " for originPostData" );
		
		
		/**
		 * 客户端是以UTF-8编码传输数据到服务器端的，所以需要设置服务器端以UTF-8的编码进行接收，否则对于中文数据就会产生乱码
		 */
		request.setCharacterEncoding("UTF-8");
		
		response.setCharacterEncoding("UTF-8");// 设置将字符以"UTF-8"编码输出到客户端浏览器
		// 通过设置响应头控制浏览器以UTF-8的编码显示数据
		response.setHeader("content-type", "text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		Enumeration<String> reqHeadInfos = request.getHeaderNames();// 获取所有的请求头
		out.write("获取到的客户端所有的请求头信息如下：");
		out.write("<hr/>");
		while (reqHeadInfos.hasMoreElements()) {
			String headName = (String) reqHeadInfos.nextElement();
			String headValue = request.getHeader(headName);// 根据请求头的名字获取对应的请求头的值
			out.write(headName + ":" + headValue);
			out.write("<br/>");
		}
		out.write("<br/>");
		out.write("获取到的客户端Accept-Encoding请求头的值：");
		out.write("<br/>");

		Enumeration<String> e = request.getHeaders("Accept-Encoding");
		while (e.hasMoreElements()) {
			String string = (String) e.nextElement();
			System.out.println(string);
			out.write("<br/>");
		}
		out.write("<br/>");
		out.write("获取到的客户端body数据：");
		out.write("<br/>");
		
		String res = new String(binaryReader(request));
		out.write(res);
		out.write("<br/>");
		
	}
	
	@RequestMapping(value = "/userTrade",method = RequestMethod.POST)
	public void usertrade(HttpServletRequest request,HttpServletResponse response,TradeInfo tradeInfo) throws IOException
	{
		logger.info("Welcome post trade, The userid: "+ tradeInfo.getId());
		response.getWriter().write("3ks:" + tradeInfo.getName() + " for trade" );
	}
	
	//二进制读取
	String binaryReader(HttpServletRequest request) throws IOException {
		int len = request.getContentLength();
		ServletInputStream iii = request.getInputStream();
		byte[] buffer = new byte[len]; //java中默认值0
	
		iii.read(buffer, 0, len);
		
		String res = new String(buffer);
		
		return res;
	}
	
}

class TradeInfo implements Serializable {  
    
    private static final long serialVersionUID = 1L;  
      
    private String id;  
    private String name;  
    private int money;  
      
    public TradeInfo() {  
        super();  
    }  
  
    public TradeInfo(String id, String name, int money) {  
        super();  
        this.id = id;  
        this.name = name;  
        this.money = money;  
    }  
  
    public int getMoney() {  
        return money;  
    }  
  
    public void setMoney(int money) {  
        this.money = money;  
    }  
  
    public String getId() {  
        return id;  
    }  
  
    public void setId(String id) {  
        this.id = id;  
    }  
  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
      
}  
