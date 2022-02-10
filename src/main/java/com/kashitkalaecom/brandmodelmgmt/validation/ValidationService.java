package com.kashitkalaecom.brandmodelmgmt.validation;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kashitkalaecom.brandmodelmgmt.utilities.AllEnum;
import com.kashitkalaecom.brandmodelmgmt.utilities.Constants;

@Service
public class ValidationService {
	
	@Autowired
	ValidationRepository validationRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(ValidationService.class);

	 public String validateField(String tenantCode, String simpleClassName, Object value, String nameOfField, String locale) throws Exception{
	   
		String error = null;
		List<Validation> validationList = validationRepository.getValidations(simpleClassName);

		// logger.info("Validation list :"+validationList);

		if (validationList == null)
		{
		    logger.error("Form Validations are undefined for the simple class name :" + simpleClassName);
		    return error;
		}

		for (Validation validation : validationList)
		{
		    String internalName = validation.getFieldNameInternal();
		    String fieldType = validation.getFieldType();

		    if (internalName.equalsIgnoreCase(nameOfField))
		    {

			fieldType = fieldType.trim();

			if (fieldType.equals(AllEnum.FieldType.UUID.name()))
			{
			    String new_value = (String) value;
			    error = validateUUIDField(new_value, validation, locale);

			} else if (fieldType.equals(AllEnum.FieldType.STRING.name()))
			{

			    String new_value = (String) value;
			    error = validateStringField(new_value, validation, locale);

			} else if (fieldType.equals(AllEnum.FieldType.DATE.name()))
			{

			    Timestamp new_value = (Timestamp) value;
			    error = validateTimestampField(new_value, validation, locale);

			} else if (fieldType.equals(AllEnum.FieldType.NUMBER.name()))
			{

			    BigDecimal new_value = (BigDecimal) value;

			    // BigDecimal new_value = value==null? (BigDecimal) value: new BigDecimal(
			    // ((Number)value).doubleValue());
			    error = validateBigDecimalField(new_value, validation, locale);

			} else if (fieldType.equals(AllEnum.FieldType.BOOLEAN.name()))
			{

			    Boolean new_value = (Boolean) value;
			    error = validateBoolean(new_value, validation, locale);

			} else if (fieldType.equals(AllEnum.FieldType.ENUM.name()))
			{

			    String new_value = (String) value;
			    error = validateEnum(new_value, validation, locale);

			} else if (fieldType.equals(AllEnum.FieldType.INTEGER.name()))
			{

			    Integer new_value = (Integer) value;
			    error = validateInteger(new_value, validation, locale);

			} else if (fieldType.equals(AllEnum.FieldType.LIST.name()))
			{
			    List new_value = (List) value;
			    error = validateListField(new_value, validation, locale);

			} else if (fieldType.equals(AllEnum.FieldType.MAP.name()))
			{
			    Map new_value = (Map) value;
			    error = validateMapField(new_value, validation, locale);

			} else
			{
			    logger.error("FIELD TYPE INFORMATION MAY BE MISSING  :" + internalName + " : " + value + " : " + fieldType);
			   
			}
		    } else
		    {
			
		    }

		}
		return error;

	}
	 
	 private String validateMapField(Map value, Validation validation, String locale)
	    {
		String displayName = validation.getFieldDisplayName();
		boolean isMandatory = validation.getMandatory();

		if (isMandatory && MapUtils.isEmpty(value))
		{
		    String error = getFormattedError(locale, Constants.ERROR_MANDATORY, displayName, "");
		    return error;

		}

		return null;
	    }

	    private String validateListField(List value, Validation validation, String locale)
	    {
		String displayName = validation.getFieldDisplayName();
		boolean isMandatory = validation.getMandatory();

		if (isMandatory && CollectionUtils.isEmpty(value))
		{
		    String error = getFormattedError(locale, Constants.ERROR_MANDATORY, displayName, "");
		    return error;

		}

		return null;
	    }

	    private String validateUUIDField(String value, Validation validation, String locale)
	    {
		String displayName = validation.getFieldDisplayName();
		boolean isMandatory = validation.getMandatory();
		boolean isUUID = true;

		if (isMandatory && StringUtils.isBlank(value))
		{
		    String error = getFormattedError(locale, Constants.ERROR_MANDATORY, displayName, "");
		    return error;

		}

		if (StringUtils.isBlank(value))
		{
		    return null;
		}

		if (!isUUID)
		{
		    String error = getFormattedError(locale, Constants.IS_UUID, displayName, "");
		    return error;
		}

		return null;
	    }

