/*
 * package com.votersystem.vs; import java.util.Date; import java.util.Scanner;
 * 
 * public class Registration { Scanner userInput = new Scanner(System.in);
 * 
 * public Registration() { VoterService voter=new VoterService(); Voter
 * vote=null; System.out.print("Enter voter first Name: "); String firstName =
 * userInput.next(); System.out.print("Enter Voter last Name: "); String
 * lastName = userInput.next();
 * 
 * Gender g = null; while (true) {
 * System.out.println("1.Male\t 2.Female\t 3.Others");
 * System.out.println("Choose the gender."); int genderChoice =
 * userInput.nextInt(); if (genderChoice == 1) { g = Gender.MALE; } else if
 * (genderChoice == 2) { g = Gender.FEMALE; } else if (genderChoice == 3) { g =
 * Gender.OTHERS; } else { System.out.println("Invalid option."); continue; }
 * break; } System.out.print("Enter your username: "); String
 * username=userInput.next(); System.out.print("Enter your password: "); String
 * password=userInput.next();
 * 
 * Voter vote1=new Voter(firstName, lastName,g,username,password); try {
 * voter.addVoter(vote1);
 * 
 * } catch (Exception e) { //System.out.println("Voter error"); }
 * 
 * System.out.print("Enter voter age: "); int age = userInput.nextInt();
 * 
 * 
 * if (age >= 18) { System.out.print("You are eligible to vote."); } else {
 * System.out.print("You are not eligible to vote."); }
 * 
 * 
 * 
 * }
 * 
 * }
 */