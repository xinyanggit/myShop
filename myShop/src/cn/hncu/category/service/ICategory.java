package cn.hncu.category.service;

import java.util.List;

import cn.hncu.category.domain.Category;

public interface ICategory {


	public abstract List<Category> findChildren(String pid);

	public abstract List<Category> findParents();

	public abstract List<Category> findAll();

	public abstract void add(Category category);

	public abstract Category load(String cid);

	public abstract void edit(Category category);

	public abstract void delete(String cid);

	public abstract int findChildrenCountByParent(String pid);

}
