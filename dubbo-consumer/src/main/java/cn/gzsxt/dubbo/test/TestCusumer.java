package cn.gzsxt.dubbo.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.gzsxt.dubbo.pojo.User;
import cn.gzsxt.dubbo.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:dubbo-consumer.xml")
public class TestCusumer {

	@Reference
	private UserService userService;
	@Test
	public void test(){
		List<User> users = userService.getAllUsers();
		for (User user : users) {
			System.out.println("用户名:"+user.getName()+",密码:"+user.getPwd());
		}
	}
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("classpath:dubbo-consumer.xml");
		UserService userService = context.getBean("userService",UserService.class);
		List<User> users = userService.getAllUsers();
		for (User user : users) {
			System.out.println("用户名:"+user.getName()+",密码:"+user.getPwd());
		}
		context.close();
	}
}
