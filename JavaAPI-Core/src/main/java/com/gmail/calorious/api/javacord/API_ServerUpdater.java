package com.gmail.calorious.api.javacord;

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

import com.gmail.calorious.util.Formatter;

public class API_ServerUpdater {
	private Server server;
	private ServerUpdaterDelegate updater;
	protected API_ServerUpdater(Server server) {
		this.server = server;
		this.updater = DelegateFactory.createServerUpdaterDelegate(server);
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
	
	public API_ServerUpdater removeRolesFromUser(User user, Collection<Role> roles) {
		updater.removeRolesFromUser(user, roles);
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
	
	public API_ServerUpdater setAfkTimeout(int time, TimeUnit unit) {
		updater.setAfkTimeoutInSeconds(Formatter.formatToSeconds(time, unit));
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
	
	public API_ServerUpdater setDeafenedState(User user, boolean deafened) {
		updater.setDeafened(user, deafened);
		return this;
	}
	
	public API_ServerUpdater setDefaultMessageNotificationLevel(DefaultMessageNotificationLevel level) {
		updater.setDefaultMessageNotificationLevel(level);
		return this;
	}
	
	public API_ServerUpdater setExplicitContentFilterLevel(ExplicitContentFilterLevel level) {
		updater.setExplicitContentFilterLevel(level);
		return this;
	}
	
	public API_ServerUpdater setModeratorsOnlyChannel(ServerTextChannel channel) {
		updater.setModeratorsOnlyChannel(channel);
		return this;
	}
	
	public API_ServerUpdater setMutedState(User user, boolean muted) {
		updater.setMuted(user, muted);
		return this;
	}
	
	public API_ServerUpdater setName(String name) {
		updater.setName(name);
		return this;
	}
	
	public API_ServerUpdater setNickname(User user, String nickname) {
		updater.setNickname(user, nickname);
		return this;
	}
	
	public API_ServerUpdater setOwner(User owner) {
		if(server.getOwnerId() != server.getApi().getYourself().getId()) throw new IllegalStateException("Cannot transfer owner status as you are not the owner of the server '" + server.getName() + "'");
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
	
	public API_ServerUpdater setSystemChannel(ServerTextChannel channel) {
		updater.setSystemChannel(channel);
		return this;
	}
	
	public API_ServerUpdater setVerificationLevel(VerificationLevel level) {
		updater.setVerificationLevel(level);
		return this;
	}
	
	public API_ServerUpdater setUserVoiceChannel(User user, ServerVoiceChannel channel) {
		updater.setVoiceChannel(user, channel);
		return this;
	}
	
	public CompletableFuture<Void> update() {
		return updater.update();
	}
	
}
