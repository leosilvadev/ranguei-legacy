package com.ranguei.domains


class Domain {

	def asMap() {		
		this.class.declaredFields.findAll { !it.synthetic }.collectEntries {
			[ (it.name):this."$it.name" ]
		}
	}
	
}
