package com.mediaocean.onlinestore.domain.model;

import java.io.Serializable;

public class ProductCost implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String productName;
	private String quantity;
	private String unitPrice;
	private String totalPrice;
	private String taxLevied;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getTaxLevied() {
		return taxLevied;
	}
	public void setTaxLevied(String taxLevied) {
		this.taxLevied = taxLevied;
	}
	
	
}
