package com.gmail.calorious.api.javacord.objects;

import java.awt.Color;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.javacord.api.entity.permission.Permissions;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.permission.RoleTags;
import org.javacord.api.entity.user.User;

import com.gmail.calorious.util.OptionalHandler;

public class API_Role implements API_DiscordEntity {
	private Role role;
	protected API_Role(Role role) {
		this.role = role;
	}
	
	public long getId() {
		return role.getId();
	}
	
	public String getName() {
		return role.getName();
	}
	
	public API_Server getServer() {
		return new API_Server(role.getServer());
	}
	
	public RoleTags getRoleTags() {
		return OptionalHandler.handle(role.getRoleTags());
	}
	
	public int getRawPosition() {
		return role.getRawPosition();
	}
	
	public Optional<Color> getColor() {
		return role.getColor();
	}
	
	public boolean isMentionable() {
		return role.isMentionable();
	}
	
	public boolean isDisplayedSeparately() {
		return role.isDisplayedSeparately();
	}
	
	public Collection<User> getUsers() {
		return role.getUsers();
	}
	
	public boolean hasUser(User user) {
		return role.hasUser(user);
	}
	
	public Permissions getPermissions() {
		return role.getPermissions();
	}
	
	public boolean isManaged() {
		return role.isManaged();
	}
	
	public CompletableFuture<Void> delete() {
		return role.delete();
	}

	
	public String getIdAsString() {
		return role.getIdAsString();
	}
	
	
}
