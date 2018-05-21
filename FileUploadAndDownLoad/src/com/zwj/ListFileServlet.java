package com.zwj;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListFileServlet
 */
@WebServlet("/ListFileServlet")
public class ListFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取上传文件的目录
		String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
		//存储要下载的文件名
		Map<String, String> fileNameMap = new HashMap<>();
		//递归遍历filepath目录下的所有文件和目录，将文件的文件名存储到map集合中
		listFile(new File(savePath),fileNameMap);//File既可以代表一个文件也可以代表一个目录
		//将Map集合发送到listfile.jsp页面进行显示
		request.setAttribute("fileNameMap", fileNameMap);
		request.getRequestDispatcher("/listFile.jsp").forward(request, response);
	}

	private void listFile(File file, Map<String, String> fileNameMap) {
		//如果file代表的不是一个文件，而是一个目录
		if(!file.isFile()) {
			//列出该目录下的所有文件和目录
			File[] files = file.listFiles();
			//遍历files
			for (File f : files) {
				listFile(f, fileNameMap);
			}
		}else {
			//处理文件名，上传后的文件是以uuid_文件名的形式去重新命名的，去除文件名的uuid_部分
			String realName = file.getName().substring(file.getName().indexOf("_")+1);
			//file.getName()得到的是文件的原始名称，这个名称是唯一的，因此可以作为key，realName是处理过后的名称，有可能会重复
			fileNameMap.put(file.getName(), realName);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
