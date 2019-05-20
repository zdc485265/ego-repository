package cn.gzsxt.common.test;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientTest {
	public static void main(String[] args) {
		doGet();
		doPost();
	}

	private static void doPost() {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post=new HttpPost("http://localhost:8081/rest/content/category/89");
		CloseableHttpResponse response=null;
		try {
			response = client.execute(post);
			int statusCode = response.getStatusLine().getStatusCode();
			if(200==statusCode){
				HttpEntity entity = response.getEntity();
				String json = EntityUtils.toString(entity, "utf-8");
				System.out.println(json);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(null!=response){
				try {
					response.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private static void doGet() {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get=new HttpGet("http://localhost:8081/rest/content/category/89");
		CloseableHttpResponse response=null;
		try {
			response = client.execute(get);
			int statusCode = response.getStatusLine().getStatusCode();
			if(200==statusCode){
				HttpEntity entity = response.getEntity();
				String json = EntityUtils.toString(entity, "utf-8");
				System.out.println(json);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(null!=response){
				try {
					response.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
