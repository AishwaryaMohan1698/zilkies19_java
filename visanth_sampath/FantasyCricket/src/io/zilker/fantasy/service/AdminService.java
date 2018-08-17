package io.zilker.fantasy.service;

import java.util.ArrayList;

import io.zilker.fantasy.bean.Match;
import io.zilker.fantasy.delegate.AdminOperations;
import io.zilker.fantasy.ui.AdminUI;

public class AdminService {
	AdminUI adminUI = new AdminUI();

	public void displayAlert(String message) {
		// TODO Auto-generated method stub
		adminUI.displayAlert(message);

	}

	public String getStringInputs() {
		// TODO Auto-generated method stub
		return adminUI.getStringInputs();
	}

	public int getIntInputs() {
		// TODO Auto-generated method stub
		return adminUI.getIntInputs();
	}

	public void printMatches(int matchId, String teamOne, String teamTwo) {
		// TODO Auto-generated method stub
		adminUI.printMatches(matchId, teamOne, teamTwo);

	}

	public void printPlayers(int playerId, String playerName, String type, int rating) {
		// TODO Auto-generated method stub
		adminUI.printPlayers(playerId, playerName, type, rating);

	}

	public void displayMatchList(ArrayList<Match> matchList) {
		// TODO Auto-generated method stub
		adminUI.displayMatchList(matchList);
	}

	public void adminServiceRedirect() {
		AdminOperations newOperations = new AdminOperations();
		newOperations.displayAdminOperations();
	}
}
