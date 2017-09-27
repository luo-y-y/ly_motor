package com.cat.sy.bean;

import java.sql.Timestamp;

public class SyRoleUser implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	  private Integer id;
	  
	  private String uuid;
	  
	  private Integer roleId;
	  
	  private Integer userId;
	  
	  private String status;
	  
	  private Timestamp createTime;
	  
	  private Integer createUserId;
	  
	  private String note;
	  
	  //虚拟字段
	  private String roleLabel;
	  
	  //虚拟字段
	  private String userName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}



	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getRoleLabel() {
		return roleLabel;
	}

	public void setRoleLabel(String roleLabel) {
		this.roleLabel = roleLabel;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}



	  
}
