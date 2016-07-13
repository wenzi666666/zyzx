package net.tfedu.zhl.cloud.resource.thirdparty.sichuan.util;





import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 *@date 2015-12-23
 *@anthor wangwr
 *@copyRight  同方知好乐教育科技北京有限公司
 */
public class HttpHelper {
	
	private HttpHelper () {}
	
	
	/**
	 * @toDo 创建URL链接对象
	 * 
	 * @param url 请求地址
	 * @param method 请求方式  {GET, POST, PUT, DELETE}
	 * @return HttpURLConnection
	 */
	public final static HttpURLConnection createCnnection (String url, String method) throws MalformedURLException, IOException {
		
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		
		conn.setUseCaches(false);
		conn.setConnectTimeout(5000000);
		conn.setRequestMethod(method);
		conn.setRequestProperty("accept", "*/*");
		conn.setRequestProperty("Accept-Charset", "UTF-8");
        conn.setRequestProperty("Content-Type","text/html;charset=UTF-8");
        conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
		
		return conn;
	}
	
	/**
	 * @toDo 发送GET请求
	 * 
	 * @param url 请求地址
	 * @return 服务端响应的数据
	 */
	public static final String sendGet (String url) {
		
		StringBuffer message = new StringBuffer();
		
		BufferedReader reader = null;
		try {
			// 建立链接对象
			HttpURLConnection conn = createCnnection(url, "GET");
			
			// 发送请求
			conn.connect();
			
			// 读取服务端响应数据
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			readMessage(reader, message);
			
			// 断开连接
			conn.disconnect();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) 
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return message.toString();
	}
	
	/**
	 * @toDo 发送POST请求
	 * 
	 * @param url 请求地址
	 * @param params 请求参数 {放在消息体中的参数}
	 * @return 服务端响应的数据
	 */
	public static final String sendPost (String url, String params) {
		return sendByOtherMethod(url, "POST",  params);
	}
	
	/**
	 * @toDo 发送POST请求
	 * 
	 * @return 服务端响应的数据
	 */
	public static final String sendPost (String url) {
		return sendPost(url, null);
	}
	
	/**
	 * @toDo 发送PUT请求
	 * 
	 * @param url 请求地址
	 * @param params 请求参数 {放在消息体中的参数}
	 * @return 服务端响应的数据
	 */
	public static final String sendPut (String url) {
		return sendByOtherMethod(url, "PUT", null);
	}
	
	/**
	 * @toDo 发送DELETE请求
	 * 
	 * @param url 请求地址
	 * @param params 请求参数 {放在消息体中的参数}
	 * @return 服务端响应的数据
	 */
	public static final String sendDelete (String url) {
		return sendByOtherMethod(url, "DELETE", null);
	}
	
	
	
	
	public static final String paramsMap (Map<String, Object> params) {
		
		if (params == null || params.isEmpty()) return "";
		
		StringBuffer buffer = new StringBuffer();
		
		boolean first = true;
		for (String key : params.keySet()) {
			
			Object value = params.get(key);
			if (value != null && !"".equals(value.toString())) {
				if (!first) buffer.append("&");
				else first = false;
				buffer.append(key).append("=").append(value);
			}
		}
		
		return buffer.toString();
	}
	
	public static final String paramsObject (Object ... params) {
		
		if (params == null || params.length == 0 || params.length <= 1) return "";
		
		int len = params.length;
			len = (len % 2 == 0 ? len : len - len % 2);
		
		StringBuffer buffer = new StringBuffer();
			
		for (int i = 0; i < len - 1; i += 2) {
			
			Object k = params[i];
			Object v = params[i + 1];
			
			if ((k != null && !"".equals(k.toString())) && 
				(v != null && !"".equals(v.toString()))) {
				if (i != 0) buffer.append("&");
				buffer.append(k).append("=").append(v);
			}
		}
		
		return buffer.toString();
	}
	
	/**
	 * @toDo 发送除GET请求外的其他请求
	 * 
	 * @param url 请求地址
	 * @param method 请求方式 {POST, PUT, DELETE}
	 * @param params 请求参数 {放在消息体中的参数}
	 * @return 服务端响应的数据
	 */
	static final String sendByOtherMethod (String url, String method, String params) {
		
		StringBuffer message = new StringBuffer();
		
		BufferedReader reader = null;
		PrintWriter print = null;
		try {
			// 建立链接对象
			HttpURLConnection conn = createCnnection(url, method);
			
			conn.setReadTimeout(80000000);
			if (!method.equals("DELETE")) {
				conn.setDoInput(true);
				conn.setDoOutput(true);
				if (params != null) {
					// 设置参数类型
					if ((params.startsWith("[") && params.endsWith("]")) || 
						(params.startsWith("{") && params.endsWith("}"))) {
						conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
					} else {
						conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
					}
					// 携带消息参数
					print = new PrintWriter(conn.getOutputStream());
					print.print(params);
					print.flush();
				}
			}
			
			// 发送请求
			conn.connect();
			
			// 读取服务端响应数据
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			readMessage(reader, message);
			
			// 断开连接
			conn.disconnect();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (print != null) 
					print.close();
				if (reader != null) 
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return message.toString();
	}
	
	/**
	 * @toDo 读取服务端返回数据
	 * 
	 * @param reader
	 * @param buffer 
	 * @throws IOException
	 */
	static final void readMessage (BufferedReader reader, StringBuffer buffer) throws IOException {
		String line = "";
		while ((line = reader.readLine()) != null) {
			buffer.append(line+"\n");
		}
	}
	
	
	public static void main(String[] rags) throws Exception{
		String url = "http://182.139.134.54:13000/SystemService/app/authentication";
		String param = "{\"params\":{\"pkey\":\"AF3B9CEA805D754F2465A8094D503929E2BC8AC3370767BAAF95CDA8D7A5C7EF214B800C2B938D61F6F6263D52D56517\",\"appkey\":\"C6661EC4194F9C1C5C032D004B1ED1251491F95CD42BB7B6ECA4C399E7A874F8214B800C2B938D61F6F6263D52D56517\",\"ticket\":\"69359D178156C2741419BF5EFF603B2F91BFB85D28AFFFA3341825DA577835767AAA76ADAE77D0031D298BBB9928325E\"}}";

//		HashMap map = new HashMap();
//		map.put("pkey", "AF3B9CEA805D754F2465A8094D503929E2BC8AC3370767BAAF95CDA8D7A5C7EF214B800C2B938D61F6F6263D52D56517");
//		map.put("appkey", "C6661EC4194F9C1C5C032D004B1ED1251491F95CD42BB7B6ECA4C399E7A874F8214B800C2B938D61F6F6263D52D56517");
//		map.put("ticket", "69359D178156C2741419BF5EFF603B2F91BFB85D28AFFFA3341825DA577835767AAA76ADAE77D0031D298BBB9928325E");
//		
//		HashMap pp = new HashMap();
//		pp.put("params", map);
//		String s =HttpHelper.paramsMap(map);// JsonTool.getJson(pp);
		
		System.out.println(HttpHelper.sendPost(url, param));
	}
}
