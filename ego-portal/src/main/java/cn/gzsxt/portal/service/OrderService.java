package cn.gzsxt.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.gzsxt.portal.pojo.CartItem;

public interface OrderService {
	public List<CartItem> showOrder(HttpServletRequest request);
}
