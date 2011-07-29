package com.library.android.domain;

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
