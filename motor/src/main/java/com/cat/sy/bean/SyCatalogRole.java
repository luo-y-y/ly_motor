package com.cat.sy.bean;

import java.sql.Timestamp;

public class SyCatalogRole implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	  private Integer id;
	  
	  private String isValid;
	  
	  private String nodeIds;
	  
	  private String powerNodeIds;
	  
	  private Integer roleId;
	  
	  
	  private Timestamp createTime;
	  
	  private Integer createUserId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getNodeIds() {
		return nodeIds;
	}

	public void setNodeIds(String nodeIds) {
		this.nodeIds = nodeIds;
	}

	public String getPowerNodeIds() {
		return powerNodeIds;
	}

	public void setPowerNodeIds(String powerNodeIds) {
		this.powerNodeIds = powerNodeIds;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
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

	  

	  
	  
}
