package com.ranguei.handlers.request

import com.ranguei.exceptions.InvalidArgumentException


class RequestHandler {
	
	private final InputStream inStream
	private final Closure function
	
	RequestHandler(InputStream inStream, Closure function){
		if(!function) throw new InvalidArgumentException("Invalid function to execute")
		this.inStream = inStream
		this.function = function
	}

	RequestSuccessHandler onSuccess(Closure onSuccess){
		if(!onSuccess) throw new InvalidArgumentException("Invalid success callback")
		new RequestSuccessHandler(onSuccess, function, inStream)
	}

	def result(){
		inStream ? function(inStream) : function()
	}
	
}