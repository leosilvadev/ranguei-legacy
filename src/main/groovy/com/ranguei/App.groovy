package com.ranguei

import io.github.javaconductor.gserv.GServ

import com.ranguei.routes.Router

class App {
	
	static main(args){
		def gserv = new GServ()
		
		def resources = Router.configure(gserv)
		
		gserv.http {
			resources.each {
				resource it
			}
		}.start(8080)
	}

}
