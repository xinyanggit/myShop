package cn.hncu.category.categoryController;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.hncu.category.domain.Category;
import cn.hncu.category.service.ICategory;

@Controller
public class CategoryController {
	@Resource(name="categoryservice")
	private ICategory categoryservice;
	
	@RequestMapping(value="/findAll")
	public String findAll(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 通过service得到所有的分类
		 * 2. 保存到request中，转发到left.jsp
		 */
		List<Category> parents = categoryservice.findAll();
		req.setAttribute("parents", parents);
		return "left";
	}
		
}
