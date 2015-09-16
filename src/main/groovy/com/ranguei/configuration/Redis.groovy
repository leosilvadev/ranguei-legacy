package com.ranguei.configuration

import redis.clients.jedis.JedisPool

@Singleton(strict=false)
class Redis {
	
	private final JedisPool jedisPool
	
	Redis(){
		this.jedisPool = configure()
	}
	
	def execute(function, errorCallback=null){
		try {
			def jedis = jedisPool.resource
			function jedis
			
		} catch (e) {
			if ( errorCallback ) errorCallback e
		}
	}
	
	private def configure(){
		String envURI = System.getenv("REDIS_URL")
		envURI ? new JedisPool(new URI(envURI)) : new JedisPool()
	}

}