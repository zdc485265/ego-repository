package cn.gzsxt.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.gzsxt.portal.pojo.CartItem;
import cn.gzsxt.portal.service.CartItemService;

@Controller
@RequestMapping("/cart")
public class CartItemController {

	@Autowired
	private CartItemService cartItemService;
	
	@RequestMapping("/add/{num}/{itemId}")
	public String add(@PathVariable("itemId")Long itemId,@PathVariable("num")Integer num,HttpServletRequest request,HttpServletResponse response){
		List<CartItem> cart = cartItemService.addToCart(itemId, num, request, response);
		request.setAttribute("cartList", cart);
		return "cart";
	} 
	@RequestMapping("/cart")
	public String showCart(HttpServletRequest request){
		List<CartItem> cart = cartItemService.showCart(request);
		request.setAttribute("cartList", cart);
		return "cart";
	}
	
	@RequestMapping("/update/num/{itemId}/{num}")
	public String update(@PathVariable("itemId")Long itemId,@PathVariable("num")Integer num,
			HttpServletRequest request,HttpServletResponse response){
		List<CartItem> cart = cartItemService.update(itemId, num, request, response);
		request.setAttribute("cartList", cart);
		return "cart";
	}
	@RequestMapping("/delete/{itemId}")
	public String delete(@PathVariable("itemId")Long itemId,
			HttpServletRequest request,HttpServletResponse response){
		List<CartItem> cart = cartItemService.delete(itemId, request, response);
		request.setAttribute("cartList", cart);
		return "cart";
	}
}
