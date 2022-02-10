package com.kashitkalaecom.brandmodelmgmt.utilities;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * common constant file all the common constant used in project must define here
 */
public class Constants {
	private static final Logger logger = LoggerFactory.getLogger(Constants.class);

	
	// DField-Error constant
	public static final String IS_UUID = "error.isUUID";
	public static final String ERROR_MANDATORY = "error.mandatory";
	public static final String ERROR_LENGTH_AFTER_DECIMAL = "error.lengthAfterDecimal";
	public static final String ERROR_LENGTH_BEFORE_DECIMAL = "error.lengthBeforeDecimal";
	public static final String ERROR_VAL_MIN_VALUE = "error.val.minValue";
	public static final String ERROR_VAL_MAX_VALUE = "error.val.maxValue";
	public static final String ERROR_PAST_DATE_ALLOWED = "error.pastDateAllowed";
	public static final String ERROR_MIN_LENTH = "error.minLength";
	public static final String ERROR_MAX_LENGTH = "error.maxLength";
	public static final String ERROR_CHAR_NOT_ALLOWED = "error.charsNotAllowed";
	public static final String ERROR_NUMBER_ONLY = "error.number";
	public static final String ERROR_NUMBER_ALLOWED = "error.numbersAllowed";
	public static final String INVALID_INPUT = "error.invalid";
	public static final String INVALID_DATA = "Invalid Input";

	public static final String BLANK = "";

	public static final String EXTERNAL_CARD = "EXTERNAL_CARD";
	public static final String EXTERNAL_BANK_ACCOUNT = "EXTERNAL_BANK_ACCOUNT";
	public static final String VOUCHER_FUND_PARKING = "VOUCHER_FUND_PARKING";

	// Pattern-constant
	public static final String PATTERN_ZERO_TO_NINE = "[0-9]";
	public static final String PATTERN_ONLY_NUMBER = "^[0-9,.]+$";
	public static final String PATTERN_HEXA_DECIMAL = "-?[0-9a-fA-F]+";

	// encoding
	public static final String ENCODE_HEX = "Hex";
	public static final String ENCODE_BASE_64 = "Base64";
	public static final String APPLICATION_JSON = "application/json";

	public static final String TO = "TO";
	public static final String FROM = "FROM";

	public static final BigDecimal ZERO = new BigDecimal(0);

	public static final String DAY = "DAY";
	public static final String WEEK = "WEEK";
	public static final String MONTH = "MONTH";
	public static final String COACodeSeperator = ".";

	public static final String ASSET_CODE = "100";
	public static final String LIABILITY_CODE = "200";
	public static final String INCOME_CODE = "400";
	public static final String EXPENDITURE_CODE = "500";

	public static final String COA_ASSET = "ASSET";
	public static final String COA_LIABILITY = "LIABILITY";
	public static final String COA_INCOME = "INCOME";
	public static final String COA_EXPENDITURE = "EXPENDITURE";

	public static final String FAILURE = "FAILURE";
	public static final String SUCCESS = "SUCCESS";

	public static final String COMMA = ",";
	public static final String DOC_ID = "docId";
	public static final String LENDER = "LENDER";
	public static final String BORROWER = "BORROWER";

}
