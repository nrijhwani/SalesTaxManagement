package org.sales.tax.service;

import org.sales.tax.constants.Constants;
import org.sales.tax.constants.NonTaxableProducts;
import org.sales.tax.model.Product;
import org.sales.tax.utility.ICommonUtil;

/**
 * ISalesTax.java - a Java interface for calculating tax It can have multiple
 * implementation classes, we are designed this interface as per Template Design
 * Pattern.
 * 
 * @author - Neel Rijhwani | rijhwani.neel@gmail.com
 * @version 1.0
 */

public interface ISalesTax {

	/**
	 * This method provides default implementation for calculating tax. Role of this
	 * method is to calculate tax for taxable product only(not imported). If product
	 * comes under exemption then no tax would be applied.
	 *
	 * @param Product product
	 * 
	 * @return String
	 * 
	 */

	default String calculateTax(Product product) {

		String tax = "";
		if (!NonTaxableProducts.isTaxApplicable(product.getName())) {
			tax = ICommonUtil.calAndRoundTax(Constants.SALES_TAX, product);
		}

		return tax;
	}
}
