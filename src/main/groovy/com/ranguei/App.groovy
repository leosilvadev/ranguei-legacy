package com.ranguei

import groovy.stream.Stream
import io.github.javaconductor.gserv.GServ

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
			static_root  'app'
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
			collection.save domain.asMap()
		}
		
		Domain.metaClass.'static'.delete = { Domain domain ->
			DBCollection collection = mongoCollection delegate
			collection.remove domain.asMap()
		}
		
		Domain.metaClass.'static'.delete = { String id ->
			DBCollection collection = mongoCollection delegate
			collection.remove(["_id": id])
		}
		
		Domain.metaClass.asMap = {
			Stream.from(this.class.declaredFields)
				.filter({ !it.synthetic })
				.filter({ !isStatic(it.modifiers) })
				.collectEntries {
					[ (it.name):this."$it.name" ]
				}
		}
	}
	
	static def mongoCollection(clazz){
		def mongoCollection = clazz.collection
		if(!mongoCollection) throw new RuntimeException("Entity not mapped as a MongoCollection")
		String name = mongoCollection.name
		MongoDB.instance.db."${name}"
	}

}
