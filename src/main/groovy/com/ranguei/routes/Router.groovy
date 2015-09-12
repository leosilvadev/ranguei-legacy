package com.ranguei.routes

import com.ranguei.controllers.EstablishmentController


class Router {

	static def configure(gserv){
		def resources = []
		resources << gserv.resource("/establishments", EstablishmentController.actions)
		resources
	}
	
}
