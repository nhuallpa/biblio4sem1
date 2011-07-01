package com.library

class CommentController {
	
	def scaffold = true

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
		String aComment = params.newComment
		Integer rating = params.rating
		rating -= 48
//		Comment comment = new Comment(description:aComment, score:rating)
//		comment.save()
		Book aBook = Book.get(params.bookId)
		if (!user.isAttached()){
			user.attach()	
			user.addBookComment aBook, aComment, rating
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
