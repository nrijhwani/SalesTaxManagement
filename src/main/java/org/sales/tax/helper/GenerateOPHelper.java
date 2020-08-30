package org.sales.tax.helper;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.logging.Logger;

import org.sales.tax.model.Product;
import org.sales.tax.model.Response;
import org.sales.tax.utility.IValidatePredicate;

/**
 * GenerateOPHelper.java - a Java class designed for generating output on
 * console.
 * 
 * @author - Neel Rijhwani | rijhwani.neel@gmail.com
 * @version 1.0
 */

public class GenerateOPHelper {

	private static final Logger logger = Logger.getLogger(SalesTaxHelper.class.getName());

	/**
	 * Generate Output method responsible for generating expected output on the
	 * console along with logging statements.
	 *
	 * @param Response response
	 */

	public static void generateOutput(List<Response> responseList) {

		logger.info("Inside generateOutput() of GenerateOPHelper, this method will generate output on console.");
		Instant startTime = Instant.now();
		int index = 1;
		for (Response response : responseList) {
			System.out.println("Output " + index + ":");
			for (Product product : response.getProducts()) {
				if (!IValidatePredicate.checkNullOrEmpty().test(product))
					System.out.println(
							product.getQuantity() + " " + product.getName() + ": " + product.getTaxablePrice());
			}
			System.out.println("Sales Taxes: " + response.getTotalSalesTax());
			System.out.println("Total: " + response.getTotalBill());
			index++;
		}

		logger.info("It took " + ChronoUnit.MILLIS.between(startTime, Instant.now())
				+ " millis to generate output on console.");
	}
}
