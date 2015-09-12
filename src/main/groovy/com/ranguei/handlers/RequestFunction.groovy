package com.ranguei.handlers

import groovy.transform.CompileStatic;
import groovy.transform.TypeChecked;

@TypeChecked
@CompileStatic
class RequestFunction {
	
	private Closure successFn
	private Closure errorFn
	private Closure function
	private InputStream inStream

	RequestFunction(Closure function, InputStream inStream=null){
		this.function = function
		this.inStream = inStream
	}

	RequestFunction onError(Closure onError){
		this.errorFn = onError
		this
	}
	
	RequestFunction onSuccess(Closure onSuccess){
		this.successFn = onSuccess
		this
	}

	def result(){
		try {
			def response = inStream ? function(inStream) : function()
			successFn response
		} catch (ex) {
			if(errorFn) errorFn ex
		}
	}
}