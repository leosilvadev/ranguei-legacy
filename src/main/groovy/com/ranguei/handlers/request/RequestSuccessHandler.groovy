package com.ranguei.handlers.request

class RequestSuccessHandler {
	
	private final Closure successFn
	private final Closure function
	private final RequestData requestData

	RequestSuccessHandler(Closure successFn, Closure function, RequestData requestData) {
		this.successFn = successFn
		this.function = function
		this.requestData = requestData
	}

	RequestErrorHandler onError(Closure onError){
		new RequestErrorHandler(successFn, onError, function, requestData)
	}
	
	def result(){
		def result = requestData ? function(requestData) : function()
		if (successFn) successFn result
	}
}