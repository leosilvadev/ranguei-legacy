package com.ranguei

import io.github.javaconductor.gserv.GServ

import org.bson.types.ObjectId

import com.mongodb.DBCollection
import com.ranguei.configuration.MongoDB
import com.ranguei.domains.Domain
import com.ranguei.routes.Router

class App {
	
	static main(args){
		applyDefaultMongoOperations()
		
		def gserv = new GServ()
		
		def resources = Router.configure(gserv)
		
		gserv.http {
			static_root  'dist'
			
			get '/', file('index.html')
			
			resources.each {
				resource it
			}
			
		}.start(port())
	}
	
	static def port(){
		System.getenv().PORT ?: 8080
	}
	
	static def applyDefaultMongoOperations(){
		Domain.metaClass.'static'.all = { ->
			DBCollection collection = mongoCollection delegate
			collection.find().toArray()
		}
		
		Domain.metaClass.'static'.save = { Domain domain ->
			DBCollection collection = mongoCollection delegate
			if(domain.id) {
				collection.update domain.asMap(), [_id:  new ObjectId(domain.id)]
			} else {
				collection.save domain.asMap()
			}
		}
		
		Domain.metaClass.'static'.delete = { String id ->
			DBCollection collection = mongoCollection delegate
			collection.remove([_id: new ObjectId(id)])
		}
	}
	
	static def mongoCollection(clazz){
		def mongoCollection = clazz.collection
		if(!mongoCollection) throw new RuntimeException("Entity not mapped as a MongoCollection")
		String name = mongoCollection.name
		MongoDB.instance.db."${name}"
	}

}
