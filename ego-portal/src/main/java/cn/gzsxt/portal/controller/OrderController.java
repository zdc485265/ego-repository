package cn.gzsxt.portal.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.gzsxt.dubbo.api.order.pojo.OrderDetail;
import cn.gzsxt.dubbo.api.order.service.OrderServiceApi;
import cn.gzsxt.portal.pojo.CartItem;
import cn.gzsxt.portal.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Reference(timeout=5000)  //默认超时时间1s
	private OrderServiceApi orderServiceApi;

	
	@RequestMapping("/order-cart")
	public String  showOrder(HttpServletRequest request){
		List<CartItem> list = orderService.showOrder(request);
		request.setAttribute("cartList", list);
		return "order-cart";
	}
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public String save(OrderDetail order,ModelMap map){
		String orderId = orderServiceApi.save(order);
		if(orderId!=null&&!"".equals(orderId)){
			map.addAttribute("orderId",orderId);
			map.addAttribute("payment", order.getPayment());
			Calendar calendar=Calendar.getInstance();
			SimpleDateFormat sdf=new SimpleDateFormat("yyy/MM/dd/E");
			calendar.add(calendar.DATE,3);
			System.out.println(sdf.format(calendar.getTime()));
			map.addAttribute("date",sdf.format(calendar.getTime()));
			return "success";
		}
		map.addAttribute("message","服务器繁忙");
		return "error/exception";
	}
}
