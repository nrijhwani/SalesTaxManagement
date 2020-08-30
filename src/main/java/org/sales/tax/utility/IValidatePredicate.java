package org.sales.tax.utility;

import java.util.function.Predicate;

public interface IValidatePredicate {

	public static Predicate<Object> checkNullOrEmpty(){
		return obj -> (obj == null || obj == "");
	}
	
	public static Predicate<String> checkNullOrEmptyString(){
		return str -> (str == null || str.trim() == "" || str.isEmpty() || str == "" );
	}
}
