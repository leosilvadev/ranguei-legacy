package com.ranguei.handlers

import spock.lang.Specification

class RequestFunctionSpec extends Specification {

	def "Should execute a success callback when everything is ok"(){
		Closure onSuccess = Mock(Closure)
		
		given: "a function to be executed"
			Closure function = { true }
			
		and: "a request function without inputstream"
			def requestFunction = new RequestFunction( function )
			
		when: "the request function register a onSuccess function"
			requestFunction.onSuccess onSuccess
			
		and: "the request function executes"
			requestFunction.result()
			
		then: "the success callback must be executed"
			1 * onSuccess(true)
	}
	
}
