package cn.hncu.admin.admin.cotroller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.hncu.admin.admin.adminservice.IAdminService;
import cn.hncu.admin.admin.domain.Admin;
import cn.hncu.utils.CommonUtils;

@RequestMapping(value="/admin")
@Controller
public class AdminController {
	
	@Resource(name="adminService")
	private IAdminService adminService;
	
	/**
	 * 登录功能
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/login")
	public String login(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 封装表单数据到Admin
		 */
		Admin form = CommonUtils.toBean(req.getParameterMap(), Admin.class);
		Admin admin = adminService.login(form);
		if(admin == null) {
			req.setAttribute("msg", "用户名或密码错误！");
			return "login_admin_admin";
		}
		req.getSession().setAttribute("admin", admin);
		return "index_admin_admin";
	}
		
}
