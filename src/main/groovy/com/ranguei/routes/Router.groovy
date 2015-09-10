package com.ranguei.routes

import io.github.javaconductor.gserv.GServ

import com.ranguei.controllers.HelloController

class Router {

	def configure(){
		def gserv = new GServ()
		
		def resourceTest = gserv.resource("/tests", HelloController.actions)
		def resourceTest2 = gserv.resource("/tests2", HelloController.actions)
		
		
		
		gserv.http {
			resource resourceTest
			resource resourceTest2
		}.start(8080);
	}
	
}
