
import grails.util.Environment

import org.grails.taggable.Tag

import com.library.*

class BootStrap {
	
	private List<Library> librarys = new ArrayList<Library>() 
	
	private List<Award> awards = new ArrayList<Award>()

    def init = { servletContext ->
//		if (Environment.current == Environment.PRODUCTION){
//			initLibraryEntities()
//			initOneLibray()
//			initOtherLibrary()
//			initThirdLibrary()
//			initFourthLibrary()
//			initAwards()
//		}
		
		if(Environment.current == Environment.DEVELOPMENT){
			initLibraryEntities()
			initOneLibray()
			initOtherLibrary()
			initThirdLibrary()
			initFourthLibrary()
			initAwards()
		}
    }
	
	private def initOneLibray(){
		
		def listOfBooks = []
		def listOfUsers = []
		
		def description = "	Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortorquam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. Aenean " +
                          "  ultricies mi vitae est. Mauris placerat eleifend leo. Quisque sit amet est et sapien ullamcorper pharetra. Vestibulum erat" + 
                          "  wisi, condimentum sed, commodo vitae, ornare sit amet, wisi. Aenean fermentum, elit eget tincidunt condimentum, " +
                           " eros ipsum rutrum orci, sagittis tempus lacus enim ac dui. Donec non enim in turpis pulvinar facilisis. Ut felis. Praesent" +
                           " dapibus, neque id cursus faucibus"
		def author = "Nulla vehicula"
		def bookNames = ["It","Thinking in Java","Learning C","Asp.net for Dummies","Codigo da Vinci","Taken","Harry Potter","Flex","HTML 5"]
		def userNames = ["gonza", "nestor", "ariel", "user", "admin"]
		Random random = new Random()
		bookNames.each { 
			def isbn = random.nextInt(456789)
			def theBook = new Book( ISBN: isbn, name: it, state: States.AVAILABLE)
			theBook.setDescription(description);
			theBook.setAuthor(author);
			assert theBook.save()
			theBook.categorizeMe("web")
			theBook.categorizeMe("developer")
			deliveryCopys(theBook)
			listOfBooks << theBook
		}
		
		userNames.each {
			User aUser = new User(name: it, password: it, phone:"222-2222", email:"zaraza@gmail.com")
			def aLoc = new Location(country:"Argentina", city:"Buenos Aires", street:"Florida 200")
			aUser.setLocation aLoc
			aUser.setScore(5)
			aLoc.save()
			aUser.save()
			assert aUser
			aUser.addMyPreferencesTags("accion, development, comedia, terror, java")
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
		(listOfUsers[0] as User).makeReservation(listOfBooks[0], librarys[0])
		(listOfUsers[0] as User).addScore(20)
		(listOfUsers[0] as User).makeReservation(listOfBooks[1], librarys[0])
		(listOfUsers[0] as User).addScore(20)
		
		(listOfUsers[1] as User).makeReservation(listOfBooks[2], librarys[0])
		(listOfUsers[1] as User).makeReservation(listOfBooks[3], librarys[0])
		
		(listOfUsers[2] as User).makeReservation(listOfBooks[4], librarys[0])
		(listOfUsers[2] as User).makeReservation(listOfBooks[5], librarys[0])
		
		(listOfUsers[3] as User).makeReservation(listOfBooks[6], librarys[0])
		
		(listOfUsers[4] as User).makeReservation(listOfBooks[7], librarys[0])
		(listOfUsers[4] as User).makeReservation(listOfBooks[8], librarys[0])
					
	} 
	
	private def initOtherLibrary(){

		def description = "Donec vitae augue ut quam tempor molestie a sed nulla. Donec feugiat ligula vitae tortor adipiscing dignissim."
		def author = "Maecenas at felis"
		
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
			def aBook = new Book(ISBN: isbn, name: it, state: States.AVAILABLE);
			aBook.setDescription(description)
			aBook.setAuthor(author)
			aBook.save()
			
			aBook.categorizeMe("fontend")
			aBook.categorizeMe("gorm")
			
			deliveryCopys(aBook)
		}
		
	}
	
