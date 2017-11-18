package cn.hncu.cart.cartserivce;

import java.util.List;

import cn.hncu.cart.domain.CartItem;

public interface ICartItemService {

	List<CartItem> loadCartItems(String cartItemIds);

	CartItem updateQuantity(String cartItemId, int quantity);

	void batchDelete(String cartItemIds);

	void add(CartItem cartItem);

	List<CartItem> myCart(String uid);

}
