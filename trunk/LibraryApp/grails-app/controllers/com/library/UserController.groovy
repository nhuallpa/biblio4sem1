package com.library

import grails.converters.JSON

import org.grails.taggable.Tag


class UserController {
	
	def geocoderService
	def scaffold = true
	
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
	
    def index = { 
		redirect(action: 'create')
	}
	
	def create = {
		
	}
	
	//mobile
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
	
	def login = {
		
		User user = User.findByNameAndPassword(params.userId, params.password)
		if (user) {
			session.user = user
		} else {
			session.user = null
		}
		redirect(uri:'/')
	}
	
	def logout = {
		log.info "User agent: " + request.getHeader("User-Agent")
		session.invalidate()
		redirect(action:"login")
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
				if(params.type_accion){
					Tag tagAction = new Tag(name: Constants.TYPE_ACTION).save() 
					user.typesFav.add tagAction
				}
				if(params.type_drama){
					Tag tagDrama = new Tag(name: Constants.TYPE_DRAMA).save()
					user.typesFav.add tagDrama
				}
				if(params.type_ficcion){
					Tag tagFiction = new Tag(name: Constants.TYPE_FICTION).save()
					user.typesFav.add tagFiction
				}
				if(params.type_novela){
					Tag tagNovelas = new Tag(name: Constants.TYPE_NOVELAS).save()
					user.typesFav.add tagNovelas
				}
				if(params.type_adventures){
					Tag tagLiteratura = new Tag(name: Constants.TYPE_ADVENTURES).save()
					user.typesFav.add tagLiteratura
				}
				user.setEmail params.email
				user.setPhone params.phone
				
				user.save()
				redirect(action: "login",userId:user.name, password:user.password)
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
//		User aUser = session.user
		if(!aUser){
			goToHome()
		}
//		if (!aUser.isAttached()) {
//			aUser.attach()
//		} else {
//			goToHome()
//		}
		[userProfile:aUser]
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
}
