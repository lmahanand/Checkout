package com.mediaocean.onlinestore.api.facade.impl;

import java.math.BigDecimal;
import java.util.List;

import com.mediaocean.onlinestore.api.facade.BillingService;
import com.mediaocean.onlinestore.domain.model.ItemizedBill;
import com.mediaocean.onlinestore.domain.model.Product;
import com.mediaocean.onlinestore.domain.model.ProductCost;
import com.mediaocean.onlinestore.util.SalesTax;

public class BillingServiceImpl implements BillingService {

	public BillingServiceImpl() {

	}

	@Override
	public ItemizedBill generateItemizedBill(List<Product> products) {

		ItemizedBill itemizedBill = new ItemizedBill();

		ProductCost[] productCost = new ProductCost[products.size()];

		BigDecimal subTotal = new BigDecimal(0);
		BigDecimal totalTax = new BigDecimal(0);
		BigDecimal totalBill = new BigDecimal(0);

		int i = 0;
		for (Product product : products) {
			ProductCost prodCost = new ProductCost();
			prodCost.setProductName(product.getProductName());
			prodCost.setQuantity(Integer.toString(product.getQuantity()));
			prodCost.setUnitPrice(Double.toString(product.getPrice()));

			double taxLevied = 0;

			if ("A".equals(product.getProductCategory())) {
				taxLevied = SalesTax.CATEGORY_A.getValue();
			} else if ("B".equals(product.getProductCategory())) {
				taxLevied = SalesTax.CATEGORY_B.getValue();
			}
			if ("C".equals(product.getProductCategory())) {
				taxLevied = SalesTax.CATEGORY_C.getValue();
			}

			BigDecimal priceWithoutTax = new BigDecimal(product.getQuantity())
					.multiply(new BigDecimal(product.getPrice()));
			subTotal = subTotal.add(priceWithoutTax);

			BigDecimal tax = new BigDecimal(taxLevied / 100);
			BigDecimal taxCost = tax.multiply(priceWithoutTax);
			totalTax = totalTax.add(taxCost);

			BigDecimal totalCost = taxCost.add(priceWithoutTax);
			totalBill = totalBill.add(totalCost);
			taxCost = taxCost.setScale(2, BigDecimal.ROUND_CEILING);
			prodCost.setTaxLevied(taxCost.toString());

			totalCost = totalCost.setScale(2, BigDecimal.ROUND_CEILING);
			prodCost.setTotalPrice(totalCost.toString());

			productCost[i] = prodCost;
			i++;
		}

		subTotal = subTotal.setScale(2, BigDecimal.ROUND_CEILING);
		totalBill = totalBill.setScale(2, BigDecimal.ROUND_CEILING);
		totalTax = totalTax.setScale(2, BigDecimal.ROUND_CEILING);

		itemizedBill.setProductCost(productCost);
		itemizedBill.setSubTotal(subTotal.toString());
		itemizedBill.setTotalBill(totalBill.toString());
		itemizedBill.setTotalTax(totalTax.toString());

		return itemizedBill;
	}

}
