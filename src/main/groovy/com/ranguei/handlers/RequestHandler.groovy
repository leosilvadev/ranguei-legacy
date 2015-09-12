package com.ranguei.handlers

import groovy.json.JsonException;

class RequestHandler {
		
	static RequestFunction handle(function, inStream=null){
		new RequestFunction(function, inStream)
	}

}
