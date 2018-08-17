package io.zilker.application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import io.zilker.application.beans.ApprovedProject;
import io.zilker.application.beans.AvailableProject;
import io.zilker.application.beans.Contractor;
import io.zilker.application.beans.Response;
import io.zilker.application.config.ConnectionConfig;
import io.zilker.application.constants.SQLConstants;
import io.zilker.application.logsession.ContractorLog;

public class ContractorDAO {
	private static final Logger LOGGER = Logger.getLogger(ContractorDAO.class.getName());
	public void executionResult(String firstName, boolean result) {
		if(result == true) {
			LOGGER.info("Details of "+firstName+" Not Saved");
		}else {
			LOGGER.info("Details of "+firstName+" Saved");
		}
	}
	
	public void contractorCreation(Contractor contractor) {
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		try {
			preparedStmt = con.prepareStatement(SQLConstants.CONTRACTOR_CREATION);
			preparedStmt.setString(1, contractor.name.getName());
			preparedStmt.setString(2, contractor.getPassword());
			preparedStmt.setString(3, contractor.company.getCompany());
			preparedStmt.setLong(4, contractor.revenue.getAnnualRevenue());
			preparedStmt.setInt(5, contractor.client.getNoOfClient());
			preparedStmt.setString(6, contractor.getEmail());
			executionResult(contractor.name.getName(), preparedStmt.execute());
		}catch(Exception e) {
			LOGGER.info("An Error Occured inside contractCreation");
			e.printStackTrace();
		}finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
		}
	}
	
	public ArrayList<AvailableProject> viewAvailableProjects() { 
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ArrayList<AvailableProject> listOfAvailable = new ArrayList<>();
		ResultSet rs = null;
		try {
			java.sql.Statement st = con.createStatement();
			rs = st.executeQuery(SQLConstants.SELECT_AVAILABLE_PROJ);
			while(rs.next()) {
				AvailableProject availableProject = new AvailableProject();
				availableProject.setProjectID(rs.getInt("ID"));
				availableProject.setProjectName(rs.getString("PROJ_NAME"));
				availableProject.setLocation(rs.getString("LOCATION"));
				availableProject.setDescription(rs.getString("DESCRIPTION"));
				listOfAvailable.add(availableProject);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
		}
		return listOfAvailable;
	}
	
	public ContractorLog isPresent(String email, String password) {
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		ContractorLog contractorLog = new ContractorLog();
		try {
			preparedStmt = con.prepareStatement(SQLConstants.CONTRACTOR_PRESENT);
			preparedStmt.setString(1, email);
			preparedStmt.setString(2, password);
			rs = preparedStmt.executeQuery();
			if(rs.next()) {
				contractorLog.setCONTR_ID(rs.getInt("CONTR_ID"));
				contractorLog.setContractorName(rs.getString("CONTR_NAME"));
			}else {
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
		}
		return contractorLog;
	}
	
	
	public void tenderRequest(int projectID, int CONTR_ID, Date start, Date end, long estCost) {
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		java.sql.Date startDate = new java.sql.Date(start.getTime());
		java.sql.Date endDate = new java.sql.Date(end.getTime());
		try {
			PreparedStatement preparedStmtAvailable = con.prepareStatement(SQLConstants.SELECT_UNAPPROVED);
			preparedStmtAvailable.setInt(1, projectID);
			rs = preparedStmtAvailable.executeQuery();
			rs.next();
			preparedStmt = con.prepareStatement(SQLConstants.TENDER_REQUEST);
			preparedStmt.setInt(1, CONTR_ID);
			preparedStmt.setString(2, rs.getString("PROJ_NAME"));
			preparedStmt.setDate(3, startDate);
			preparedStmt.setDate(4, endDate);
			preparedStmt.setLong(5, estCost);
			preparedStmt.setString(6, rs.getString("LOCATION"));
			preparedStmt.setString(7, rs.getString("DESCRIPTION"));
			preparedStmt.setInt(8, projectID);
			executionResult(String.valueOf(CONTR_ID), preparedStmt.execute());
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
		}
	}
	
	public ArrayList<ApprovedProject> getProjects(int contractorID) {
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		ArrayList<ApprovedProject> listOfProjects = new ArrayList<>();
		try {
			preparedStmt = con.prepareStatement(SQLConstants.CONTRACTOR_PROJECT);
			preparedStmt.setInt(1, contractorID);
			rs = preparedStmt.executeQuery();
			while(rs.next()) {
				ApprovedProject project = new ApprovedProject();
				project.setProjectID(rs.getInt("PROJ_ID"));
				project.setProjectName(rs.getString("PROJ_NAME"));
				project.setStartDate(rs.getDate("START_DATE"));
				project.setEndDate(rs.getDate("END_DATE"));
				project.setContrID(rs.getInt("CONTR_ID"));
				project.setProjectStatus(rs.getString("PROJ_STATUS"));
				project.setLocation(rs.getString("LOCATION"));
				project.setEstCost(rs.getBigDecimal("EST_COST"));
				listOfProjects.add(project);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
		}
		return listOfProjects;
	}
	
	public ArrayList<ApprovedProject> delayedProjectsDAO(int contractorID) {
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		ArrayList<ApprovedProject> listOfProject = new ArrayList<>();
		try {
			preparedStmt = con.prepareStatement(SQLConstants.GET_DELAYED_PROJ);
			preparedStmt.setString(1, "Delayed");
			preparedStmt.setInt(2, contractorID);
			rs = preparedStmt.executeQuery();
			while(rs.next()) {
				ApprovedProject project = new ApprovedProject();
				project.setProjectID(rs.getInt("PROJ_ID"));
				project.setProjectName(rs.getString("PROJ_NAME"));
				project.setStartDate(rs.getDate("START_DATE"));
				project.setEndDate(rs.getDate("END_DATE"));
				project.setContrID(rs.getInt("CONTR_ID"));
				project.setProjectStatus(rs.getString("PROJ_STATUS"));
				project.setLocation(rs.getString("LOCATION"));
				project.setEstCost(rs.getBigDecimal("EST_COST"));
				listOfProject.add(project);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
		}
		return listOfProject;
	}
	public ArrayList<ApprovedProject> viewDelayedDetail(int ID, int contractorID) {
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		ArrayList<ApprovedProject> listOfProjects = new ArrayList<>();
		try {
			preparedStmt = con.prepareStatement(SQLConstants.VIEW_DELAYED_DETAIL);
			preparedStmt.setString(1, "Delayed");
			preparedStmt.setInt(2, contractorID);
			rs = preparedStmt.executeQuery();
			while(rs.next()) {
				ApprovedProject project = new ApprovedProject();
				project.setProjectID(rs.getInt("PROJ_ID"));
				project.setProjectName(rs.getString("PROJ_NAME"));
				project.setStartDate(rs.getDate("START_DATE"));
				project.setEndDate(rs.getDate("END_DATE"));
				project.setContrID(rs.getInt("CONTR_ID"));
				project.setProjectStatus(rs.getString("PROJ_STATUS"));
				project.setLocation(rs.getString("LOCATION"));
				project.setEstCost(rs.getBigDecimal("EST_COST"));
				listOfProjects.add(project);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
		}
		return listOfProjects;
	}
	
	public void addResponseDAO(int projectID, int contractorID, String response) {
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		try {
			preparedStmt = con.prepareStatement(SQLConstants.ADD_RESPONSE);
			preparedStmt.setInt(1, projectID);
			preparedStmt.setString(2, response);
			executionResult(String.valueOf(projectID), preparedStmt.execute());
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
		}
	}
	public ArrayList<Response> displayResponse(int projectID) {
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		ArrayList<Response> responsesList = new ArrayList<>();
		try {
			preparedStmt = con.prepareStatement(SQLConstants.VIEW_RESPONSE);
			preparedStmt.setInt(1, projectID);
			rs = preparedStmt.executeQuery();
			while(rs.next()) {
				Response response = new Response();
				response.setProjectID(rs.getInt("PROJ_ID"));
				response.setResponseText(rs.getString("RESPONSE_TEXT"));
				responsesList.add(response);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
		}
		return responsesList;
	}
	
	public void projectCompleted(int projectID) {
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		try {
			preparedStmt = con.prepareStatement(SQLConstants.PROJECT_COMPLETED);
			preparedStmt.setInt(1, projectID);
			preparedStmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
		}
	}
}












