package com.kashitkalaecom.brandmodelmgmt.emuns;

public enum StatusCodeEnum {
	
    // Brand
    BRAND_CREATED("1001","Brand Created Successfully."),
    BRAND_UPDATED("1002","Brand Updated Successfully."),
    BRAND_CREATION_FAILED("1003","Failed to Create Brand."),
    BRAND_UPDATION_FAILED("1004","Failed to Update Brand."),
    BRAND_NOT_EXISTS("1005","Brand doesn't exists."),
    
    // Model
    MODEL_CREATED("2001","Model Created Successfully."),
    MODEL_UPDATED("2002","Model Updated Successfully."),
    MODEL_CREATION_FAILED("2003","Failed to Create Model."),
    MODEL_UPDATION_FAILED("2004","Failed to Update Model."),
    MODEL_NOT_EXISTS("2005","Model doesn't exists."),
    
    
    // Category
    CATEGORY_CREATED("3001","Category Created Successfully."),
    CATEGORY_UPDATED("3002","Category Updated Successfully."),
    CATEGORY_CREATION_FAILED("3003","Failed to Create Category."),
    CATEGORY_UPDATION_FAILED("3004","Failed to Update Category."),
    CATEGORY_NOT_EXISTS("3005","Category doesn't exists."),
    CATEGORY_INVALID("3006","Invalid Category."),
    CATEGORY_DUPLICATE("3007","Category already exists."),
	
	// Outlet
    OUTLET_CREATED("4001","Outlet Created Successfully."),
    OUTLET_UPDATED("4002","Outlet Updated Successfully."),
    OUTLET_CREATION_FAILED("4003","Failed to Create Outlet."),
    OUTLET_UPDATION_FAILED("4004","Failed to Update Outlet."),
    OUTLET_NOT_EXISTS("4005","Outlet doesn't exists."),
	
	// Product
    PRODUCT_CREATED("5001","Product Created Successfully."),
    PRODUCT_UPDATED("5002","Product Updated Successfully."),
    PRODUCT_CREATION_FAILED("5003","Failed to Create Product."),
    PRODUCT_UPDATION_FAILED("5004","Failed to Update Product."),
    PRODUCT_NOT_EXISTS("5005","Product doesn't exists."),
	
	// Discount Code
    DISCOUNT_CODE_CREATED("6001","Discount Code Created Successfully."),
    DISCOUNT_CODE_UPDATED("6002","Discount Code Updated Successfully."),
    DISCOUNT_CODE_CREATION_FAILED("6003","Failed to Create Discount Code."),
    DISCOUNT_CODE_UPDATION_FAILED("6004","Failed to Update Discount Code."),
    DISCOUNT_CODE_NOT_EXISTS("6005","Discount Code doesn't exists."),
	
	// Referral Code
    REFERRAL_CODE_CREATED("7001","Referral Code Created Successfully."),
    REFERRAL_CODE_UPDATED("7002","Referral Code Updated Successfully."),
    REFERRAL_CODE_CREATION_FAILED("7003","Failed to Create Referral Code."),
    REFERRAL_CODE_UPDATION_FAILED("7004","Failed to Update Referral Code."),
	
	// User
    USER_CREATED("8001","User Created Successfully."),
    USER_UPDATED("8002","User Updated Successfully."),
    USER_CREATION_FAILED("8003","Failed to Create User."),
    USER_UPDATION_FAILED("8004","Failed to Update User."),
    USER_NOT_EXISTS("8005","User doesn't exists."),
    USER_INVALID("8006","Invalid Category."),
	
	// Role
    ROLE_CREATED("9001","Role Created Successfully."),
    ROLE_UPDATED("9002","Role Updated Successfully."),
    ROLE_CREATION_FAILED("9003","Failed to Create Role."),
    ROLE_UPDATION_FAILED("9004","Failed to Update Role."),
    ROLE_NOT_EXISTS("9005","Role doesn't exists."),
	
	// Delivery Configuration
    DELIVERY_CONFIG_CREATED("1101","Delivery Configuration Created Successfully."),
    DELIVERY_CONFIG_UPDATED("1102","Delivery Configuration Updated Successfully."),
    DELIVERY_CONFIG_CREATION_FAILED("1103","Failed to Create Delivery Configuration."),
    DELIVERY_CONFIG_UPDATION_FAILED("1104","Failed to Update Delivery Configuration."),
    DELIVERY_CONFIG_NOT_EXISTS("1105","Failed to Update Delivery Configuration."),


	
	// Error while validating request 
    ERROR_ON_VALIDATING_REQUEST("8000","Error while validating request."),
	
	// Invalid Request
    INVALID_REQUEST("1010","Invalid Request."),
	
	// Tenant Code Missing
    TENANT_CODE_MISSING("9000","tenantCode missing from Request header."),
	
	//Common Message for Error while getting records
	ERROR_WHILE_RETREVING_DATA("7000","Error while getting data");
	
	
    private String code;
    private Integer type;
    private String desc;

    public Integer getType() {
		return type;
	}

	StatusCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    

    /**
     * gets the http status code
     *
     * @return the status code number
     */
    public String getCode() {
        return code;
    }

    /**
     * get the description
     *
     * @return the description of the status code
     */
    public String getDesc() {
        return desc;
    }

}
