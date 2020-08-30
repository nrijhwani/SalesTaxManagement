package org.sales.tax.helper;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.sales.tax.constants.Constants;
import org.sales.tax.constants.ErrorMessage;
import org.sales.tax.customException.InvalidDataException;
import org.sales.tax.model.Product;
import org.sales.tax.model.Response;
import org.sales.tax.serviceImpl.ImportedSalesTax;
import org.sales.tax.serviceImpl.SalesTax;
import org.sales.tax.utility.IValidatePredicate;

/**
 * SalesTaxHelper.java - a Java helper class consists of methods like creating
 * Response object for the generation of output, calculating total sales tax,
 * calculating total bill
 * 
 * @author - Neel Rijhwani | rijhwani.neel@gmail.com
 * @version 1.0
 */

public class SalesTaxHelper {

	private static final Logger logger = Logger.getLogger(SalesTaxHelper.class.getName());

	/**
	 * This method is used for creation of list of Response. In this method we are
	 * creating new product list with all product related information like product
	 * name, quantity, base price of that product, tax applied on that product,
	 * total price after applying tax on that product, whether product is imported
	 * or not.
	 *
	 * @param Map<String, List<String>> inputMap
	 * 
	 * @return List<Response>
	 * 
	 */

	public static List<Response> createResponseObj(Map<String, List<String>> inputMap) {

		logger.info("Inside createResponseObj() of SalesTaxHelper, method creates Response object.");
		Instant startTime = Instant.now();
		List<Response> responseList = new ArrayList<Response>();

		List<Product> products = null;
		String totalSalesTax = "";
		String totalBill = "";

		Set<String> keySet = inputMap.keySet();

		for (String key : keySet) {
			products = new ArrayList<Product>();
			List<String> inputList = inputMap.get(key);
			for (int i = 0; i < inputList.size(); i++) {
				String patternStr = Constants.PATTERN;
				Pattern pattern = Pattern.compile(patternStr);
				Matcher matcher = pattern.matcher(inputList.get(i));

				if (matcher.find()) {
					products.add(createProductObj(matcher, key));
				} else {
					throw new InvalidDataException(ErrorMessage.INVALID_INPUT.getMessage() + ": " + inputList.get(i));
				}
			}
			totalSalesTax = calcTotalSalesTax(products);
			totalBill = calcTotalBill(products);
			responseList.add(new Response(products, totalSalesTax, totalBill));
		}

		logger.info("It took " + ChronoUnit.MILLIS.between(startTime, Instant.now())
				+ " millis to execute for creating response object.");
		return responseList;
	}

	/**
	 * A helper method for creation of Product object.
	 *
	 * @param Matcher matcher, String key
	 * 
	 * @return Product
	 * 
	 */
	private static Product createProductObj(Matcher matcher, String key) {
		logger.info("Inside createProductObj() of SalesTaxHelper, method creates Product object.");
		Instant startTime = Instant.now();
		String tax = "";
		boolean isImported = matcher.group(2).contains("imported");
		long quantity = Long.parseLong(matcher.group(1));
		float price = Float.parseFloat(matcher.group(3));
		Product product = new Product(matcher.group(2), quantity, price, isImported);
		if (isImported) {
			ImportedSalesTax importedSalesTax = new ImportedSalesTax();
			tax = (importedSalesTax.calculateTax(product));
		} else {
			SalesTax salesTax = new SalesTax();
			tax = (salesTax.calculateTax(product));
		}
		tax = ((tax != null && !tax.isEmpty()) ? tax : "0.0");
		product.setTax(tax);
		try {
			if (quantity < 1) {
				throw new InvalidDataException(ErrorMessage.QUANTITY_INVALID.getMessage() + "\"" + product.getName() + "\" present in " + key);
			}
			product.setTaxablePrice(String.format("%.2f", price + (Float.parseFloat(tax) / quantity)));
		} catch (InvalidDataException invalidDataException) {
			invalidDataException.printStackTrace();
			return null;
		}
		logger.info("It took " + ChronoUnit.MILLIS.between(startTime, Instant.now())
				+ " millis to execute for creating product object.");
		return product;
	}

	/**
	 * Calculate total sales tax, method for calculating total sales tax for all
	 * products.
	 *
	 * @param List<Product> products
	 * 
	 * @return String
	 * 
	 */
	private static String calcTotalSalesTax(List<Product> products) {

		logger.info("Inside calcTotalSalesTax() of SalesTaxHelper, method for calculating total sales tax.");
		Instant startTime = Instant.now();
		float totalSalesTax = 0.0f;
		for (Product product : products) {
			if (!IValidatePredicate.checkNullOrEmpty().test(product)) {
				float tax = Float.parseFloat(product.getTax());
				totalSalesTax += tax;
			}
		}

		logger.info("It took " + ChronoUnit.MILLIS.between(startTime, Instant.now())
				+ " millis to calculate total sales tax");
		return String.format("%.2f", totalSalesTax);
	}

	/**
	 * Calculate total bill, method for calculating total bills (inclusive of taxes)
	 * for all products.
	 *
	 * @param List<Product> products
	 * 
	 * @return String
	 * 
	 */
	private static String calcTotalBill(List<Product> products) {

		logger.info("Inside calcTotalBill() of SalesTaxHelper, method for calculating total bill.");
		Instant startTime = Instant.now();
		float totalBill = 0.0f;
		for (Product product : products) {
			if (!IValidatePredicate.checkNullOrEmpty().test(product))
				totalBill += Float.parseFloat(product.getTaxablePrice()) * product.getQuantity();
		}

		logger.info(
				"It took " + ChronoUnit.MILLIS.between(startTime, Instant.now()) + " millis to calculate total bill.");
		return String.format("%.2f", totalBill);
	}

}
