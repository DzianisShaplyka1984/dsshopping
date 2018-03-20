package com.dsshopping.model;

public class ProductVO {

	private long id;
	private float price;
	private float amount;
	private boolean purchased;
	private String description;
	private long listId;
	private String name;
	
	public ProductVO(long id, float price, float amount, int purchased,
			String description, long listId, String name) {
		this.id = id;
		this.price = price;
		this.amount = amount;
		this.purchased = purchased == 0 ? false : true;
		this.description = description;
		this.listId = listId;
		this.name = name;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public float getAmount() {
		return amount;
	}
	
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public boolean isPurchased() {
		return purchased;
	}
	
	public void setPurchased(boolean purchased) {
		this.purchased = purchased;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public long getListId() {
		return listId;
	}

	public void setListId(long listId) {
		this.listId = listId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
