package cn.hncu.user.userDao;

import java.sql.SQLException;

import cn.hncu.user.domain.User;


public interface IUserDao {

	public abstract User findByLoginnameAndLoginpass(String loginname, String loginpass)
			throws SQLException;

	public abstract boolean findByUidAndPassword(String uid, String oldPass) throws SQLException;

	public abstract void updatePassword(String uid, String newPass) throws SQLException;

	public abstract User findByCode(String code) throws SQLException;

	public abstract void updateStatus(String uid, boolean b) throws SQLException;

	public abstract boolean ajaxValidateLoginname(String loginname) throws SQLException;

	public abstract boolean ajaxValidateEmail(String email) throws SQLException;

	public abstract void add(User user) throws SQLException;

}
