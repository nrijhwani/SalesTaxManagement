package org.sales.tax;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.sales.tax.customException.InvalidDataException;
import org.sales.tax.utility.ICommonUtil;

public class SalesTaxTest {

	@Test
	public void testGenerateBillMain() throws Exception {

		String[] args = null;
		final InputStream original = System.in;
		SalesTaxMain.main(args);
		System.setIn(original);

	}

	@Test
	public void testGenerateBill() throws IOException {

		final InputStream original = System.in;
		ICommonUtil.generateOutput(
				ICommonUtil.generateInputData("/inputTestGenerateBill.txt"));
		System.setIn(original);
	}

	@Test
	public void testGenerateBill2() throws IOException {

		final InputStream original = System.in;
		ICommonUtil.generateOutput(
				ICommonUtil.generateInputData("/inputTestGenerateBill2.txt"));
		System.setIn(original);

	}

	@Test
	public void testGenerateBillWithQuantityAsZero() throws IOException {

		final InputStream original = System.in;
		ICommonUtil.generateOutput(
				ICommonUtil.generateInputData("/inputTestWithQuantityAs0.txt"));
		System.setIn(original);

	}

	@Test(expected = InvalidDataException.class)
	public void testGenerateBillWithInvalidInput() throws IOException {

		final InputStream original = System.in;
		ICommonUtil.generateOutput(
				ICommonUtil.generateInputData("/inputTestWithInvalidData.txt"));
		System.setIn(original);

	}

}
