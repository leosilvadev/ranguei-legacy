package com.ranguei.configuration

import com.gmongo.GMongo
import com.gmongo.GMongoClient
import com.mongodb.DB
import com.mongodb.MongoURI

@Singleton(lazy=true, strict=false)
class MongoDB {
	
	DB db
	
	MongoDB(){
		db = configure()
	}
	
	private DB configure(){
		GMongo gmongo
		
		String envURI = System.getenv("MONGOLAB_URI")
		if(envURI){
			MongoURI uri = new MongoURI(envURI)
			gmongo = new GMongo(uri)
			return gmongo.getDB(uri.database)
			
		} else {
			return new GMongoClient().getDB("ranguei_db")
		
		}
	}

}
