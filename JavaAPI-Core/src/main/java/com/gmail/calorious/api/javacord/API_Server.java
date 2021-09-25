package com.gmail.calorious.api.javacord;

import java.util.Collection;

import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;

public class API_Server {
	private Server server = null;
	
	protected API_Server(Server server) {
		if(server == null) return;
		this.server = server;
	}
	
	public static API_Server from(Server server) {
		return new API_Server(server);
	}
	
	public static API_Server newInstance(Server server) {
		return new API_Server(server);
	}
	
	public Server parent() {
		return server;
	}
	
	public Server getServer() {
		return server;
	}
	
	public Collection<User> getMembers() {
		return server.getMembers();
	}
}
