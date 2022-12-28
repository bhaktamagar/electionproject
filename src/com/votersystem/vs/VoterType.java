package com.votersystem.vs;


public enum VoterType {
	ADMIN("ADMIN"),
	USER("USER");
	
	String value;
	
	VoterType(String value) {
		this.value = value;
	}
	
	static VoterType getByValue(String voterType) {
		for (VoterType g : VoterType.values()) {
			if (g.value.equals(voterType)) {
				return g;
			}
		}
		return null;
	}

}
