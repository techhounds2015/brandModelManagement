package com.kashitkalaecom.brandmodelmgmt.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class AllEnum {
	private static final Logger logger = LoggerFactory.getLogger(AllEnum.class);

	public enum FieldType {

		STRING, BOOLEAN, ENUM, DATE, NUMBER, INTEGER, UUID, LIST, MAP;
	}

	/*
	 * public enum Modules { currency, mobileApplicationSettings,
	 * currencyConversion, coa, businessProduct, authorization, charge,
	 * organization, basicUser, loanApplication, loanProduct, role, walletSettings,
	 * specialField, account, linkedproduct, linkedCard, faq, linkedAccount,
	 * cardType, cardAssociation, favourite, notification, permission, validation,
	 * Account, transaction, walletType, enums, offer, pendingRequest, collateral,
	 * loan, Offer, voucher, biller, billType, channel, billerPayment, billerType,
	 * billSubscription, bulkMapping, bulkTransaction, bulkUser, bulkUserMapping,
	 * cardTransactions, creditRating, kycDocument, loanCollateral, loanenums,
	 * loanRestructure, loanWaveOffWriteOff, MFIGroup, MFIGroupUser,
	 * notificationTemplate, recon, reconJob, savingProduct, savingProduc
dormancy, coaeod, securityQuestion, userOTP, configurationSetting,
	 * multilingual, RuleType, atmCashOut, referralOffer, atmConfig,
	 * insuranceProduct, licenseInfo, merchantTransaction, userKYC, operationsUser,
	 * someobject, login, digitalAsset, rd, fd, sa, rdProduct, fdProduct, saProduct
	 * , merchantuser , agentuser , billeruser , distributoruser , initialuser,
	 * kyccustomeruser, nonkyccustomeruser , otherbpuser , othercustomeruser ,
	 * removeduser , password , makercheckerobject, linkajaSpecific ,
	 * userSettlement, commonUser, resetPasswordByAdmin , changeRole , usersearch
	 *
	 * }
	 */

	public enum DEDUP_PARAM {
		PHONE_NUMBER, EMAIL, NATIONAL_ID
	}

	public enum APPLICATIONS {
		CORE, TRANSACTION, RECON_SETTLEMENT, LOAN, DEPOSIT, USSD_GATEWAY, INTEGRATION_BUS, CARDS, LINKAJA_API
	}

	public enum SCHEDULER_TYPE {
		EOD_PROCESS, BACKGROUND_PROCESS
	}

	public enum Modules {
		UserManagement, BulkTransactionsManagement, BulkUserManagement, BusinessProductsManagement, GeneralConfigurationManagement,
		CurrencyAndAccountTypeManagement, NotificationManagement, Reconcilation, loan, loanApplication, loanProduct,LoanManagement,
		MFIGroup, currency, collateral,FundMovementManagement,collectionManagement,CollectionWorkList,BasicPermission,SchedularManagement
;

		public static Modules[] defaultModule() {
			return new Modules[]{UserManagement, BulkTransactionsManagement, Reconcilation,
					BulkUserManagement, BusinessProductsManagement, GeneralConfigurationManagement,
					CurrencyAndAccountTypeManagement, NotificationManagement, LoanManagement, FundMovementManagement,collectionManagement,CollectionWorkList,BasicPermission.SchedularManagement
			};

		}

	}

	public enum TypeOfFintech{
		 Provider, MicroInsurance, MicroLoanGuarantee, MicroFinancing, MicroFinanceInstitution, InternetBanking, RobotAdvisor, Blockchain, OtherITProvider;
	}

	
	public enum BuyingRecordPrinciple{
		Active, Inactive;
	}

	public enum Locales {
		en_IN, en_US;
	}

	public enum ReferralStatus {
		CLAIMED, NOT_CLAIMED;
	}

	public enum GLCoreTypes {
		ASSET, LIABILITY, INCOME, EXPENDITURE;
	}

	public enum Wallet_Type {
		BUSINESS, CUSTOMER;
	}

	public enum ApprovalStatus {
		APPROVED, PENDING, SENTBACK, REJECTED, UPDATE_PENDING, UPDATE_SENTBACK, UPDATE_REJECTED, UPDATE_APPROVED,
		FAILED,PENDING_MAKER_APPROVAL,PENDING_CHECKER_APPROVAL;
	}

	public enum ComputationType {
		FIXED, PERCENTAGE;
	}

	public enum Authorizer {
		PAYER, RECEIVER, ADMIN;
	}

	public enum Operation {
		NONE, VIEW, CREATE, EXECUTE, EXECUTEREVERSE, EXECUTECHARGEBACK, CHECKER, MAKER
	}

	public enum FDC_STATUS {
		DPD,CURRENT,FDC_MANUAL_RETRY;
	}

//DEPENDENT
	public enum RoleType {
		OPERATION, MERCHANT, KYC_CUSTOMER, NON_KYC_CUSTOMER, REMOVED, INDIVIDUAL_LENDER, INSTITUTIONAL_LENDER, PARTNER,
		INDIVIDUAL_BORROWER, CORPORATE_BORROWER, BAD_CREDIT_HISTORY, BAD_CREDIT_HISTORY_FDC,COLLECTOR,COLLECTOR_SUPERVISOR,COLLECTOR_MANAGER,COLLECTOR_INQUIRY,BORROWER_WRITTEN_OFF,
		INDIVIDUAL_DISTRIBUTOR,CORPORATE_DISTRIBUTOR;
	}

	public enum OPERATION_SUB_ROLE {
		MAKER, CHECKER
	}

	public enum Gender {
		MALE, FEMALE, OTHERS;
	}

	public enum UserStatus {
		APPROVED, PENDING, REJECTED
	}

	public enum UserOperationStatus {
		BLOCKED, CLOSED, SUSPENDED, DORMANT, LOAN_RESTRICT, ACTIVE, INACTIVE, CAPPED, FROZEN;
	}

	public enum SpecialFieldDataType {
		INTEGER, STRING, BIG_DECIMAL, DATE, BOOLEAN, ENUMERATION;
	}

	public enum SpecialFieldControlUIType {
		TEXTBOX, TEXTAREA, RICH_EDITOR, RADIO, CHECKBOX, DROPDOWN;
	}

	public enum FAQ_CATEGORY {

		REGISTRATIONS, PAYMENTS;
	}

	public enum CARD_CATEGORY {

		PLATINUM, GOLD, SILVER;
	}

	public enum CARD_BILLING_CYCLE {

		MONTHLY, YEARLY;
	}

	public enum CARD_BILL_STATUS {
		NOT_GENERATED, GENERATED, PARTIALLY_PAID, FULLY_PAID, EXCESS_PAID;
	}

	public enum CARD_STATUS {

		ACTIVE, ASSIGNED, CLOSED, UNASSIGNED, BLOCKED;
	}

	public enum EMAIL_STATUS {

		SENT, PENDING, FAILED;
	}

	public enum TX_SOURCE_TYPE {

		WALLET, CARD, ACCOUNT, VOUCHER, ONUS_CARD, EXTERNAL_VOUCHER;
	}

	// maintain alphabetical order while adding new values to category enum
	public enum OFFER_CATEGORY {
		APPARELS, ELECTRONICS, FOOD, OTHERS
	}

	public enum CODE_TYPE {
		ONCE, UNLIMITED, RESTRICTED;
	}

	public enum OFFER_RECEIVER {

		SOURCE, DESTINATION;
	}

	public enum WEEK_DAYS {
		MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
	}

	public enum CurrencyType {
		STANDARD, CUSTOM
	}

	public enum ChargeType {
		SOURCE, FIXED, BUSINESS, DESTINATION, SOURCE_OTHER_WALLET, DESTINATION_OTHER_WALLET
	}

	public enum QUEUE_STATUS {
		PENDING, ERROR, PROCESSED;
	}

	public enum CARD_TYPE {
		AMERICAN_EXPRESS, VISA, MASTERCARD, DISCOVER, DINERS_CLUB, JCB_15, JCB_16;
	}

	public enum KYCDocumentType {
		PASSPORT, NATIONAL_ID, OTHERS, NATIONAL_FRONT, NATIONAL_BACK, SELFIE, SIGNATURE;
	}

	public enum DORMANCY_SCHEDULER_STATUS {
		SCHEDULED, EXECUTED;
	}


	public enum ACCOUNT_PRODUCT_TYPE {
		WALLET, LOAN_ACCOUNT, SAVING_ACCOUNT, RECURRING_DEPOSIT, FIXED_DEPOSIT, CARD_ACCOUNT;
	}

	public enum Type_Of_Proof {
		ADDRESS_PROOF, ID_PROOF, OTHERS,DRIVER_LICENSE,KITAS,KKS,NATIONAL_Id,NIK,PASSPORT,STUDENT_CARD;
	}

	public enum DEDUCTION_TYPE {
		ALL, CREDIT, DEBIT;

	}

	public enum REQUEST_MONEY_TYPE {
		SENT, RECEIVED;

	}

	public enum FILE_FORMAT {
		JPG, JPEG, TIF, PNG, PDF;

	}

	public enum LOAN_TYPE {
		SINGLE_PAYMENT, MULTIPLE_PAYMENT;
	}

	public enum VOUCHER_CODE_STATUS {

		VALID, EXPIRED, CLAIMED, REVERSED, PARTIALLY_CLAIMED
	}

	public enum CURRENCY_DENOMINATIONS {

		THOUSAND, TWO_THOUSAND;
	}


	public enum REMAINING_VOUCHER_STATUS {
		PAID, NOT_PAID
	}
	
	
	public enum ACCOUNT_ENTRY_TYPE {
		ALL, CREDIT, DEBIT;
	}

	public enum CHANGE_ROLE_STATUS {
		PENDING, APPROVED, REJECTED

	}

	public enum BP_CATEGORY {
		PAYMENT, BILLPAY, VOUCHER_CREATION
	}

	public enum TRX_MODE_OF_AUTH {
		OTP, DIRECT
	}

	public enum TRX_INTEGRATION_STRATEGY {
		TRX_FIRST, ONLY_CHECK_FIRST, RESERVE_BALANCE
	}

	public enum CHARGE_APPLICABILITY_STRATEGY {
		USER_ID, ROLE_ID, USER_SEGMENT
	}

	public enum NOTIFICATION_TYPE {
		SMS, EMAIL;

	}

	public enum NOTIFICATION_STATUS {
		SENT, DELIVERED, FAILED, QUEUED;

	}

	public enum NOTIFICATION_CATEGORY {
		OPERATIONAL, TRANSACTIONAL, MARKETING, GENERAL;
	}

	public enum WALLET_BALANCE_PRODUCT_TYPE {
		WALLET, LOAN_ACCOUNT, SAVING_ACCOUNT, RECURRING_DEPOSIT, FIXED_DEPOSIT, CARD_ACCOUNT;
	}

	public enum WalletType {
		BUSINESS, CUSTOMER;
	}

	public enum LENDER_USER_TYPE {
		PERSON, LEGAL_ENTITY;
	}

	public enum IDENTITY_TYPE {
		ID_CARD, TAXPAYER_IDENTIFICATION_NUMBER, NOTARIAL_DEED;
	}

	public enum LEGAL_IDENTITY_TYPE_LEGAL_ENTITY {
		LIMITED_COMPANY, COOPERATIVE, CENTRAL_GOVERNMENT, OTHERS, INDIVIDUAL;
	}

	public enum LEGAL_IDENTITY_TYPE_INDIVIDUAL {
		INDIVIDUAL;
	}

	public enum RELIGION {
		ISLAM, CATHOLIC, PROTESTENT, HINDU, BUDDHIST, CONFUCIAN, OTHERS, LEGAL_ENTITY;
	}


	public enum MARITAL_STATUS {
		MARRIED, NOT_MARRIED, LEGAL_ENTITY;
	}
	
 
	public enum PROFESSION {
		CIVIL_SERVANTS, NATIONAL_ARMED_FORCES, STATE_OWNED_ENTERPRISE_EMPLOYEES, PRIVATE_EMPLOYEES, ENTREPRENEUR,
		STUDENTS, OTHERS, NOT_WORKING, LEGAL_ENTITY;
	}

	public enum WORK_EXPERIENCE_LEGAL_ENTITY {
		ESTABLISHMENT_PERIOD, LEGAL_ENTITY;
	}

	public enum WORK_EXPERIENCE_INDIVIDUAL {
		STUDENTS_OR_NEVER_WORKING, LESS_THAT_1_YEAR, BETWEEN_1_AND_2_YEARS, BETWEEN_2_AND_3_YEARS, GREATER_THAN_3_YEARS,
		LEGAL_ENTITY;
	}

	public enum EDUCATION {
		PRIMARY_SCHOOL, JUNIOR_HIGH_SCHOOL, SENIOR_HIGH_SCHOOL, DIPLOMA, BACHELOR, MASTER, DOCKTORATE, LEGAL_ENTITY;

	}

	public enum LEGAL_ENTITY {
		PT, CV, OTHERS;
	}

	public enum NUMBER_OF_EMPLOYEES {
		ONE_TO_FIFTY, FIFTYONE_TO_TWO_HUNDRED, GREATER_THAN_200
	}

	public enum BORROWER_TYPE {
		INDIVIDUAL, CORPORATE
	}

	public enum RESIDENTIAL_STATUS {
		RENTED, OWNER;
	}

	public enum KYC_STATUS {
		PENDING, COMPLETED, REJECTED, FAILED, NOT_INITIATED, SOFT_REJECTED, HARD_REJECTED
	}

	public enum DIGI_SIGN_ACCOUNT_STATUS {
		FAILED, CREATED, PENDING
	}

	public enum USER_REGISTRATION_STAGES {
		KYC, DIGISIGN
	}

	public enum USER_WORK_FLOW_TYPE {
		KYC_CHECK, AML_CHECK, DIGI_SIGN_REGISTRATION,FDC_CHECK
	}

	public enum LOAN_CHECK_WORK_FLOW_TYPE {

		BORROWER_TRANSACTION_CHECK, FDC_INQUIRY, PEFINDO_REPORT, PEFINDO_SEARCH_USER, INITIATED,
		PENDING_LENDER_APPROVAL, LIMIT_CALCULATED, P2P_REJECTED, CONFIRM_TO_FUND, LOAN_DOCUMENT_TO_DIGISIGN, FAILED,
		COMPLETED, PENDING, RDL_DISBURSEMENT_COMPLETED, P2P_DISBURSEMENT_COMPLETED, ESCROW_LOAN_DISBURSEMENT_COMPLETED,
		DISBURSESED , CORE_PAYMENT_ESCROW_LOAN_DISBURSEMENT_COMPLETED,P2P_DISBURSEMENT_INITIATED,RDL_DISBURSEMENT_FAILED,CORE_PAYMENT_ESCROW_LOAN_DISBURSEMENT_FAILED,
		CREDIT_MANUAL_RETRY
	}

	public enum EVENT_NAME {
		LOAN_WORK_FLOW_START, LOAN_WORK_FLOW_PEFINDO, LOAN_WORK_FLOW_FDC, LOAN_WORK_FLOW_USER_TRANSACTION,
		LOAN_WORK_FLOW_CREDIT_RULE, LOAN_WORK_FLOW_NON_CREDIT_RULE, DIGISIGN_REGISTRATION, LOAN_WORK_FLOW_NEXT,
		PEFINDO_REJECTION_NOTIFICATION, P2P_REJECTION_NOTIFICATION, LENDER_APPROVAL, LENDER_ACCOUNT_TOPUP_CALLBACK,
		LENDER_DISBURSMENT_TO_ESCROW_CALLBACK, LENDER_VA_ESCROW__PAID_CALLBACK, LOAN_WORK_FLOW_LIMIT_CAL,
		IM_LOAN_DISBURSEMENT_CALLBACK, IM_LENDER_VA_OPERATIONAL_PAID_CALLBACK, IM_RDL_TOPUP_CALLBACK,
		IM_LENDER_VA_ESCROW_PAID_CALLBACK, IM_RDL_DISBURSEMENT_CALLBACK, IM_VA_ACTIVATION,
		IM_LENDER_LOAN_DISBURSEMENT_CALLBACK, LOAN_AGREEMENT_TO_DIGISIGN, REPAYMENT_CALLBACK, WITHDRAW_CALLBACK,
		READY_FOR_DISBURSEMENT,INITIATE_OPERATIONAL_ESCROW_DISBURSEMENT,LOAN_DISBURSEMENT_EWALLET, REPAYMENT_INITIATED, IM_ESCROW_CORE_PAYMENT_TO_CORE_LENDING,
		LOAN_CREATION_AFTER_DIGISIGN_COMPLETED, REPAYMENT_TRANSFER_TO_OPERATIONAL_ACCOUNT, REPAYMENT_TRANSFER_TO_RDL_ACCOUNT,RDL_REPAYMENT_CALLBACK,IM_LENDER_EXISTING_REGISTER,
		LOAN_AGREEMENT_TO_BORROWER_DIGISIGN,PEFINDO_REJECTION,REQUEST_LOGGING,FDC_REJECTION, CREDIT_MANUAL_RETRY,BORROWER_FDC_CREDIT_MANUAL_RETRY,INSURANCE_MONEY_CALLBACK,
		RDL_INSURANCE__CALLBACK,PROCESS_INSURANCE_CLAIM

	}

	public enum INSTAMONEY_STATUS {
		ACTIVE, FAILED, PENDING, SETTLED,COMPLETED
	}



	public enum INSTAMONEY_PAYMENT_TYPE {
		REPAYMENT, LOAN, WITHDRAWL
	}


	public enum DISBURSEMENT_BATCH_STATUS {
		INITIATED, FAILED, COMPLETED, PENDING, RDL_DISBURSEMENT_COMPLETED, P2P_DISBURSEMENT_COMPLETED,
		ESCROW_LOAN_DISBURSEMENT_COMPLETED, DISBURSESED, CORE_PAYMENT_ESCROW_LOAN_DISBURSEMENT_COMPLETED,ESCROW_LOAN_DISBURSEMENT_INITIATED,P2P_DISBURSEMENT_INITIATED,
		RDL_DISBURSEMENT_INITIATED,RDL_DISBURSEMENT_FAILED,CORE_PAYMENT_ESCROW_LOAN_DISBURSEMENT_FAILED
	}

	public enum TRANSACTION_TYPE {
		CREDIT, DEBIT
	}

	public enum IM_CUSTOMER_TYPE {
		LENDER, BORROWER, ADMIN
	}

	public enum IM_STATUS {
		COMPLETED, SETTLED, SETTLING, PENDING, SUCCESS, FAILURE,FAILED
	}

	

	public enum BUSINESS_PRODUCT_SEARCH {
		BPCODE, NAME, DATE;
	}

	public enum CALLBACKTYPE{
		DIGISIGN_DOCUMENT_EXIST,DIGISIGN_DOCUMENT_STATUS_UPDATE,DIGISIGN_ACCOUNT_ACTIVATE

	}

	public enum LOAN_NOTIFICATION_CONFIGUARTION_TYPE{
		LOAN_DUE_PAYMENT,LOAN_LATE_PAYMENT
	}
	
	public enum NOTIFICATION_CODE{
		PAS001,LAS001,BAS001,BAS002,LKYCA001,LKYCA002,LKYCNP001,LKYCNP002,LPS002,LPS001,LRML001,LRML002;
	}
	
	public enum Last_Collection_Activity{
		Outgoing_Call, Incoming_Call, Whatsapp_Messaging, Field_Visit ;
	}
	
	public enum Last_Contacted{
		Borrower, Spouse, Pegawai,Rekanan, Wrong_Number, Emergency_Contact, No_Answer, Rejected, Busy_Tone, Invalid

	}
	
	public enum Latest_Collection_Activity_Summary{
		Promise_Taken, Delay_Payment , Refuse_to_Pay, Left_Messag, Avoidance, Bankrupt, Skip, Suspect_Fraud, Deceased;
	}

   
    public enum WHITELIST_SEGMENT
    {
    	PROMOTION_01,PROMOTION_02,PROMOTION_TEST,PROMOTION_MA_PLN,PROMOTION_MA_CHATTIME,PROMOTION_REMITTANCE_CASHOUT,PROMOTION_PLNPRA,
    	PROMOTION_PURCHASETAP,PROMOTION_CASHIN,PROMOTION_AIRTIME_10k,PROMOTION_BTPN_WOW,PROMOTION_BANGTCASH
    }
    


    public enum COLLECTION_LOAN_STATE{
    	NORMAL, MORNING, NIGHT, PROMISE, BROKEN_PROMISE, REVIEW , HOLD, REFER   
    }
 

    public enum COLLECTION_LOAN_SUPERVISOR_STATE{
    	NORMAL, MORNING, NIGHT, PROMISE, BROKEN_PROMISE, HOLD, REFER
    }
   
    
    public enum COLLECTION_LOAN_COLLECTOR_STATE{
    	REVIEW
    }

    public enum COLLECTION_LOAN_PHONE{
    	OUTGOING_CALL, INCOMING_CALL
    }

    public enum COLLECTION_LOAN_NOTICE{
    	WHATSAPP, SMS, EMAIL, FIELD_VISIT, OTHER
    }
   
    
    public enum COLLECTION_WORKED_STATUS{
    	WORKED,UNWORKED,UNCONTACTED
    }
     
    public enum COLLECTION_LOAN_CONTACT{
    	BORROWER, SPOUSE, PEGAWAI_REKANAN,  EMERGENCY_CONTACT, WRONG_NUMBER, NO_ANSWER, REJECTED, BUSY_TONE , INVALID
    }

    public enum COLLECTION_LOAN_SUMMARY{
    	NO_CONTACT, PROMISE_TAKEN, DELAY_PAYMENT, REFUSE_TO_PAY, LEFT_MESSAGE, AVOIDANCE, BANKRUPT, SKIP, SUSPECT_FRAUD, DECEASED
    }

    public enum COLLECTION_PHONE_TYPE{
    	MOBILE,HOME,OFFICE,EMERGENCY,FAX,WHATSAPP
    }

    public enum COLLECTION_ADDRESS_TYPE{
    	HOME_DEFAULT, HOME_DOMICILE, OFFICE, OTHER
    }
    
    public enum COLLECTION_COMMUNICATION_TYPE
    {
    	SEND, RECEIVE;
    }
 

    public enum COLLECTION_WORK_STATUS
    {
    	WORKED, UNWORKED, UNCONTACTED;
    }
    
    public enum COLLECTION_BUCKET
    {
    	GRACE_PERIOD, FRONT_END, MIDRANGE, BACK_END;
    }
    
    
    public enum COLLECTION_INSURANCE_CLAIM
    {
    	OPEN, DRAFT, PENDING, APPROVED, REJECTED;
    }
    
    public enum COLLECTION_INSURANCE_CLAIM_WRITEOFFSTATUSLIST
    {
    	CLOSED, WRITE_OFF;
    }
    
    public enum COLLECTION_INSURANCE_CLAIM_DECISIONLIST
    {
    	APPROVED, REJECTED;
    }
   

    public enum STAGE_PROCESS
    {
		CREDIT_MANUAL_RETRY_FDC, CREDIT_MANUAL_RETRY_PEFINDO, CREDIT_MANUAL_RETRY_LIMIT_CALCULATE,
		BORROWER_FDC_CREDIT_MANUAL_RETRY;
    }

    public enum MANUAL_CREDIT_DOC_TYPE{
		MAKER_DOC,CHECKER_DOC
	}
    
    public enum KYC_DIGISIGN_STATUS{
    	FAILED,SUCCESS
    }
}
