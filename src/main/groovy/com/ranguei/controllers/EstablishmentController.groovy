package com.ranguei.controllers

import com.mongodb.DB
import com.ranguei.configuration.MongoDB
import static com.ranguei.handlers.RequestHandler.handle

class EstablishmentController {
	
	static def actions = {
		
		DB db = MongoDB.instance.db
		
		post "/", { instream ->
			handle(instream, {
				def establishment = to.json(instream)
				db.establishments << establishment
				
			}).success({
				writeJSON(['message':'Establishment was registered!'])
			
			}).error({ ex ->
				error 400, 'Invalid JSON Data'
			
			}).result()
		}
	}

}
