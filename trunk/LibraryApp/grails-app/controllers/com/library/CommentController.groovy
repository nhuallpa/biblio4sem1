package com.library

class CommentController {
	
	def scaffold = true

	def rateTemp = ""
	
    def index = {
		redirect(action: 'create')
	}
	
	def viewMyComments = {
		User user = session.user
		def listOfMyComments = null
		if (!user.isAttached()) {
			user.attach()
			listOfMyComments = user.comments
		}
		
		[comments : listOfMyComments]
	}
	
	def view= {
		
	}
	
	def toComment = {
		User user = session.user
		if (!user){
			redirect(uri: '/')
		}
		
		Book aBook = Book.get(params.bookId)
		if (!user.isAttached()) {
			user.attach()
		}
		[book : aBook]
		
	}
	
	def toCommentBook = {
		User user = session.user
		if (!user){
			redirect(uri: '/')
		}
		def aComment = params.newComment
		
		
		Book aBook = Book.get(params.bookId)
		if (!user.isAttached()){//  && rateTemp != "") {
			user.attach()	
			user.addBookComment aBook, aComment, rateTemp
			redirect(action: "viewMyComments")
		} else {
			redirect(action: "toComment")
		}
		
		
		
	}
	
	def deleteComment = {
		User user = session.user
		if (!user){
			redirect(uri: '/')
		}
		Comment aComment = Comment.get(params.commentId)
		
//		if (!user.isAttached()) {
//			user.attach()
			
			user.deleteComment aComment
			
		
		redirect(action: "viewMyComments")
	}
	
	def rate = {
		
		//QUE ES LO QUE HACE ESTA PARTE????
		rateTemp = params.rating
		
		Comment comment = params.comment
		
		render(template: "/comment/rate",
			model: [comment: comment, score: rateTemp])
//		render(template: "/comment/rating",
//			model: [user: user, rating: average])
		
		
	}
	

	
}
