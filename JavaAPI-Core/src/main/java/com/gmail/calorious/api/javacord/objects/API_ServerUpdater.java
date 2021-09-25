package com.gmail.calorious.api.javacord.objects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.javacord.api.entity.Icon;
import org.javacord.api.entity.Region;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.server.DefaultMessageNotificationLevel;
import org.javacord.api.entity.server.ExplicitContentFilterLevel;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.server.VerificationLevel;
import org.javacord.api.entity.server.internal.ServerUpdaterDelegate;
import org.javacord.api.entity.user.User;
import org.javacord.api.util.internal.DelegateFactory;

public class API_ServerUpdater {
	private ServerUpdaterDelegate updater;
	protected API_ServerUpdater(Server server) {
		if(server == null) throw new NullPointerException("Server cannot be null!");
		this.updater = DelegateFactory.createServerUpdaterDelegate(server);
	}
	
	public static API_ServerUpdater with(Server server) {
		return new API_ServerUpdater(server);
	}
	
	public static API_ServerUpdater newInstance(Server server) {
		return new API_ServerUpdater(server);
	}
	
	public API_ServerUpdater addRolesToUser(User user, Collection<Role> roles) {
		updater.addRolesToUser(user, roles);
		return this;
	}
	
	public API_ServerUpdater addRoleToUser(User user, Role role) {
		updater.addRoleToUser(user, role);
		return this;
	}
	
	public API_ServerUpdater removeAfkChannel() {
		updater.removeAfkChannel();
		return this;
	}
	
	public API_ServerUpdater removeAllRolesFromUser(User user) {
		updater.removeAllRolesFromUser(user);
		return this;
	}
	
	public API_ServerUpdater removeBanner() {
		updater.removeBanner();
		return this;
	}
	
	public API_ServerUpdater removeIcon() {
		updater.removeIcon();
		return this;
	}
	
	public API_ServerUpdater removeModeratorsOnlyChannel() {
		updater.removeModeratorsOnlyChannel();
		return this;
	}
	
	public API_ServerUpdater removeRoleFromUser(User user, Role role) {
		updater.removeRoleFromUser(user, role);
		return this;
	}
	
	public API_ServerUpdater removeRolesFromUser(User user, Collection<Role> role) {
		updater.removeRolesFromUser(user, role);
		return this;
	}
	
	public API_ServerUpdater removeRulesChannel() {
		updater.removeRulesChannel();
		return this;
	}
	
	public API_ServerUpdater removeSplash() {
		updater.removeSplash();
		return this;
	}
	
	public API_ServerUpdater removeSystemChannel() {
		updater.removeSystemChannel();
		return this;
	}
	
	public API_ServerUpdater reorderRoles(List<Role> roles) {
		updater.reorderRoles(roles);
		return this;
	}
	
	public API_ServerUpdater setAfkChannel(ServerVoiceChannel channel) {
		updater.setAfkChannel(channel);
		return this;
	}
	
	public API_ServerUpdater setAfkTimeout(int afkTimeout, TimeUnit unit) {
		if(unit == TimeUnit.NANOSECONDS) {
			int seconds = afkTimeout / 1000000000;
			updater.setAfkTimeoutInSeconds(seconds);
			return this;
		}
		if(unit == TimeUnit.MICROSECONDS) {
			int seconds = afkTimeout / 1000000;
			updater.setAfkTimeoutInSeconds(seconds);
			return this;
		}
		if(unit == TimeUnit.MILLISECONDS) {
			int seconds = afkTimeout / 1000;
			updater.setAfkTimeoutInSeconds(seconds);
			return this;
		}
		if(unit == TimeUnit.SECONDS) {
			updater.setAfkTimeoutInSeconds(afkTimeout);
			return this;
		}
		if(unit == TimeUnit.MINUTES) {
			int seconds = afkTimeout * 60;
			updater.setAfkTimeoutInSeconds(seconds);
			return this;
		}
		if(unit == TimeUnit.HOURS) {
			int seconds = afkTimeout * 3600;
			updater.setAfkTimeoutInSeconds(seconds);
			return this;
		}
		if(unit == TimeUnit.DAYS) {
			int seconds = afkTimeout * 86400;
			updater.setAfkTimeoutInSeconds(seconds);
			return this;
		}
		return this;
	}
	
	public API_ServerUpdater setAuditLogReason(String reason) {	
		updater.setAuditLogReason(reason);
		return this;
	}
	
	public API_ServerUpdater setBanner(byte[] banner) {
		updater.setBanner(banner);
		return this;
	}
	
	public API_ServerUpdater setBanner(byte[] banner, String fileType) {
		updater.setBanner(banner, fileType);
		return this;
	}
	
	public API_ServerUpdater setBanner(BufferedImage banner) {
		updater.setBanner(banner);
		return this;
	}
	
	public API_ServerUpdater setBanner(BufferedImage banner, String fileType) {
		updater.setBanner(banner, fileType);
		return this;
	}
	
