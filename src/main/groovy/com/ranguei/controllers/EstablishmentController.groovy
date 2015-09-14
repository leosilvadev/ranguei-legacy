package com.ranguei.controllers

import groovy.stream.Stream

import com.mongodb.DB
import com.ranguei.configuration.MongoDB
import com.ranguei.domains.Establishment
import com.ranguei.handlers.request.RequestData
import com.ranguei.handlers.request.RequestHandler

class EstablishmentController {
	
	static def actions = {
		
		DB db = MongoDB.instance.db
		
		get "/", { inputStream ->
			new RequestHandler(new RequestData(inputStream), {
				Establishment.all()
			
			}).onSuccess({ establishments ->
				writeJSON(
					Stream.from(establishments)
							.map { Establishment.fromDocument it }
							.collect { it.asMap() }
				)
			
			}).onError({ ex ->
				error 500, ex.message
			
			}).result()
		}
		
		post "/", { inputStream ->
			try{
			new RequestHandler(new RequestData(inputStream), { data ->
				Establishment.save new Establishment(to.json(data.body))
				
			}).onSuccess({
				writeJSON(['message':'Establishment was registered!'])
			
			}).onError({ ex ->
				error 400, 'Invalid JSON Data'
			
			}).result()
			}catch(ex){ex.printStackTrace()}
		}
		
		delete "/:id", { id ->
			new RequestHandler(new RequestData([id: id]), { data ->
				Establishment.delete data.params.id
				
			}).onSuccess({
				writeJSON(['message':'Establishment was deleted!'])
			
			}).onError({ ex ->
				error 400, 'Invalid Establishment'
			
			}).result()
		}
		
		put "/:id", { inputStream, id ->
			new RequestHandler(new RequestData(inputStream, [id:id]), { data ->
				Establishment.save new Establishment(to.json(data.body), data.params.id)
				
			}).onSuccess({
				
			}).onError({ ex ->
				
			}).result()
		}
	}

}
