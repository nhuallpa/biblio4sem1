package com.library

import grails.test.*

class CommentUsersIntegrationTests extends GroovyTestCase {
	
	Comment comment
	Location location
	String desc
	
    protected void setUp() {
        super.setUp()
		desc = "Muy buen libro!!"
		

		location = new Location(country: "countryTest", street: "streetTest")
		
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSomething() {
		
		User aUser = new User(name: "Ariel", location: location)
		User otherUser = new User(name: "Ariel", location: location)
		comment = new Comment(sourceUser : aUser, description : desc, thingCommented: otherUser)
		aUser.comments.add comment
		comment.validate()

		assertNotNull comment.save()
		
		Comment commentFound = Comment.get(comment.id)
		assertEquals desc, commentFound.getDescription()
		
    }
}
