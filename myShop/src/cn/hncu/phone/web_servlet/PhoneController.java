package cn.hncu.phone.web_servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.hncu.pager.PageBean;
import cn.hncu.phone.domain.Phone;
import cn.hncu.phone.service.IPhoneService;
import cn.hncu.utils.CommonUtils;

@Controller
public class PhoneController {
	/**
	 * controller层充当控制层，也就是servlet层
	 */
		@Resource(name="phoneService")
		private IPhoneService phoneService ;
		
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
					pc = Integer.parseInt(param); //若出现abc ，则会出现异常，没有异常显示， 默认为1
				}catch(RuntimeException e) {}
			}
			return pc;
		}
		
		/**
		 * 截取url，页面中的分页导航中需要使用它做为超链接的目标！
		 * @param req
		 * @return
		 * @throws UnsupportedEncodingException 
		 */
		/*
		 * http://localhost:8080/goods/BookServlet?methed=findByCategory&cid=xxx&pc=3
		 * /goods/BookServlet + methed=findByCategory&cid=xxx&pc=3
		 */
		private String getUrl(HttpServletRequest req) throws UnsupportedEncodingException {
			/**
			 * req.getQueryString() 截取的是参数？之后的内容，前面不带3
			 */
			String url = req.getRequestURI() ;
			String cid=	req.getParameter("cid"); //点击分类出现list页面
			String netType=	req.getParameter("netType"); //网络类型出现
			String pcompany=	req.getParameter("pcompany"); //公司
			String pname=	req.getParameter("pname"); //手机名称
			
			if(cid!=null){
				 req.getSession().setAttribute("cid", cid);
				url = url+"?cid="+cid;	
				if(netType!=null){
					req.getSession().setAttribute("netType", "");
				}
				if(pcompany!=null){
					req.getSession().setAttribute("pcompany", "");
				}
				if(pname!=null){
					req.getSession().setAttribute("pname", "");
				}
			}
			if(pname!=null){
				req.getSession().setAttribute("pname", pname);
				url = url+"?pname="+pname;
				if(netType!=null){
					req.getSession().setAttribute("netType", "");
				}
				if(pcompany!=null){
					req.getSession().setAttribute("pcompany", "");
				}
				if(cid!=null){
					req.getSession().setAttribute("cid", "");
				}
				
			}
			if(netType!=null){
				url = url+"?netType="+netType;
				if(pname!=null){
					req.getSession().setAttribute("pname", "");
				}
				if(pcompany!=null){
					req.getSession().setAttribute("pcompany", "");
				}
				if(cid!=null){
					req.getSession().setAttribute("cid", "");
				}
			}
			if(pcompany!=null){
				url = url+"?pcompany="+pcompany;
				if(pname!=null){
					req.getSession().setAttribute("pname", "");
				}
				if(netType!=null){
					req.getSession().setAttribute("netType", "");
				}
				if(cid!=null){
					req.getSession().setAttribute("cid", "");
				}
			}
		
			/*
			 * 如果url中存在pc参数，截取掉，如果不存在那就不用截取。
			 */
			return url;
		}
		
		/**
		 * 按bid查询
		 * @param req
		 * @param resp
		 * @return
		 * @throws ServletException
		 * @throws IOException
		 */
		@RequestMapping(value="/load")
		public String load(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			String pid = req.getParameter("pid");//获取链接的参数bid
			Phone phone = phoneService.findByPid(pid);//通过bid得到book对象
			req.setAttribute("phone", phone);//保存到req中
			return "desc";//转发到desc.jsp
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
			/*
			 * 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1
			 */
			int pc = getPc(req);
			/*
			 * 2. 得到url：...
			 */
			String url = getUrl(req);
			/*
			 * 3. 获取查询条件，本方法就是cid，即分类的id
			 */
			String cid = req.getParameter("cid");
			/*
			 * 4. 使用pc和cid调用service#findByCategory得到PageBean
			 */
			PageBean<Phone> pb = phoneService.findByCategory(cid, pc);
			/*
			 * 5. 给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
			 */
			pb.setUrl(url);
			req.setAttribute("pb", pb);
			return "list";
		}
		
		/**
		 * 按网络版本查询
		 * @param req
		 * @param resp
		 * @return
		 * @throws ServletException
		 * @throws IOException
		 */
		
		@RequestMapping(value="/findByNetType")
		public String findByNetType(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			/*
			 * 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1
			 */
			
			int pc = getPc(req);
			/*
			 * 2. 得到url：...
			 */
			String url = getUrl(req);
			/*
			 * 3. 获取查询条件，本方法就是cid，即分类的id
			 */
			String netType = req.getParameter("netType");
			req.getSession().setAttribute("netType", netType);
			/*
			 * 4. 使用pc和cid调用service#findByCategory得到PageBean
			 */
			System.out.println(netType);
			PageBean<Phone> pb = phoneService.findByNetType(netType, pc);
			/*
			 * 5. 给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
			 */
			pb.setUrl(url);
			req.setAttribute("pb", pb);
			return "list";
		}
		
		@RequestMapping(value="/findByPcompany")
		public String findByPcompany(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			/*
			 * 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1
			 */
			req.setCharacterEncoding("utf-8");
			resp.setContentType("text/html;charset=utf-8");
			int pc = getPc(req);
			/*
			 * 2. 得到url：...
			 */
			String url = getUrl(req);
			
			/*
			 * 3. 获取查询条件，本方法就是cid，即分类的id
			 */
			System.out.println("url" +url);
			String pcompany = req.getParameter("pcompany");
			/*
			 * 4. 使用pc和cid调用service#findByCategory得到PageBean
			 */
			System.out.println(pcompany);
			PageBean<Phone> pb = phoneService.findByPcompany(pcompany, pc);
			/*
			 * 5. 给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
			 */
			pb.setUrl(url);
			req.setAttribute("pb", pb);
			return "list";
		}
		
	
			
		/**
		 * 按名查
		 * @param req
		 * @param resp
		 * @return
		 * @throws ServletException
		 * @throws IOException
		 */
		
		@RequestMapping(value="/findByPname")
		public String findByPname(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			/*
			 * 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1
			 */
			req.setCharacterEncoding("utf-8");
			resp.setContentType("text/html;charset=utf-8");
			int pc = getPc(req);
			/*
			 * 2. 得到url：...
			 */
			String url = getUrl(req);
			/*
			 * 3. 获取查询条件，本方法就是cid，即分类的id
			 */
			String bname= (String) req.getSession().getAttribute("pname");
			/*
			 * 4. 使用pc和cid调用service#findByCategory得到PageBean
			 */
			System.out.println("pname"+bname);
			PageBean<Phone> pb = phoneService.findByPname(bname, pc) ;
			/*
			 * 5. 给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
			 */
			pb.setUrl(url);
			req.setAttribute("pb", pb);
			return "list";
		}
		
		/**
		 * 多条件组合查询
		 * @param req
		 * @param resp
		 * @return
		 * @throws ServletException
		 * @throws IOException
		 */
		@RequestMapping(value="/findByCombination")
		public String findByCombination(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			/*
			 * 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1
			 */
			int pc = getPc(req);
			/*
			 * 2. 得到url：...
			 */
			String url = getUrl(req);
			/*
			 * 3. 获取查询条件，本方法就是cid，即分类的id
			 */
			Phone phone = CommonUtils.toBean(req.getParameterMap(), Phone.class);
			/*
			 * 4. 使用pc和cid调用service#findByCategory得到PageBean
			 */
			PageBean<Phone> pb = phoneService.findByCombination(phone, pc);
			/*
			 * 5. 给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
			 */
			pb.setUrl(url);
			
			req.setAttribute("pb", pb);
			return "list";
		}
		
}
