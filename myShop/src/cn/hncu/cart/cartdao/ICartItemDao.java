package cn.hncu.cart.cartdao;

import java.sql.SQLException;
import java.util.List;

import cn.hncu.cart.domain.CartItem;

public interface ICartItemDao {

	List<CartItem> loadCartItems(String cartItemIds) throws SQLException;

	void updateQuantity(String cartItemId, int quantity) throws SQLException;

	CartItem findByCartItemId(String cartItemId) throws SQLException;

	void batchDelete(String cartItemIds) throws SQLException;

	void addCartItem(CartItem cartItem) throws SQLException;

	CartItem findByUidAndBid(String uid, String pid) throws SQLException;

	List<CartItem> findByUser(String uid) throws SQLException;

}