	public API_ServerUpdater setBanner(File banner) {
		updater.setBanner(banner);
		return this;
	}
	
	public API_ServerUpdater setBanner(InputStream banner) {
		updater.setBanner(banner);
		return this;
	}
	
	public API_ServerUpdater setBanner(InputStream banner, String fileType) {
		updater.setBanner(banner, fileType);
		return this;
	}
	
	public API_ServerUpdater setBanner(URL banner) {
		updater.setBanner(banner);
		return this;
	}
	
	public API_ServerUpdater setBanner(Icon banner) {
		updater.setBanner(banner);
		return this;
	}
	
	public API_ServerUpdater setDeafened(User user, boolean deafened) {
		updater.setDeafened(user, deafened);
		return this;
	}
	
	public API_ServerUpdater setDefaultMessageNotificationLevel(DefaultMessageNotificationLevel level) {
		updater.setDefaultMessageNotificationLevel(level);
		return this;
	}
	
	public API_ServerUpdater setExplicitContentFilter(ExplicitContentFilterLevel level) {
		updater.setExplicitContentFilterLevel(level);
		return this;
	}
	
	public API_ServerUpdater setIcon(byte[] icon) {
		updater.setIcon(icon);
		return this;
	}
	
	public API_ServerUpdater setIcon(byte[] icon, String fileType) {
		updater.setIcon(icon, fileType);
		return this;
	}
	
	public API_ServerUpdater setIcon(BufferedImage icon) {
		updater.setIcon(icon);
		return this;
	}
	
	public API_ServerUpdater setIcon(BufferedImage icon, String fileType) {
		updater.setIcon(icon, fileType);
		return this;
	}
	
	public API_ServerUpdater setIcon(File icon) {
		updater.setIcon(icon);
		return this;
	}
	
	public API_ServerUpdater setIcon(InputStream icon) {
		updater.setIcon(icon);
		return this;
	}
	
	public API_ServerUpdater setIcon(InputStream icon, String fileType) {
		updater.setIcon(icon, fileType);
		return this;
	}
	
	public API_ServerUpdater setIcon(URL icon) {
		updater.setIcon(icon);
		return this;
	}
	
	public API_ServerUpdater setIcon(Icon icon) {
		updater.setIcon(icon);
		return this;
	}	
	
	public API_ServerUpdater setModeratorsOnlyChannel(ServerTextChannel channel) {
		updater.setModeratorsOnlyChannel(channel);
		return this;
	}
	
	public API_ServerUpdater setMuted(User user, boolean muted) {
		updater.setMuted(user, muted);
		return this;
	}
	
	public API_ServerUpdater setName(String name) {
		updater.setName(name);
		return this;
	}
	
	public API_ServerUpdater setUserNickname(User user, String nickname) {
		updater.setNickname(user, nickname);
		return this;
	}
	
	public API_ServerUpdater setOwner(User owner) {
		updater.setOwner(owner);
		return this;
	}
	
	public API_ServerUpdater setLocale(Locale locale) {
		updater.setPreferredLocale(locale);
		return this;
	}
	
	public API_ServerUpdater setRegion(Region region) {
		updater.setRegion(region);
		return this;
	}
	
	public API_ServerUpdater setRulesChannel(ServerTextChannel channel) {
		updater.setRulesChannel(channel);
		return this;
	}
	
	public API_ServerUpdater setSplash(byte[] splash) {
		updater.setSplash(splash);
		return this;
	}
	
	public API_ServerUpdater setSplash(byte[] splash, String fileType) {
		updater.setSplash(splash, fileType);
		return this;
	}
	
	public API_ServerUpdater setSplash(BufferedImage splash) {
		updater.setSplash(splash);
		return this;
	}
	
	public API_ServerUpdater setSplash(BufferedImage splash, String fileType) {
		updater.setSplash(splash, fileType);
		return this;
	}
	
	public API_ServerUpdater setSplash(File splash) {
		updater.setSplash(splash);
		return this;
	}
	
	public API_ServerUpdater setSplash(InputStream splash) {
		updater.setSplash(splash);
		return this;
	}
	
	public API_ServerUpdater setSplash(InputStream splash, String fileType) {
		updater.setSplash(splash, fileType);
		return this;
	}

	public API_ServerUpdater setSplash(URL splash) {
		updater.setSplash(splash);
		return this;
	}
	
	public API_ServerUpdater setSplash(Icon splash) {
		updater.setSplash(splash);
		return this;
	}
	
	public API_ServerUpdater setSystemChannel(ServerTextChannel channel) {
		updater.setSystemChannel(channel);
		return this;
	}
	
	public API_ServerUpdater setVerificationLevel(VerificationLevel level) {
		updater.setVerificationLevel(level);
		return this;
	}
	
	public API_ServerUpdater moveUser(User user, ServerVoiceChannel channel) {
		updater.setVoiceChannel(user, channel);
		return this;
	}
	
	public CompletableFuture<Void> update() {
		return updater.update();
	}
}
