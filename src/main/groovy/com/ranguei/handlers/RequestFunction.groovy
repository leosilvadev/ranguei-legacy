package com.ranguei.handlers

class RequestFunction {
	
	private def onSuccess
	private def onError
	private def function
	private def inStream

	RequestFunction(inStream, function){
		this.inStream = inStream
		this.function = function
	}

	RequestFunction error(error){
		onError = error
		this
	}

	RequestFunction success(success){
		onSuccess = success
		this
	}

	def result(){
		try {
			def response = function(inStream)
			onSuccess response
		} catch (ex) {
			onError ex
		}
	}
}