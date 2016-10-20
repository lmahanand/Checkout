package com.mediaocean.onlinestore.domain.model;

import java.io.Serializable;
import java.util.Arrays;

public class ItemizedBill implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProductCost[] productCost;
	private String subTotal;
	private String totalTax;
	private String totalBill;
	
	public ProductCost[] getProductCost() {
		return productCost;
	}
	public void setProductCost(ProductCost[] productCost) {
		this.productCost = productCost;
	}
	public String getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}
	public String getTotalTax() {
		return totalTax;
	}
	public void setTotalTax(String totalTax) {
		this.totalTax = totalTax;
	}
	public String getTotalBill() {
		return totalBill;
	}
	public void setTotalBill(String totalBill) {
		this.totalBill = totalBill;
	}
	@Override
	public String toString() {
		return "ItemizedBill [productCost=" + Arrays.toString(productCost) + ", subTotal=" + subTotal + ", totalTax="
				+ totalTax + ", totalBill=" + totalBill + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((totalBill == null) ? 0 : totalBill.hashCode());
		result = prime * result + ((totalTax == null) ? 0 : totalTax.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemizedBill other = (ItemizedBill) obj;
		if (totalBill == null) {
			if (other.totalBill != null)
				return false;
		} else if (!totalBill.equals(other.totalBill))
			return false;
		if (totalTax == null) {
			if (other.totalTax != null)
				return false;
		} else if (!totalTax.equals(other.totalTax))
			return false;
		return true;
	}
	
	
}
