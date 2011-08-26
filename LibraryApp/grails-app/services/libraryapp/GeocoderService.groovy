package libraryapp

import com.library.Library
import com.library.User

class GeocoderService {

    static transactional = false

	def geocodeLocation(User aUser) {
		
		String base = 'http://maps.google.com/maps/api/geocode/xml?'
		
		def params = [sensor:false,
			  address:[aUser.getLocation().getCountry(),aUser.getLocation().getCity(),aUser.getLocation().getStreet()].collect { URLEncoder.encode(it,'UTF-8') }.join(',+')]
		
		def url = base + params.collect { k,v -> "$k=$v" }.join('&')
		
		def response = new XmlSlurper().parse(url)
		
		return response		

	}
	
	def geocodeLocation(Library aLibrary) {
		
		String base = 'http://maps.google.com/maps/api/geocode/xml?'
		
		def params = [sensor:false,
			  address:[aLibrary.getLocation().getCountry(),aLibrary.getLocation().getCity(),aLibrary.getLocation().getStreet()].collect { URLEncoder.encode(it,'UTF-8') }.join(',+')]
		
		def url = base + params.collect { k,v -> "$k=$v" }.join('&')
		
		def response = new XmlSlurper().parse(url)
		
		return response

	}
	
	
}
