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
		if (!user.isAttached()) {
			user.attach()
			user.addBookComment aBook, aComment, 45
		}
		redirect(action: "viewMyComments");
		
	}
	
	
}
