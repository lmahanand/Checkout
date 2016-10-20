package com.mediaocean.onlinestore.domain.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int productCode;
	private String productCategory;
	private String productName;
	private int quantity;
	private double price;

	public Product() {

	}

	public Product(int productCode, String productCategory, String productName,int quantity, double price) {
		super();
		this.productCode = productCode;
		this.productCategory = productCategory;
		this.productName = productName;
		this.quantity=quantity;
		this.price = price;
	}

	public int getProductCode() {
		return productCode;
	}

	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [productCode=" + productCode + ", productCategory=" + productCategory + ", productName="
				+ productName + ", quantity=" + quantity + ", price=" + price + "]";
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
