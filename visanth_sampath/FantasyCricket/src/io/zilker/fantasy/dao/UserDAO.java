package io.zilker.fantasy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import io.zilker.fantasy.bean.Match;
import io.zilker.fantasy.bean.Message;
import io.zilker.fantasy.bean.Player;
import io.zilker.fantasy.bean.ResultBoard;
import io.zilker.fantasy.bean.UserPickedTeam;
import io.zilker.fantasy.constants.SqlConstants;
import io.zilker.fantasy.service.UserService;

public class UserDAO {
	public Connection connection;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	DbConfig dbConfig = new DbConfig();
	UserService userService = new UserService();
	UserPickedTeam userPickedTeam = new UserPickedTeam();
	private ArrayList<Integer> players = new ArrayList<Integer>();
	private ArrayList<Integer> credits = new ArrayList<Integer>();
	ResultBoard resultBoard = new ResultBoard();

	public String getRole(int playerId) {
		// TODO Auto-generated method stub
		String role = null;
		try {
			connection = dbConfig.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.SELECT_ROLE);
			preparedStatement.setInt(1, playerId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				role = resultSet.getString("type");
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbConfig.closeConnection(connection, preparedStatement, resultSet);
		}
		return role;
	}

	public int getPlayerCredits(int playerId) {
		// TODO Auto-generated method stub
		int rating = 0;
		try {
			connection = dbConfig.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.SELECT_ROLE);
			preparedStatement.setInt(1, playerId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				rating = resultSet.getInt("rating");
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbConfig.closeConnection(connection, preparedStatement, resultSet);
		}

		return rating;
	}

	// display active matches
	public void displayActiveMatches() {
		// TODO Auto-generated method stub
		try {
			AdminDAO adminDAO = new AdminDAO();
			adminDAO.listActiveMatches();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbConfig.closeConnection(connection, preparedStatement, resultSet);
		}
	}

