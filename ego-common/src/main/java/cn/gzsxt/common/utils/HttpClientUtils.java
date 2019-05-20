package cn.gzsxt.common.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtils {

	/**
	 * 执行有参GET请求
	 * 
	 * @param url
	 * @param params   
	 * @return
	 */
	public static String doGet(String url,Map<String, String> params){
		
		//获取httpclient客户端
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		String resultString = "";
		
		CloseableHttpResponse response = null;
		
		try {
			URIBuilder builder = new URIBuilder(url);
			
			if(null!=params){
				for (String key:params.keySet()) {
					builder.setParameter(key, params.get(key));
				}
			}
			
			HttpGet get = new HttpGet(builder.build());
			
			
			response = httpclient.execute(get);
			
			System.out.println(response.getStatusLine());
			
			if(200==response.getStatusLine().getStatusCode()){
				HttpEntity entity = response.getEntity();
				resultString = EntityUtils.toString(entity, "utf-8");
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			if(null!=response){
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(null!=httpclient){
				try {
					httpclient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return resultString;
	}
	
	/**执行无参GET请求
	 * 
	 * @param url
	 * @return
	 */
	public static String doGet(String url){
		return doGet(url, null);
	}
	
	/**
	 * 执行有参POST请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String doPost(String url,Map<String,String> params){
		/**
		 * 在4.0及以上httpclient版本中，post需要指定重定向的策略，如果不指定则按默认的重定向策略。
		 * 
		 * 获取httpclient客户端
		 */
		CloseableHttpClient httpclient = HttpClientBuilder.create().setRedirectStrategy( new LaxRedirectStrategy()).build();
		
		String resultString = "";
		
		CloseableHttpResponse response = null;
		
		try {
			
			
			HttpPost post = new HttpPost(url);
			
			List<NameValuePair> paramaters = new ArrayList<>();
			
			if(null!=params){
				for (String key : params.keySet()) {
					paramaters.add(new BasicNameValuePair(key,params.get(key)));
				}
				// 构造一个form表单式的实体
				UrlEncodedFormEntity  formEntity = new UrlEncodedFormEntity (paramaters);
				// 将请求实体设置到httpPost对象中
				post.setEntity(formEntity);
			}
			
			
			/**
			 * HTTP/1.1 403 Forbidden
			 *   原因：
			 *      有些网站，设置了反爬虫机制
			 *   解决的办法：
			 *      设置请求头，伪装浏览器
			 */
			post.addHeader("user-agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
			
			response = httpclient.execute(post);
			
			System.out.println(response.getStatusLine());
			
			if(200==response.getStatusLine().getStatusCode()){
				HttpEntity entity = response.getEntity();
				resultString = EntityUtils.toString(entity, "utf-8");
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			if(null!=response){
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(null!=httpclient){
				try {
					httpclient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return resultString;
	}
	
	public static String doPost(String url){
		return doPost(url, null);
	}
	
	
	public static void main(String[] args) {
		
		Map<String,String> params = new HashMap<>();
		params.put("scope", "project");
		params.put("q", "数据库");
//		
//		System.out.println(doGet("http://www.baidu.com/s",params));
		
		/**
		 * 有一部分网站，禁止爬虫技术访问网站。
		 * 
		 * 解决方案：
		 *    伪装浏览器   
		 *    post.addHeader("user-agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
		 */
		System.out.println(doPost("http://www.oschina.net/search",params));
	}
}
