package cn.hncu.order.orderdao;

import java.sql.SQLException;

import cn.hncu.order.domain.Order;
import cn.hncu.pager.PageBean;

public interface IOrderDao {

	void updateStatus(String oid, int status) throws SQLException ;

	int findStatus(String oid) throws SQLException ;

	void add(Order order) throws SQLException ;

	Order load(String oid) throws SQLException ;

	PageBean<Order> findByUser(String uid, int pc) throws SQLException ;

	PageBean<Order> findAll(int pc) throws SQLException ;

	PageBean<Order> findByStatus(int status, int pc) throws SQLException ;

}
