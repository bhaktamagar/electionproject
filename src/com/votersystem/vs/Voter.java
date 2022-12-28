package com.votersystem.vs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

public class Voter {
    
	private Integer id;
	
	private String firstName;
	
	private String lastName;
	
	private Gender gender;
	
	private String username;
	
	private String password;
	private String numCandidates;
	//private String candiName;
	//private String partyName;
	//private VoterType voterType;
	
	public Voter() {
		super();
	}
	
	public Voter(Integer id, String firstName, String lastName, Gender gender, String username, String password,
			String numCandidates) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.username = username;
		this.password = password;
		this.numCandidates = numCandidates;
	}
	
	public Voter(String firstName, String lastName, Gender gender, String username, String password,
			String numCandidates) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.username = username;
		this.password = password;
		this.numCandidates = numCandidates;
	}
	
	public Voter(String firstName, String lastName, Gender gender, String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.username = username;
		this.password = password;
		this.numCandidates=numCandidates;
		}

	public Integer getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	

	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	public String getNumCandidates() {
		return numCandidates;
	}

	public void setNumCandidates(String numCandidates) {
		this.numCandidates = numCandidates;
	}

	@Override
	public String toString() {
		return "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender + ", username="
		        + username + ", password=" + password + ", numCandidatese=" + numCandidates;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voter other = (Voter) obj;
		return numCandidates == other.numCandidates && Objects.equals(firstName, other.firstName) && gender == other.gender
		        && Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName)
		        && Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}

	public static int getCandVotes(int i) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
	
