package io.ztech.expensesapp.constants;

public class QueryConstants {
	public static final String INSERT_INTO_USERS = "insert into Users(user_name,email_id,password,expense_limit) values(?,?,md5(?),?)";
	public static final String CHECK_EXISTING_USERNAME = "select count(*) from Users where user_name = ?";
	public static final String VALIDATE_USER = "select user_name, email_id, expense_limit,u_id from Users where ( user_name = ? or email_id = ? )and password = md5(?)";
	public static final String INSERT_INTO_EXPENSES = "insert into Expenses(u_id,category_id,type_id,description,amount) values(?,?,?,?,?)";
	public static final String SELECT_ALL_EXPENSES = "select category_id,type_id,description,amount from Expenses where u_id = ?";
}
