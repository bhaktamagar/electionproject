package com.votersystem.vs;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class MainVotingSystem {

	public static void main(String[] args) throws Exception {

		Scanner scan = new Scanner(System.in);
		VoterService voter = new VoterService();
		LoginService loginService = new LoginService();
		VoterDaoDB loginDB = new VoterDaoDB();		

		Voter voter1 = null;
		while (true) {
			System.out.println("\n\t\t\t\t Login online voting\t\t\n");

			System.out.print("Enter your username: ");
			String username = scan.next();
			System.out.print("Enter your password: ");
			String password = scan.next();

			voter1 = loginService.login(username, password);

			if (voter1 != null) {
				System.out.println("You are logged in Successfully.");

			} else {
				System.out.println("Invalid username or password. Try again!!");
				continue;
			}
			System.out.println("Welcome, " + voter1.getFirstName() + " " + voter1.getLastName());			

			int choice = 0;						
			while (true) {
				System.out.println("\n\n \t\t\t\t Voter Management System\t\t\n\n");

				System.out.println("1. Register to vote");
				System.out.println("2. Candidate Registration");
				System.out.println("3. View All Candidate List");
				System.out.println("4. Vote");
				System.out.println("5. ElectionResult");
				System.out.println("6. View All");
				System.out.println("7. Exit");

				System.out.print("Select your choice what you wish: \n");

				choice = scan.nextInt();
				if (choice == 1) { //Register to vote
					Scanner userInput = new Scanner(System.in);
					System.out.print("Enter voter first Name: ");
					String firstName = userInput.next();
					System.out.print("Enter Voter last Name: ");
					String lastName = userInput.next();

					Gender g = null;
					while (true) {
						System.out.println("1.Male\t 2.Female\t 3.Others");
						System.out.println("Choose the gender.");
						int genderChoice = userInput.nextInt();
						if (genderChoice == 1) {
							g = Gender.MALE;
						} else if (genderChoice == 2) {
							g = Gender.FEMALE;
						} else if (genderChoice == 3) {
							g = Gender.OTHERS;
						} else {
							System.out.println("Invalid option.");
							continue;
						}
						break;
					}
					System.out.print("Enter your username: ");
					String username1 = userInput.next();
					System.out.print("Enter your password: ");
					String password1 = userInput.next();

					Voter vote1 = new Voter(firstName, lastName, g, username1, password1);
					try {
						voter.addRegistration(vote1);
					 System.out.println("Added vote Successfully");
					} catch (Exception e) {
						System.out.println("Voter error");
					}

				} else if (choice == 2) {// candidate registration
					Scanner userInput = new Scanner(System.in);
					CandidateServices candi = new CandidateServices();
					Candidate candidate = null;				

					System.out.print("Enter First Name : ");
					String firstname = userInput.next();

					System.out.print("Enter Last Name: ");
					String lastname = userInput.next();

					System.out.print("Enter Party Name: ");
					String partyname = userInput.next();
					
					Candidate candidate1 = new Candidate(firstname, lastname, partyname);
					try {
						candi.addCandidate(candidate1);

					} catch (IOException | ClassNotFoundException | SQLException e) {
						System.out.println("Invalid to add voter. Try again.");
						e.printStackTrace();
					}	
					
				} else if (choice == 3) {					
					
					VoterService.candidatesList1(voter1);
						
			} else if (choice == 4) {		
				

	                if (voter != null) {
	                    System.out.println("Enter your Party for Vote ");
	                } else {
	                    System.out.println("You are not registered for vote.");

	                    continue;
	                }
	                VoterService.candidatesList1(voter1);
	                System.out.println("Enter voter Id");
	                int voterId = scan.nextInt();
	                System.out.println("Choose party name:1 for Nepali congress and 2 for Amale ");
	                int symbol = scan.nextInt();
	               // VoterService.doVoteNow(voterId);
	            
					
		} else if (choice == 5) {					
			
			
               VoterService.ElectionResult(voter1);
           }    
					

				 else if (choice == 6) {
					try {
						VoterService.viewAll();
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				} else if (choice == 7) {

					System.out.println("Exit");
					break;
				} else {
					System.out.println("Try later");
				}

			}

		}
	}
}
