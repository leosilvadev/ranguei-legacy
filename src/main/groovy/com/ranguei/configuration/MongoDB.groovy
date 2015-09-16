package com.ranguei.configuration

import com.gmongo.GMongo
import com.gmongo.GMongoClient
import com.mongodb.DB
import com.mongodb.MongoURI

@Singleton(strict=false)
class MongoDB {
	
	final DB db
	
	MongoDB(){
		db = configure()
	}
	
	private DB configure(){
		String envURI = System.getenv("MONGOLAB_URI")
		
		if (envURI) {
			MongoURI uri = new MongoURI(envURI)
			GMongo gmongo = new GMongo(uri)
			return gmongo.getDB(uri.database)
			
		} else {
			return new GMongoClient().getDB("ranguei_db")
		
		}
	}

}
