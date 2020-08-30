package org.sales.tax.constants;

/**
 * NonTaxableProducts.java - a Java ENUM designed for storing information about
 * non-taxable products
 * 
 * @author - Neel Rijhwani | rijhwani.neel@gmail.com
 * @version 1.0
 */

public enum NonTaxableProducts {

	FOOD, MEDICAL, BOOK, CHOCOLATE, PILL, TABLET, SYRUP, APPLE, MANGO, VEG, POTATO, BANANA, MEAT;

	/**
	 * This method determines whether tax is applicable for a particular product or
	 * not.
	 * 
	 * @param String productName
	 * 
	 * @return boolean
	 * 
	 */
	public static boolean isTaxApplicable(String productName) {
		for (NonTaxableProducts product : NonTaxableProducts.values()) {
			if (productName.toLowerCase().contains(product.name().toLowerCase()))
				return true;
		}
		return false;
	}
}