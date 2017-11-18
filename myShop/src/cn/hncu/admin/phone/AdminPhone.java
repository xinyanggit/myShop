package cn.hncu.admin.phone;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.hncu.category.domain.Category;
import cn.hncu.category.service.ICategory;
import cn.hncu.pager.PageBean;
import cn.hncu.phone.domain.Phone;
import cn.hncu.phone.service.IPhoneService;
import cn.hncu.utils.CommonUtils;

@RequestMapping(value="/admin")
@Controller
public class AdminPhone extends HttpServlet{
	
	@Resource(name="phoneService")
	private IPhoneService phoneService;
	@Resource(name="categoryservice")
	private ICategory categoryService;
	
	/**
	 * 删除phone
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/delete")
	public String delete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String pid = req.getParameter("pid");
		
		
//		 * 删除图片
		 
		Phone book = phoneService.findByPid(pid);
//		String savepath = this.getServletContext().getRealPath("/book_img");//获取真实的路径
//		new File(savepath, book.getImage_w()).delete();//删除文件
//		new File(savepath, book.getImage_b()).delete();//删除文件
		
		phoneService.delete(pid);//删除数据库的记录
		
		req.setAttribute("msg", "删除商品成功！");
		return "msg_admin_category";
	}
	
	/**
	 * 修改商品
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/edit")
	public String edit(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 把表单数据封装到Book对象中
		 * 2. 封装cid到Category中
		 * 3. 把Category赋给Book
		 * 4. 调用service完成工作
		 * 5. 保存成功信息，转发到msg.jsp*/
		 
		Map map = req.getParameterMap();
		Phone phone = CommonUtils.toBean(map, Phone.class);
		Category category = CommonUtils.toBean(map, Category.class);
		phone.setCategory(category);
		
		phoneService.edit(phone);
		req.setAttribute("msg", "修改手机信息成功！");
		return "msg_admin_category";
	}
	
	/**
	 * 加载商品
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/load")
	public String load(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
//		 * 1. 获取bid，得到Book对象，保存之
		 
		String pid = req.getParameter("pid");
		Phone  phone =  phoneService.findByPid(pid);
		req.setAttribute("phone", phone);
		
		
//		 * 2. 获取所有一级分类，保存之，所有的一级显示
		 
		req.setAttribute("parents", categoryService.findParents()); 
		
//		 * 3. 当前商品下的父节点（一级菜单）的id
		 
		String parentid = phone.getCategory().getParent().getCid(); //一级菜单的cid
		req.setAttribute("children", categoryService.findChildren(parentid));
		//获得一级目录下的所有二级菜单列表
		
//		 * 4. 转发到desc.jsp显示
		 
		return "desc_admin_phone";
	}
	
	/**
	 * 添加商品：第一步
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/addPre")
	public String addPre(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		/* * 1. 获取所有一级分类，保存之
		 * 2. 转发到add.jsp，该页面会在下拉列表中显示所有一级分类*/
		 
		List<Category> parents = categoryService.findParents();
		req.setAttribute("parents", parents);
		return "add_admin_phone";
	}
	@RequestMapping(value="/ajaxFindChildren")
	public String ajaxFindChildren(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
//		 * 1. 获取pid
//		 * 2. 通过pid查询出所有2级分类
//		 * 3. 把List<Category>转换成json，输出给客户端
//		 
		String pid = req.getParameter("parentid");
		List<Category> children = categoryService.findChildren(pid);
		String json = toJson(children);
		resp.getWriter().print(json);
		return null;
	}
	
	// {"cid":"fdsafdsa", "cname":"fdsafdas"}
	private String toJson(Category category) {
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"cid\"").append(":").append("\"").append(category.getCid()).append("\"");
		sb.append(",");
		sb.append("\"cname\"").append(":").append("\"").append(category.getCname()).append("\"");
		sb.append("}");
		return sb.toString();
	}
	
	// [{"cid":"fdsafdsa", "cname":"fdsafdas"}, {"cid":"fdsafdsa", "cname":"fdsafdas"}]
	private String toJson(List<Category> categoryList) {
		StringBuilder sb = new StringBuilder("[");
		for(int i = 0; i < categoryList.size(); i++) {
			sb.append(toJson(categoryList.get(i)));
			if(i < categoryList.size() - 1) {
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
	/**
	 * 显示所有分类
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/findCategoryAll")
	public String findCategoryAll(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		/* * 1. 通过service得到所有的分类
		 * 2. 保存到request中，转发到left.jsp*/
		 
		List<Category> parents = categoryService.findAll();
		req.setAttribute("parents", parents);
		return "left_admin_phone";
	}
	
	
	
	
	
	
	
	/**
	 * 获取当前页码
	 * @param req
	 * @return
	 */
	private int getPc(HttpServletRequest req) {
		int pc = 1;
		String param = req.getParameter("pc");
		if(param != null && !param.trim().isEmpty()) {
			try {
				pc = Integer.parseInt(param);
			} catch(RuntimeException e) {}
		}
		return pc;
	}
	
	/**
	 * 截取url，页面中的分页导航中需要使用它做为超链接的目标！
	 * @param req
	 * @return
	 */
	
	 
	private String getUrl(HttpServletRequest req) {
		String url = req.getRequestURI() + "?" + req.getQueryString();
		
//		 * 如果url中存在pc参数，截取掉，如果不存在那就不用截取。
		 
		int index = url.lastIndexOf("&pc=");
		if(index != -1) {
			url = url.substring(0, index);
		}
		return url;
	}
	
	/**
	 * 按分类查
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/findByCategory")
	public String findByCategory(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
//		 * 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1
		 
		int pc = getPc(req);
		
//		 * 2. 得到url：...
		 
		String url = getUrl(req);
		
//		 * 3. 获取查询条件，本方法就是cid，即分类的id
		 
		String cid = req.getParameter("cid");
		
//		 * 4. 使用pc和cid调用service#findByCategory得到PageBean
		 
		PageBean<Phone> pb = phoneService.findByCategory(cid, pc);
		
//		 * 5. 给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
		 
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "list_admin_phone";
	}
	
	/*public String findByCombination(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
//		 * 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1
		 
		int pc = getPc(req);
		
//		 * 2. 得到url：...
		 
		String url = getUrl(req);
		
//		 * 3. 获取查询条件，本方法就是cid，即分类的id
		 
		Book criteria = CommonUtils.toBean(req.getParameterMap(), Book.class);
		
//		 * 4. 使用pc和cid调用service#findByCategory得到PageBean
		 
		PageBean<Book> pb = bookService.findByCombination(criteria, pc);
		
//		 * 5. 给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
		 
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "list_admin_phone";
	}*/

}
