package com.callippus.water.erp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(locations = "/config/git.properties", prefix = "git", ignoreUnknownFields = false)
public class VersionProperties {

	String dirty;
	String branch;
	Build build;
	Remote remote;
	Closest closest;
	Commit commit;
	
	class Commit {
		ID id;
		User user;
		String time;
		Message message;

		class Message {
			String full;			
			String shortMessage;
		}
		
		class ID {
			String describe;
		}
	}
	
	class Closest {
		Tag tag;
		
		class Tag {
			Commit commit;			
			String name;
			
			class Commit {
				
			}
		}
	}
	
	class Remote {
		Origin origin;
		
		class Origin {
			String url;
		}
	}
	
	
	class Build {
		User user;
		String host;
		String version;
	}
	
	class User {
		String email;
		String name;
	}
	
}
