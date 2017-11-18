package cn.hncu.cart.cartController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.hncu.cart.cartserivce.ICartItemService;
import cn.hncu.cart.domain.CartItem;
import cn.hncu.filter.AuthPassport;
import cn.hncu.phone.domain.Phone;
import cn.hncu.user.domain.User;
import cn.hncu.utils.CommonUtils;
import cn.hncu.utils.Log;


@Controller
public class CartItemServlet {
	Log log =Log.getLogger();
	
	@Resource(name="cartItemService")
	private ICartItemService cartItemService ;
	
	/**
	 * 加载多个CartItem
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	
	@AuthPassport
	@RequestMapping(value="/loadCartItems")
	public String loadCartItems(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 获取cartItemIds参数
		 */
		String cartItemIds = req.getParameter("cartItemIds");
		double total = Double.parseDouble(req.getParameter("total"));
		/*
		 * 2. 通过service得到List<CartItem>
		 */
		List<CartItem> cartItemList = cartItemService.loadCartItems(cartItemIds);
		/*
		 * 3. 保存，然后转发到/cart/showitem.jsp
		 */
		log.logger.info("加载详单"+cartItemList.toString());
		req.setAttribute("cartItemList", cartItemList);
		req.setAttribute("total", total);
		req.setAttribute("cartItemIds", cartItemIds);
//		return "f:/jsps/cart/showitem.jsp";
		return "showitem";
	}
	@AuthPassport
	@RequestMapping(value="/updateQuantity")
	public String updateQuantity(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String cartItemId = req.getParameter("cartItemId");
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		CartItem cartItem = cartItemService.updateQuantity(cartItemId, quantity);
		
		// 给客户端返回一个json对象
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"quantity\"").append(":").append(cartItem.getQuantity());
		sb.append(",");
		sb.append("\"subtotal\"").append(":").append(cartItem.getSubtotal());
		sb.append("}");
		log.logger.info(sb.toString());
		resp.getWriter().print(sb);
		return null;
	}
	
	/**
	 * 批量删除功能
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/batchDelete")
	public String batchDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 获取cartItemIds参数
		 * 2. 调用service方法完成工作
		 * 3. 返回到list.jsp
		 */
		String cartItemIds = req.getParameter("cartItemIds");
		cartItemService.batchDelete(cartItemIds);
		log.logger.info( myCart(req, resp));
		return myCart(req, resp);
	}
	
	/**
	 * 添加购物车条目
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	
	@RequestMapping(value="/add")
	public String add(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 封装表单数据到CartItem(bid, quantity)
		 */
		Map map = req.getParameterMap();
		CartItem cartItem = CommonUtils.toBean(map, CartItem.class);
		Phone phone = CommonUtils.toBean(map, Phone.class);
		User user = (User)req.getSession().getAttribute("sessionUser");
		if(user==null){
			return "error_auth";
		}
		cartItem.setPhone(phone);
		cartItem.setUser(user);
		/*
		 * 2. 调用service完成添加
		 */
		cartItemService.add(cartItem);
		log.logger.info("add"+myCart(req, resp));
		/*
		 * 3. 查询出当前用户的所有条目，转发到list.jsp显示
		 */
		return myCart(req, resp);
	}
	
	/**
	 * 我的购物车
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@AuthPassport
	@RequestMapping(value="/myCart")
	public String myCart(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 得到uid
		 */
		User user = (User)req.getSession().getAttribute("sessionUser");
		if(user==null){
			return "error_auth";
		}
		String uid = user.getUid();
		/*
		 * 2. 通过service得到当前用户的所有购物车条目
		 */
		List<CartItem> cartItemLIst = cartItemService.myCart(uid);
		/*
		 * 3. 保存起来，转发到/cart/list.jsp
		 */
		log.logger.info("myCart"+cartItemLIst);
		req.setAttribute("cartItemList", cartItemLIst);
//		return "f:/jsps/cart/list.jsp";
		return "list_cart";
	}
}
