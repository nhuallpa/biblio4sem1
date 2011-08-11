import grails.util.Environment

import org.grails.taggable.Tag

import com.library.*

class BootStrap {

    def init = { servletContext ->
		if (Environment.current == Environment.DEVELOPMENT){
			initOneLibray()
			initOtherLibrary()
			initThirdLibrary()
			initFourthLibrary()
		}
    }
	
	private def initOneLibray(){
		def l = new Location(country:"Argentina", city:"Buenos Aires", street:"Florida 600")
		l.save()
		def a = new Library(libraryId:'BM_ATENEO', name:'Ateneo', location: l)
		if  (!a.save()){
			a.errors.each {
				println it
			}
			assert a
		}
		
		
		def listOfBooks = []
		def listOfUsers = []
		
		def description = """	Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor
							quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. Aenean
                            ultricies mi vitae est. Mauris placerat eleifend leo. Quisque sit amet est et sapien ullamcorper pharetra. Vestibulum erat
                            wisi, condimentum sed, commodo vitae, ornare sit amet, wisi. Aenean fermentum, elit eget tincidunt condimentum,
                            eros ipsum rutrum orci, sagittis tempus lacus enim ac dui. Donec non enim in turpis pulvinar facilisis. Ut felis. Praesent
                            dapibus, neque id cursus faucibus"""
		def author = "Nulla vehicula"
		def bookNames = ["It","Thinking in Java","Learning C","Asp.net for Dummies","Codigo da Vinci","Taken","Harry Potter","Flex","HTML 5"]
		def userNames = ["gonza", "nestor", "ariel", "user", "admin"]
		
		def tagAction = new Tag(name: Constants.TYPE_ACTION).save()
		def tagFiction = new Tag(name: Constants.TYPE_FICTION).save()
		def tagDrama = new Tag(name: Constants.TYPE_DRAMA).save()

		Random random = new Random()
		bookNames.each { 
			def isbn = random.nextInt(456789)
			def theBook = new Book( ISBN: isbn, name: it, state: States.AVAILABLE)
			theBook.setDescription(description);
			theBook.setAuthor(author);
			a.addToBooks(theBook)
			listOfBooks << theBook
		}
		
		userNames.each {
			def aUser = new User(name: it, password: it)
			aUser.typesFav.add tagFiction
			aUser.typesFav.add tagAction
			aUser.save()
			assert aUser
			listOfUsers << aUser 
		}
		
		assert listOfUsers.size() == 5
		
		/**** COMMENTS ****/
		listOfBooks.each { theBook ->
			listOfUsers[0].addBookComment theBook, randomComments(), randomScore()
		}
		listOfBooks.each { theBook ->
			listOfUsers[1].addBookComment theBook, randomComments(), randomScore()
		}
		listOfBooks.each { theBook ->
			listOfUsers[2].addBookComment theBook, randomComments(), randomScore()
		}
		listOfBooks.each { theBook ->
			listOfUsers[3].addBookComment theBook, randomComments(), randomScore()
		}
		listOfBooks.each { theBook ->
			listOfUsers[4].addBookComment theBook, randomComments(), randomScore()
		}
		
		assert listOfBooks.size() == 9
		
		/*** RESERVATION ****/
		(listOfUsers[0] as User).makeReservation(listOfBooks[0])
		(listOfUsers[0] as User).makeReservation(listOfBooks[1])
		(listOfUsers[1] as User).makeReservation(listOfBooks[2])
		(listOfUsers[1] as User).makeReservation(listOfBooks[3])
		(listOfUsers[2] as User).makeReservation(listOfBooks[4])
		(listOfUsers[2] as User).makeReservation(listOfBooks[5])
		(listOfUsers[3] as User).makeReservation(listOfBooks[6])
		(listOfUsers[4] as User).makeReservation(listOfBooks[7])
		(listOfUsers[4] as User).makeReservation(listOfBooks[8])
					
	} 
	
	private def initOtherLibrary(){

		def description = "Donec vitae augue ut quam tempor molestie a sed nulla. Donec feugiat ligula vitae tortor adipiscing dignissim."
		def author = "Maecenas at felis"
		
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
						 "Practical Guide to Linux",
						 "Spring Batch in Action",
						 "Spring Integration in Action",
						 "The Library of Babel"]
		Random random = new Random()
		bookNames.each { 
			def isbn = random.nextInt(456789)
			def abook = new Book(ISBN: isbn, name: it, library: libraryCongreso, state: States.AVAILABLE);
			abook.setDescription(description)
			abook.setAuthor(author)
			libraryCongreso.addToBooks(abook) 	
		}
		
	}
	
	private void initThirdLibrary(){
		def description = "Donec vitae augue ut quam tempor molestie a sed nulla. Donec feugiat ligula vitae tortor adipiscing dignissim."
		def author = "Maecenas at felis"
		
		def l = new Location(country:"Argentina", city:"Buenos Aires", street:"Av Rivadavia 6800")
		l.save()
		def libraryFlores = new Library(libraryId:'BM_Flores', name:'Flores', location: l)
		if  (!libraryFlores.save()){
			libraryFlores.errors.each {
				println it
			}
			assert libraryFlores
		}
		
		def bookNames = ["iPad 2",
						 "My iPad",
						 "iPad For Seniors For Dummies",
						 "Hello Android",
						 "Beginning Android",
						 "Learn Java for Android Development",
						 "Pro Android",
						 "IPhone The Missing Manual",
						 "IPhone For Dummies",
						 "Beginning Blackberry Development",
						 "Advanced Blackberry Development",
						 "Blackberry Storm2",
						 "Professional Blackberry",
						 "The Ruby Programming Language"]
		Random random = new Random()
		bookNames.each {
			def isbn = random.nextInt(456789)
			def abook = new Book(ISBN: isbn, name: it, library: libraryFlores, state: States.AVAILABLE);
			abook.setDescription(description)
			abook.setAuthor(author)
			libraryFlores.addToBooks(abook)
		}
	}
	
	private void initFourthLibrary(){
		def description = "Donec vitae augue ut quam tempor molestie a sed nulla. Donec feugiat ligula vitae tortor adipiscing dignissim."
		def author = "Maecenas at felis"
		
		def l = new Location(country:"Argentina", city:"Buenos Aires", street:"Av Rivadavia 11684")
		l.save()
		def libraryLiniers = new Library(libraryId:'BM_Liniers', name:'Liniers', location: l)
		if  (!libraryLiniers.save()){
			libraryLiniers.errors.each {
				println it
			}
			assert libraryLiniers
		}
		
		def bookNames = ["Beginning Ruby",
						 "Ruby on Rails 3",
						 "Grails in Action",
						 "Grails Persistence with GORM and GSQL",
						 "Groovy in Action",
						 "Cobol for Dummies",
						 "Cobol Unleashed"]
		Random random = new Random()
		bookNames.each {
			def isbn = random.nextInt(456789)
			def abook = new Book(ISBN: isbn, name: it, library: libraryLiniers, state: States.AVAILABLE);
			abook.setDescription(description)
			abook.setAuthor(author)
			libraryLiniers.addToBooks(abook)
		}
		
	}
	
	private String randomComments() {
		def comments = ['Pesimo libro', 'Muy buen libro', 'Bueno', 'Muy malo', 'Desearia que tenga otra tapa']
		Random random = new Random()
		return comments[random.nextInt(comments.size())]
	}
	
	private Integer randomScore() {
		Random random = new Random()
		return random.nextInt(5)
	}
	
    def destroy = {
    }
}
