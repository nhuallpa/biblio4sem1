package com.library

import com.sun.jndi.toolkit.url.Uri;

class ReservationController {

	def scaffold = true
	
    def index = { 
		redirect(action: 'create')
	}
	
	def reserveRequest = {
		
	}
	
	
	def toReserve = {
		User user = session.user
		if (!user){
			goToHome()
		}
		def aMessage = null
		Book aBook = Book.get(params.bookId)
		if(!bookAvailable(aBook)){
			aMessage = "${aBook.name} is not available"
		} else if (!user.isAttached() && aBook) {
					user.attach()
					user.makeReservation(aBook)
					aMessage = "You have reserved ${aBook.name}!"
				} 
		flash.message = aMessage
		redirect(action: 'viewMyReservation')
	}
	
	
	def cancelReserve = {
		User user = session.user
		Book aBook = Book.get(params.bookId)
		if (!user.isAttached()){
			user.attach();
			user.cancelReservation aBook  
			flash.message = "You have canceled the reservation ${aBook.name}"
			redirect(controller:'user', action: 'viewProfile', params:[userId:user.id])
		}
	}
	
	boolean bookAvailable(Book book){
		return book.state.state == 'Available'
	}
	
	void goToHome(){
		redirect(uri: '/')
	}
}
