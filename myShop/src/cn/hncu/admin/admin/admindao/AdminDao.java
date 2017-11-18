package cn.hncu.admin.admin.admindao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.springframework.stereotype.Repository;

import cn.hncu.admin.admin.domain.Admin;
import cn.hncu.utils.TxQueryRunner;

@Repository(value="adminDao")
public class AdminDao implements IAdminDao{
	private QueryRunner qr = new TxQueryRunner();
	
	/**
	 * 通过管理员登录名和登录密码查询
	 * @param adminname
	 * @param adminpwd
	 * @return
	 * @throws SQLException
	 */
	public Admin find(String adminname, String adminpwd) throws SQLException {
		String sql = "select * from t_admin where adminname=? and adminpwd=?";
		return qr.query(sql, new BeanHandler<Admin>(Admin.class), adminname, adminpwd);
	}
		

}
