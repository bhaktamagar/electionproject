package com.votersystem.vs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VoterDaoDB implements VoterDaoInterface {

	private final static String USERNAME = "root";

	private final static String PASSWORD = "root";

	private final static String URL = "jdbc:mysql://localhost:3306/vs";

	public static Voter[] voterList;	

	public void addTable() {
		Connection con = null;
		Statement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			StringBuilder query = new StringBuilder();
			query.append(
					"Create table if not exists voter1 (id int not null, fistname varchar(20) not null), lastname varchar(20) not null)");

			statement = con.createStatement();
			statement.execute(query.toString());

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				statement.close();
				con.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

	}

	@Override
	public void viewAll() throws IOException {
		Connection con = null;
		Statement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			String query = "SELECT * from voter";

			statement = con.createStatement();

			ResultSet results = statement.executeQuery(query);

			while (results.next()) {

				System.out.println(results.getInt(1));
				System.out.println(results.getString(2));
				System.out.println(results.getString(3));
				System.out.println(results.getString(4));
				System.out.println(results.getString(5));
				System.out.println(results.getString(6));
				System.out.println(results.getBoolean(8));

			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				statement.close();
				con.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

	}

	@Override
	public Voter searchByUsernameAndPassword(String username, String password) throws IOException {
		Connection con = null;
		Statement statement = null;
		Voter vote = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			String query = "Select * from voter where username='" + username + "' and password='" + password + "'";

			statement = con.createStatement();
			ResultSet results = statement.executeQuery(query);
			while (results.next()) {
				vote = new Voter();
				vote.setId(results.getInt(1));
				vote.setFirstName(results.getString(2));
				vote.setLastName(results.getString(3));
				vote.setGender(Gender.getByValue(results.getString(4)));
				vote.setUsername(results.getString(5));
				vote.setPassword(results.getString(6));

			}
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				statement.close();
				con.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

		return vote;

	}

	@Override
	public Voter addVoter(Voter voter) throws IOException, SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		Class.forName("com.mysql.cj.jdbc.Driver");

		con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

		String query = "INSERT INTO voter (first_name, last_name, gender, username, password) values (?,?,?,?,?)";
		pst = con.prepareStatement(query);
		pst.setString(1, voter.getFirstName());
		pst.setString(2, voter.getLastName());
		pst.setString(3, voter.getGender().value);
		pst.setString(4, voter.getUsername());
		pst.setString(5, voter.getPassword());
		// pst.setString(6, "VOTER");
		int resultvalue = pst.executeUpdate();

		if (resultvalue == 0) {
			System.out.println("Failed to insert data. Check your data and try again : ");

		}
		pst.close();
		con.close();
		return voter;

	}

	@Override
	public void addRegistration(Voter voter) throws IOException, SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        String query = null;
		try {
			query = "INSERT INTO voterlist (first_name, last_name,gender,dateofbirth,username,password)"
			        + "value ( '" + voter.getFirstName() + "', '" + voter.getLastName() + "','" + voter.getGender() + "'," +
			        "'"  +  "','" + voter.getPassword() + "')";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


        System.out.println(query);

        Statement statement = con.createStatement();
        int resultValue = statement.executeUpdate(query);


        if (resultValue == 2) {
            System.out.println("Failed to insert/update data. Check your data and try again.");
        }

        statement.close();
        con.close();

        //return voter;

    }
		
	

	@Override
	public void ElectionResult(Voter vote) throws IOException, SQLException, ClassNotFoundException {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = con.createStatement();
			String querytotal = "select *,count(candidate_id) from(\n"
					+ "select ca.candidate_id,ca.candidate_firstname,ca.candidate_lastname,ca.party_name from candidate as ca\n"
					+ "left join voting as vo on ca.candidate_id = vo.candidate_id) as total group by 1,2,3,4 order by count(candidate_id)desc";
			ResultSet rs = stmt.executeQuery(querytotal);
			while(rs.next()) {
			int id = rs.getInt(1);
			String firstname = rs.getString(2);
			String lastname = rs.getString(3);
			String partyname = rs.getString(4);
			int total = rs.getInt(5);
			System.out.println("Candidate ID: " + id + " Full Name: "+firstname+" "+lastname+" partyname: "+partyname+" Total Vote: "+total);
			System.out.println(" ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return null;
	}	

	@Override
	public void addCandidate(Candidate candidate) throws IOException, ClassNotFoundException, SQLException {
		Connection con = null;
		PreparedStatement pst = null;

		Class.forName("com.mysql.cj.jdbc.Driver");

		con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

		String query = "INSERT INTO candidate(candidate_firstname,candidate_lastname,party_name) values (?,?,?)";

		pst = con.prepareStatement(query);

		pst.setString(1, candidate.getFirstName());

		pst.setString(2, candidate.getLastName());

		pst.setObject(3, candidate.getPartyName());

		int resultvalue = pst.executeUpdate();

		if (resultvalue == 0) {
			System.out.println("Failed to insert data. Check your data and try again : ");

		}
		pst.close();
		con.close();
		//return candidate;
	}

	/*
	 * @Override public void candidateList() throws IOException,
	 * ClassNotFoundException, SQLException { Connection con = null; Statement
	 * statement = null; Voter voter = null; try {
	 * Class.forName("com.mysql.cj.jdbc.Driver"); con =
	 * DriverManager.getConnection(URL, USERNAME, PASSWORD);
	 * 
	 * String query = "SELECT * from candidatelist";
	 * 
	 * // System.out.println(query);
	 * 
	 * statement = con.createStatement();
	 * 
	 * ResultSet results = statement.executeQuery(query);
	 * 
	 * while (results.next()) {
	 * 
	 * System.out.println(results.getInt(1));
	 * System.out.println(results.getString(2));
	 * System.out.println(results.getString(3)); }
	 * 
	 * } catch (ClassNotFoundException e) { e.printStackTrace(); } catch
	 * (SQLException e) { e.printStackTrace(); } finally { try { statement.close();
	 * con.close(); } catch (SQLException e) { e.printStackTrace(); }
	 * 
	 * }
	 * 
	 * 
	 * }
	 */
	
	@Override
	public void viewAllCandidate() throws IOException {
		Connection con = null;
		Statement statement = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			String query = "SELECT * from candidate";

			// System.out.println(query);

			statement = con.createStatement();

			ResultSet results = statement.executeQuery(query);

			while (results.next()) {

				System.out.println(results.getInt(1) + "." + "Candidate Full Name: " + results.getString(2) + " "
						+ results.getString(3) + " Party Name:" + " " + results.getString(4));

			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Override
	public void candidatesList1(Voter voter1) throws IOException, ClassNotFoundException, SQLException {
		Connection con = null;
        Statement statement = null;
        Voter voter = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "SELECT * from candidatelist";

            //			System.out.println(query);

            statement = con.createStatement();

            ResultSet results = statement.executeQuery(query);

            while (results.next()) {

                System.out.println(results.getInt(1));
                System.out.println(results.getString(2));
                System.out.println(results.getString(3));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

		
	}

	
	
	
}
