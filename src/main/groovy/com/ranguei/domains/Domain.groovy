package com.ranguei.domains

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;

import groovy.stream.Stream
import static java.lang.reflect.Modifier.isStatic

trait Domain {
	
	abstract Map asMap()
	
}
