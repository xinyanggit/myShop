package cn.hncu.admin.admin.adminservice;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.hncu.admin.admin.admindao.IAdminDao;
import cn.hncu.admin.admin.domain.Admin;

@Service(value="adminService")
public class AdminService implements IAdminService{
	
	@Resource(name="adminDao")
	private IAdminDao adminDao;

	@Override
	public Admin login(Admin admin) {
		try {
			return adminDao.find(admin.getAdminname(), admin.getAdminpwd());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
