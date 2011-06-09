package com.library

import com.sun.org.apache.xalan.internal.xsltc.compiler.When;

import grails.test.*
import groovy.mock.interceptor.MockFor;

class CommentIntegrationTests extends GroovyTestCase {
	
	String desc;
	
    protected void setUp() {
        super.setUp()
		desc = "Muy buen libro!!";
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSomething() {
		
		Comment comment = new Comment(description : desc)
		assertTrue comment.validate()
		assertNotNull comment.save()
		
		Comment commentFound = Comment.get(comment.id)
		assertEquals desc, commentFound.getDescription()
    }
}
