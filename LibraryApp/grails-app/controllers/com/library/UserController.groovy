package com.library

import org.grails.taggable.Tag


class UserController {
	
	def geocoderService
	def scaffold = true
	
    def index = { 
		redirect(action: 'create')
	}
	
	def create = {
		
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
					user.typesFav.add new Tag(name: Constants.TYPE_ACTION)
				}
				if(params.type_drama){
					user.typesFav.add new Tag(name: Constants.TYPE_DRAMA)
				}
				if(params.type_ficcion){
					user.typesFav.add new Tag(name: Constants.TYPE_FICTION)
				}
				if(params.type_novela){
					user.typesFav.add new Tag(name: Constants.TYPE_NOVELAS)
				}
				if(params.type_literatura){
					user.typesFav.add new Tag(name: Constants.TYPE_LITERATURA)
				}
				user.setEmail params.email
				user.setPhone params.phone
				
				//setear los demas valores...
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
//		User aUser = User.get(params.userId)
		User aUser = session.user
		if (!aUser.isAttached()) {
			aUser.attach()
		} else {
			goToHome()
		}
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
