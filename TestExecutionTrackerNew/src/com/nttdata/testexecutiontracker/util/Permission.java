package com.nttdata.testexecutiontracker.util;

public enum Permission {
	FULL_ACCESS("FULL_ACCESS",1),READ_ACCESS("READ_ACCESS",2), NO_ACCESS("NO_ACCESS",3), ADMIN("ADMIN", 4), L1USER("L1USER", 5), L2USER("L2USER", 6);
	
	private String permissionName;

	private int permissionId;
	
	Permission(String permissionName, int permissionID){
		this.permissionId = permissionId;
		this.permissionName = permissionName;
	}
}
