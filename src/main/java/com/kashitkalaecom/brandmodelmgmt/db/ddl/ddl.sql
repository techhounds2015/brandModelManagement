-- ecom.billing definition

-- Drop table

-- DROP TABLE billing;

CREATE TABLE billing (
	id varchar NOT NULL,
	createdby varchar NULL,
	createdon timestamp NULL,
	modifiedby varchar NULL,
	modifiedon timestamp NULL,
	orderId not null,
	billingaddress1 varchar NULL,
	billingaddress2 varchar NULL,
	billingcity varchar NULL,
	billingstate varchar NULL,
	billingzipcode varchar NULL,
	CONSTRAINT billing_pk PRIMARY KEY (id)
);


-- ecom.brand definition

-- Drop table

-- DROP TABLE brand;

CREATE TABLE brand (
	id varchar NOT NULL,
	createdby varchar NULL,
	createdon timestamp NULL,
	modifiedby varchar NULL,
	modifiedon timestamp NULL,
	categoryid varchar NULL,
	"name" varchar NULL,
	description varchar NULL,
	logo varchar NULL,
	status bool NULL,
	CONSTRAINT brand_pk PRIMARY KEY (id)
);


-- ecom.category definition

-- Drop table

-- DROP TABLE category;

CREATE TABLE category (
	id varchar NOT NULL,
	createdby varchar NULL,
	createdon timestamp NULL,
	modifiedby varchar NULL,
	modifiedon timestamp NULL,
	"name" varchar NULL,
	parentcategory varchar NULL,
	description varchar NULL,
	image varchar NULL,
	friendlyname varchar NULL,
	category varchar NULL,
	status bool NULL,
	CONSTRAINT category_pk PRIMARY KEY (id)
);


-- ecom.datasourceconfig definition

-- Drop table

-- DROP TABLE datasourceconfig;

CREATE TABLE datasourceconfig (
	id int8 NOT NULL,
	username varchar NULL,
	"password" varchar NULL,
	driverclassname varchar NULL,
	url varchar NULL,
	"name" varchar NULL,
	initialize bool NULL
);


-- ecom.deliveryslots definition

-- Drop table

-- DROP TABLE deliveryslots;

CREATE TABLE deliveryslots (
	id varchar NOT NULL,
	createdby varchar NULL,
	createdon timestamp NULL,
	modifiedby varchar NULL,
	modifiedon timestamp NULL,
	outletid varchar NULL,
	starthour varchar NULL,
	endhour varchar NULL,
	maxorders varchar NULL,
	sunday varchar NULL,
	monday varchar NULL,
	tuesday varchar NULL,
	wednesday varchar NULL,
	thursday varchar NULL,
	friday varchar NULL,
	saturday varchar NULL,
	alldays varchar NULL,
	status bool NULL,
	CONSTRAINT deliveryslots_pk PRIMARY KEY (id)
);


-- ecom.discount definition

-- Drop table

-- DROP TABLE discount;

CREATE TABLE discount (
	id varchar NOT NULL,
	createdon timestamp NULL,
	createdby varchar NULL,
	modifiedon timestamp NULL,
	modifiedby varchar NULL,
	code varchar NULL,
	title varchar NULL,
	discount varchar NULL,
	discounttype varchar NULL,
	maxdiscount varchar NULL,
	categoryids varchar NULL,
	minpurchase varchar NULL,
	startingdate timestamp NULL,
	expirydate timestamp NULL,
	status bool NULL,
	CONSTRAINT discount_pk PRIMARY KEY (id)
);


-- ecom.emailqueue definition

-- Drop table

-- DROP TABLE emailqueue;

CREATE TABLE emailqueue (
	id varchar NOT NULL,
	message varchar NULL,
	email varchar NULL,
	sentdatetime timestamp(0) NULL,
	status varchar NULL,
	"event" varchar NULL,
	createdon timestamp(0) NULL
);


-- ecom.homeslider definition

-- Drop table

-- DROP TABLE homeslider;

CREATE TABLE homeslider (
	id varchar NOT NULL,
	createdon timestamp NULL,
	createdby varchar NULL,
	modifiedon timestamp NULL,
	modifiedby varchar NULL,
	maxprice int8 NULL,
	minprice int8 NULL,
	brandid varchar NULL,
	categoryid varchar NULL,
	image varchar NULL,
	status bool NULL,
	CONSTRAINT homeslider_pk PRIMARY KEY (id)
);


-- ecom.inventory definition

-- Drop table

-- DROP TABLE inventory;

