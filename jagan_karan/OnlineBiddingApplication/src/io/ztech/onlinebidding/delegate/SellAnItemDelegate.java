package io.ztech.onlinebidding.delegate;

import io.ztech.onlinebidding.dao.IdRetrieval;
import io.ztech.onlinebidding.dao.InsertBidItemBaseLog;
import io.ztech.onlinebidding.dao.InsertBidderId;

public class SellAnItemDelegate {
	InsertBidderId insertBidderId = new InsertBidderId();
	InsertBidItemBaseLog insertBidLog = new InsertBidItemBaseLog();
	IdRetrieval idRetreive = new IdRetrieval();
	int bidderid;

	public int insertBidder(String username) throws Exception {
		try {
			int customerId = idRetreive.customerIdRetrieve(username);
			bidderid = insertBidderId.insertBidder(customerId);
			return bidderid;
		} catch (Exception e) {
			throw e;
		}
	}

	public void insertBidItemLog(int bidderId, String itemId, String categoryId, String itemName, String price,
			String starttime, String endtime) throws Exception {
		try {
			insertBidLog.insertBidItem(bidderId, itemId, categoryId, itemName, price, starttime, endtime);
		} catch (Exception e) {
			throw e;
		}
	}
}
