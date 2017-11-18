package cn.hncu.phone.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import cn.hncu.category.domain.Category;
import cn.hncu.pager.Expression;
import cn.hncu.pager.PageBean;
import cn.hncu.pager.PageConstants;
import cn.hncu.phone.domain.Phone;
import cn.hncu.utils.CommonUtils;
import cn.hncu.utils.Log;
import cn.hncu.utils.TxQueryRunner;

@Repository(value="phoneDao")
public class PhoneDao implements IPhoneDao {
	
		private  Log log =Log.getLogger(); 
	private QueryRunner qr = new TxQueryRunner();

		/** 
		 * 根据手机的id  来进行删除
		 * @param pid
		 * @throws SQLException
		 */
		@Override
		public void delete(String pid) throws SQLException {
			String sql = "delete from t_phone where pid=?";
			log.logger.info(sql+"=="+pid);
			qr.update(sql, pid);
		}
		
		@Override
		public void edit(Phone phone) throws SQLException {
			String sql = "update t_phone set pname=?,pcompany=?,price=?,currprice=?," +
					"discount=?,publishTime=?,pversion=?,netType=?,color=?,taocan=?," +
					"cid=? where pid=?";
			
			Object[] params = {phone.getPname(),phone.getPcompany(),phone.getPrice(),phone.getCurrprice(),phone.getDiscount(),
					phone.getPublishTime() , phone.getPversion(), phone.getNetType(), phone.getColor(),phone.getTaocan(),
					phone.getCategory().getCid(),phone.getPid()};
			log.logger.info("编辑手机"+sql + "-->"+params.toString());
			qr.update(sql, params);
		}
		
		
		@Override
		public Phone findByPid(String pid) throws SQLException {
			String sql = "SELECT * FROM t_phone b, t_category c WHERE b.cid=c.cid AND b.pid=?";
			// 一行记录中，包含了很多的phone的属性，还有一个cid属性
			System.out.println(sql);
			Map<String,Object> map = qr.query(sql, new MapHandler(), pid);
			System.out.println(pid);
			// 把Map中除了cid以外的其他属性映射到phone对象中
			Phone phone = CommonUtils.toBean(map, Phone.class);
			// 把Map中cid属性映射到Category中，即这个Category只有cid
			Category category = CommonUtils.toBean(map, Category.class);
			// 两者建立关系
			phone.setCategory(category);
			
			// 把pid获取出来，创建一个Category parnet，把pid赋给它，然后再把parent赋给category
			if( null!= map.get("parentid")) {
				Category parent = new Category();
				parent.setCid((String)map.get("parentid"));
				category.setParent(parent);
			}
			log.logger.info("通过pid查找"+map.toString());
			return phone;
		}
		
		/**
		 * 查询指定分类下商品的个数
		 * @param cid
		 * @return
		 * @throws SQLException
		 */
		@Override
		public int findPhoneCountByCategory(String cid) throws SQLException {
			String sql = "select count(*) from t_phone where cid=?";
			Number cnt = (Number)qr.query(sql, new ScalarHandler(), cid);
			return cnt == null ? 0 : cnt.intValue();
		}
		
		/**
		 * 按分类查询
		 * @param cid
		 * @param pc
		 * @return
		 * @throws SQLException 
		 */
		@Override
		public PageBean<Phone> findByCategory(String cid, int pc) throws SQLException {
			List<Expression> exprList = new ArrayList<Expression>();
			exprList.add(new Expression("cid", "=", cid)); // 变量名 + 符号 +值
			log.logger.info("目录返回pagebea"+findByCriteria(exprList, pc));
			return findByCriteria(exprList, pc);
		}
		
		/**
		 * 按手机名模糊查询
		 * @param bname
		 * @param pc
		 * @return
		 * @throws SQLException
		 */
		@Override
		public PageBean<Phone> findByPname(String bname, int pc) throws SQLException {
			List<Expression> exprList = new ArrayList<Expression>();
			exprList.add(new Expression("pname", "like", "%" + bname + "%"));
			log.logger.info("pname返回pagebea"+findByCriteria(exprList, pc));
			return findByCriteria(exprList, pc);
		}
		
		/**
		 * 按版本信息查询
		 * @param bname
		 * @param pc
		 * @return
		 * @throws SQLException
		 */
		@Override
		public PageBean<Phone> findByPversion(String pversion, int pc) throws SQLException {
			List<Expression> exprList = new ArrayList<Expression>();
			exprList.add(new Expression("pversion", "like", "%" + pversion + "%"));
			log.logger.info("findByPversion返回pagebea"+findByCriteria(exprList, pc));
			return findByCriteria(exprList, pc);
		}
		