	// selects from match table and set the match bean
	public Match setMatchBean(int matchId) {
		// TODO Auto-generated method stub
		Match match = new Match();
		try {
			connection = dbConfig.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.SELECT_MATCH);
			preparedStatement.setInt(1, matchId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				match.setMatchTable(resultSet.getString("team1"), resultSet.getString("team2"),
						resultSet.getString("scheduled_date"), resultSet.getString("start_time"),
						resultSet.getString("end_time"), resultSet.getInt("match_credits"));
			}

		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbConfig.closeConnection(connection, preparedStatement, resultSet);
		}
		return match;
	}

	public ArrayList<Player> displayTeam(String teamName) {
		// TODO Auto-generated method stub
		ArrayList<Player> playersList = new ArrayList<Player>();
		try {
			connection = dbConfig.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.SELECT_TEAM);
			preparedStatement.setString(1, teamName);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Player player = new Player();
				player.setPlayer(resultSet.getString("player_name"), "team",resultSet.getString("type") , resultSet.getInt("rating"));
				player.setPlayerId(resultSet.getInt("player_id"));
				playersList.add(player);
				/*userService.printPlayers(resultSet.getInt("player_id"), resultSet.getString("player_name"),
						resultSet.getString("type"), resultSet.getInt("rating"));*/
				
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbConfig.closeConnection(connection, preparedStatement, resultSet);
		}
		return playersList;
	}

	// add the team one by one
	public void addPlayerToTeam(int userId, int matchId, int playerId, int credits) {
		// TODO Auto-generated method stub
		try {
			connection = dbConfig.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.PLAYERS_PICKED_INSERT);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, matchId);
			preparedStatement.setInt(3, playerId);
			preparedStatement.setInt(4, credits);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbConfig.closeConnection(connection, preparedStatement, resultSet);
		}
	}

	//
	public void insertIntoChat(int userId, String message) {
		// TODO Auto-generated method stub
		try {
			connection = dbConfig.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.CHAT_INSERT);
			preparedStatement.setInt(1, userId);
			preparedStatement.setString(2, message);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbConfig.closeConnection(connection, preparedStatement, resultSet);
		}

	}

	public ArrayList<Message> displayMessages() {
		// TODO Auto-generated method stub
		ArrayList<Message> messages = new ArrayList<Message>();
		try {
			connection = dbConfig.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.CHAT_MESSAGES);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Message message = new Message();
				message.setMessage(resultSet.getString("users.user_name"), resultSet.getString("message"), resultSet.getString("inserted_time"));
				/*userService.printMessages(resultSet.getString("users.user_name"), resultSet.getString("message"),
						resultSet.getString("inserted_time"));*/
				messages.add(message);
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbConfig.closeConnection(connection, preparedStatement, resultSet);
		}
		return messages;
	}

	public ArrayList <Player> displaySelectedTeam(int matchId, int userId) {
		// TODO Auto-generated method stub
		ArrayList <Player> playersList = new ArrayList<Player> ();
		try {
			connection = dbConfig.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.PLAYER_SELECTED_TEAM);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, matchId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Player player = new Player();
				player.setPlayer(resultSet.getString("player_name"), "team", resultSet.getString("type") , resultSet.getInt("rating"));
				player.setPlayerId(resultSet.getInt("player_id"));
				playersList.add(player);
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbConfig.closeConnection(connection, preparedStatement, resultSet);
		}
		return playersList;

	}

	// display completed Matches
	public void displayCompletedMatches() {
		ArrayList<Integer> matchId = new ArrayList<Integer>();
		ArrayList<String> teamOne = new ArrayList<String>();
		ArrayList<String> teamTwo = new ArrayList<String>();
		try {
			connection = dbConfig.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.DISPLAY_COMPLETED_MATCHES);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				matchId.add(resultSet.getInt("match_id"));
				teamOne.add(resultSet.getString("team1"));
				teamTwo.add(resultSet.getString("team2"));
			}
			userService.displayMatches(matchId, teamOne, teamTwo);
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbConfig.closeConnection(connection, preparedStatement, resultSet);
		}
	}

	public UserPickedTeam setUserTeamBean(int userId, int matchId) {
		// TODO Auto-generated method stub
		try {
			connection = dbConfig.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.SELECT_FROM_PLAYERS_PICKED);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, matchId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				players.add(resultSet.getInt("player_id"));
				credits.add(resultSet.getInt("points"));
			}
			userPickedTeam.setMatchId(matchId);
			userPickedTeam.setPlayerCredits(credits);
			userPickedTeam.setPlayerId(players);

		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbConfig.closeConnection(connection, preparedStatement, resultSet);
		}
		return userPickedTeam;
	}

	// delete the existing team for a particular match
	public void deleteOldTeam(int matchId, int userId) {
		// TODO Auto-generated method stub
		try {
			connection = dbConfig.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.DELETE_EXISTING);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, matchId);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbConfig.closeConnection(connection, preparedStatement, resultSet);
		}

	}

	public int calculateMatchScore(int matchId, int userId) {
		// TODO Auto-generated method stub
		int total = 0;
		try {
			connection = dbConfig.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.SCORE_CALCULATE);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, matchId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				total += resultSet.getInt("points");
			}

		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbConfig.closeConnection(connection, preparedStatement, resultSet);
		}
		return total;
	}

	public int checkResultTable(int userId, int matchId) {
		// TODO Auto-generated method stub
		int total = 0;
		try {
			connection = dbConfig.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.CHECK_RESULT_TABLE);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, matchId);
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				total = 0;
			} else {
				total = resultSet.getInt("match_points");
			}

		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbConfig.closeConnection(connection, preparedStatement, resultSet);
		}
		return total;
	}

	public ResultBoard setResultBoard(int matchId) {
		// TODO Auto-generated method stub
		ArrayList<Integer> users = new ArrayList<Integer>();
		ArrayList<Integer> matchPoints = new ArrayList<Integer>();
		ArrayList<String> names = new ArrayList<String>();
		try {
			connection = dbConfig.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.SELECT_RESULT_TABLE);
			preparedStatement.setInt(1, matchId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				users.add(resultSet.getInt("user_id"));
				names.add(resultSet.getString("user_name"));
				matchPoints.add(resultSet.getInt("match_points"));
			}
			resultBoard.setResultBoard(users, names, matchPoints);

		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbConfig.closeConnection(connection, preparedStatement, resultSet);
		}
		return resultBoard;
	}

	public ArrayList<String> getPlayersName(int matchId) {
		// TODO Auto-generated method stub
		ArrayList<String> names = new ArrayList<String>();
		try {
			connection = dbConfig.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.SELECT_MOST_PICKED);
			preparedStatement.setInt(1, matchId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				names.add(resultSet.getString("player_name"));
			}

		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbConfig.closeConnection(connection, preparedStatement, resultSet);
		}
		return names;
	}

}
