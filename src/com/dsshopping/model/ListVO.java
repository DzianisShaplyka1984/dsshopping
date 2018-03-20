package com.dsshopping.model;

import java.io.Serializable;
import java.util.Date;

public class ListVO implements Serializable {

	private static final long serialVersionUID = -2561100875101743859L;
	private long id;
	private String name;
	private String description;
	private int total;
	private int purchased;
	private Date created;
		
	public ListVO() {
		this.name = "";
		this.description = "";
		this.created = new Date();
		this.total = 0;
		this.purchased = 0;
	}
	
	public ListVO(long id, String name, String description, Date created, int total, int purchased) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.created = created;
		this.total = total;
		this.purchased = purchased;
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
	
	public Date getCreated() {
		return created;
	}
	
	public void setCreated(Date created) {
		this.created = created;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPurchased() {
		return purchased;
	}

	public void setPurchased(int purchased) {
		this.purchased = purchased;
	}
}
