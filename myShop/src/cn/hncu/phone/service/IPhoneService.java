package cn.hncu.phone.service;

import cn.hncu.pager.PageBean;
import cn.hncu.phone.domain.Phone;

public interface IPhoneService {

	public abstract void delete(String pid);

	public abstract void edit(Phone phone);

	public abstract Phone findByPid(String pid);

	public abstract int findPhoneCountByCategory(String cid);

	public abstract PageBean<Phone> findByCategory(String cid, int pc);

	public abstract PageBean<Phone> findByPname(String bname, int pc);

	public abstract PageBean<Phone> findByPversion(String pversion, int pc);

	public abstract PageBean<Phone> findByPcompany(String pcompany, int pc);

	public abstract PageBean<Phone> findByCombination(Phone criteria, int pc);

	public abstract void add(Phone phone);

	public abstract PageBean<Phone> findByNetType(String netType, int pc);


}
