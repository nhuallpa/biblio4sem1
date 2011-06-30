import com.library.Library;

import com.library.*

class BootStrap {

    def init = { servletContext ->
		
		def a = new Library(libraryId:'BM_ATENEO', name:'Ateno')
		if  (!a.save()){
			a.errors.each { 
				println it	
			}
			assert a
		}
				
		def libraries = [a]
		def bookNames = ["It","Thinking in Java","Learning C","Visual Basic for Dummies","Codigo da Vinci","Taken","Harry Potter","Bla","BlaBla"]
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
		   new Book( ISBN: isbn, name: aBook, library: a, state: States.AVAILABLE).save()
		   def theUser   
		   if (i == 1) {
			   theUser = new User(name: 'admin', password: 'admin', score: [1]).save()
		   } else{
			   theUser = new User(name: aName, password: pass).save()
		   }

		   theUser.addBookComment theBook, 'Pesimo libro', 15
		   theUser.addBookComment theBook, 'Muy buen libro', 30
		   theUser.makeReservation(theBook)

		}

		assert( Book.list().size() == 50 )
		assert( User.list().size() == 50 )
		
		
    }
    def destroy = {
    }
}
