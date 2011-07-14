package com.library

class CommentController {
	
	def scaffold = true
	
	def index = {
		redirect(action: 'create')
	}
	
	def viewMyComments = {
		User aUser = session.user
		def listOfMyComments = null
		if(!aUser){
			goToHome()
		}
		if (!aUser.isAttached()) {
			aUser.attach()
			listOfMyComments = aUser.comments
		}
		

		
		[comments : listOfMyComments]
	
	}
	
	
	def toComment = {
		User user = session.user
		if (!user){
			goToHome()
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
			goToHome()
		}
		String aComment = params.newComment
		Integer rating = params.rating
		rating -= 48
		
		Book aBook = Book.get(params.bookId)
		if (!user.isAttached() && aBook){
			user = user.attach()	
			user.addBookComment aBook, aComment, rating
			user.save()
			flash.message = "You are commented on ${aBook.name}!!"
			redirect(action: 'viewMyComments')
		} else {
			redirect(action: 'toComment')
		}				
	}
	
	def deleteComment = {
		User user = session.user
		if (!user){
			goToHome()
		}
		Comment aComment = Comment.get(params.commentId)
		
//		if (!user.isAttached()) {
//			user.attach()
			try{
				user.deleteMyComment aComment
			}catch (Exception e){
				goToHome()
				}
//		}
		redirect(action: "viewMyComments")
	}
	
	void goToHome(){
		redirect(uri: '/')
	}

	
}
