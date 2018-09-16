package io.ztech.carstats.services;

import java.sql.SQLException;

import io.ztech.carstats.beans.Specification;
import io.ztech.carstats.beans.Statistics;
import io.ztech.carstats.delegate.AddStatisticsDelegate;

public class AddStatisticsService {

	AddStatisticsDelegate addStatisticsDelegate= new AddStatisticsDelegate();
	public boolean addStatistics(Specification specification, Statistics statistics) throws SQLException {
		return addStatisticsDelegate.addStatistics(specification, statistics);
	}
}