	private void initThirdLibrary(){
		def description = "Donec vitae augue ut quam tempor molestie a sed nulla. Donec feugiat ligula vitae tortor adipiscing dignissim."
		def author = "Maecenas at felis"
		
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
			def aBook = new Book(ISBN: isbn, name: it, state: States.AVAILABLE);
			aBook.setDescription(description)
			aBook.setAuthor(author)
			aBook.save()
			
			aBook.categorizeMe("development")
			aBook.categorizeMe("mobile")
			
			deliveryCopys(aBook)
		}
	}
	
	private void initFourthLibrary(){
		def description = "Donec vitae augue ut quam tempor molestie a sed nulla. Donec feugiat ligula vitae tortor adipiscing dignissim."
		def author = "Maecenas at felis"
		
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
			def aBook = new Book(ISBN: isbn, name: it, state: States.AVAILABLE);
			aBook.setDescription(description)
			aBook.setAuthor(author)
			aBook.save()
			aBook.categorizeMe("development")
			aBook.categorizeMe("accion")
			deliveryCopys(aBook)
		}
		
	}
	
	private String randomComments() {
		def comments = ['Pesimo libro', 'Muy buen libro', 'Bueno', 'Muy malo', 'Desearia que tenga otra tapa']
		Random random = new Random()
		return comments[random.nextInt(comments.size())]
	}
	
	private void initLibraryEntities() {
		
		
		
		def l1 = new Location(country:"Argentina", city:"Buenos Aires", street:"Florida 600")
		l1.save()
		def libraryAteneo = new Library(libraryId:'BM_ATENEO', name:'Ateneo', location: l1,
										phone:"49532266", email:"ateneo@libreria.com.ar")
		if  (!libraryAteneo.save()){
			libraryAteneo.errors.each {
				println it
			}
			assert libraryAteneo
		}
		
		librarys.add(libraryAteneo)
		
		def l2 = new Location(country:"Argentina", city:"Buenos Aires", street:"Av de Mayo 1400")
		l2.save()
		def libraryCongreso = new Library(libraryId:'BM_Congreso', name:'Congreso', location: l2,
										phone:"42222222", email:"congreso@libreria.com.ar")
		assert libraryCongreso.save()
		librarys.add(libraryCongreso)
		
		
		def l3 = new Location(country:"Argentina", city:"Buenos Aires", street:"Av Rivadavia 6800")
		l3.save()
		def libraryFlores = new Library(libraryId:'BM_Flores', name:'Flores', location: l3,
										phone:"49002266", email:"flores@libreria.com.ar")
		assert libraryFlores.save()
		librarys.add(libraryFlores)
		
		
		def l4 = new Location(country:"Argentina", city:"Buenos Aires", street:"Av Rivadavia 11684")
		l4.save()
		def libraryLiniers = new Library(libraryId:'BM_Liniers', name:'Liniers', location: l4,
										phone:"44442266", email:"linears@libreria.com.ar")
		assert libraryLiniers.save()
		librarys.add(libraryLiniers)
	}
	
	// van a haber 3 copias en cada libreria por cada libro
	private void deliveryCopys(Book aBook) {
		for (Library library : librarys) {
			library.addBookCopyOf(aBook)
			library.addBookCopyOf(aBook)
			library.addBookCopyOf(aBook)
		}
	}
	
	private Integer randomScore() {
		Random random = new Random()
		return random.nextInt(5)
	}
	
	private void initAwards(){
		
		def awardsInfo = ["At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.",
							"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
							"The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from de Finibus Bonorum et Malorum by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.",
							"It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like)."]

		
//		// Do it this way instead.
//		def book = new Book(title: 'Misery')
//		author.addToBooks(book)
//		author.save()
		
		
		
		def a1 = new Award(score : 50, detail : 'Cine x 1', category : 'cine', info : awardsInfo[0])
		assert a1.save()
		awards.add(a1)
		
		def a2 = new Award(score : 300, detail : 'Cine x 2', category : 'cine', info : awardsInfo[0])
		assert a2.save()
		awards.add(a2)
		
		def a3 = new Award(score : 350, detail : 'Comida x 1' , category : 'comida', info : awardsInfo[1])
		assert a3.save()
		awards.add(a3)
		
		def a4 = new Award(score : 400, detail : 'Comida x 2', category : 'comida', info : awardsInfo[1])
		assert a4.save()
		awards.add(a4)
		
		def a5 = new Award(score : 450, detail : 'Entrada recital', category : 'recital', info : awardsInfo[2])
		assert a5.save()
		awards.add(a5)
		
		def a6 = new Award(score : 550, detail : 'Entrada recital x 2', category : 'recital', info : awardsInfo[2])
		assert a6.save()
		awards.add(a6)
		
		def a7 = new Award(score : 1000, detail : '1 Pasaje a Colonia - Uruguay', category : 'viaje', info : awardsInfo[3])
		assert a7.save()
		awards.add(a7)
		
		def a8 = new Award(score : 1500, detail : '1 Pasaje a Cataratas' , category : 'viaje', info : awardsInfo[3])
		assert a8.save()
		awards.add(a8)
		
		def a9 = new Award(score : 2000, detail : '1 Pasaje a Brasil', category : 'viaje', info : awardsInfo[3])
		assert a9.save()
		awards.add(a9)
		
	}
	
    def destroy = {
    }
}
