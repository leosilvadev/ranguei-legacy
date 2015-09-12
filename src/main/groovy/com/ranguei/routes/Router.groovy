package com.ranguei.routes

import io.github.javaconductor.gserv.GServ

import com.ranguei.controllers.HelloController

class Router {

	static def configure(gserv){
		def resources = []
		resources << gserv.resource("/tests", HelloController.actions)
		resources << gserv.resource("/tests2", HelloController.actions)
		resources
	}
	
}
