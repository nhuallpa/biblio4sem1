package com.library

class UserController {
	
	def geocoderService
	def scaffold = true
	
    def index = { 
		redirect(action: 'create')
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
		[user:aUser]
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
	
}
