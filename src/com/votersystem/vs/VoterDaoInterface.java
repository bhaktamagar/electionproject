package com.votersystem.vs;

import java.io.IOException;
import java.sql.SQLException;

public interface VoterDaoInterface {

	public void viewAll() throws IOException;

	public Voter searchByUsernameAndPassword(String username, String password) throws IOException;

	public Voter addVoter(Voter voter) throws IOException, SQLException, ClassNotFoundException;

	public void addRegistration(Voter voter) throws IOException, SQLException, ClassNotFoundException;

	public void ElectionResult(Voter vote) throws IOException, SQLException, ClassNotFoundException;

	public void addCandidate(Candidate candidate) throws IOException, ClassNotFoundException, SQLException;

	//public void candidateList() throws IOException, ClassNotFoundException, SQLException;

	void viewAllCandidate() throws IOException, ClassNotFoundException, SQLException;

	public void candidatesList1(Voter voter1) throws IOException, ClassNotFoundException, SQLException;

	//public void candidatesList()throws IOException, ClassNotFoundException, SQLException;

	

	

}
