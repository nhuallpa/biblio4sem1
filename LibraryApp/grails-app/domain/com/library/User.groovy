package com.library

import org.grails.taggable.*
import com.library.exceptions.*

class User implements Taggable{
	
	String name
	String password
	byte[] photo
	String homepage
	String email
	String phone
	long rating
	long totalVotes
	int score
	List<Award> myAwards = new ArrayList<Award>()
	List<Reservation> reservations = new ArrayList<Reservation>()
	List<Comment> commentsRcvd = new ArrayList<Comment>()
	List<Comment> commentsDone = new ArrayList<Comment>()
	
	Location location

	static constraints = {
		name(size: 3..60, nullable:true)
		password(size: 3..15, nullable:true)
		photo(nullable: true)
		homepage(url: true, nullable: true)
		email(email: true, nullable: true)
		phone(nullable: true)
		location(nullable: true)
		reservations(nullable: true)
		commentsRcvd(nullable: true)
		commentsDone(nullable: true)
		score(nullable:true)
		myAwards(nullable: true)
    }
	
	static mapping = {
		location lazy: false
		reservations lazy: false
		commentsRcvd lazy: false
		commentsDone lazy: false
		myAwards lazy : false
	}
	
	static hasMany = [commentsDone : Comment, reservations : Reservation, myAwards : Award]
	
	
	public String toString(){
		return name
	}
	
	
	
	void clearScore(){
		this.score = 0
	}
	
	
	/**
	 * No se pertmite reservar varios ejemplares del mismo libro
	 * 
	 * @param aBook Libro que desee reservar
	 * @param aLibrary Libreria donde se quiere pasar a buscar el ejemplar
	 * 
	 * @throws BookAlreadyReservedException Un ejemplar del libro ya fue reservado
	 * @throws NotExistBookCopyAvailable No hay un ejemplar disponible en la libreria seleccionada
	 * 
	 * */
	void makeReservation(Book aBook, Library aLibrary){

		if (this.isReserved(aBook)){
			 throw new BookAlreadyReservedException()
		} else {
			BookCopy aBookCopyAvailable = aLibrary.getBookCopyAvailable(aBook)
			if (!aBookCopyAvailable) throw new NotExistBookCopyAvailable()
			Reservation aReservation = new Reservation(aBookCopyAvailable, this)
			this.addReservation(aReservation)
			aLibrary.addToReservations(aReservation)
			this.save()
			log.debug "se creo una reservaci�n con id: " + aReservation.id
		}
		
	}

	private Boolean isReserved(Book aBook) {
		return reservations.any { it.getBookCopy().getBookMaster() == aBook};
	}
			
	void exchangeAward(Award award){
		substractScore(award.score)
		this.myAwards.add(award)
	}
	
	/**
	* Agrega comentario a un libro con score
	* @param aBook <b>Book</b> Un libro
	* @param aComment <b>String</b> Un comentario
	* @param Score <b>Integer</b> un puntaje
	* */
	void addBookComment(Book aBook, String aString, Integer score ){
		
		def aComment = new Comment(description: aString, sourceUser: this,
									score: score, date:new Date())
		
		aBook.addComment(aComment)
		this.commentsDone.add aComment
		addScore(Constants.SCORE_COMMENT)
	}
	
	

	
	void categorizeBook(Book aBook, String tag){
		aBook.categorizeMe(tag)
	}
	
	List<Reservation> lookMyReservations(){
		return this.reservations
	}
	
	void cancelReservation(Book aBook){
		Reservation reservationFound = this.reservations.find{it.getBookCopy().bookMaster == aBook}
		
				if (reservationFound) {
					reservationFound.cancel()
//					this.recalculateScore(reservationFound)
					if(!reservationFound.good()){
						substractScore(Constants.SCORE_PENALIZATION)
					}
					this.reservations.remove reservationFound
					reservationFound.delete()
				} else {
					throw new ReservationDoesNotExistException()
				}
	}
	
	void deleteMyComment(Comment aComment){
		if (!(this.commentsDone as ArrayList<Comment>).contains(aComment)) {
			throw new CommentDoesNotExistException()
		} else {
			this.commentsDone.remove aComment
			substractScore(Constants.SCORE_COMMENT)
		}
	}
	
	
	private void addReservation(Reservation aReservation){
		Date date = new Date()
		aReservation.reservationDate = date
		this.reservations?.add aReservation
		aReservation.save()
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		User user = (User)obj;
		if(user.getName().equals(this.name) && user.getPassword().equals(this.password)){
			return true;
		} 
		return false;
	}

	/**
	 * Apply save before you use this method
	 * */
	void addMyPreferencesTags(String tags) {
		this.parseTags(tags) // is taggable plugin's method
	}
	
	
	Boolean existReservation(Reservation reservation) {
		return reservations.contains(reservation)
	}
	
	void returnBook(BookCopy aBookCopy) {
		Reservation reservationFound = this.reservations.find{it.getBookCopy() == aBookCopy}
		
		if (reservationFound) {
			reservationFound.returnBook()
			this.recalculateScore(reservationFound);
			this.reservations.remove reservationFound
			reservationFound.delete()
		} else {
			throw new ReservationDoesNotExistException()
		}
	}
	
	void recalculateScore(Reservation reservation) {
		if (reservation.good()) {
			addScore(Constants.SCORE_RESERVE)//Score por reservar
		} else {
			substractScore(Constants.SCORE_PENALIZATION)
		}
	}
	
	private void substractScore(int score){
		if(score <= this.score){
			this.score = this.score - score;
		}
	}
	
	private void addScore(int score){
		this.score = this.score + score
	}

	@Deprecated
	void addUserLocation(String country, String city, String address){
		Location aLocation = new Location(country: country, city: city, street: address)
		this.location = aLocation
	}
	
	@Deprecated
	String seeAddress(){
		if (this.location == null) return "Buenos Aires, Paseo Colon 850"
		return this.location.address()
	}
}
