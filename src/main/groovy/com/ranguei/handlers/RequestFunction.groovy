package com.ranguei.handlers

class RequestFunction {
	
	private def onSuccess
	private def onError
	private def function
	private def inStream

	RequestFunction(function, inStream=null){
		this.function = function
		this.inStream = inStream
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
			def response = inStream ? function(inStream) : function()
			onSuccess response
		} catch (ex) {
			if(onError) onError ex
		}
	}
}