CREATE TABLE inventory (
	id varchar NULL,
	createdby varchar NULL,
	createdon timestamp(0) NULL,
	modifiedby varchar NULL,
	modifiedon timestamp(0) NULL,
	productid varchar NULL,
	sku varchar NULL,
	mrp float8 NULL,
	sellingprice float8 NULL,
	cgst float8 NULL,
	sgst float8 NULL,
	cess float8 NULL,
	outletid varchar NULL,
	stockavaiable int8 NULL,
	batchnumber varchar NULL,
	warehouseracknumber varchar NULL,
	vendorid varchar NULL,
	ponumber varchar NULL,
	sold int8 NULL,
	status bool NULL,
	buyingprice float8 NULL,
	mfgdate date NULL,
	expdate date NULL
);


-- ecom.masterdata definition

-- Drop table

-- DROP TABLE masterdata;

CREATE TABLE masterdata (
	id varchar NOT NULL,
	createdby varchar NULL,
	createdon timestamp NULL,
	modifiedby varchar NULL,
	modifiedon timestamp NULL,
	masterdatatype varchar NULL,
	datavalue varchar NULL,
	dataname varchar NULL,
	dataname_key varchar NULL,
	displayorder int4 NULL,
	editable bool NULL,
	status varchar NULL,
	datavaluecode varchar NULL,
	CONSTRAINT masterdata_pkey PRIMARY KEY (id)
);


-- ecom.model definition

-- Drop table

-- DROP TABLE model;

CREATE TABLE model (
	id varchar NOT NULL,
	createdby varchar NULL,
	createdon timestamp NULL,
	modifiedby varchar NULL,
	modifiedon timestamp NULL,
	categoryid varchar NULL,
	brandid varchar NULL,
	"name" varchar NULL,
	description varchar NULL,
	status bool NULL,
	CONSTRAINT model_pk PRIMARY KEY (id)
);


-- ecom.notificationtemplate definition

-- Drop table

-- DROP TABLE notificationtemplate;

CREATE TABLE notificationtemplate (
	id varchar(50) NOT NULL,
	createdby varchar(50) NULL,
	createdon timestamp NULL,
	modifiedby varchar(50) NULL,
	modifiedon timestamp NULL,
	"name" varchar(100) NULL,
	body varchar(4000) NULL,
	subject varchar(2000) NULL,
	"type" varchar(50) NULL,
	code varchar(50) NULL,
	title varchar(2000) NULL,
	status bool NULL,
	CONSTRAINT notificationtemplate_pk PRIMARY KEY (id)
);


-- ecom."order" definition

-- Drop table

-- DROP TABLE "order";

CREATE TABLE "order" (
	id varchar NOT NULL,
	createdby varchar NULL,
	createdon timestamp NULL,
	modifiedby varchar NULL,
	modifiedon varchar NULL,
	status bool NULL,
	subtotal int8 NULL,
	tax numeric NULL,
	total int8 NULL,
	created timestamp NULL,
	shippingcharge numeric NULL,
	customerid numeric NULL,
	CONSTRAINT order_pk PRIMARY KEY (id)
);


-- ecom.orderitem definition

-- Drop table

-- DROP TABLE orderitem;

CREATE TABLE orderitem (
	id varchar NOT NULL,
	orderId not null,
	createdby varchar NULL,
	createdon timestamp NULL,
	modifiedby varchar NULL,
	modifiedon timestamp NULL,
	itemid numeric NULL,
	itemquantity numeric NULL,
	itemname varchar NULL,
	itemprice varchar NULL,
	CONSTRAINT orderitem_pk PRIMARY KEY (id)
);


-- ecom.orderpaymentinfo definition

-- Drop table

-- DROP TABLE orderpaymentinfo;

CREATE TABLE orderpaymentinfo (
	id varchar NOT NULL,
	createdon timestamp NULL,
	createdby varchar NULL,
	modifiedon timestamp NULL,
	modifiedby varchar NULL,
	orderid varchar NULL,
	amount int8 NULL,
	status varchar NULL,
	referencenumber varchar NULL,
	razorpaypaymentid varchar NULL,
	razorpayorderid varchar NULL,
	razorpaysignature varchar NULL,
	fees int8 NULL
);


-- ecom.outlet definition

-- Drop table

-- DROP TABLE outlet;

CREATE TABLE outlet (
	id varchar NOT NULL,
	createdby varchar NULL,
	createdon timestamp NULL,
	modifiedby varchar NULL,
	modifiedon timestamp NULL,
	"name" varchar NULL,
	description varchar NULL,
	address varchar NULL,
	code varchar NULL,
	gstno varchar NULL,
	latitude varchar NULL,
	longitude varchar NULL,
	status bool NULL,
	CONSTRAINT outlet_pk PRIMARY KEY (id)
);


