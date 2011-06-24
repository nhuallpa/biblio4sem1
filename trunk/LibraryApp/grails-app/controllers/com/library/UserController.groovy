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

		User user = User.get(new Long(params.id))
		def rating = params.rating

		def average = (rating.toDouble() + user.lookScore())
//		user.rating = average
		user.totalVotes += 1
		user.save()
		session.voted[user.name] = true
		render(template: "/plugin/rate",
			model: [user: user, rating: average])

//		render(template: "rate", model: [rating: average])

	}
	
}
