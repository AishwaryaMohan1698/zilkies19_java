package io.ztech.cricalert.delegate;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.cricalert.beans.BallStats;
import io.ztech.cricalert.beans.Match;
import io.ztech.cricalert.beans.Player;
import io.ztech.cricalert.beans.Team;
import io.ztech.cricalert.beans.User;
import io.ztech.cricalert.constants.MatchResult;
import io.ztech.cricalert.constants.UserMessages;
import io.ztech.cricalert.dao.CricketDAO;

public class MatchDelegate {

	Scanner scanner;
	Logger logger;
	CricketDAO dao;

	public MatchDelegate() {
		logger = Logger.getLogger(MatchDelegate.class.getName());
		scanner = new Scanner(System.in);
		dao = new CricketDAO();
	}

	public void setMatch(Match newMatch) {
		dao.insertMatch(newMatch);
	}

	public void displayMatches(User user) {
		ArrayList<Match> matchList = dao.fetchMatches(user);
		logger.info(UserMessages.MATCH_TABLE);
		for (Match match : matchList) {
			Team team = match.getTeamA();
			team = dao.fetchTeam(team.getTeamId());
			match.setTeamA(team);
			team = match.getTeamB();
			team = dao.fetchTeam(team.getTeamId());
			match.setTeamB(team);
			String toss;
			if (match.getTossWonBy() == match.getTeamA().getTeamId()) {
				toss = match.getTeamA().getTeamName();
			} else if (match.getTossWonBy() == match.getTeamB().getTeamId()) {
				toss = match.getTeamB().getTeamName();
			} else {
				toss = "NA";
			}
			
			if (match.getMatchResult() == null) {
				match.setMatchResult(MatchResult.NA);
			}

			logger.info(match.getMatchId() + "\t" + match.getMatchDate() + "\t" + match.getTeamA().getTeamName()
					+ "\t" + match.getTeamB().getTeamName() + "\t" + match.getStatus() + "\t" + toss + "\t"
					+ match.getMatchResult());
		}
	}

	public boolean searchMatch(User user, int matchId) {
		return dao.searchMatch(user, matchId);
	}

	public Match fetchMatch(int matchId) {
		return dao.fetchMatch(matchId);
	}

	public ArrayList<Player> fetchPlayers(ArrayList<Integer> players) {
		return dao.fetchPlayers(players);
	}

	public void insertBallStats(BallStats ballStats) {
		dao.insertBallStats(ballStats);
	}
	
	public boolean isMatchScheduled(User user) {
		return dao.fetchScheduledMatch(user);
	}
	
	public void updateMatchDate(Match match) {
		dao.updateMatchDate(match);
	}
	
	public void updateTeam(Match match, String team) {
		dao.updateTeam(match, team);
	}
}