-- ecom."permission" definition

-- Drop table

-- DROP TABLE "permission";

CREATE TABLE "permission" (
	id varchar NULL,
	createdby varchar NULL,
	createdon timestamp(0) NULL,
	modifiedon timestamp(0) NULL,
	modifiedby varchar NULL,
	"name" varchar NULL,
	description varchar NULL,
	status bool NULL
);


-- ecom.pickupanddeliveryconfiguration definition

-- Drop table

-- DROP TABLE pickupanddeliveryconfiguration;

CREATE TABLE pickupanddeliveryconfiguration (
	id varchar NOT NULL,
	createdon timestamp NULL,
	createdby varchar NULL,
	modifiedon timestamp NULL,
	modifiedby varchar NULL,
	outletid varchar NULL,
	distance varchar NULL,
	dileverycharges varchar NULL,
	extraperkm varchar NULL,
	minorderamountfornodeliverycharges varchar NULL,
	deliverychargesforbeloworderamount varchar NULL,
	status bool NULL,
	CONSTRAINT pickupanddeliveryconfiguration_pk PRIMARY KEY (id)
);


-- ecom.product definition

-- Drop table

-- DROP TABLE product;

CREATE TABLE product (
	id varchar NOT NULL,
	createdby varchar NULL,
	createdon timestamp NULL,
	modifiedby varchar NULL,
	modifiedon timestamp NULL,
	categoryid varchar NULL,
	brandid varchar NULL,
	modelid varchar NULL,
	title varchar NULL,
	hsncode varchar NULL,
	vegnonvegstatus bool NULL,
	sizes int8 NULL,
	avaliableforsubscription bool NULL,
	description varchar NULL,
	maxqualitypurchased varchar NULL,
	status bool NULL,
	sku varchar NULL,
	mrp varchar NULL,
	sellingprice int8 NULL,
	cgst varchar NULL,
	sgst varchar NULL,
	cesss varchar NULL,
	outletid varchar NULL,
	markasfeatured varchar NULL,
	markasbestproduct varchar NULL,
	CONSTRAINT product_pk PRIMARY KEY (id)
);


-- ecom.productimages definition

-- Drop table

-- DROP TABLE productimages;

CREATE TABLE productimages (
	id varchar NOT NULL,
	createdby varchar NULL,
	createdon timestamp NULL,
	modifiedby varchar NULL,
	modifiedon timestamp NULL,
	productid varchar NULL,
	imagename varchar NULL,
	"size" int8 NULL,
	"path" varchar NULL,
	status bool NULL,
	CONSTRAINT productimages_pk PRIMARY KEY (id)
);


-- ecom.referral definition

-- Drop table

-- DROP TABLE referral;

CREATE TABLE referral (
	id varchar NOT NULL,
	createdon timestamp NULL,
	createdby varchar NULL,
	modifiedon timestamp NULL,
	modifiedby varchar NULL,
	code varchar NULL,
	title varchar NULL,
	discountforsender varchar NULL,
	discounttype varchar NULL,
	maxdiscount varchar NULL,
	startingdate timestamp NULL,
	expirydate timestamp NULL,
	discountforuser varchar NULL,
	status bool NULL,
	CONSTRAINT referrral_pk PRIMARY KEY (id)
);


-- ecom."role" definition

-- Drop table

-- DROP TABLE "role";

CREATE TABLE "role" (
	id varchar NULL,
	createdby varchar NULL,
	createdon timestamp(0) NULL,
	modifiedby varchar NULL,
	modifiedon timestamp(0) NULL,
	"name" varchar NULL,
	description varchar NULL,
	roleType varchar NULL,
	status bool NULL
);


-- ecom.rolepermissionmapping definition

-- Drop table

-- DROP TABLE rolepermissionmapping;

CREATE TABLE rolepermissionmapping (
	id varchar NULL,
	createdby varchar NULL,
	createdon timestamp(0) NULL,
	modifiedby varchar NULL,
	modifiedon timestamp(0) NULL,
	roleid varchar NULL,
	permissionid varchar NULL,
	"action" varchar NULL,
	status bool NULL
);


-- ecom.smsqueue definition

-- Drop table

-- DROP TABLE smsqueue;

CREATE TABLE smsqueue (
	id varchar NOT NULL,
	message varchar NULL,
	phonenumber varchar NULL,
	sentdatetime timestamp(0) NULL,
	status varchar NULL,
	"event" varchar NULL,
	createdon timestamp(0) NULL
);


