package cn.hncu.phone.domain;

import cn.hncu.category.domain.Category;


public class Phone {

	private String pid;//手机id
	private String pname;//手机名称
	private String pcompany; //公司
	private double price; //价格
	private double currprice; //当前价格
	private String discount; //折扣
	private String publishTime; //上市时间
	private String pversion; //版本  一种手机很多版本
	private String netType; //网络类型
	private String color; //手机颜色
	private String taocan; //套餐  --官方标配
	private Category category;//所属分类
	private String image_w;//大图路径
	private String image_b;//小图路径
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPcompany() {
		return pcompany;
	}
	public void setPcompany(String pcompany) {
		this.pcompany = pcompany;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getCurrprice() {
		return currprice;
	}
	public void setCurrprice(double currprice) {
		this.currprice = currprice;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public String getPversion() {
		return pversion;
	}
	public void setPversion(String pversion) {
		this.pversion = pversion;
	}
	public String getNetType() {
		return netType;
	}
	public void setNetType(String netType) {
		this.netType = netType;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getTaocan() {
		return taocan;
	}
	public void setTaocan(String taocan) {
		this.taocan = taocan;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getImage_w() {
		return image_w;
	}
	public void setImage_w(String image_w) {
		this.image_w = image_w;
	}
	public String getImage_b() {
		return image_b;
	}
	public void setImage_b(String image_b) {
		this.image_b = image_b;
	}
	@Override
	public String toString() {
		return "Phone [pid=" + pid + ", pname=" + pname + ", pcompany="
				+ pcompany + ", price=" + price + ", currprice=" + currprice
				+ ", discount=" + discount + ", publishTime=" + publishTime
				+ ", pversion=" + pversion + ", netType=" + netType
				+ ", color=" + color + ", taocan=" + taocan + ", category="
				+ category + ", image_w=" + image_w + ", image_b=" + image_b
				+ "]";
	}
	
	
	
	
}
