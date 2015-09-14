package com.ranguei.domains

class Establishment implements Domain {
	
	static def collection = [
		name: 'establishments'
	]
	
	final String id
	final String username
	final String password
	
	Establishment(String username, String password) {
		this.username = username
		this.password = password
	}
	
	Establishment(String id, String username, String password) {
		this.id = id
		this.username = username
		this.password = password
	}
	
	Establishment(Map map) {
		this.username = map.username
		this.password = map.password
	}
	
	Establishment(Map map, String id) {
		this.id = id
		this.username = map.username
		this.password = map.password
	}

	Map asMap() {
		[id: id, username: username, password: password]
	}

	static Establishment fromDocument(doc) {
		new Establishment(doc._id.toString(), doc.username, doc.password)
	}

}
