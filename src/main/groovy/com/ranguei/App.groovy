package com.ranguei

import io.github.javaconductor.gserv.GServ

import com.ranguei.routes.Router

class App {
	
	static main(args){
		def gserv = new GServ()
		
		def resources = Router.configure(gserv)
		
		gserv.http {
			static_root  'app'
			resources.each {
				resource it
			}
		}.start(port())
	}
	
	static def port(){
		System.getenv().PORT ?: 8080
	}

}
