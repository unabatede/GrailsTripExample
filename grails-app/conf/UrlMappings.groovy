class UrlMappings {
    static mappings = {

/*      "/$controller/$action?/$id?"{
	      constraints {
			 // apply constraints here
		  }
	  }*/
      "/"(view:"/index")
	  "500"(view:'/error')

/*      "/$controller/$id"{
        action = [POST:"create", GET:"show", PUT:"update", DELETE:"delete"]
      }*/

      "/trips"(controller: "trip", parseRequest: true) {
        action = [GET: "list", POST: "save"]
      }

      "/trips/$id"(controller: "trip", parseRequest: true) {
        action = [GET: "show", PUT: "update", DELETE: "delete"]
      }

	}
}
