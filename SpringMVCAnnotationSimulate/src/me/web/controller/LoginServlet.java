package me.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.web.annotation.Controller;
import me.web.annotation.RequestMapping;
import me.web.context.WebContext;
import me.web.view.View;
import me.web.view.ViewData;

/**
 * LoginServlet现在就是一个普通的java类，不是一个真正的Servlet
 * @author 张文杰
 *
 */
@Controller
public class LoginServlet {

	/**
	 * 处理以普通方式提交的请求
	 * @return
	 */
	//使用RequestMapping注解标注loginHandle方法，指明loginHandle方法的访问路径是login/handle
	@RequestMapping("login/handle")
	public View loginHandle() {
		//创建一个ViewData对象，用于存储需要发送到客户端的响应数据
		ViewData viewData = new ViewData();
		//通过WebContext类获取当前线程中的HttpServletRequest对象
		HttpServletRequest request = WebContext.requestHodler.get();
		//获取提交上来的参数
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		//校验用户名密码
		if(username.equals("zwj") && pwd.equals("123")) {
			request.getSession().setAttribute("username", username);
			//将响应数据存储到ViewData对象中
			viewData.put("msg", "欢迎您！" + username);
			//返回一个View对象，指明要跳转的视图的路径
			return new View("/index.jsp");
		}else {
			//将响应数据存储到ViewData对象中
			viewData.put("msg", "登陆失败，请检查用户名或密码是否正确！");
			return new View("/login2.jsp");
		}
	}
	
	/*
	 * 处理Ajax请求
	 */
	//使用RequestMapping注解标注ajaxLoginHandle方法，指明ajaxLoginHandle方法的访问路径是ajaxLogin/handle
	@RequestMapping("ajaxLogin/handle")
	public void ajaxLoginHandle() throws IOException {
		//通过WebContext类获取当前线程中的HttpServletRequest对象
        HttpServletRequest request = WebContext.requestHodler.get();
        //接收提交上来的参数
        String username =request.getParameter("usename");
        String pwd = request.getParameter("pwd");
        //通过WebContext类获取当前线程中的HttpServletResponse对象
        HttpServletResponse response = WebContext.responseHodler.get();
        if (username.equals("zwj") && pwd.equals("123")) {
            request.getSession().setAttribute("usename", username);
            response.getWriter().write("success");
        }else {
            response.getWriter().write("fail");
        }
	}
}
