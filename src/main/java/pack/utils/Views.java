package pack.utils;

public class Views {
	
	//Admin
	public static String TBL_ADMIN = "admin";
	public static String COL_ADMIN_ID = "admin";
	public static String COL_ADMIN_USERNAME = "username";
	public static String COL_ADMIN_PASSWORD = "password";
	public static String COL_ADMIN_EMAIL = "email";
	public static String COL_ADMIN_CREATEDATE = "created_at";
	
	//USER
	public static String TBL_USER = "users";
	public static String COL_USER_ID = "id";
	public static String COL_USER_USERNAME = "username";
	public static String COL_USER_PASSWORD = "password";
	public static String COL_USER_EMAIL = "email";
	public static String COL_USER_PHONE = "phone";
	public static String COL_USER_ADDRESS = "address";
	public static String COL_USER_IMAGES = "images";
	public static String COL_USER_CREATEDATE = "created_at";
	
	//ARTICLES
	public static String TBL_ARTICLE = "articles";
	public static String COL_ARTICLE_ID = "id";
	public static String COL_ARTICLE_TITLE = "title";
	public static String COL_ARTICLE_CONTENT = "content";
	public static String COL_ARTICLE_IMAGES = "images";
	public static String COL_ARTICLE_CREATEDATE = "created_at";
	public static String COL_ARTICLE_UPDATE_DATE = "updated_at";
	
	//ORDERS
	public static String TBL_ORDER = "orders";
	public static String COL_ORDERS_ID = "id";
	public static String COL_ORDERS_USER_ID = "user_id";
	public static String COL_ORDERS_PRICE = "price";
	public static String COL_ORDERS_START_DATE = "start_date";
	public static String COL_ORDERS_CREATEDATE = "created_at";
	public static String COL_ORDERS_STATUS = "status";
	
	//ORDER_DETAILS
	public static String TBL_ORDER_DETAIL = "order_details";
	public static String COL_ORDER_DETAIL_ID = "id";
	public static String COL_ORDER_DETAIL_ORDER_ID = "order_id";
	public static String COL_ORDER_DETAIL_SERVICE_ID = "service_id";
	public static String COL_ORDER_DETAIL_PRICE = "price";
	public static String COL_ORDER_DETAIL_START_DATE = "start_date";
	public static String COL_ORDER_DETAIL_COMPLETED_DATE = "completed_date";
	public static String COL_ORDER_DETAIL_CREATEDATE = "created_at";
	public static String COL_ORDER_DETAIL_STATUS = "status";
	
	//CONFIRM_IMAGES
	public static String TBL_CONFIRM_IMAGES = "confirm_images";
	public static String COL_CONFIRM_IMAGES_ID = "id";
	public static String COL_CONFIRM_IMAGES_DETAIL_ID = "detail_id";
	public static String COL_CONFIRM_IMAGES_IMAGES = "images";
	public static String COL_CONFIRM_IMAGES_NOTE= "note";
	public static String COL_CONFIRM_IMAGES_UPDATE_DATE = "uploaded_at";
	
	//STAFFS
	public static String TBL_STAFFS = "staffs";
	public static String COL_STAFFS_ID = "id";
	public static String COL_STAFFS_USERNAME = "username";
	public static String COL_STAFFS_PASSWORD = "password";
	public static String COL_STAFFS_EMAIL = "email";
	public static String COL_STAFFS_PHONE = "phone";
	public static String COL_STAFFS_JOB_LIMIT = "job_limit";
	public static String COL_STAFFS_IMAGES = "images";
	public static String COL_STAFFS_CREATEDATE = "created_at";
	public static String COL_STAFFS_STATUS = "status";
	
	//SCHEDULES
	public static String TBL_SCHEDULES = "schedules";
	public static String COL_SCHEDULES_ID = "id";
	public static String COL_SCHEDULES_STAFF_ID = "staff_id";
	public static String COL_SCHEDULES_DETAIL_ID = "detail_id";
	public static String COL_SCHEDULES_START_AT = "start_at";
	public static String COL_SCHEDULES_END_AT = "end_at";
	public static String COL_SCHEDULES_STATUS = "status";
	
	//REQUESTS
	public static String TBL_REQUESTS = "requests";
	public static String COL_REQUESTS_ID = "id";
	public static String COL_REQUESTS_SCHEDULE_ID = "schedule_id";
	public static String COL_REQUESTS_DATE_ADJUST = "date_adjust";
	public static String COL_REQUESTS_PRICE_ADJUST = "price_adjust";
	public static String COL_REQUESTS_REASON = "reason";
	public static String COL_REQUESTS_DATE_STATUS = "date_status";
	public static String COL_REQUESTS_PRICE_STATUS = "price_status";
	
	//PAYMENT_ACCOUNTS
	public static String TBL_PAYMENT_ACCOUNTS = "payment_accounts";
	public static String COL_PAYMENT_ACCOUNTS_ID = "id";
	public static String COL_PAYMENT_ACCOUNTS_ACCOUNT_NUMBER = "account_number";
	public static String COL_PAYMENT_ACCOUNTS_ACCOUNT_NAME = "account_name";
	public static String COL_PAYMENT_ACCOUNTS_BANK_NAME = "bank_name";
	
	//PAYMENTS
	public static String TBL_PAYMENTS = "payments";
	public static String COL_PAYMENTS_ID = "id";
	public static String COL_PAYMENTS_PAYACC_ID = "payAcc_id";
	public static String COL_PAYMENTS_ORDER_ID = "order_id";
	public static String COL_PAYMENTS_AMOUNT = "amount";
	public static String COL_PAYMENTS_PAID_DATE = "paid_date";
}