-- ecom.storesetting definition

-- Drop table

-- DROP TABLE storesetting;

CREATE TABLE storesetting (
	id varchar NOT NULL,
	createdby varchar NULL,
	createdon timestamp NULL,
	modifiedby varchar NULL,
	modifiedon timestamp NULL,
	storename varchar NULL,
	address varchar NULL,
	city varchar NULL,
	state varchar NULL,
	pincode varchar NULL,
	country varchar NULL,
	supportemail varchar NULL,
	supportphonenumber varchar NULL,
	companyname varchar NULL,
	gstnumber varchar NULL,
	playstorelink varchar NULL,
	instagramlink varchar NULL,
	facebooklink varchar NULL,
	appstorelink varchar NULL,
	twitterlink varchar NULL,
	phonenumberoninvoice varchar NULL,
	whatsappnumber varchar NULL,
	logo varchar NULL,
	favicon varchar NULL,
	status bool NULL,
	CONSTRAINT storesetting_pk PRIMARY KEY (id)
);


-- ecom.users definition

-- Drop table

-- DROP TABLE users;

CREATE TABLE users (
	id varchar NOT NULL,
	createdon timestamp NULL,
	createdby varchar NULL,
	modifiedon timestamp NULL,
	modifiedby varchar NULL,
	"name" varchar NULL,
	username varchar NULL,
	email varchar NULL,
	mobile varchar NULL,
	walletid varchar NULL,
	signupdate timestamp NULL,
	status bool NULL,
	roleid varchar NULL,
	"password" varchar NULL,
	salt varchar NULL,
	forcepasswordchange bool NULL DEFAULT false,
	optedformarketing bool NULL DEFAULT false,
	deviceid varchar NULL,
	"token" varchar NULL,
	CONSTRAINT user_pk PRIMARY KEY (id)
);


-- ecom.usersaddress definition

-- Drop table

-- DROP TABLE usersaddress;

CREATE TABLE usersaddress (
	id varchar NOT NULL,
	createdon timestamp NULL,
	createdby varchar NULL,
	modifiedon timestamp NULL,
	modifiedby varchar NULL,
	userid varchar NULL,
	addresstype varchar NULL,
	address1 varchar NULL,
	address2 varchar NULL,
	pincode varchar NULL,
	city varchar NULL,
	state varchar NULL,
	country varchar NULL,
	status bool NULL DEFAULT true
);


-- ecom.userscontactinfo definition

-- Drop table

-- DROP TABLE userscontactinfo;

CREATE TABLE userscontactinfo (
	id varchar NOT NULL,
	createdon timestamp NULL,
	createdby varchar NULL,
	modifiedon timestamp NULL,
	modifiedby varchar NULL,
	userid varchar NULL,
	contacttype varchar NULL,
	primarycontact bool NULL DEFAULT false,
	contactvalue varchar NULL,
	status bool NULL DEFAULT true
);


-- ecom.validation definition

-- Drop table

-- DROP TABLE validation;

CREATE TABLE validation (
	id varchar(50) NOT NULL,
	createdby varchar(50) NULL,
	createdon timestamp NULL,
	modifiedby varchar(50) NULL,
	modifiedon timestamp NULL,
	simpleclassname varchar(200) NULL,
	fieldnameinternal varchar(200) NULL,
	fieldnamekey varchar(200) NULL,
	mandatory bool NULL,
	fieldtype varchar(100) NULL,
	minlength numeric(10) NULL,
	maxlength numeric(10) NULL,
	charsnotallowed varchar(100) NULL,
	numbersallowed bool NULL,
	lengthbeforedecimal numeric(10) NULL,
	lengthafterdecimal numeric(10) NULL,
	pastdateallowed bool NULL,
	infokey varchar(100) NULL,
	fielddisplayname varchar(100) NULL,
	fieldinfomessage varchar(100) NULL,
	regex varchar(100) NULL,
	"minvalue" varchar(50) NULL,
	"maxvalue" varchar(50) NULL,
	mobilefield bool NULL,
	"disable" bool NULL,
	CONSTRAINT validation_pk PRIMARY KEY (id)
);


-- ecom.webpages definition

-- Drop table

-- DROP TABLE webpages;

CREATE TABLE webpages (
	id varchar NOT NULL,
	createdon timestamp NULL,
	createdby varchar NULL,
	modifiedon timestamp NULL,
	modifiedby varchar NULL,
	title varchar NULL,
	"content" varchar NULL,
	status bool NULL,
	CONSTRAINT webpages_pk PRIMARY KEY (id)
);