package io.ztech.onlinebidding.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import io.ztech.onlinebidding.constant.DBFields;
import io.ztech.onlinebidding.constant.SqlQueries;
import io.ztech.onlinebidding.utils.DatabaseConfig;

public class PriceRetrieval implements SqlQueries, DBFields {
	DatabaseConfig dbConfig = new DatabaseConfig();

	public float priceRetrive(String bitItemId) throws Exception {
		float price = 0;
		Connection databaseConnection = dbConfig.getConnection();
		try {
			PreparedStatement bidItemFromLog = databaseConnection.prepareStatement(SELECT_PRICE_LOG);
			bidItemFromLog.setInt(1, Integer.parseInt(bitItemId));
			ResultSet biditemset = bidItemFromLog.executeQuery();
			while (biditemset.next()) {
				price = biditemset.getFloat(DB_PRICE);
			}
			if (price == 0) {
				PreparedStatement biditem = databaseConnection.prepareStatement(SELECT_PRICE);
				biditem.setInt(1, Integer.parseInt(bitItemId));
				ResultSet biditemset1 = biditem.executeQuery();
				while (biditemset1.next()) {
					price = biditemset1.getFloat(DB_PRICE);
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			dbConfig.closeConnection(databaseConnection);
		}
		return price;
	}
}
