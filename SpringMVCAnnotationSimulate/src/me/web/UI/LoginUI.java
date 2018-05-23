package me.web.UI;

import me.web.annotation.Controller;
import me.web.annotation.RequestMapping;
import me.web.view.View;

/**
 * 使用Controller注解标注LoginUI类
 * @author 张文杰
 *
 */
@Controller
public class LoginUI {

	//使用RequestMapping注解指明forward1方法的访问路径  
	@RequestMapping("loginUI/Login2")
	public View forword2() {
		return new View("/login2.jsp");
	}
	
	//使用RequestMapping注解指明forward2方法的访问路径  
	@RequestMapping("loginUI/Login3")
	public View forward3(){
	    //执行完forward2方法之后返回的视图
	    return new View("/login3.jsp");  
	} 
}
