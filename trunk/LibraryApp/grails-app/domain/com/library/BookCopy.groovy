package com.library

class BookCopy {

	States state
	Reservation reservation
	// No agrego [reservation:Reservation] para  q al  eliminar una reservacion, no elimine en cascada
	static belongsTo = [library:Library, bookMaster:Book ]
	
	
    static constraints = {
		bookMaster(nullable:false)
		state(inList: States.list())
		reservation(nullable:true)
    }
	
	void returnMe(){
		this.state = States.AVAILABLE
		//reservation = null;
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
