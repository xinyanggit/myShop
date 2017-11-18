package cn.hncu.phone.service;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.hncu.pager.PageBean;
import cn.hncu.phone.dao.IPhoneDao;
import cn.hncu.phone.domain.Phone;

@Service(value="phoneService")
public class PhoneService implements IPhoneService {
	@Resource(name="phoneDao")
	private IPhoneDao phoneDao;
	
	@Override
	public  void add(Phone phone){
		try {
			phoneDao.add(phone);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public  PageBean<Phone> findByCombination(Phone criteria, int pc){
		try {
		return 	phoneDao.findByCombination(criteria, pc);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public  PageBean<Phone> findByPcompany(String pcompany, int pc){
		try {
			return phoneDao.findByPcompany(pcompany, pc);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public  PageBean<Phone> findByPversion(String pversion, int pc){
		try {
			return phoneDao.findByPversion(pversion, pc);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public  PageBean<Phone> findByPname(String bname, int pc){
		try {
			return phoneDao.findByPname(bname, pc);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public  PageBean<Phone> findByCategory(String cid, int pc){
			try {
				return phoneDao.findByCategory(cid, pc);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		
	}

	@Override
	public  int findPhoneCountByCategory(String cid){
		try {
			return phoneDao.findPhoneCountByCategory(cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public  Phone findByPid(String pid) {
		try {
			return phoneDao.findByPid(pid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	@Override
	public  void edit(Phone phone){
		try {
			phoneDao.edit(phone);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	@Override
	public  void delete(String pid)
	{
		try {
			phoneDao.delete(pid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public PageBean<Phone> findByNetType(String netType, int pc) {
		
		try {
			return phoneDao.findByNetType(netType,pc);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	
}
