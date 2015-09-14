package com.ranguei.handlers.request


class RequestErrorHandler {
	
	private final Closure successFn
	private final Closure errorFn
	private final Closure function
	private final RequestData requestData

	RequestErrorHandler(Closure successFn, Closure errorFn, Closure function, RequestData requestData) {
		this.successFn = successFn
		this.errorFn = errorFn
		this.function = function
		this.requestData = requestData
	}


	def result(){
		try {
			successFn requestData ? function(requestData) : function()
		} catch (ex) {
			ex.printStackTrace()
			errorFn ex
		}
	}
}