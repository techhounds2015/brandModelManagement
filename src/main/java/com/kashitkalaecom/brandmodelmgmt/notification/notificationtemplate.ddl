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