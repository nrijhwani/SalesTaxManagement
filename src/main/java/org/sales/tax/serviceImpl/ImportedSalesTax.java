package org.sales.tax.serviceImpl;

import org.sales.tax.constants.Constants;
import org.sales.tax.constants.NonTaxableProducts;
import org.sales.tax.model.Product;
import org.sales.tax.service.ISalesTax;
import org.sales.tax.utility.ICommonUtil;

/**
 * ImportedSalesTax.java - a Java class which implements ISalesTax interface for
 * calculating tax. This class will have its own implementation of calculating
 * tax for imported products.
 * 
 * @author - Neel Rijhwani | rijhwani.neel@gmail.com
 * @version 1.0
 */

public class ImportedSalesTax implements ISalesTax {

	/**
	 * This method overrides calculateTax() method of ISalesTax interface. Role of
	 * this method is to calculate tax for imported products. All products would be
	 * charged additional tax.
	 *
	 * @param Product product
	 * 
	 * @return String
	 * 
	 */

	@Override
	public String calculateTax(Product product) {
		String tax = "";
		if (NonTaxableProducts.isTaxApplicable(product.getName())) {
			tax = ICommonUtil.calAndRoundTax(Constants.IMPORTED_TAX, product);
		} else {
			tax = ICommonUtil.calAndRoundTax((Constants.SALES_TAX + Constants.IMPORTED_TAX), product);
		}

		return tax;
	}

}
