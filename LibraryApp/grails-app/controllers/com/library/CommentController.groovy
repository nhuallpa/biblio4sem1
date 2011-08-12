package com.library

import com.library.exceptions.CommentDoesNotExistException;

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
		if(aUser){
			User userFound = User.get(aUser.id)
			listOfMyComments = userFound.commentsDone
		}
		[comments : listOfMyComments]
	}
	
	
	def toComment = {
		def user = session.user
		if (!user){
			goToHome()
		}
		Book aBook = Book.get(params.bookId)
		[book : aBook]
	
	}
	
	def toCommentBook = {
		User user = session.user
		if (!user){
			goToHome()
		}
		String aComment = (params.newComment)?params.newComment:"Without comment"
		Integer rating = (params.rating)?params.rating:0;
		
		rating -= 48
		
		Book aBook = Book.get(params.bookId)
		if (!user.isAttached() && aBook){
			def userFound = User.get(user.id)
			assert userFound
			Comment comment = new Comment(description:aComment,book:aBook).save()
			userFound.addBookComment aBook, aComment, rating
			session.user = null
			session.user = userFound
			flash.message = "You are commented on ${aBook.name}!!"
			redirect(action: 'viewMyComments')
		} else {
			redirect(action: 'toComment')
		}				
	}
	
	def addCommentToBook = {
		User user = session.user
		if (!user){
			goToHome()
		}
		String aComment = (params.commentText)?params.commentText:"Without comment"
		Integer rating = (params.rating)?params.rating:0;
		
		rating -= 48
		
		Book aBook = Book.get(params.bookId)
		if (!user.isAttached() && aBook){
			def userFound = User.get(user.id)
			assert userFound
			userFound.addBookComment aBook, aComment, rating
			session.user = null
			session.user = userFound
			flash.message = "You are commented on ${aBook.name}!!"			
		} 		
		redirect(controller:'book', action: 'viewDetails', params:[bookId:params.bookId])
	}
	
	def deleteComment = {
		User user = session.user
		if (!user){
			goToHome()
		}
		Comment aComment = Comment.get(params.commentId)
		Book aBook = Book.get(params.bookId)
		
//		if (!user.isAttached()) {
//			user.attach()
		
			try{
				aBook.deleteComment aComment
				user.deleteMyComment aComment
				flash.message = "You deleted comment about ${aComment.book.name}"
				aComment.delete()
				
								
			}catch (CommentDoesNotExistException e){
				goToHome()
				}
//		}
		redirect(action: "viewMyComments")
	}
	
	void goToHome(){
		redirect(uri: '/')
	}

	void goToHomeAndBack(String urlBack){
		
	}
	
}
