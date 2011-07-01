package com.library

class UserController {

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
		session.user = null
		redirect(uri:'/')
	}
	

	
	def rate = {

		//User user = User.get(new Long(params.id))
		
		User user = session.user
		def rating = params.rating

		if (!user.isAttached()) {
			user.attach()
		}
		
		def average = (rating.toDouble() + 
            user.rating*user.totalVotes)/
                (user.totalVotes + 1)
		user.rating = average
		user.totalVotes += 1
		user.save()
		session.voted[user.name] = true
		render(template: "/user/rate",
			model: [user: user, rating: average])

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
	
	
}
