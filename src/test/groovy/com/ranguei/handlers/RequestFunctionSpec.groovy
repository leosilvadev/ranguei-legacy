package com.ranguei.handlers

import spock.lang.Specification

import com.ranguei.exceptions.InvalidArgumentException
import com.ranguei.handlers.request.RequestData;
import com.ranguei.handlers.request.RequestHandler
import com.ranguei.handlers.request.RequestSuccessHandler

class RequestFunctionSpec extends Specification {

	def "Should create a success callback function when pass an success callback"(){
		Closure mockSuccess = Mock(Closure)
		RequestData mockData = Mock(RequestData)
		
		given: "a function to be executed"
			Closure function = { true }
			
		and: "a request handler function"
			def requestFunction = new RequestHandler( mockData, function )
			
		when: "the request function register a success function"
			def successFn = requestFunction.onSuccess mockSuccess
			
		then: "a success callback must be executed"
			successFn instanceof RequestSuccessHandler
	}
	
	def "Should throw an exception with a null success callback"(){
		RequestData mockData = Mock(RequestData)
		
		given: "a function to be executed"
			Closure function = { true }
			
		and: "a request handler function"
			def requestFunction = new RequestHandler( mockData, function )
			
		when: "the request function try to register a null success function"
			requestFunction.onSuccess null
			
		then: "an InvalidArgumentException must be thrown"
			thrown(InvalidArgumentException)
	}
	
	def "Should throw an exception with a null function to execute"(){
		RequestData mockData = Mock(RequestData)
		
		given: "a null function"
			def function = null
			
		when: "the request handler function is created with this null function"
			def requestFunction = new RequestHandler( mockData, function )
			
		then: "an InvalidArgumentException must be thrown"
			thrown(InvalidArgumentException)
	}
	
}
