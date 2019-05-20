package cn.gzsxt.portal.service.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.gzsxt.common.pojo.JsonUtils;
import cn.gzsxt.portal.pojo.CartItem;
import cn.gzsxt.portal.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Value("${EGO_CART_COOKIE}")
	private String EGO_CART_COOKIE;
	
	@Override
	public List<CartItem> showOrder(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		boolean flag=true;
		List<CartItem> cart=null;
		for (Cookie cookie : cookies) {
			String name = cookie.getName();
			if(EGO_CART_COOKIE.equals(name)){
				String value = cookie.getValue();
				cart = JsonUtils.jsonToList(value, CartItem.class);
				flag=false;
				break;
			}
		}
		if (flag) {
			cart=new ArrayList<>();
		}
		return cart;
	}

}
