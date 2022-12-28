package com.votersystem.vs;

import java.io.IOException;
import java.sql.SQLException;

public class LoginService {
	Voter login(String username, String password) throws ClassNotFoundException, SQLException {

		VoterDaoDB dao = new VoterDaoDB();
		Voter voter = null;

		try {
			voter = dao.searchByUsernameAndPassword(username, password);
		} catch (Exception e) {
			System.out.println("Some issue occurred");
			e.printStackTrace();
		}
		return voter;
	}

}
