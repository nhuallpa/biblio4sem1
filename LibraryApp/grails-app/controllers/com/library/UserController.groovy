package com.library

import grails.converters.JSON

import org.grails.taggable.Tag


class UserController {
	
	def geocoderService
	def scaffold = true
	def bookService
	
	def index = {
		redirect(action: 'create')
	}
	
	def create = {
		
	}
	
	def login = {
		
		User user = User.findByNameAndPassword(params.userId, params.password)
		if (user) {
			flash.message = "your login was successful"
			session.user = user
		} else {
			flash.message = "I am sorry but your user or password was incorrected. try again..."
			session.user = null
			redirect(controller:"home", action:"notification")
		}
		goToHome()
	}
	
	def logout = {
		log.info "User agent: " + request.getHeader("User-Agent")
		session.invalidate()
		goToHome()
	}
	
	def registration = {
	}
	
	
	def toRegister = {
		String pass1 = params.password1
		String pass2 = params.password2
		if(!pass1.equals(pass2)){
			goToHome()
		} else {
			User user = new User(name : params.user_name, password : pass1)
			if(!isUser(user)){
				user.setEmail params.email
				user.setPhone params.phone
				
				Location location = new Location(country:params.country, city:params.city, street:params.street).save()
				user.setLocation(location)
				if (user.save()) {
					flash.message = "your resgistration was successful"
					user.addMyPreferencesTags(params.tags)
					redirect(controller:"home", action:"notification")
				}
			} else {
				flash.message = "${user.name} is existing!! Try again..."
				redirect(action: "registration")
			}
		}
		
	}

	
	def rate = {

		User user = session.user
		Integer rating = params.rating
		rating -= 48

		if (!user.isAttached()) {
			user.attach()
		}
		
		user.addUserComment user, "", rating
		user.save()
		session.voted[user.name] = true
		render(template: "/user/rate",
			model: [user: user, rating: rating])
	}
	
	def beforeInterceptor = {
		if (!session || !session.voted) {
			def voted = [:]
			def names = User.list().collect { it.name }
			names.each { name ->
				voted[name] = false
			}
			session.voted = voted
	   }
	}
	
	
	def viewProfile = {
		User aUser = User.get(params.userId)
		if(!aUser){
			goToHome()
		}
		List tags = aUser.tags.collect {it}
		def booksRecommended = bookService.getRecommendation(tags)
		[userProfile:aUser, booksRecommended:booksRecommended]
	}
	
	def viewImage = {
		User user = session.user
		
		if (!user.isAttached()) {
			user.attach()
		}
		
		response.contentType = "image/jpeg"
		response.contentLength = user?.photo.length
		response.outputStream.write(user?.photo)
	}
	
	def geocode = {
		
		User user = session.user
		def response = geocoderService.geocodeLocation(user)
		
		def lat = response.result.geometry.location.lat
		def lng = response.'**'.find { it.name() =~ /lng/ }
		
		user.location.latitude = lat
		user.location.longitude = lng
		
	  }
	
	void goToHome(){
		redirect(uri: '/')
	}
	
	boolean isUser(User user){
		User.list().contains(user) 
	}
	
	def updateTags = {		
		User user = session.user
		if (!user){
			goToHome()
		}
		User userFound = User.get(session.user.id)
		if (userFound) {
			String tags = params.get("user-tags-area")
			userFound.addMyPreferencesTags(tags);
			flash.message = "Tags updated"
		}
		redirect(controller:'user', action: 'viewProfile', params:[userId:userFound.id])
	}
	
	/** MOBILE **/
	
	def getUser = {
		def user = User.get(params.userId)
		def reservationList = new ArrayList()
		def commentsList = new ArrayList()
		
		for (obj in user.reservations){
			def jsonReservation = [
				book: [
					 id: obj.book.id,
					 name: obj.book.name
					],
				reservationDate: obj.reservationDate,
				state: obj.state.state,
				
			]
			reservationList.add jsonReservation
		}
		
		for(obj in user.commentsDone){
			def jsonComment = [
					description:obj.description,
					date: obj.date,
					book: [
							id: obj.book.id,
							name: obj.book.name
						],
					score: obj.score,
				]
			commentsList.add jsonComment
		}
		
		def jsonData = [
				name: user.name,
				homepage: user.homepage,
				email: user.email,
				phone: user.phone,
				rating: user.rating,
				reservations:  reservationList,
				comments: commentsList,
			]
		render jsonData as JSON
	}
	

	
	def loginUser = {
		User user = User.findByNameAndPassword(params.name, params.password)
		def jsonData = null;
		if(user){
			session.user = user
			jsonData = [
					userId: user.id,
					state:"OK"
				]
			
		} else {
			session.user = null
			jsonData = [
				state:"Error params"
			]
		}
		render jsonData as JSON
	}
	
	def getMyComments = {
		User user = User.get(params.id)
		def listComments = new ArrayList()
		for(commentFounded in user.commentsDone){
			def jsonData = [
				id: commentFounded.id,
				description: commentFounded.description,
				date: commentFounded.date,
				book: [
					id:commentFounded.book.id,
					name: commentFounded.book.name,
					],
				score: commentFounded.score
			]
			listComments.add jsonData
		}
		
		render listComments as JSON
		
	}
	
	def getMyReservations = {
		User user = User.get(params.userId)
		def reservationsList = new ArrayList()
		for(reservFounded in user.reservations){
			def jsonData = [
					id: reservFounded.id,
					date: reservFounded.reservationDate,
					library: [
							id: reservFounded.library.id,
							libraryName: reservFounded.library.name
						],
					book: [
							id: reservFounded.bookCopy.bookMaster.id,
							name: reservFounded.nameOfBook()
						],
				]
			reservationsList.add jsonData
		}
		
		render reservationsList as JSON
	}
	
	def myScore = {
		User user = User.get(params.userId)
		def jsonData = [
				score : user.score
			]
		render jsonData as JSON
	}
	
	
}
