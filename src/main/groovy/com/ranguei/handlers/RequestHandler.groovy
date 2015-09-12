package com.ranguei.handlers

import groovy.transform.CompileStatic;
import groovy.transform.TypeChecked;

@TypeChecked
@CompileStatic
class RequestHandler {
	
	static RequestFunction handle(Closure function, InputStream inStream=null){
		new RequestFunction(function, inStream)
	}

}
