package cn.hncu.order.orderservice;

import cn.hncu.order.domain.Order;
import cn.hncu.pager.PageBean;

public interface IOrderService {

	public abstract PageBean<Order> findAll(int pc);

	public abstract PageBean<Order> findByStatus(int status, int pc);

	public abstract PageBean<Order> myOrders(String uid, int pc);

	public abstract void createOrder(Order order);

	public abstract Order load(String oid);

	public abstract int findStatus(String oid);

	public abstract void updateStatus(String oid, int status);

}
