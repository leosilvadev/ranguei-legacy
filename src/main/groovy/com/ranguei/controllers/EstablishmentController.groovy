package com.ranguei.controllers

import groovy.stream.Stream;
import groovy.transform.CompileStatic

import com.mongodb.DB
import com.ranguei.configuration.MongoDB
import com.ranguei.domains.Establishment;
import com.ranguei.handlers.request.RequestHandler

class EstablishmentController {
	
	static def actions = {
		
		DB db = MongoDB.instance.db
		
		get "/", { instream ->
				new RequestHandler(instream, {
					Establishment.all()
					
				}).onSuccess({ establishments ->
					writeJSON(
						Stream.from(establishments).collect {
							[username: it.username, password: it.password]
						}
					)
				
				}).onError({ ex ->
					error 500, ex.message
				
				}).result()
		}
		
		post "/", { instream ->
			new RequestHandler(instream, {
				Establishment.save new Establishment(to.json(instream))
				
			}).onSuccess({
				writeJSON(['message':'Establishment was registered!'])
			
			}).onError({ ex ->
				error 400, 'Invalid JSON Data'
			
			}).result()
		}
	}

}
