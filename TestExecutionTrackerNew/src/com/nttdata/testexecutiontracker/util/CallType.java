package com.nttdata.testexecutiontracker.util;

public enum CallType {
	HOME("home"),NEXT("next"), PREVIOUS("previuos"), PAGE("page");
	
	private String type;
	CallType(String type)
	{
		this.type=type;
	}
}
