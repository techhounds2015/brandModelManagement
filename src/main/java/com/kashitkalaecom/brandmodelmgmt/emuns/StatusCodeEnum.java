package com.kashitkalaecom.brandmodelmgmt.emuns;

public enum StatusCodeEnum {
	
    // Brand
    BRAND_CREATED("1001","Brand Created Successfully."),
    BRAND_UPDATED("1002","Brand Updated Successfully."),
    BRAND_CREATION_FAILED("1003","Failed to Create Brand."),
    BRAND_UPDATION_FAILED("1004","Failed to Update Brand."),
    
    // Model
    MODEL_CREATED("2001","Model Created Successfully."),
    MODEL_UPDATED("2002","Model Updated Successfully."),
    MODEL_CREATION_FAILED("2003","Failed to Create Model."),
    MODEL_UPDATION_FAILED("2004","Failed to Update Model."),
    
    
    // Category
    CATEGORY_CREATED("3001","Category Created Successfully."),
    CATEGORY_UPDATED("3002","Category Updated Successfully."),
    CATEGORY_CREATION_FAILED("3003","Failed to Create Category."),
    CATEGORY_UPDATION_FAILED("3004","Failed to Update Category."),
	
	// Outlet
    OUTLET_CREATED("4001","Outlet Created Successfully."),
    OUTLET_UPDATED("4002","Outlet Updated Successfully."),
    OUTLET_CREATION_FAILED("4003","Failed to Create Outlet."),
    OUTLET_UPDATION_FAILED("4004","Failed to Update Outlet."),
	
	// Product
    PRODUCT_CREATED("5001","Product Created Successfully."),
    PRODUCT_UPDATED("5002","Product Updated Successfully."),
    PRODUCT_CREATION_FAILED("5003","Failed to Create Product."),
    PRODUCT_UPDATION_FAILED("5004","Failed to Update Product.");


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
    
    StatusCodeEnum(Integer type, String desc) {
        this.type = type;
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
