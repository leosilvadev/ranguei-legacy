package com.ranguei.controllers

class HelloController {
	
	static def actions = {
		get "/", {  ->
		  writeJSON(['message':'Hello!'])
		}
	}

}
