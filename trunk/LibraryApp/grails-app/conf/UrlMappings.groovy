class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/admin"(view:"/index")
		"/map"(view:"/gmaps")
		"/" {
			controller = "home"
			action	   = "index"
		}
		"500"(view:'/error')
		
	}
}