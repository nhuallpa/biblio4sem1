package com.library

import grails.converters.JSON

import org.codehaus.groovy.grails.web.json.JSONObject

import com.library.exceptions.CommentDoesNotExistException

class CommentController {
	
	def scaffold = true
	
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
	
	def addCommentToBook = {
		User user = session.user
		if (!user){
			goToHome()
		}
		String aComment = (params.commentText)?params.commentText:"Without comment"
		Integer rating = (params.bookRating)
		rating -= 48
		println (rating)
		println("OK")
		
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
		User userFound = User.get(user.id)
		try {
			aBook.deleteComment aComment
			userFound.deleteMyComment aComment
			flash.message = "You have deleted a comment about ${aComment.book.name}"
			userFound.save()
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
	
	
	/** MOBILE **/
	
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


	
	//params: userId,bookId
	//request: text,rating,extra
	def toCommentBook = {
		User user = User.get(params.userId)
		Book aBook = Book.get(params.bookId)
		
		JSONObject jsonObject = request.JSON
		String aComment = jsonObject.getString("text")
		Integer rating = jsonObject.getString("rating")
		rating -= 48
		user.addBookComment aBook, aComment, rating
	}
	
	def delComment = {
//		User user = User.get(params.userId)
//		Comment aComment = Comment.get(params.commentId)
//		Book aBook = Book.get(params.bookId)
		
		JSONObject jsonObject = request.JSON
		User user = User.get(jsonObject.getString("userId"))
		Comment aComment = Comment.get(jsonObject.getString("commentId"))
		Book aBook = Book.get(jsonObject.getString("bookId"))
		
		try {
			aBook.deleteComment aComment
			user.deleteMyComment aComment
			user.save()
			aBook.save()
			aComment.delete()
		} catch (CommentDoesNotExistException e){
			response.writer.println("Error: " + e)
		}
		
//		render "Comment deleted"
	}

}
