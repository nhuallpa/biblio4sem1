package com.library

class CommentController {
	
	def scaffold = true
	
	def index = {
		redirect(action: 'create')
	}
	
	def viewMyComments = {
		User aUser = session.user
		def listOfMyComments = null
		if (!aUser.isAttached()) {
			aUser.attach()
			listOfMyComments = aUser.comments
		}	
		
		[comments : listOfMyComments]
	
	}
	
	
	def toComment = {
		User user = session.user
		if (!user){
			redirect(uri: '/')
		}
		
		Book aBook = Book.get(params.bookId)
//		if (!user.isAttached()) {
//			user.attach()
//		}
		[book : aBook]
	
	}
	
	def toCommentBook = {
		User user = session.user
		if (!user){
			redirect(uri: '/')
		}
		String aComment = params.newComment
		Integer rating = params.rating
		rating -= 48
		Date d = new Date();
		Comment comment = new Comment(description: aComment, score: rating, date: d)
		comment.save()
		Book aBook = Book.get(params.bookId)
		if (!user.isAttached()){
			user = user.attach()	
//			user.addBookComment aBook, comment.getDescription(), comment.getScore()
			user.addToBookComment aBook, comment
			flash.message = "You are commented!!"
			redirect(action: 'viewMyComments')
		} else {
		redirect(action: 'toComment')
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
			try{
				user.deleteMyComment aComment
			}catch (Exception e){
				redirect(uri: '/')
				}
//		}
		redirect(action: "viewMyComments")
	}
	

	
}
