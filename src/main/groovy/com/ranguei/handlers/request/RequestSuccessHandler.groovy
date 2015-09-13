package com.ranguei.handlers.request

import java.io.InputStream;

import groovy.lang.Closure;
import groovy.transform.Immutable


class RequestSuccessHandler {
	
	private final Closure successFn
	private final Closure function
	private final InputStream inStream

	RequestSuccessHandler(Closure successFn, Closure function, InputStream inStream) {
		this.successFn = successFn
		this.function = function
		this.inStream = inStream
	}

	RequestErrorHandler onError(Closure onError){
		new RequestErrorHandler(successFn, onError, function, inStream)
	}
	
	def result(){
		def result = inStream ? function(inStream) : function()
		if (successFn) successFn result
	}
}