package com.mediaocean.onlinestore.api.facade;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mediaocean.onlinestore.api.facade.impl.BillingServiceImpl;
import com.mediaocean.onlinestore.domain.model.ItemizedBill;
import com.mediaocean.onlinestore.domain.model.Product;

public class BillingServiceImplTest {

	@Test
	public void generateItemizedBill() {
		List<Product> products = new ArrayList<Product>();
		Product product1 = new Product(1, "A", "ProdA", 2, 100);
		Product product2 = new Product(2, "B", "ProdB", 3, 50);
		products.add(product1);
		products.add(product2);

		BillingService service = new BillingServiceImpl();
		ItemizedBill itemizedBill = service.generateItemizedBill(products);
		
		assertEquals("50.001",itemizedBill.getTotalTax());
		assertEquals("350.000",itemizedBill.getSubTotal());
		assertEquals("400.001",itemizedBill.getTotalBill());
	}
}
