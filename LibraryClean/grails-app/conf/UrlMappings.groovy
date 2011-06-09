class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		"/home" {
			controller = "home"
			action	   = "index"
		}

		"/"(view:"/index")
		"500"(view:'/error')
		
	}
}
