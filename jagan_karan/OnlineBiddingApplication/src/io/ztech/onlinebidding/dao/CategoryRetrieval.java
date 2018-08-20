package io.ztech.onlinebidding.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import io.ztech.onlinebidding.constant.ConstantDisplayStatement;
import io.ztech.onlinebidding.constant.DBFields;
import io.ztech.onlinebidding.constant.SqlQueries;
import io.ztech.onlinebidding.utils.DatabaseConfig;

public class CategoryRetrieval implements SqlQueries, ConstantDisplayStatement, DBFields {
	DatabaseConfig dbConfig = new DatabaseConfig();
	HashMap<String, String> categoryList = new HashMap<>();

	public HashMap<String, String> retreiveCategory() throws Exception {
		Connection databaseConnection = dbConfig.getConnection();
		try {
			PreparedStatement selectCategory = databaseConnection.prepareStatement(SELECT_CATEGORY);
			ResultSet category = selectCategory.executeQuery();
			while (category.next()) {
				categoryList.put(Integer.toString(category.getInt(DB_CATEGORY_ID)),
						category.getString(DB_CATEGORY_NAME));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			dbConfig.closeConnection(databaseConnection);
		}
		return categoryList;
	}
}
