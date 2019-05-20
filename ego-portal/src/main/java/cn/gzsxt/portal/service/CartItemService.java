package cn.gzsxt.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.gzsxt.portal.pojo.CartItem;

public interface CartItemService {

	public List<CartItem> addToCart(Long itemId, Integer num, HttpServletRequest request,
			HttpServletResponse response);
	
	public List<CartItem> showCart(HttpServletRequest request);
	public List<CartItem> update(Long itemId, Integer num, HttpServletRequest request, 
			HttpServletResponse response);
	public List<CartItem> delete(Long itemId, HttpServletRequest request, 
			HttpServletResponse response);
}
