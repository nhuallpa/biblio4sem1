
import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes;
import org.springframework.context.ApplicationContext;

import com.library.Library;

import com.library.*

class BootStrap {

    def init = { servletContext ->
		
		def l = new Location(country:"Argentina", city:"Buenos Aires", street:"Florida 600")
		l.save()
		def a = new Library(libraryId:'BM_ATENEO', name:'Ateno', location: l)
		if  (!a.save()){
			a.errors.each { 
				println it	
			}
			assert a
		}
		
		def libraries = [a]
		def bookNames = ["It","Thinking in Java","Learning C","Asp.net for Dummies","Codigo da Vinci","Taken","Harry Potter","Flex","HTML 5"]
		def lastNames = ["Smith","Flinstone","Abbot","Williams","Adams","Goober","Brady","Jones","Heffernen"]
				
		Random random = new Random()
		1.upto(50) { i ->
		   def aBook = bookNames[ random.nextInt(9)]
		   def aName = lastNames[ random.nextInt(9)] + i
		   // def aLibrary = libraries[ random.nextInt(3)]
		   def isbn = random.nextInt(456789)
		   def pass = random.nextInt(888999)
		   
		   def theBook = new Book( ISBN: isbn, name: aBook, state: States.AVAILABLE)
		   a.addToBooks(theBook)
		   
		   //new Book( ISBN: isbn, name: aBook, library: a, state: States.AVAILABLE).save()
		   def theUser   
		   if (i == 1) {
			   theUser = new User(name: 'admin', password: 'admin').save()
		   } else{
			   theUser = new User(name: aName, password: pass).save()
		   }

		   theUser.addBookComment theBook, 'Pesimo libro', 1
		   theUser.addBookComment theBook, 'Muy buen libro', 4
		   theUser.addBookComment theBook, 'Creo que podria ser mejor', 2
		   theUser.addBookComment theBook, 'Bueno', 3
		   theUser.addBookComment theBook, 'Muy malo', 1
		   theUser.addBookComment theBook, 'Desearia que tenga otra tapa', 2
		   theUser.makeReservation(theBook)

		}

//		assert( Book.list().size() == 50 )
//		assert( User.list().size() == 50 )
		
		initOtherLibrary()
		
    }
	
	def initOtherLibrary(){
		
		def l = new Location(country:"Argentina", city:"Buenos Aires", street:"Av de Mayo 1400")
		l.save()
		def libraryCongreso = new Library(libraryId:'BM_Congreso', name:'Congreso', location: l)
		if  (!libraryCongreso.save()){
			libraryCongreso.errors.each {
				println it
			}
			assert libraryCongreso
		}
		
		def bookNames = ["Borges and His Fiction",
						 "Building Java Programs",
						 "Flex Solutions",
						 "Hibernate in Action (In Action series)",
						 "Intermediate Perl",
						 "Introduction to Java Programming",
						 "Java How to Program",
						 "Perl Cookbook, Second Edition",
						 "Practical Guide to Linux Commands, Editors, and Shell Programming, A (2nd Edition)",
						 "Spring Batch in Action",
						 "Spring Integration in Action",
						 "The Library of Babel"]
		Random random = new Random()
		bookNames.each { 
			def isbn = random.nextInt(456789)
			def abook = new Book(ISBN: isbn, name: it, library: libraryCongreso, state: States.AVAILABLE);
			libraryCongreso.addToBooks(abook) 	
		}
		
	}
	
    def destroy = {
    }
}
