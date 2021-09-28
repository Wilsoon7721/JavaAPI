package com.gmail.calorious.api.javacord;

import org.javacord.api.entity.permission.PermissionState;
import org.javacord.api.entity.permission.PermissionType;
import org.javacord.api.entity.permission.Permissions;
import org.javacord.api.entity.permission.internal.PermissionsBuilderDelegate;
import org.javacord.api.util.internal.DelegateFactory;

public class API_PermissionsBuilder {
	private PermissionsBuilderDelegate updater;
	
	protected API_PermissionsBuilder() {
		this.updater = DelegateFactory.createPermissionsBuilderDelegate();
	}	
	
	protected API_PermissionsBuilder(Permissions permissions) {
		this.updater = DelegateFactory.createPermissionsBuilderDelegate(permissions);	
	}
	
	public PermissionState getPermissionState(PermissionType type) {
		return updater.getState(type);
	}
	
	public API_PermissionsBuilder setPermission(PermissionType type, PermissionState state) {
		updater.setState(type, state);
		return this;
	}
	
	public Permissions create() {
		return updater.build();
	}
}
