package com.library

public enum States{
	RESERVED('Reserved'),
	AVAILABLE('Available'),
	DELIVERED('Delivered'),

	String state

	States(String state){
		this.state = state
	}
	static list(){
		[RESERVED, AVAILABLE, DELIVERED]
	}
	public String toString(){
		return state
	}
}
