package com.library

import grails.converters.JSON

import org.codehaus.groovy.grails.web.json.JSONObject

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
		def awardsList = null
		if(type.equals('default')){
			awardsList = Award.list()
		} else {
			int score = userFound.score
			awardsList = getMyAwardList(score)
		}
		[awards : awardsList, myScore : userFound.score]
	}
	
	def info = {
		User user = session.user
		if (!user){
			goToHome()
		}
		def awardId = params.award_id
		Award awardFounded = Award.get(Long.valueOf(awardId))
		User userFound = User.get(user.id)
		[award : awardFounded, myScore : userFound.score]
	}
	
	def exchange = {
		User user = User.get(Long.valueOf(params.userId))
		Award award = Award.get(Long.valueOf(params.awardId))
//		user.substractScore(Integer.valueOf(params.subScore))
		user.exchangeAward(award)
		
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
	
	/** MOBILE **/
	
	def awardsList = {
		def awardsList = new ArrayList()
		def awardsFounded = Award.list()
		
		for(obj in awardsFounded){
			def jsonAward = [
				awardId : obj.id,
				detail : obj.detail,
				score : obj.score,
				category : obj.category
			]
			awardsList.add(jsonAward)
		}
		
		def jsonData = [
				awards : awardsList
			]
		render jsonData as JSON
		
	}
	
	def picture = {
		JSONObject jsonObject = request.JSON
		def awardName = jsonObject.getString("category")
		def location = new File("web-app/images/award/" + awardName + "/award.jpg")
		response.setContentType("application/jpg")
		response.setContentLength(location.size().toInteger())
		OutputStream out = response.getOutputStream();
		out.write(location.bytes);
		out.close();
	}
	
	def toExchange = {
		JSONObject jsonObject = request.JSON
		def userId = jsonObject.getString("userId")
		def awardId = jsonObject.getString("awardId")
		User userFounded = User.get(Long.valueOf(userId))
		Award award = Award.get(Long.valueOf(awardId))
		userFounded.exchangeAward(award)
//		userFounded.substractScore(Integer.valueOf(subScore))
	}
	
	def getAward = {
		
		def awardId = params.awardId
		Award award = Award.get(Long.valueOf(awardId))
		def jsonData = [
				score : award.score,
				info : award.info,
				category : award.category,
				detail : award.detail
			]
		
		render jsonData as JSON
			
		
	}
}
