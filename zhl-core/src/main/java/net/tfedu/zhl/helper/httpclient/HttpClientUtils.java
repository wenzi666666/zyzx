package net.tfedu.zhl.helper.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import net.tfedu.zhl.core.exception.APIErrorException;
import net.tfedu.zhl.core.exception.CustomException;



/**
 * http接口工具类
 * @author wangwr
 *
 */
public class HttpClientUtils {
	
	
	public static void main(String[] args) throws Exception {
		String url = "http://www.webapi.ccroom.com.cn:8033/userBaseData/GetUserBaseData?id=33031&appid=679636&sign=259123ded2f5ba1b578f2d76ba75f8ba";
		
		String result = doGET(url);

		System.out.println(result);
		
		
	}
	
	
	public static String doGET(String url) throws Exception{
		String result = null ;
		HttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);
		HttpResponse response= null ;
						
			try {
				response=client.execute(get);

				String statusLine = response.getStatusLine().toString();
				
				if(statusLine.contains("200")){
					HttpEntity entity = response.getEntity();
					result = (EntityUtils.toString(entity, "utf-8"));	
				}else{
					throw new APIErrorException(statusLine);
				}
				
			} catch (Exception e) {
				throw e ;
			}finally{
				response = null ;
				client = null ;
			}
			return result ;
	}
	
	

	
	
	
	public static String doGet(APIForm form)
			throws ClientProtocolException, IOException, CustomException, URISyntaxException {

		String json = null;
		CloseableHttpClient client = HttpClients.createDefault();

		ArrayList<HashMap<String, String>> header_params = form.getHeader_params();

		ArrayList<HashMap<String, String>> request_params = form.getRequest_params();

		URIBuilder ub = new URIBuilder();
		ub.setPath(form.getUrl());

		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		if (request_params != null && request_params.size() > 0) {
			for (HashMap<String, String> hashMap : request_params) {
				Set<Entry<String, String>> temp = hashMap.entrySet();
				for (Iterator<Entry<String, String>> iterator = temp.iterator(); iterator.hasNext();) {
					Entry<String, String> entry = (Entry<String, String>) iterator.next();
					NameValuePair e = new BasicNameValuePair(entry.getKey(), entry.getValue());
					pairs.add(e);
				}

			}
		}
		ub.setParameters(pairs);

		HttpGet get = new HttpGet(ub.build());

		// 增加header
		if (header_params != null && header_params.size() > 0) {
			for (HashMap<String, String> hashMap : header_params) {
				Set<Entry<String, String>> temp = hashMap.entrySet();
				for (Iterator<Entry<String, String>> iterator = temp.iterator(); iterator.hasNext();) {
					Entry<String, String> entry = (Entry<String, String>) iterator.next();
					get.addHeader(entry.getKey(), entry.getValue());
				}

			}

		}

		CloseableHttpResponse response = null;

		try {
			response = client.execute(get);
			String statusLine = response.getStatusLine().toString();
			if (statusLine.contains("200")) {
				HttpEntity entity = response.getEntity();
				json = EntityUtils.toString(entity, "utf-8");
			} else {
				throw new APIErrorException(statusLine);
			}	
		} finally {
			if(null!=response){
				response.close();
				response = null;
			}
			
			if(null!=client){
				client.close();
				client = null;
			}
		}
		
		return json;
	}

	@SuppressWarnings("deprecation")
	public static String doPost(APIForm form) throws ClientProtocolException, IOException, CustomException {
		// 获取form表单中的参数
		ArrayList<HashMap<String, String>> header_params = form.getHeader_params();

		ArrayList<HashMap<String, String>> request_params = form.getRequest_params();

		String json = null;

		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(form.getUrl());

		// 增加header
		if (header_params != null && header_params.size() > 0) {
			for (HashMap<String, String> hashMap : header_params) {
				Set<Entry<String, String>> temp = hashMap.entrySet();
				for (Iterator<Entry<String, String>> iterator = temp.iterator(); iterator.hasNext();) {
					Entry<String, String> entry = (Entry<String, String>) iterator.next();
					post.addHeader(entry.getKey(), entry.getValue());
				}

			}
		}

		List<NameValuePair> params = new ArrayList<NameValuePair>();

		if (request_params != null && request_params.size() > 0) {
			for (HashMap<String, String> hashMap : request_params) {
				Set<Entry<String, String>> temp = hashMap.entrySet();
				for (Iterator<Entry<String, String>> iterator = temp.iterator(); iterator.hasNext();) {
					Entry<String, String> entry = (Entry<String, String>) iterator.next();
					NameValuePair value = new BasicNameValuePair(entry.getKey(), entry.getValue());
					params.add(value);
				}

			}
		}
		post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

		
		
		CloseableHttpResponse response =  null ;
		
		try {
			response =  client.execute(post);
			String statusLine = response.getStatusLine().toString();
			if (statusLine.contains("200")) {
				HttpEntity entity = response.getEntity();
				json = (EntityUtils.toString(entity));
			} else {
				throw new APIErrorException(statusLine);
			}
			
		} finally {
			if(null!=response){
				response.close();
				response = null;
			}
			if(null!=client){
				client.close();
				client = null;
			}
		}
		return json;
	}
	
	
	public static String getUrlString(String url){
		try{
			HttpURLConnection conn = (HttpURLConnection)new URL(url).openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(50000);
			conn.setDoOutput(true);
			conn.connect();
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			StringBuffer sb = new StringBuffer();
			String temp = rd.readLine();
			while(temp!=null){
				sb.append(temp);
				temp = rd.readLine();
			}
			rd.close();
			conn.disconnect();
			return sb.toString();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}

}
