package org.sales.tax.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import org.sales.tax.constants.ErrorMessage;
import org.sales.tax.customException.InvalidDataException;
import org.sales.tax.helper.GenerateOPHelper;
import org.sales.tax.helper.SalesTaxHelper;
import org.sales.tax.model.Product;
import org.sales.tax.model.Response;

/**
 * ICommonUtil.java - a Java interface designed to contain all utility methods.
 * console.
 * 
 * @author - Neel Rijhwani | rijhwani.neel@gmail.com
 * @version 1.0
 */

public interface ICommonUtil {

	static final Logger logger = Logger.getLogger(ICommonUtil.class.getName());

	/**
	 * This method is used for reading messages using InputStream.
	 *
	 * @param InputStream inputStream
	 * 
	 * @return String[]
	 * 
	 */
	public static String[] readFromInputStream(InputStream inputStream) throws IOException {
		StringBuilder resultStringBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = br.readLine()) != null) {
				resultStringBuilder.append(line).append("\n");
			}
		}
		String[] stringArr = resultStringBuilder.toString().split("\\n");
		return stringArr;
	}

	/**
	 * This method is used for generating input data in the required format from
	 * .txt file
	 *
	 * @param String inputFile
	 * 
	 * @return Map<String, List<String>>
	 * 
	 */
	public static Map<String, List<String>> generateInputData(String inputFile) throws IOException {

		Map<String, List<String>> inputMap = new LinkedHashMap<String, List<String>>();
		InputStream inputStream = ICommonUtil.class.getResourceAsStream(inputFile);
		
		String[] dataArr = readFromInputStream(inputStream);
		List<String> inputList = null;
		String key = "";
		Set<String> keys = new HashSet<String>();
		for (String data : dataArr) {
			if (data.toLowerCase().contains("Input".toLowerCase())) {
				if (!keys.contains(data)) {
					inputList = new ArrayList<String>();
					keys.add(data);
				}
				key = data;
			} else {
				if (data != null && !data.isEmpty()) {
					if (!IValidatePredicate.checkNullOrEmptyString().test(key)) {
						inputList.add(data);
						inputMap.put(key, inputList);
					} else {
						throw new InvalidDataException(ErrorMessage.INVALID_INPUT.getMessage());
					}
				}
			}
		}

		return inputMap;
	}

	/**
	 * This method is used for generating output on the console.
	 *
	 * @param Map<String, List<String>> inputMap
	 * 
	 */
	public static void generateOutput(Map<String, List<String>> inputMap) {
		List<Response> responseList = SalesTaxHelper.createResponseObj(inputMap);
		if (!IValidatePredicate.checkNullOrEmpty().test(responseList)) {
			GenerateOPHelper.generateOutput(responseList);
		} else {
			throw new InvalidDataException("Unable to create valid Response object.");

		}
	}

	/**
	 * This method is used for calculating and rounding the tax applied.
	 *
	 * @param double  taxPercent
	 * @param Product product
	 * 
	 * @return String
	 * 
	 */
	public static String calAndRoundTax(double taxPercent, Product product) {

		logger.info("Inside taxRounding() of SalesTaxHelper, method for rounding tax value. Applying this method for "
				+ product.getName());
		long quantity = product.getQuantity();
		Instant startTime = Instant.now();
		float tax = 0.0f;
		while (quantity != 0) {
			tax += (float) (Math.ceil((product.getPrice() * taxPercent) / 100 * 20.0) / 20.0);
			quantity--;
		}

		logger.info("It took " + ChronoUnit.MILLIS.between(startTime, Instant.now())
				+ " millis to execute tax rounding method.");
		return String.format("%.2f", tax);
	}
}
