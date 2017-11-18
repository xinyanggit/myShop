package cn.hncu.cart.cartdao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.springframework.stereotype.Repository;

import cn.hncu.cart.domain.CartItem;
import cn.hncu.phone.domain.Phone;
import cn.hncu.user.domain.User;
import cn.hncu.utils.CommonUtils;
import cn.hncu.utils.TxQueryRunner;

@Repository(value="cartItemdao")
public class CartItemDao implements ICartItemDao {
	private QueryRunner qr = new TxQueryRunner();
	
	/*
	 * 用来生成where子句
	 */
	private String toWhereSql(int len) {
		StringBuilder sb = new StringBuilder("cartItemId in(");
		for(int i = 0; i < len; i++) {
			sb.append("?");
			if(i < len - 1) {
				sb.append(",");
			}
		}
		sb.append(")");
		return sb.toString();
	}
	
	/**
	 * 加载多个CartItem
	 * @param cartItemIds
	 * @return
	 * @throws SQLException 
	 */
	public List<CartItem> loadCartItems(String cartItemIds) throws SQLException {
		/*
		 * 1.把cartItemIds转换成数组
		 */
		Object[] cartItemIdArray = cartItemIds.split(",");
		/*
		 * 2. 生成wehre子句
		 */
		String whereSql = toWhereSql(cartItemIdArray.length);
		/*
		 * 3. 生成sql语句
		 */
		String sql = "select * from t_cartitem c, t_phone b where c.pid=b.pid and " + whereSql;
		/*
		 * 4. 执行sql，返回List<CartItem>
		 */
		System.out.println(sql);
		System.out.println(cartItemIdArray.toString());
		return toCartItemList(qr.query(sql, new MapListHandler(), cartItemIdArray));
	}
	
	/**
	 * 按id查询
	 * @param cartItemId
	 * @return
	 * @throws SQLException 
	 */
	public CartItem findByCartItemId(String cartItemId) throws SQLException {
		String sql = "select * from t_cartItem c, t_phone b where c.pid=b.pid and c.cartItemId=?";
		Map<String,Object> map = qr.query(sql, new MapHandler(), cartItemId);
		return toCartItem(map);
	}
	
	/**
	 * 批量删除
	 * @param cartItemIds
	 * @throws SQLException
	 */
	public void batchDelete(String cartItemIds) throws SQLException {
		/*
		 * 需要先把cartItemIds转换成数组
		 * 1. 把cartItemIds转换成一个where子句
		 * 2. 与delete from 连接在一起，然后执行之
		 */
		Object[] cartItemIdArray = cartItemIds.split(",");
		String whereSql = toWhereSql(cartItemIdArray.length);
		String sql = "delete from t_cartitem where " + whereSql;
		qr.update(sql, cartItemIdArray);//其中cartItemIdArray必须是Object类型的数组！
	}
	
	/**
	 * 查询某个用户的某商品的购物车条目是否存在
	 * @throws SQLException 
	 */
	public CartItem findByUidAndBid(String uid, String bid) throws SQLException {
		String sql = "select * from t_cartitem where uid=? and pid=?";
		Map<String,Object> map = qr.query(sql, new MapHandler(), uid, bid);
		CartItem cartItem = toCartItem(map);
		return cartItem;
	}
	
	/**
	 * 修改指定条目的数量
	 * @param cartItemId
	 * @param quantity
	 * @throws SQLException 
	 */
	public void updateQuantity(String cartItemId, int quantity) throws SQLException {
		String sql = "update t_cartitem set quantity=? where cartItemId=?";
		qr.update(sql, quantity, cartItemId);
	}
	
	/**
	 * 添加条目
	 * @param cartItem
	 * @throws SQLException 
	 */
	public void addCartItem(CartItem cartItem) throws SQLException {
		String sql = "insert into t_cartitem(cartItemId, quantity, pid, uid)" +
				" values(?,?,?,?)";
		
		Object[] params = {cartItem.getCartItemId(), cartItem.getQuantity(),
				cartItem.getPhone().getPid(), cartItem.getUser().getUid()};
		System.out.println(sql);
		System.out.println(params.toString());
		qr.update(sql, params);
	}
	
	/*
	 * 把一个Map映射成一个Cartitem
	 */
	private CartItem toCartItem(Map<String,Object> map) {
		if(map == null || map.size() == 0) return null;
		CartItem cartItem = CommonUtils.toBean(map, CartItem.class);
		Phone phone = CommonUtils.toBean(map, Phone.class);
		User user = CommonUtils.toBean(map, User.class);
		cartItem.setPhone(phone);
		cartItem.setUser(user);
		return cartItem;
	}
	
	/*
	 * 把多个Map(List<Map>)映射成多个CartItem(List<CartItem>)
	 */
	private List<CartItem> toCartItemList(List<Map<String,Object>> mapList) {
		List<CartItem> cartItemList = new ArrayList<CartItem>();
		for(Map<String,Object> map : mapList) {
			CartItem cartItem = toCartItem(map); //里面包含user 和phone 两个对象
			cartItemList.add(cartItem);
		}
		return cartItemList;
	}
	
	/**
	 * 通过用户查询购物车条目
	 * @param uid
	 * @return
	 * @throws SQLException 
	 */
	public List<CartItem> findByUser(String uid) throws SQLException {
		String sql = "select * from t_cartitem c, t_phone b where c.pid=b.pid and uid=? order by c.orderBy";
		List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler(), uid);
		//maplist  查询出来 list 集合，里面的元素全是map  每一条map都是一个cartItem 现在需要转换成cartitem list 所有需要将map进行遍历
		return toCartItemList(mapList);
	}
}
