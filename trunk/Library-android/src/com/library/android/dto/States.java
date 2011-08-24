package com.library.android.dto;

public enum States {

	RESERVED("Reserved"),
	AVAILABLE("Available"),
	DELIVERED("Delivered");

	String state;

	States(String state){
		this.state = state;
	}
	
	public String toString(){
		return state;
	}
	
}
