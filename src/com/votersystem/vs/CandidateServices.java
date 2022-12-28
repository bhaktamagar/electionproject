package com.votersystem.vs;

import java.io.IOException;
import java.sql.SQLException;

public class CandidateServices {
	public Candidate addCandidate(Candidate candidate) throws IOException, SQLException, ClassNotFoundException {
		VoterDaoInterface dao = new VoterDaoDB();
		dao.addCandidate(candidate);
		System.out.println("Candidate added successfully!");
		return candidate;
	}
}