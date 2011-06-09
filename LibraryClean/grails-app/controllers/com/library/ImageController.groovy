package com.library

class PhotoUploadCommand {
	byte[] photo
	String libraryId
}


class ImageController {

	def upload = { PhotoUploadCommand puc ->
		def library = Library.findByLibraryId(puc.libraryId)
		library.photo = puc.photo
		redirect(controller:'library', action:'view')

	}

	def form = {
		[libraryList : Library.list()]
	}

	def view = {

	}

	def renderImage = {
		def library = Library.findByLibraryId(params.id)
		if (library?.photo) {
			response.setContentLength(library.photo.length)
			response.outputStream.write(library.photo)
		} else {
			response.sendError(404)
		}                                                                                        
	}
}
