package com.mediaocean.onlinestore.api.facade;

import java.util.List;

import com.mediaocean.onlinestore.domain.model.ItemizedBill;
import com.mediaocean.onlinestore.domain.model.Product;

/**
 * This facade shields the domain layer - model, services etc. from
 * concerns such as the user interface and remoting.
 */

public interface BillingService {
	public ItemizedBill generateItemizedBill(List<Product> products);
}
