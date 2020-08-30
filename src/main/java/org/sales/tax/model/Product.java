package org.sales.tax.model;

/**
 * Product.java - a Java class acts as a Model or POJO for storing Product
 * information like product name, quantity, base price of that product, tax
 * applied on that product, total price after applying tax on that product,
 * whether product is imported or not.
 * 
 * @author - Neel Rijhwani | rijhwani.neel@gmail.com
 * @version 1.0
 */

public class Product {

	private String name;
	private long quantity;
	private float price;
	private String tax;
	private String taxablePrice;
	private boolean isImported;

	public Product() {
		super();
	}

	/**
	 * Product parameterized constructor
	 *
	 * @param String  name
	 * @param int     quantity
	 * @param float   price
	 * @param boolean isImported
	 * 
	 * @return Product
	 * 
	 */

	public Product(String name, long quantity, float price, boolean isImported) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getTaxablePrice() {
		return taxablePrice;
	}

	public void setTaxablePrice(String taxablePrice) {
		this.taxablePrice = taxablePrice;
	}

	public boolean isImported() {
		return isImported;
	}

	public void setImported(boolean isImported) {
		this.isImported = isImported;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", quantity=" + quantity + ", price=" + price + ", tax=" + tax
				+ ", taxablePrice=" + taxablePrice + ", isImported=" + isImported + "]";
	}

}
