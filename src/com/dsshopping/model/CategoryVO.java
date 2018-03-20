package com.dsshopping.model;

import java.util.ArrayList;
import java.util.List;

public class CategoryVO {
	private long id;
	private String name;
	private String description;
	private List<ProductDescriptionVO> productDescriptionList;
	
	public CategoryVO() {
		productDescriptionList = new ArrayList<ProductDescriptionVO>();
	}

	public CategoryVO(long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
		
		this.productDescriptionList = new ArrayList<ProductDescriptionVO>();
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
	
	public List<ProductDescriptionVO> getProductDescriptionList() {
		return productDescriptionList;
	}

	public void setProductDescriptionList(List<ProductDescriptionVO> productDescriptionList) {
		this.productDescriptionList = productDescriptionList;
	}
	

	public void removeProductDescription(ProductDescriptionVO productDescription) {
		this.productDescriptionList.remove(productDescription);
	}

	public void addProductDescription(ProductDescriptionVO productDescription) {
		this.productDescriptionList.add(productDescription);
	}
}
