class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/admin"(view:"/index")
		"/" {
			controller = "book"
			action	   = "searchBookHome"
		}
		"500"(view:'/error')
		
	}
}
