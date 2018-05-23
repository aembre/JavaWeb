<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>login3登录页面</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/Utils.js"></script>
    <script type="text/javascript">
    
        function login(){
             $.ajax({ 
            	    type : "POST", //提交方式 
            	    url : "${pageContext.request.contextPath}/ajaxLogin/handle.do",//路径 
            	    data : { 
            	    	"usename" : document.getElementById("usename").value,
                        "pwd" : document.getElementById("pwd").value
            	    },//数据，这里使用的是Json格式进行传输 
            	    async : true,//默认为true如果需要发送同步请求，请将此选项设置为 false
            	    success: function (data) {
            	    	onData(data)
            	    },
            	    error: function () {
            	    
            	    }
            	}); 
        }
        
        function onData(responseText){
            if(responseText=="success"){
                //window.location.href="index.jsp";//改变url地址
                /*
                window.location.replace("url")：将地址替换成新url，
                该方法通过指定URL替换当前缓存在历史里（客户端）的项目，因此当使用replace方法之后，
                你不能通过“前进”和“后 退”来访问已经被替换的URL，这个特点对于做一些过渡页面非常有用！
                */
                location.replace(g_basePath+"/index.jsp");
            }else{
                alert("用户名和密码错误");
            }
        }
    </script>
  </head>
  
  <body>
  	<p align="center">login3登陆页面</p>
    <fieldset>
        <legend>用户登录</legend>
        <form>
            用户名：<input type="text" name="usename" id="usename">
            <br/>
            密码：<input type="password" name="pwd" id="pwd">
            <br/>
            <input type="button" value="登录" onclick="login()"/>
        </form>
    </fieldset>
  </body>
</html>