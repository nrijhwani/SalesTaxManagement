package org.sales.tax.model;

import java.util.List;

public class Response {

	private List<Product> products;
	private String totalSalesTax;
	private String totalBill;

	public Response(List<Product> products, String totalSalesTax, String totalBill) {
		super();
		this.products = products;
		this.totalSalesTax = totalSalesTax;
		this.totalBill = totalBill;
	}

	public Response() {
		super();
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public String getTotalSalesTax() {
		return totalSalesTax;
	}

	public void setTotalSalesTax(String totalSalesTax) {
		this.totalSalesTax = totalSalesTax;
	}

	public String getTotalBill() {
		return totalBill;
	}

	public void setTotalBill(String totalBill) {
		this.totalBill = totalBill;
	}

	@Override
	public String toString() {
		return "Response [products=" + products + ", totalSalesTax=" + totalSalesTax + ", totalBill=" + totalBill + "]";
	}

}
