package com.ranguei.handlers

import groovy.json.JsonException;

class RequestHandler {
		
	static RequestFunction handle(inStream, function){
		new RequestFunction(inStream, function)
	}

}
