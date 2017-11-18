package cn.hncu.user.domain;

public class User {

	// 对应数据库表
	/*
	 * 所有属性，除了登录和注册时需要，在修改密码的时候也是需要表单校验
	 */
		private String uid;//主键
		private String loginname;//登录名
		private String loginpass;//登录密码
		private String email;//邮箱
		private boolean status;//状态，true表示已激活，或者未激活
		private String activationCode;//激活码，它是唯一值！即每个用户的激活码是不同的！
		private String reloginpass;//确认密码
		private String verifyCode;//验证码
		private String newpass;//新密码
		private String serverHost ;// 主机名称，便于以后使用
		public String getUid() {
			return uid;
		}

		public String getServerHost() {
			return serverHost;
		}

		public void setServerHost(String serverHost) {
			this.serverHost = serverHost;
		}

		public void setUid(String uid) {
			this.uid = uid;
		}

		public String getLoginname() {
			return loginname;
		}

		public void setLoginname(String loginname) {
			this.loginname = loginname;
		}

		public String getLoginpass() {
			return loginpass;
		}

		public void setLoginpass(String loginpass) {
			this.loginpass = loginpass;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public boolean isStatus() {
			return status;
		}

		public void setStatus(boolean status) {
			this.status = status;
		}

		public String getActivationCode() {
			return activationCode;
		}

		public void setActivationCode(String activationCode) {
			this.activationCode = activationCode;
		}

		public String getReloginpass() {
			return reloginpass;
		}

		public void setReloginpass(String reloginpass) {
			this.reloginpass = reloginpass;
		}

		public String getVerifyCode() {
			return verifyCode;
		}

		public void setVerifyCode(String verifyCode) {
			this.verifyCode = verifyCode;
		}

		public String getNewpass() {
			return newpass;
		}

		public void setNewpass(String newpass) {
			this.newpass = newpass;
		}

		@Override
		public String toString() {
			return "User [uid=" + uid + ", loginname=" + loginname
					+ ", loginpass=" + loginpass + ", email=" + email
					+ ", status=" + status + ", activationCode="
					+ activationCode + ", reloginpass=" + reloginpass
					+ ", verifyCode=" + verifyCode + ", newpass=" + newpass
					+ "]";
		}

}
