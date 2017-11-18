package cn.hncu.category.dao;

import java.sql.SQLException;
import java.util.List;

import cn.hncu.category.domain.Category;

public interface ICategoryDao {

	public abstract void delete(String cid) throws SQLException;

	public abstract int findChildrenCountByParent(String pid)
			throws SQLException;

	public abstract void edit(Category category) throws SQLException;

	public abstract Category load(String cid) throws SQLException;

	public abstract List<Category> findParents() throws SQLException;

	public abstract void add(Category category) throws SQLException;

	public abstract List<Category> findByParent(String pid) throws SQLException;

	public abstract List<Category> findAll() throws SQLException;

}
