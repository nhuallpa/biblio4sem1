import com.library.*

class BootStrap {

    def init = { servletContext ->
		
		Library a = new Library( libraryId: 01, name: "El Ateneo").save();
		Library b = new Library( libraryId: 02, name: "Cuspide").save();
		Library c = new Library( libraryId: 03, name: "Piramide").save();
		
		def libraries = [a, b, c]
		def bookNames = ["It","Thinking in Java","Learning C","Visual Basic for Dummies","Codigo da Vinci","Taken","Harry Potter","Bla","BlaBla"]
		def lastNames = ["Smith","Flinstone","Abbot","Williams","Adams","Goober","Brady","Jones","Heffernen"]
		
		Random random = new Random()
		1.upto(50) { i ->
		   def aBook = bookNames[ random.nextInt(9)]
		   def aName = lastNames[ random.nextInt(9)] + i
		   // def aLibrary = libraries[ random.nextInt(3)]
		   def isbn = random.nextInt(456789)
		   def pass = random.nextInt(888999)
		   
		   def theBook = new Book( ISBN: isbn, title: aBook, library: a, state: "Available").save()
		   def theUser   
		   if (i == 1) {
			   theUser = new User(name: 'admin', password: 'admin').save()
		   } else{
			   theUser = new User(name: aName, password: pass).save()
		   }
		   
		   theBook.addComment theUser, 'Pesimo libro', 15

		   theUser.makeReservation(theBook)
		   
		   
		}
		
		assert( Book.list().size() == 50 )
		assert( User.list().size() == 50 )
		
		
    }
    def destroy = {
    }
}
