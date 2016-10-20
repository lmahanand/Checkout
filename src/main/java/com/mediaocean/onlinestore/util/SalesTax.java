package com.mediaocean.onlinestore.util;

public enum SalesTax {
	CATEGORY_A(10), CATEGORY_B(20), CATEGORY_C(0);

	private final double value;

	SalesTax(final double newValue) {
		value = newValue;
	}

	public double getValue() {
		return value;
	}
}
