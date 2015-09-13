package com.ranguei.handlers.request

import java.io.InputStream;

import groovy.lang.Closure;
import groovy.transform.Immutable


class RequestErrorHandler {
	
	private final Closure successFn
	private final Closure errorFn
	private final Closure function
	private final InputStream inStream

	RequestErrorHandler(Closure successFn, Closure errorFn, Closure function, InputStream inStream) {
		this.successFn = successFn
		this.errorFn = errorFn
		this.function = function
		this.inStream = inStream
	}


	def result(){
		try {
			successFn inStream ? function(inStream) : function()
		} catch (ex) {
			errorFn ex
		}
	}
}