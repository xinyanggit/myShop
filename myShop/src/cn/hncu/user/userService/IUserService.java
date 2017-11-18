package cn.hncu.user.userService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hncu.user.domain.User;

public interface IUserService {


	public abstract User login(User user);


	public abstract boolean ajaxValidateLoginname(String loginname);

	public abstract boolean ajaxValidateEmail(String email);

	public abstract void activatioin(String code) throws Exception;

	public abstract void updatePassword(String uid, String newpass,
			String loginpass) throws Exception;

	public abstract void regist(User user);
	
	
}
