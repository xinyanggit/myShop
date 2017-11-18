package cn.hncu.admin.admin.admindao;

import java.sql.SQLException;

import cn.hncu.admin.admin.domain.Admin;

public interface IAdminDao {

	Admin find(String adminname, String adminpwd) throws SQLException;

}
