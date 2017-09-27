package com.cat.sy.bean;

import java.sql.Timestamp;

public class SyCatalogNode implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String uuid;

	private Integer catalogId;

	private Integer parentId;

	private String isValid;

	private String code;

	private String name;

	private String label;

	private String hasChild;

	private String typeName;

	private String iconName;

	private Integer dispOrder;

	private String linkCatalog;
	
	private String outerCatalog;
	
	private String doRedirect;
	
	private String doInvoke;
	
	private String doIntroduce;
	
	private String note;
	
	private Timestamp createTime;
	
	private Integer createUserId;
	
	  
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

	public Integer getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Integer catalogId) {
		this.catalogId = catalogId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getHasChild() {
		return hasChild;
	}

	public void setHasChild(String hasChild) {
		this.hasChild = hasChild;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getIconName() {
		return iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

	public Integer getDispOrder() {
		return dispOrder;
	}

	public void setDispOrder(Integer dispOrder) {
		this.dispOrder = dispOrder;
	}

	public String getLinkCatalog() {
		return linkCatalog;
	}

	public void setLinkCatalog(String linkCatalog) {
		this.linkCatalog = linkCatalog;
	}

	public String getOuterCatalog() {
		return outerCatalog;
	}

	public void setOuterCatalog(String outerCatalog) {
		this.outerCatalog = outerCatalog;
	}

	public String getDoRedirect() {
		return doRedirect;
	}

	public void setDoRedirect(String doRedirect) {
		this.doRedirect = doRedirect;
	}

	public String getDoInvoke() {
		return doInvoke;
	}

	public void setDoInvoke(String doInvoke) {
		this.doInvoke = doInvoke;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public String getDoIntroduce() {
		return doIntroduce;
	}

	public void setDoIntroduce(String doIntroduce) {
		this.doIntroduce = doIntroduce;
	}

	

}