	    private String validateInteger(Integer value, Validation validation, String locale)
	    {

		String displayName = validation.getFieldDisplayName();
		boolean isMandatory = validation.getMandatory();

		if (isMandatory && value == null)
		{
		    String error = getFormattedError(locale, Constants.ERROR_MANDATORY, displayName, "");
		    return error;

		} else if (value == null)
		{
		    return null;
		}

		int digitsBeforeDecimal = String.valueOf(value).length();

		int lengthBeforeDecimalDefined = validation.getLengthBeforeDecimal();

		if (digitsBeforeDecimal > lengthBeforeDecimalDefined)
		{
		    String reqvalueStr = String.valueOf(lengthBeforeDecimalDefined);
		    String error = getFormattedError(locale, Constants.ERROR_LENGTH_BEFORE_DECIMAL, displayName, reqvalueStr);
		    return error;
		}

		String minVal = validation.getMinValue();
		if (minVal != null && !minVal.equals(""))
		{
		    int minValInt = StringUtils.isNotBlank(minVal) ? Integer.parseInt(minVal) : 0;
		    if (value < minValInt)
		    {

			return getFormattedError(locale, Constants.ERROR_VAL_MIN_VALUE, displayName, minVal);

		    }

		}

		String maxVal = validation.getMaxValue();
		if (maxVal != null && !maxVal.equals(""))
		{
		    int maxValInt = StringUtils.isNotBlank(maxVal) ? Integer.parseInt(maxVal) : 0;
		    if (maxValInt != 0 && value > maxValInt)
		    {

			return getFormattedError(locale, Constants.ERROR_VAL_MAX_VALUE, displayName, maxVal);
		    }
		}

		return null;
	    }

	    private String validateEnum(String value, Validation validation, String locale)
	    {

		String displayName = validation.getFieldDisplayName();
		boolean isMandatory = validation.getMandatory();

		if (isMandatory && StringUtils.isBlank(value))
		{
		    String error = getFormattedError(locale, Constants.ERROR_MANDATORY, displayName, "");
		    return error;
		}

		return null;
	    }

	    // replace Boolean by boolean OR remove boolean check....

	    private String validateBoolean(Boolean value, Validation validation, String locale)
	    {

		String displayName = validation.getFieldDisplayName();
		boolean isMandatory = validation.getMandatory();

		if (isMandatory && value == null)
		{
		    String error = getFormattedError(locale, Constants.ERROR_MANDATORY, displayName, "");
		    return error;
		}
		return null;
	    }

	    private String validateBigDecimalField(BigDecimal value, Validation validation, String locale)
	    {

		int lengthAfterDecimalDefined = validation.getLengthAfterDecimal();
		String displayName = validation.getFieldDisplayName();
		boolean isMandatory = validation.getMandatory();

		if (isMandatory && value == null)
		{
		    String error = getFormattedError(locale, Constants.ERROR_MANDATORY, displayName, "");
		    return error;

		} else if (value == null)
		{
		    return null;
		}

		int noDecimalPlaces = getNumberOfDecimalPlaces(value);

		if (noDecimalPlaces > lengthAfterDecimalDefined)
		{

		    String reqvalueStr = String.valueOf(lengthAfterDecimalDefined);
		    String error = getFormattedError(locale, Constants.ERROR_LENGTH_AFTER_DECIMAL, displayName, reqvalueStr);
		    return error;
		}

		int digitsBeforeDecimal = getNumberBeforeDecimal(value);
		int lengthBeforeDecimalDefined = validation.getLengthBeforeDecimal();

		if (digitsBeforeDecimal > lengthBeforeDecimalDefined)
		{
		    String reqvalueStr = String.valueOf(lengthBeforeDecimalDefined);
		    String error = getFormattedError(locale, Constants.ERROR_LENGTH_BEFORE_DECIMAL, displayName, reqvalueStr);
		    return error;
		}

		String minVal = validation.getMinValue();
		String maxVal = validation.getMaxValue();
		if (StringUtils.isNotBlank(minVal))
		{
		    BigDecimal minValDecimal = new BigDecimal(0);
		    minValDecimal = StringUtils.isNotBlank(minVal) ? new BigDecimal(minVal) : minValDecimal;
		    if (value.compareTo(minValDecimal) < 0)
		    {

			return getFormattedError(locale, Constants.ERROR_VAL_MIN_VALUE, displayName, minValDecimal.toString());

		    }
		}

		if (StringUtils.isNotBlank(maxVal))
		{

		    BigDecimal maxValDecimal = new BigDecimal(0);
		    maxValDecimal = StringUtils.isNotBlank(maxVal) ? new BigDecimal(maxVal) : maxValDecimal;
		    if (maxValDecimal.intValue() != 0 && value.compareTo(maxValDecimal) > 0)
		    {

			return getFormattedError(locale, Constants.ERROR_VAL_MAX_VALUE, displayName, maxValDecimal.toString());
		    }
		}

		return null;
	    }

