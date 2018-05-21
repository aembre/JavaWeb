package com.zwj;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取文件名称
		String filename = request.getParameter("filename");
		//上传的文件都是保存在/WEB-INF/upload目录下的子目录当中
		String fileSaveRootPath = this.getServletContext().getRealPath("/WEB-INF/upload");
		//通过文件名找出文件的所在目录
		String path = findFileSavePathByFileName(filename,fileSaveRootPath);
		//得到要下载的文件
		File file = new File(path + "\\" + filename);
		if(!file.exists()) {
			request.setAttribute("message", "您要下载的资源已被删除！！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		//处理文件名
		String realName = filename.substring(filename.indexOf("_") + 1);
		//设置响应头，控制浏览器下载该文件
		response.setHeader("content-disposition", "attachment;filename="+URLEncoder.encode(realName, "UTF-8"));
		//读取要下载的文件，保存到文件输入流
		FileInputStream in = new FileInputStream(path + "\\" + filename);
		//创建输出流
		ServletOutputStream out = response.getOutputStream();
		//创建缓冲区
		byte[] buffer = new byte[1024];
		int len = 0;
		//循环将输入流中的内容读取到缓冲区当中
		while((len = in.read(buffer)) > 0) {
			//输出缓冲区的内容到浏览器，实现文件下载
			out.write(buffer, 0, len);
		}
		//关闭文件输入流
		in.close();
		//关闭输出流
		out.flush();
		out.close();
	}
	/**
	* @Method: findFileSavePathByFileName
	* @Description: 通过文件名和存储上传文件根目录找出要下载的文件的所在路径
	* @Anthor:孤傲苍狼
	* @param filename 要下载的文件名
	* @param saveRootPath 上传文件保存的根目录，也就是/WEB-INF/upload目录
	* @return 要下载的文件的存储目录
	*/ 
	private String findFileSavePathByFileName(String filename, String fileSaveRootPath) {
		int hashCode = filename.hashCode();
		int dir1 = hashCode & 0xf;//0-15
		int dir2 = (hashCode & 0xf0) >> 4;//0-15
		String dir = fileSaveRootPath + "\\" + dir1 + "\\" + dir2;
		File file = new File(dir);
		if(!file.exists()) {
			//创建目录
			file.mkdirs();
		}
		return dir;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
