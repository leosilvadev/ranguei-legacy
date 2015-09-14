package com.ranguei.handlers.request

class RequestData {

	final InputStream body
	final Map params

	RequestData(InputStream inputStream, Map params) {
		this.body = inputStream
		this.params = params
	}
	
	RequestData(InputStream inputStream) {
		this.body = inputStream
	}
	
	RequestData(Map params) {
		this.params = params
	}
	
}
