package com.library

import grails.converters.JSON

import com.library.exceptions.CommentDoesNotExistException

class CommentController {
	
	def scaffold = true
	
	def getComment = {
		def commentFounded = Comment.get(params.commentId)
//		render commentFounded as JSON
		def jsonData = [
				description: commentFounded.description,
				date: commentFounded.date,
				user: [
					id: commentFounded.sourceUser.id,
					name: commentFounded.sourceUser.name,
					
					],
				book: [
					id:commentFounded.book.id,
					name: commentFounded.book.name,
					]
			]
		render jsonData
	}
	
	
	def index = {
		redirect(action: 'create')
	}
	
	def toComment = {
		def user = session.user
		if (!user){
			goToHome()
		}
		Book aBook = Book.get(params.bookId)
		[book : aBook]
	
	}
	
//	def toCommentBook = {
//		User user = session.user
//		if (!user){
//			goToHome()
//		}
//		String aComment = (params.newComment)?params.newComment:"Without comment"
//		Integer rating = (params.rating)?params.rating:0;
//		
//		rating -= 48
//		
//		Book aBook = Book.get(params.bookId)
//		if (!user.isAttached() && aBook){
//			def userFound = User.get(user.id)
//			assert userFound
//			Comment comment = new Comment(description:aComment,book:aBook).save()
//			userFound.addBookComment aBook, aComment, rating
//			session.user = null
//			session.user = userFound
//			flash.message = "You are commented on ${aBook.name}!!"
//			redirect(action: 'viewMyComments')
//		} else {
//			redirect(action: 'toComment')
//		}				
//	}
	
	//params: userId,text,rating,bookId
	def toCommentBook = {
		User user = User.get(Long.valueOf(params.userId))
		redirect(controller:'comment', action:'addCommentToBook', user:user, commentText: params.text,
					rating:params.rating, bookId: params.bookId)
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

		try {
			aBook.deleteComment aComment
			user.deleteMyComment aComment
			flash.message = "You have deleted a comment about ${aComment.book.name}"
			user.save()
			aBook.save()
			aComment.delete()
		} catch (CommentDoesNotExistException e){
			goToHome()
		}
		redirect(controller:'user', action: 'viewProfile', params:[userId:user.id])
	}
	
	void goToHome(){
		redirect(uri: '/')
	}

	void goToHomeAndBack(String urlBack){
		
	}
	
}
