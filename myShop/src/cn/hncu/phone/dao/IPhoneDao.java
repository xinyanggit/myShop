package cn.hncu.phone.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import cn.hncu.pager.PageBean;
import cn.hncu.phone.domain.Phone;
import cn.hncu.utils.TxQueryRunner;

public interface IPhoneDao {

	public abstract void add(Phone phone) throws SQLException;

	public abstract PageBean<Phone> findByCombination(Phone criteria, int pc)
			throws SQLException;

	public abstract PageBean<Phone> findByPcompany(String pcompany, int pc)
			throws SQLException;

	public abstract PageBean<Phone> findByPversion(String pversion, int pc)
			throws SQLException;

	public abstract PageBean<Phone> findByPname(String bname, int pc)
			throws SQLException;

	public abstract PageBean<Phone> findByCategory(String cid, int pc)
			throws SQLException;

	public abstract int findPhoneCountByCategory(String cid)
			throws SQLException;

	public abstract Phone findByPid(String pid) throws SQLException;

	public abstract void edit(Phone phone) throws SQLException;

	public abstract void delete(String pid) throws SQLException;


	public abstract PageBean<Phone> findByNetType(String netType, int pc)
			throws SQLException;

}
