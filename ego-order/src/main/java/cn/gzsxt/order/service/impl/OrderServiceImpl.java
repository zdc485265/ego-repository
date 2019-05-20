package cn.gzsxt.order.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;

import cn.gzsxt.common.pojo.IDUtils;
import cn.gzsxt.dubbo.api.order.pojo.OrderDetail;
import cn.gzsxt.dubbo.api.order.service.OrderServiceApi;
import cn.gzsxt.manager.mapper.OrderItemMapper;
import cn.gzsxt.manager.mapper.OrderMapper;
import cn.gzsxt.manager.mapper.OrderShippingMapper;
import cn.gzsxt.manager.pojo.OrderItem;
import cn.gzsxt.manager.pojo.OrderShipping;

@Service
public class OrderServiceImpl implements OrderServiceApi{
	
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderItemMapper orderItemMapper;
	@Autowired
	private OrderShippingMapper orderShippingMapper;

	@Override
	public String save(OrderDetail order) {
		long orderId = IDUtils.genItemId();
		try {
			order.setOrderId(orderId+"");
			order.setStatus(1);
			order.setCreateTime(new Date());
			order.setUpdateTime(order.getCreateTime());
			System.out.println("order:1");
			orderMapper.insertSelective(order);
			System.out.println("order:2");
			List<OrderItem> orderItems = order.getOrderItems();
			for (OrderItem orderItem : orderItems) {
				orderItem.setId(IDUtils.genItemId()+"");
				orderItem.setOrderId(orderId+"");
				System.out.println("order:3");
				orderItemMapper.insertSelective(orderItem);
			}
			System.out.println("order:4");
			OrderShipping orderShipping = order.getOrderShipping();
			orderShipping.setOrderId(orderId+"");
			System.out.println("order:5");
			orderShippingMapper.insertSelective(orderShipping);
			return orderId+"";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
