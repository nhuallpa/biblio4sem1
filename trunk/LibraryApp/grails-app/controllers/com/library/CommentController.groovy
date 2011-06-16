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
	
	def toReserve = {
		User user = session.user
		if (!user){
			redirect(uri: '/')
		}
		Comment comment = Comment.get(params.description)
		Book aBook = Book.get(params.bookId)
		if (!user.isAttached() && comment) {
			user.attach()
			user.addBookComment aBook, comment.getDescription(), comment.getScore()
		}
		redirect(action: 'viewMyComments')
	}
	
	
}
