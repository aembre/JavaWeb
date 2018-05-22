package me.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Description: AnnotationHandleServlet��Ϊ�Զ���ע��ĺ��Ĵ������Լ��������Ŀ��ҵ�񷽷������û�����<p>
 * @author ���Ľ�
 *
 */
@WebServlet(name = "AnnotationHandleServlet",
	urlPatterns = "*.do",
	description = "����Ҫɨ��������Ӱ�, ����ж����,�Զ��ŷָ�",
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
		//����ǰ�߳���HttpServletRequest����洢��ThreadLocal�У��Ա���Controller����ʹ��
		
	}

}
