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
				
			}).onSuccess({ establishments ->
				writeJSON(
					establishments.collect {
						[username: it.username, password: it.password]
					}
				)
			
			}).onError({ ex ->
				error 500, ex.message
			
			}).result()
		}
		
		post "/", { instream ->
			handle({
				def establishment = to.json(instream)
				db.establishments << establishment
				
			}, instream).onSuccess({
				writeJSON(['message':'Establishment was registered!'])
			
			}).onError({ ex ->
				error 400, 'Invalid JSON Data'
			
			}).result()
		}
	}

}
