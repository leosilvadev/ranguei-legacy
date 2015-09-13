package com.ranguei.handlers.request

import groovy.transform.Immutable


class RequestHandler {
	
	private final InputStream inStream
	private final Closure function
	
	RequestHandler(InputStream inStream, Closure function){
		this.inStream = inStream
		this.function = function
	}

	RequestSuccessHandler onSuccess(Closure onSuccess){
		new RequestSuccessHandler(onSuccess, function, inStream)
	}

	def result(){
		inStream ? function(inStream) : function()
	}
}