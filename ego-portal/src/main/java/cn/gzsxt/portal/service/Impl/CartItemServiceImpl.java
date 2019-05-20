package cn.gzsxt.portal.service.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.gzsxt.common.pojo.JsonUtils;
import cn.gzsxt.common.utils.HttpClientUtils;
import cn.gzsxt.manager.pojo.Item;
import cn.gzsxt.portal.pojo.CartItem;
import cn.gzsxt.portal.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService{
	
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_ITEM_URL}")
	private String REST_ITEM_URL;

	@Value("${EGO_CART_COOKIE}")
	private String EGO_CART_COOKIE;
	@Override
	public List<CartItem> addToCart(Long itemId, Integer num, HttpServletRequest request,
			HttpServletResponse response) {
		Item item = getById(itemId);
		List<CartItem> cart= getCartListFromCookie(request);
		boolean isExist=false;
		for (CartItem cartItem : cart) {
			if(item.toString().equals(cartItem)){
				cartItem.setNum(cartItem.getNum()+num);
				isExist=true;
				break;
			}
		}
		if(!isExist){
			CartItem cItem = new CartItem();
			cItem.setId(itemId.toString());
			cItem.setNum(num);
			cItem.setPrice(item.getPrice());
			cItem.setTitle(item.getTitle());
			cItem.setImage(item.getImage());
			
			cart.add(cItem);
		}
		
		Cookie cookie=new Cookie(EGO_CART_COOKIE,JsonUtils.objectToJson(cart));
		cookie.setPath("/");
		response.addCookie(cookie);
		return cart;
	}

	private List<CartItem> getCartListFromCookie(HttpServletRequest request) {
		List<CartItem> cart=null;
		Cookie[] cookies = request.getCookies();
		boolean flag=true;
		for (Cookie cookie : cookies) {
			if(EGO_CART_COOKIE.equals(cookie.getName())){
				cart = JsonUtils.jsonToList(cookie.getValue(), CartItem.class);
				flag=false;
				break;
			}
			
		}
		if(flag){
			cart=new ArrayList<>();
		}
		return cart;
	}

	private Item getById(Long itemId) {
		String doGet = HttpClientUtils.doGet(REST_BASE_URL+REST_ITEM_URL+itemId);
		Item item=JsonUtils.jsonToPojo(doGet, Item.class);
		return item;
	}

	@Override
	public List<CartItem> showCart(HttpServletRequest request) {
		List<CartItem> cart = getCartListFromCookie(request);
		return cart;
	}

	@Override
	public List<CartItem> update(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {
		Item item = getById(itemId);
		List<CartItem> cart= getCartListFromCookie(request);
		for (CartItem cartItem : cart) {
			if(item.toString().equals(cartItem)){
				cartItem.setNum(num);
				break;
			}
		}
		
		Cookie cookie=new Cookie(EGO_CART_COOKIE,JsonUtils.objectToJson(cart));
		cookie.setPath("/");
		response.addCookie(cookie);
		return cart;
	}

	@Override
	public List<CartItem> delete(Long itemId, HttpServletRequest request, HttpServletResponse response) {
		Item item = getById(itemId);
		List<CartItem> cart= getCartListFromCookie(request);
		for (CartItem cartItem : cart) {
			if(item.toString().equals(cartItem)){
				cart.remove(cartItem);
				break;
			}
		}
		Cookie cookie=new Cookie(EGO_CART_COOKIE,JsonUtils.objectToJson(cart));
		cookie.setPath("/");
		response.addCookie(cookie);
		return cart;
	}

}
