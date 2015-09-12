package com.ranguei.controllers

import com.mongodb.DB
import com.ranguei.configuration.MongoDB
import static com.ranguei.handlers.RequestHandler.handle

class EstablishmentController {
	
	static def actions = {
		
		DB db = MongoDB.instance.db
		
		get "/", {
			handle({
				db.establishments.find().toArray()
				
			}).success({ establishments ->
				writeJSON(
					establishments.collect {
						[username: it.username, password: it.password]
					}
				)
			
			}).error({ ex ->
				error 500, ex.message
			
			}).result()
		}
		
		post "/", { instream ->
			handle({
				def establishment = to.json(instream)
				db.establishments << establishment
				
			}, instream).success({
				writeJSON(['message':'Establishment was registered!'])
			
			}).error({ ex ->
				error 400, 'Invalid JSON Data'
			
			}).result()
		}
	}

}
