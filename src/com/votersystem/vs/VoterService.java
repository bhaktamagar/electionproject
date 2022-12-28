package com.votersystem.vs;
import java.io.IOException;
import java.sql.SQLException;



public class VoterService {
	public static void viewAll() throws IOException {
        VoterDaoInterface dao = new VoterDaoDB();
        dao.viewAll();
    }
	public Voter addRegistration(Voter voter) throws IOException, SQLException, ClassNotFoundException {
		VoterDaoInterface dao = new VoterDaoDB();		
		dao.addRegistration(voter);		
		return voter;
	}
	
	  public Voter addVote(Voter vote) throws IOException, SQLException, ClassNotFoundException { 
		  VoterDaoInterface dao = new  VoterDaoDB(); 
		  dao.addVoter(vote);
			System.out.println("Vote added successfully!");
			return vote;
		  }
	
	  public static Voter ElectionResult(Voter vote1) throws IOException, SQLException, ClassNotFoundException { 
		  VoterDaoInterface dao = new  VoterDaoDB(); 
		  dao.ElectionResult(vote1);
			System.out.println("Election Result suceessfully!");
			return vote1;
		  }
	
	public static void candidatesList1(Voter voter1) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Candidates registered for 2023 election.");
        VoterDaoInterface dao = new VoterDaoDB();
        dao.candidatesList1(voter1);

	}

}
	
