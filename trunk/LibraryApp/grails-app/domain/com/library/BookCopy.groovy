package com.library

class BookCopy {

	States state
	static belongsTo = [library:Library, bookMaster:Book, reservation:Reservation]
	
	
    static constraints = {
		bookMaster(nullable:false)
		state(inList: States.list())
		reservation(nullable:true)
    }
	
	void returnMe(){
		this.state = States.AVAILABLE
	}
	
	void cancelReservation(){
		this.state = States.AVAILABLE
	}
	
	void deliver(){
		this.state = States.DELIVERED
	}
	
	void reserveMe(){
		this.state = States.RESERVED
	}
	
	Boolean isReserved(){
		return (state == States.RESERVED)
	}
	
	Boolean isDelivered(){
		return (state == States.DELIVERED)
	}
}