	    private String validateTimestampField(Timestamp value, Validation validation, String locale) throws ParseException
	    {

		String displayName = validation.getFieldDisplayName();
		boolean isPastAllowed = validation.isPastDateAllowed();
		boolean isMandatory = validation.getMandatory();

		if (isMandatory && value == null)
		{
		    String error = getFormattedError(locale, Constants.ERROR_MANDATORY, displayName, "");
		    return error;

		} else if (value == null)
		{
		    return null;
		}

		if (!isPastAllowed)
		{
		    boolean isPast = isTSPast(value);
		    if (isPast)
		    {
			String error = getFormattedError(locale, Constants.ERROR_PAST_DATE_ALLOWED, displayName, "");
			return error;
		    }
		}

		return null;
	    }

	    private String validateStringField(String value, Validation validation, String locale)
	    {

		String displayName = validation.getFieldDisplayName();
		boolean isMandatory = validation.getMandatory();
		int minLength = validation.getMinLength();
		int maxLength = validation.getMaxLength();
		String charsNotAllowed = validation.getCharsNotAllowed();
		boolean numbersAllowed = validation.isNumbersAllowed();
		String regex = validation.getRegex();

		if (isMandatory && StringUtils.isBlank(value))
		{
		    String error = getFormattedError(locale, Constants.ERROR_MANDATORY, displayName, "");
		    return error;

		} else if (StringUtils.isBlank(value))
		{
		    return null;
		}

		int length = value.trim().length();

		if (length < minLength)
		{
		    String error = getFormattedError(locale, Constants.ERROR_MIN_LENTH, displayName, String.valueOf(minLength));
		    return error;
		}

		if (maxLength > 0)
		{

		    if (length > maxLength)
		    {
			String error = getFormattedError(locale, Constants.ERROR_MAX_LENGTH, displayName, String.valueOf(maxLength));
			return error;
		    }
		}

		boolean containsChars = checkIfValueContainsChars(value, charsNotAllowed);

		if (containsChars)
		{
		    String error = getFormattedError(locale, Constants.ERROR_CHAR_NOT_ALLOWED, displayName, charsNotAllowed);
		    return error;
		}

		if (!numbersAllowed)
		{
		    boolean containsNumbers = checkIfStringContainsNumbers(value);
		    if (containsNumbers)
		    {
			String error = getFormattedError(locale, Constants.ERROR_NUMBER_ALLOWED, displayName, "");
			return error;
		    }
		}

		if (StringUtils.isNotBlank(regex))
		{

		    boolean matchesRegex = matchesRegex(value, regex);

		    if (!matchesRegex)
		    {
			String error = getFormattedError(locale, Constants.INVALID_INPUT, displayName, "");
			return error;
		    }
		}

		return null;
	    }

	    public boolean matchesRegex(String value, String regex)
	    {
		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	    }

	    private boolean checkIfStringContainsNumbers(String value)
	    {

		return Pattern.compile(Constants.PATTERN_ZERO_TO_NINE).matcher(value).find();

	    }

	    private boolean checkIfValueContainsChars(String value, String charsNotAllowed)
	    {

		return StringUtils.containsAny(value, charsNotAllowed);

	    }

	    public int getNumberBeforeDecimal(BigDecimal value)
	    {
		String string = value.stripTrailingZeros().toPlainString();
		int index = string.indexOf(".");

		return (index < 0) ? string.length() : string.substring(0, index).length();

	    }

	    public int getNumberOfDecimalPlaces(BigDecimal bigDecimal)
	    {
		String string = bigDecimal.stripTrailingZeros().toPlainString();
		int index = string.indexOf(".");
		return index < 0 ? 0 : string.length() - index - 1;
	    }

	    public String getFormattedError(String locale, String errorKey, String displayFieldName, String requiredValue)
	    {

		String errorString = "";//LanguageManager.getValue(locale, errorKey);

	
		return errorString;
	    }

	    private boolean isTSPast(Timestamp value)
	    {
		logger.info("THIS METHOS IS NOT IMPLEMENTED.....");
		return false;
	    }

	}



