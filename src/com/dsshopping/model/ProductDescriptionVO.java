package com.dsshopping.model;

public class ProductDescriptionVO {
	
	private long id;
	private String name;
	private String description;
	private long categoryId;
	
	public ProductDescriptionVO(long id, String name, String description, long categoryId) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.categoryId = categoryId;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
}
