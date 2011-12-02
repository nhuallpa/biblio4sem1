package com.library

class AwardController {

	def scaffold = true
	
	def index = {
		redirect(action: 'create')
	}
	
	def awards = {
		User user = session.user
		if (!user){
			goToHome()
		}
		User userFound = User.get(user.id)
		def type = params.type
		def defaultAwards = null
		if(type.equals('default')){
			defaultAwards = Award.list()
		} else {
			int score = Integer.valueOf(params.score)
			defaultAwards = getMyAwardList(score)
		}
		[awards : defaultAwards, myScore : userFound.score]
	}
	
	def exchange = {
		User user = User.get(Long.valueOf(params.userId))
		user.substractScore(Integer.valueOf(params.subScore))
		
		redirect(controller:'user', action: 'viewProfile', params:[userId:user.id])
	}
	
	private ArrayList<Award> getMyAwardList(int myScore){
		ArrayList<Award> defaultAwards = Award.list()
		def myAwards = new ArrayList<Award>()
		def high = false
		def pos = 0
		while(!high && pos < defaultAwards.size){
			Award item = defaultAwards.get(pos)
			if(item.getScore() <= myScore){
				myAwards.add(item)
			} else {
				high = true
			}
			pos++
		}
		myAwards
	}
	
	void goToHome(){
		redirect(uri: '/')
	}
}
