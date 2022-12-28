package com.votersystem.vs;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;



public class VoterDao implements VoterDaoInterface {	
	@Override
	public void editVoter1(Voter voter) throws IOException {
		// TODO Auto-generated method stub
		
	}	
	 
	
	@Override
	public void editVoter(Voter voter) throws IOException {	
		
		BufferedReader reader = new BufferedReader(new FileReader("voter.txt"));
		String line = reader.readLine();
		StringBuilder builder = new StringBuilder();
		while (line != null) {
			String[] values = line.split(", ");
			boolean matches = false;
			for (String val : values) {
				if (val.contains("id") && val.equals("id=" + voter.getId())) {
					matches = true;
					break;
				}
				
			}
			if (matches) {
				builder.append(voter.toString());
			} else {
				builder.append(line);
			}
			builder.append("\n");
			line = reader.readLine();
		}
		reader.close();
		
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("voter.txt"));
		bw.write(builder.toString());
		bw.write("\n");
		bw.flush();
		
		bw.close();
		
	}

	@Override
	public void viewAll() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("voter.txt"));
		String line = reader.readLine();
		
		while (line != null) {
			System.out.println(line);
			line = reader.readLine();
		}
	}
	
	
	
	@Override
	public Voter searchById(Integer id) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("voter.txt"));
		
		String line = reader.readLine();
		Voter voter = null;
		
		while (line != null) {
			boolean matches = false;
			String[] values = line.split(", ");
			
			String firstName = null;
			String lastName = null;
			Gender g = null;
			VoterType type = null;
			
			for (String val : values) {
				if (val.contains("id") && val.equals("id=" + id)) {
					matches = true;
				}
				
				if (val.contains("id=")) {
					String vals[] = val.split("=");
					id = Integer.parseInt(vals[1]);
				} else if (val.contains("firstName=")) {
					String vals[] = val.split("=");
					firstName = vals[1];
				} else if (val.contains("lastName=")) {
					String vals[] = val.split("=");
					lastName = vals[1];
				} else if (val.contains("gender=")) {
					String vals[] = val.split("=");
					String gender = vals[1];
					g = Gender.getByValue(gender);
				} else if (val.contains("voterType=")) {
					String vals[] = val.split("=");
					String voterType = vals[1];
					type = VoterType.getByValue(voterType);
				}
			}
			
			if (matches) {
			Voter vote = new Voter();
				vote.setId(id);
				vote.setFirstName(firstName);
				vote.setLastName(lastName);
				vote.setGender(g);
				vote.setVoterType(type);
				return vote;
			}
		}
		return voter;
	}
	
	@Override
	public Voter searchByUsernameAndPassword(String username, String password) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("voter.txt"));
		
		String line = reader.readLine();
		Voter voter = null;
		
		while (line != null) {
			int count = 0;
			String[] values = line.split(", ");
			
			String firstName = null;
			String lastName = null;
			Integer id = null;
			Gender g = null;
			VoterType type = null;
			
			for (String val : values) {
				if (val.equals("username=" + username)) {
					count++;
				} else if (val.equals("password=" + password)) {
					count++;
				}
				
				if (val.contains("id=")) {
					String vals[] = val.split("=");
					id = Integer.parseInt(vals[1]);
				} else if (val.contains("firstName=")) {
					String vals[] = val.split("=");
					firstName = vals[1];
				} else if (val.contains("lastName=")) {
					String vals[] = val.split("=");
					lastName = vals[1];
				} else if (val.contains("gender=")) {
					String vals[] = val.split("=");
					String gender = vals[1];
					g = Gender.getByValue(gender);
				} else if (val.contains("employeeType=")) {
					String vals[] = val.split("=");
					String voterType = vals[1];
					type = VoterType.getByValue(voterType);
				}
			}
			
			if (count == 2) {
				voter = new Voter();
				voter.setId(id);
				voter.setFirstName(firstName);
				voter.setLastName(lastName);
				voter.setGender(g);
				voter.setVoterType(type);
				voter.setUsername(username);
				voter.setPassword(password);
				return voter;
			}
			
			line = reader.readLine();
			
		}
		reader.close();
		return voter;
	}
	
	@Override
	public Voter addVoter(Voter voter) throws IOException {
		
		BufferedReader reader = new BufferedReader(new FileReader(new File("voter.txt")));
		int count = (int) reader.lines().count();
		voter.setId(++count);
		reader.close();
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("employee.txt", true));
		bw.write(voter.toString());
		//		bw.newLine();
		bw.write("\n");
		bw.flush();
		

		bw.close();
		return voter;
	}
	
	@Override
	public Voter searchByUsername(String username) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("voter.txt"));
		
		String line = reader.readLine();
		Voter voter = null;
		
		while (line != null) {
			int count = 0;
			String[] values = line.split(", ");
			
			String firstName = null;
			String lastName = null;
			Integer id = null;
			Gender g = null;
			VoterType type = null;
			
			for (String val : values) {
				if (val.contains("username") && val.equals("username=" + username)) {
					count++;
				}
				if (val.contains("id=")) {
					String vals[] = val.split("=");
					id = Integer.parseInt(vals[1]);
				} else if (val.contains("firstName=")) {
					String vals[] = val.split("=");
					firstName = vals[1];
				} else if (val.contains("lastName=")) {
					String vals[] = val.split("=");
					lastName = vals[1];
				} else if (val.contains("gender=")) {
					String vals[] = val.split("=");
					String gender = vals[1];
					g = Gender.getByValue(gender);
				} else if (val.contains("voterType=")) {
					String vals[] = val.split("=");
					String voterType = vals[1];
					type = VoterType.getByValue(voterType);
				}
			}
			
			if (count == 1) {
				voter = new Voter();
				voter.setId(id);
				voter.setFirstName(firstName);
				voter.setLastName(lastName);
				voter.setGender(g);
				voter.setVoterType(type);
				voter.setUsername(username);
				return voter;
			}
			
			line = reader.readLine();
			
		}
		
		reader.close();
		return voter;
	}
	
	@Override
	public void removeVoter(Integer id) throws IOException {
		
		BufferedReader reader = new BufferedReader(new FileReader("voter.txt"));
		
		String line = reader.readLine();
		StringBuilder builder = new StringBuilder();
		while (line != null) {
			String[] values = line.split(", ");
			boolean matches = false;
			for (String val : values) {
				if (val.contains("id") && val.equals("id=" + id)) {
					matches = true;
					break;
				}
			}
			if (matches) {
				builder.append("");
			} else {
				builder.append(line);
			}
			builder.append("\n");
			line = reader.readLine();
		}
		reader.close();
		
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("voter.txt"));
		bw.write(builder.toString());
		bw.write("\n");
		bw.flush();
		
		bw.close();
	}


	@Override
	public void addVote(Voter vote) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void addRegistration(Voter voter) throws IOException, SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void ElectionResult(Voter vote) throws IOException, SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}

	
	

	
}


