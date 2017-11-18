package cn.hncu.category.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.hncu.category.dao.ICategoryDao;
import cn.hncu.category.domain.Category;

@Service(value="categoryservice")
public class CategoryService implements ICategory {
	@Resource(name="categorydao")
	private ICategoryDao categorydao;

	/**
	 * 查询指定父分类下子分类的个数
	 * @param pid
	 * @return
	 */
	@Override
	public int findChildrenCountByParent(String pid) {
		try {
			return categorydao.findChildrenCountByParent(pid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 删除分类
	 * @param cid
	 */
	@Override
	public void delete(String cid) {
		try {
			categorydao.delete(cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 修改分类
	 * @param category
	 */
	@Override
	public void edit(Category category) {
		try {
			categorydao.edit(category);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 加载分类
	 * @param cid
	 * @return
	 */
	@Override
	public Category load(String cid) {
		try {
			return categorydao.load(cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 添加分类
	 * @param category
	 */
	@Override
	public void add(Category category) {
		try {
			categorydao.add(category);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 查询所有分类
	 * @return
	 */
	@Override
	public List<Category> findAll() {
		try {
			return categorydao.findAll();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 获取所有父分类，不带子分类
	 * @return
	 */
	@Override
	public List<Category> findParents() {
		try {
			return categorydao.findParents();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 查询指定父分类下的所有子分类
	 * @param pid
	 * @return
	 */
	@Override
	public List<Category> findChildren(String pid) {
		try {
			return categorydao.findByParent(pid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	
}
