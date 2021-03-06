package io.ztech.cricalert.beans;

import java.sql.Timestamp;

import io.ztech.cricalert.constants.MatchResult;

public class Match {
	int matchId;
	User user;
	Timestamp matchDatetime;
	Team teamA, teamB;
	LineUp teamALineUp, teamBLineUp;
	String status, venue;
	int tossWonBy;
	MatchResult matchResult;
	
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public LineUp getTeamALineUp() {
		return teamALineUp;
	}
	public void setTeamALineUp(LineUp teamALineUp) {
		this.teamALineUp = teamALineUp;
	}
	public LineUp getTeamBLineUp() {
		return teamBLineUp;
	}
	public void setTeamBLineUp(LineUp teamBLineUp) {
		this.teamBLineUp = teamBLineUp;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public MatchResult getMatchResult() {
		return matchResult;
	}
	public void setMatchResult(MatchResult matchResult) {
		this.matchResult = matchResult;
	}
	public int getMatchId() {
		return matchId;
	}
	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}
	public Team getTeamA() {
		return teamA;
	}
	public void setTeamA(Team teamA) {
		this.teamA = teamA;
	}
	public Team getTeamB() {
		return teamB;
	}
	public void setTeamB(Team teamB) {
		this.teamB = teamB;
	}
	public int getTossWonBy() {
		return tossWonBy;
	}
	public void setTossWonBy(int tossWonBy) {
		this.tossWonBy = tossWonBy;
	}
	public Timestamp getMatchDatetime() {
		return matchDatetime;
	}
	public void setMatchDatetime(Timestamp matchDatetime) {
		this.matchDatetime = matchDatetime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
