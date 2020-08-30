package org.sales.tax;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.sales.tax.constants.ErrorMessage;
import org.sales.tax.customException.InvalidDataException;
import org.sales.tax.utility.ICommonUtil;

/**
 * SalesTaxMain.java - a Java class consists of main method, for running this
 * application this class acts as bootstrap.
 * 
 * @author - Neel Rijhwani | rijhwani.neel@gmail.com
 * @version 1.0
 */

public class SalesTaxMain {

	private static Logger logger;

	/**
	 * Static block for configuring logging properties throughout the application.
	 */
	static {
		String path = SalesTaxMain.class.getClassLoader().getResource("logging.properties").getFile();
		System.setProperty("java.util.logging.config.file", path);
		logger = Logger.getLogger(SalesTaxMain.class.getName());
	}

	/**
	 * Main method for running this application.
	 *
	 * @param String[] args
	 * 
	 */
	public static void main(String[] args) throws Exception {

		logger.info(
				"Inside main() of SalesTaxMain, this method is starting method or bootstrap method of this application.");
		Instant startTime = Instant.now();
		Map<String, List<String>> inputMap = new LinkedHashMap<String, List<String>>();
		try {
			inputMap = ICommonUtil.generateInputData("/input.txt");
			ICommonUtil.generateOutput(inputMap);
		} catch (Exception exception) {
			if (exception instanceof InvalidDataException) {
				InvalidDataException invalidDataExp = (InvalidDataException) exception;
				throw new InvalidDataException(invalidDataExp.getMessage());
			}
			throw new Exception(ErrorMessage.APPLICATION_ERROR.getMessage(), exception);
		}

		logger.info("It took " + ChronoUnit.MILLIS.between(startTime, Instant.now())
				+ " millis to execute entire application.");
	}

}
