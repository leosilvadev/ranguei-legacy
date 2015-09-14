package com.ranguei.handlers.request

import com.ranguei.exceptions.InvalidArgumentException


class RequestHandler {
	
	private final RequestData requestData
	private final Closure function
	
	RequestHandler(RequestData requestData, Closure function){
		if(!function) throw new InvalidArgumentException("Invalid function to execute")
		this.requestData = requestData
		this.function = function
	}

	RequestSuccessHandler onSuccess(Closure onSuccess){
		if(!onSuccess) throw new InvalidArgumentException("Invalid success callback")
		new RequestSuccessHandler(onSuccess, function, requestData)
	}

	def result(){
		requestData ? function(requestData) : function()
	}
	
}