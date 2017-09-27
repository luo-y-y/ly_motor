package com.cat.sy.bean;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * 表名：sy_user
 * 
 * @author Administrator
 */
public class SyUser implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	private Integer id;

	/**
	 * 1 启用 2 不启用
	 */
	private String status;

	/**
	 * 登录帐号
	 */
	private String userName;

	/**
	 * 登录密码
	 */
	private String password;

	/**
	 * 中文名字
	 */
	private String name;

	/**
	 * 手机号码
	 */
	private String mobile;

	/**
	 * 证件
	 */
	private String idNumber;

	/**
	 * 男Male: M;女Female:F
	 */
	private String sexCd;

	/**
	 * 生日
	 */
	private Date birthday;

	/**
	 * 创建时间
	 */
	private Timestamp createTime;

	/**
	 * 创建人编号
	 */
	private Integer createUserId;

	/**
	 * 备注
	 */
	private String note;
	
	/**
	 * 短信发送状态
	 * @return
	 */
	private String msgStatus;
	

	public String getMsgStatus() {
		return msgStatus;
	}

	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getSexCd() {
		return sexCd;
	}

	public void setSexCd(String sexCd) {
		this.sexCd = sexCd;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
