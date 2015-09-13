package com.ranguei.domains

class Establishment implements Domain {
	
	static def collection = [
		name: 'establishments'
	]
	
	final String username
	final String password
	
	Establishment(String username, String password) {
		this.username = username
		this.password = password
	}

	Establishment(Map map) {
		this.username = map.username
		this.password = map.password
	}

}