		/**
		 * 按公司查询
		 * @param press
		 * @param pc
		 * @return
		 * @throws SQLException
		 */
		@Override
		public PageBean<Phone> findByPcompany(String pcompany, int pc) throws SQLException {
			List<Expression> exprList = new ArrayList<Expression>();
			exprList.add(new Expression("pcompany", "like", "%" + pcompany + "%"));
			log.logger.info("findByPcompany返回pagebean"+findByCriteria(exprList, pc));
			return findByCriteria(exprList, pc);
		}
		
		/**
		 * 多条件组合查询
		 * @param combination
		 * @param pc
		 * @return
		 * @throws SQLException
		 */
		@Override
		public PageBean<Phone> findByCombination(Phone phone, int pc) throws SQLException {
			List<Expression> exprList = new ArrayList<Expression>();
			exprList.add(new Expression("pname", "like", "%" +phone.getPname() + "%"));
			exprList.add(new Expression("pcompany", "like", "%" +phone.getPcompany()+ "%"));
			exprList.add(new Expression("color", "like", "%" + phone.getColor()+ "%"));
			return findByCriteria(exprList, pc);
		}
		
		
		/**
		 * 通用的查询方法
		 * @param exprList
		 * @param pc
		 * @return
		 * @throws SQLException 
		 */
		private PageBean<Phone> findByCriteria(List<Expression> exprList, int pc) throws SQLException {
			/*
			 * 1. 得到ps
			 * 2. 得到tr
			 * 3. 得到beanList
			 * 4. 创建PageBean，返回
			 */
			/*
			 * 1. 得到ps
			 */
			int ps = PageConstants.BOOK_PAGE_SIZE;//每页记录数 设置成常量
			/*
			 * 2. 通过exprList来生成where子句
			 */
			StringBuilder whereSql = new StringBuilder(" where 1=1"); 
			List<Object> params = new ArrayList<Object>();//SQL中有问号，它是对应问号的值
			for(Expression expr : exprList) {
				/*
				 * 添加一个条件上，
				 * 1) 以and开头
				 * 2) 条件的名称
				 * 3) 条件的运算符，可以是=、!=、>、< ... is null，is null没有值
				 * 4) 如果条件不是is null，再追加问号，然后再向params中添加一与问号对应的值
				 */
				whereSql.append(" and ").append(expr.getName())
					.append(" ").append(expr.getOperator()).append(" ");
				// where 1=1 and pid = ?
				if(!expr.getOperator().equals("is null")) {
					whereSql.append("?");
					params.add(expr.getValue());
				}
			}

			/*
			 * 3. 总记录数 
			 */
			String sql = "select count(*) from t_phone" + whereSql;
			System.out.println("查询总记录数=="+sql);
			System.out.println( params.toString());
			Number number = (Number)qr.query(sql, new ScalarHandler(), params.toArray());
//			Number number = (Number)qr.query(sql, new ScalarHandler(), "458795C27E7346A8A5F1B942319297E0");
			int tr = number.intValue();//得到了总记录数
			/*
			 * 4. 得到beanList，即当前页记录
			 */
			sql = "select * from t_phone" + whereSql + " order by orderBy limit ?,?";
			System.out.println("分页："+sql);
			
			params.add((pc-1) * ps);//当前页首行记录的下标
			params.add(ps);//一共查询几行，就是每页记录数
			
			List<Phone> beanList = qr.query(sql, new BeanListHandler<Phone>(Phone.class), 
					params.toArray());
			log.logger.info(beanList.toString()+"=="+params.toString());
			System.out.println(params.toString());
			/*
			 * 5. 创建PageBean，设置参数
			 */
			PageBean<Phone> pb = new PageBean<Phone>();
			/*
			 * 其中PageBean没有url，这个任务由Servlet完成
			 */
			pb.setBeanList(beanList);
			pb.setPc(pc);
			pb.setPs(ps);
			pb.setTr(tr);
			
			return pb;
		}

		
		@Override
		public void add(Phone phone) throws SQLException {
			String sql = "insert into t_phone(pid,pname,pcompany,price,currprice," +
					"discount,publishTime,pversion,netType,color,taocan," +
					"cid,image_w,image_b)" +
					" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			Object[] params = {phone.getPid(),phone.getPname(),phone.getPcompany(),phone.getPrice(),phone.getCurrprice(),
						phone.getDiscount(),phone.getPublishTime(),phone.getPversion(),phone.getNetType(),phone.getColor(),phone.getTaocan(),
								phone.getCategory().getCid(),phone.getImage_w(),phone.getImage_b()};
			System.out.println(params.toString());
			log.logger.info(sql+"=="+params);
			qr.update(sql, params);
		}

		
		@Override
		public PageBean<Phone> findByNetType(String netType, int pc) throws SQLException {
			List<Expression> exprList = new ArrayList<Expression>();
			exprList.add(new Expression("netType", "like", "%" + netType + "%"));
			
			return findByCriteria(exprList, pc);
		}
	}

