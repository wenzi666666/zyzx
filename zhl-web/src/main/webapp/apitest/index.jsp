<%@page import="java.util.Collections"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.lang.annotation.Annotation"%>
<%@page import="java.lang.reflect.Method"%>
<%@page
	import="org.springframework.core.LocalVariableTableParameterNameDiscoverer"%>
<%@page import="org.springframework.web.bind.annotation.RequestMapping"%>
<%@page import="java.util.List"%>
<%@page import="net.tfedu.zhl.helper.httpclient.APIForm"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		
		
		
		LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();

		Class<?> ac = Class.forName(
				"net.tfedu.zhl.cloud.resource.back.appuserpoolconfig.controller.AppUserPoolConfigController");

		
		Annotation[] ls = ac.getDeclaredAnnotations();
		//url的前綴
		String urlPrefix = "";
		for (int i = 0; i < ls.length; i++) {

			if (ls[i].annotationType().equals(RequestMapping.class)) {
				RequestMapping m = (RequestMapping) ls[i];
				urlPrefix = (m.value())[0];
			}

		}

		Method[] methods = ac.getDeclaredMethods();
		List<APIForm> list = new ArrayList<APIForm>();
		//增加登录接口
			APIForm l = new APIForm();
			l.setUrl("resBackAPI/v1.0/login");
			l.setHttp_method("POST");
			l.setRequest_params(null);
			ArrayList<HashMap<String,String>> ps1 = new ArrayList<HashMap<String,String>>();
				
				
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("name","username");
			map.put("type","String");
			ps1.add(map);
			HashMap<String,String> map1 = new HashMap<String,String>();
			map1.put("name","password");
			map1.put("type","String");
			ps1.add(map1);
			
			l.setRequest_params(ps1);
			
			list.add(l);
		
		

		for (Method m : methods) {
		
			if(!m.isAnnotationPresent(RequestMapping.class)){
				continue;
			}
			
		

			APIForm form = new APIForm();
			Annotation[] lls = m.getDeclaredAnnotations();
			for (int j = 0; j < lls.length; j++) {

				if (lls[j].annotationType().equals(RequestMapping.class)) {

					RequestMapping p = (RequestMapping) lls[j];
					String url_append = p.value()[0];

					form.setUrl(urlPrefix + (urlPrefix.endsWith("/")?"":url_append.startsWith("/")?"":"/") + url_append);
					form.setHttp_method(
							p.method() != null && p.method().length > 0 ? (p.method()[0].name()) : "GET");

				}

			}



			String[] parameterNames = discoverer.getParameterNames(m);
			
			Class<?>[]  clist =  m.getParameterTypes();
			if(parameterNames.length>0){
				
				ArrayList<HashMap<String,String>> ps = new ArrayList<HashMap<String,String>>();
			
				for (int x= 0;x<parameterNames.length;x++) {
					HashMap<String,String> _map = new HashMap<String,String>();
					
					_map.put("name",parameterNames[x]);
					_map.put("type",clist[x].getName());
					ps.add(_map);
					
				}

				form.setRequest_params(ps);	
			}







			list.add(form);
		}
			
			
	%>
	
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>接口測試_云洲对接</title>

	<style>
	table[Attributes Style] {
    border-top-width: 0px;
    border-right-width: 0px;
    border-bottom-width: 0px;
    border-left-width: 0px;
    border-spacing: 0px;
	}
	table {
	    display: table;
		width:300px;
	        border: 1px solid #99BBE8;
	}
	
	.HttpHeaderTable  div,.cell{
	margin: 0;
    padding: 3px 4px;
    white-space: nowrap;
    word-wrap: normal;
    overflow: hidden;
    text-align: center;
    border-right: 1px dotted #ccc;
	}
	
	div{
		margin-top:10px;
	}
	
	</style> 
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/common/jsonFormater.css">
	<script type="text/javascript" src="<%=basePath %>/common/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>/common/jsonFormater.js"></script>

 	<script type="text/javascript" src="<%=basePath %>/common/json2.js"></script>
 	<script type="text/javascript" src="<%=basePath %>/apitest/js/api.js"></script>
	
</head>
<body>
    <h4 align='center'>云洲对接接口测试页面</h4> 
  	
  	<h5>测试服务器：<input size=60 type='text' id="server" name='server' value='<%=basePath%>'/></h5>
  	<h5>APPID：<input size=60 type='text' id="appId" name='appId' value='679636'/></h5>
  	<h5>APPKEY：<input size=60 type='text' id="appKey" name='appKey' value='0dd97ecaf1d3'/></h5>
  	<input size=60 type='hidden' id="basePath" name='basePath' value='<%=basePath%>'/>
  	<input size=60 type='hidden' id="token" name='token' value=''/>


  <%
  	for(int i=0;i<list.size();i++){ 
  		APIForm info = list.get(i);
  		ArrayList<HashMap<String,String>> request_params = info.getRequest_params();
  		
   %>
  	
  	<div id='testDiv_<%=i%>' style="background:#fafafa;width:800px;padding-left:60px;padding-top:20px;">
  		<div><label>接口:<span name='api_name'><%=info.getUrl()%></span></label></div>
  		<div><label>方法:<span name='api_method'><%=info.getHttp_method() %></span></label></div>
  		<div><label>测试链接:<span name='api_url'><%=info.getUrl() %></span></label></div>
  		<%if(request_params!=null && request_params.size()>0){ %>
  		<div><label> request请求参数:</label></div>
  		<div><table style='width:500px;margin-left:100px;' class='RequestTable'>
  					<thead>
  						<tr>
  							<td>参数名</td>
  							<td>参数类型</td>
  							<td></td>
  						</tr>
  					</thead>
					<tbody>
					<% for(int j=0;j<request_params.size();j++){ %>
						<tr>
						<td><div><%=request_params.get(j).get("name") %></div></td>
						<td><div><%=request_params.get(j).get("type") %></div></td>
						<td><div><input type='text' name='request_<%=request_params.get(j).get("name") %>'></div></td>
						</tr>					
					<% } %>	
					</tbody>
				</table>  		
  		</div>
  		<%} %>
  		
  		<div align='center'><input type='button' class='goBtn' value='Go'></div>
    
  	</div>
  	<br>
  	<br>
  <%} %>
  
  
  
  
  
  <form id="targetForm">
	<input type="hidden" name="url" size='80'></input>
	<input type="hidden" name="http_method" size='80'></input>
	<input type="hidden" name="header_key" size='80'></input>
	<input type="hidden" name="header_value" size='80'></input>
	<input type="hidden" name="request_key" size='80'></input>
	<input type="hidden" name="request_value" size='80'></input>
</form>


</body>
</html>