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
			listOfMyComments = aUser.commentsDone
		}
		[comments : listOfMyComments]
	}
	
	
	def toComment = {
		def user = session.user
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
			def userFound = User.get(user.id)
			assert userFound
			userFound.addBookComment aBook, aComment, rating
			session.user = null
			session.user = userFound
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
				aComment.delete()
				user.deleteMyComment aComment
				flash.message = "You deleted comment about ${aComment.book.name}"
								
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
