package me.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Description: AnnotationHandleServlet作为自定义注解的核心处理器以及负责调用目标业务方法处理用户请求<p>
 * @author 张文杰
 *
 */
@WebServlet(name = "AnnotationHandleServlet",
	urlPatterns = "*.do",
	description = "配置要扫描包及其子包, 如果有多个包,以逗号分隔",
	initParams = {
			@WebInitParam(name="basePackage",value="me.web.controller,me.web.UI")
	}
)
public class AnnotationHandleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	private String pareRequestURI(HttpServletRequest request) {
		String path = request.getContextPath()+"/";
		String requestUri = request.getRequestURI();
		String midUrl = requestUri.replaceFirst(path, "");
		String lastUrl = midUrl.substring(0, midUrl.lastIndexOf("."));
		return lastUrl;
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.excute(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.excute(request, response);
	}
	
	private void excute(HttpServletRequest request, HttpServletResponse response) {
		//将当前线程中HttpServletRequest对象存储到ThreadLocal中，以便在Controller类中使用
		
	}

}
