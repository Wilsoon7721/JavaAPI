package com.gmail.calorious.api.javacord;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.javacord.api.audio.AudioConnection;
import org.javacord.api.entity.Region;
import org.javacord.api.entity.auditlog.AuditLog;
import org.javacord.api.entity.auditlog.AuditLogActionType;
import org.javacord.api.entity.channel.ServerChannel;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.server.Ban;
import org.javacord.api.entity.server.ExplicitContentFilterLevel;
import org.javacord.api.entity.server.MultiFactorAuthenticationLevel;
import org.javacord.api.entity.server.NsfwLevel;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.server.ServerFeature;
import org.javacord.api.entity.server.ServerUpdater;
import org.javacord.api.entity.server.invite.RichInvite;
import org.javacord.api.entity.user.User;
import org.javacord.api.entity.webhook.Webhook;

import com.gmail.calorious.api.javacord.util.ServerBoost;
import com.gmail.calorious.util.Formatter;
import com.gmail.calorious.util.FutureCompleter;
import com.gmail.calorious.util.OptionalHandler;

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
	
	public ServerVoiceChannel getAfkVoiceChannel() {
		return OptionalHandler.handle(server.getAfkChannel());
	}
	
	// Returns AFK Timeout in seconds if no unit is specified. Otherwise, return according to TimeUnit specified.
	public int getAfkTimeout(TimeUnit unit) {
		if(unit == null || unit == TimeUnit.SECONDS) return server.getAfkTimeoutInSeconds();
		if(unit == TimeUnit.MINUTES) return server.getAfkTimeoutInSeconds() / 60;
		if(unit == TimeUnit.HOURS) return server.getAfkTimeoutInSeconds() / 3600;
		if(unit == TimeUnit.DAYS) return server.getAfkTimeoutInSeconds() / 86400;
		if(unit == TimeUnit.MILLISECONDS) return server.getAfkTimeoutInSeconds() / 1000;
		if(unit == TimeUnit.MICROSECONDS) return server.getAfkTimeoutInSeconds() / 1000000;
		if(unit == TimeUnit.NANOSECONDS) return server.getAfkTimeoutInSeconds() / 1000000000;
		return -1; // Won't be called.
	}
	
	public AudioConnection getAudioConnection() {
		return OptionalHandler.handle(server.getAudioConnection());
	}
	
	public AuditLog getAuditLog(int limitEvents) {
		if(limitEvents == -1 || limitEvents == 0 || limitEvents == (Long)null) {
			limitEvents = 50;
		}
		return FutureCompleter.handle(server.getAuditLog(limitEvents));
	}
	
	public AuditLog getAuditLog(int limitEvents, AuditLogActionType type) {
		if(limitEvents == -1 || limitEvents == 0 || limitEvents == (Long)null) {
			limitEvents = 50;
		}
		return FutureCompleter.handle(server.getAuditLog(limitEvents, type));
	}
	
	public Collection<Ban> getBans() {
		return FutureCompleter.handle(server.getBans());
	}
	
	public ServerBoost getBoostStats() {
		ServerBoost boostDetails = new ServerBoost(server.getBoostCount(), server.getBoostLevel());
		return boostDetails;
	}
	
	public ServerChannel getChannelById(String id) {
		return OptionalHandler.handle(server.getChannelById(id));
	}
	
	public ServerChannel getChannelById(long id) {
		return OptionalHandler.handle(server.getChannelById(id));
	}
	
	public Collection<ServerChannel> getChannels() {
		return server.getChannels();
	}
	
	public Collection<ServerChannel> getChannelsByName(String query, boolean ignoreCase) {
		if(ignoreCase) return server.getChannelsByNameIgnoreCase(query);
		return server.getChannelsByName(query);
	}
	
	public String getServerCreationTimestamp(DateTimeFormatter formatter) {
		return Formatter.formatTimestamp(server.getCreationTimestamp(), formatter);
	}
	
	public Role getEveryoneRole() {
		return server.getEveryoneRole();
	}
	
	public ExplicitContentFilterLevel getExplicitContentFilter() {
		return server.getExplicitContentFilterLevel();
	}
	
	public NsfwLevel getNSFWLevel() {
		return server.getNsfwLevel();	
	}
	
	public String getDescription() {
		return OptionalHandler.handle(server.getDescription());
	}
	
	public boolean isCommunityServer() {
		return server.getFeatures().contains(ServerFeature.COMMUNITY);
	}
	
	public boolean isPartnered() {
		return server.getFeatures().contains(ServerFeature.PARTNERED);
	}
	
	public boolean hasServerFeature(ServerFeature feature) {
		return server.getFeatures().contains(feature);
	}
	
	public boolean isDiscoverable() {
		return server.getFeatures().contains(ServerFeature.DISCOVERABLE);
	}
	
	public Collection<User> getMembersByUsername(String username, boolean ignoreCase) {
		if(ignoreCase) return server.getMembersByNameIgnoreCase(username);
		return server.getMembersByName(username);
	}
	
	public API_User getMemberById(long id) {
		return API_User.from(OptionalHandler.handle(server.getMemberById(id)));
	}
	
	public API_User getMemberById(String id) {
		return API_User.from(OptionalHandler.handle(server.getMemberById(id)));
	}
	
	public API_User getMemberByNameAndDiscriminator(String name, String discriminator, boolean ignoreCase) {
		if(ignoreCase) return API_User.from(OptionalHandler.handle(server.getMemberByNameAndDiscriminatorIgnoreCase(name, discriminator)));
		return API_User.from(OptionalHandler.handle(server.getMemberByNameAndDiscriminator(name, discriminator)));
	}
	
	public API_User getMemberByDiscriminatedName(String discriminatedName, boolean ignoreCase) {
		if(ignoreCase) return API_User.from(OptionalHandler.handle(server.getMemberByDiscriminatedNameIgnoreCase(discriminatedName)));
		return API_User.from(OptionalHandler.handle(server.getMemberByDiscriminatedName(discriminatedName)));
	}
	
	public Collection<RichInvite> getInvites() {
		return FutureCompleter.handle(server.getInvites());
	}
	
	public String getJoinServerTimestamp(DateTimeFormatter formatter) {
		return Formatter.formatTimestamp(server.getCreationTimestamp(), formatter);
	}
	
	public MultiFactorAuthenticationLevel getAuthenticationLevel() {
		return server.getMultiFactorAuthenticationLevel();
	}
	
	public int getMemberCount() {
		return server.getMemberCount();
	}
	
	public API_User getOwner() {
		return API_User.from(OptionalHandler.handle(server.getOwner()));
	}
	
	public String getServerName() {
		return server.getName();
	}
	
	public Role getRoleById(long id) {
		return OptionalHandler.handle(server.getRoleById(id));
	}
	
	public Role getRoleById(String id) {
		return OptionalHandler.handle(server.getRoleById(id));
	}
	
	public Region getServerRegion() {
		return server.getRegion();
	}
	
	public Collection<Role> getRoles() {
		return server.getRoles();
	}
	
	public Collection<Role> getRolesByName(String name, boolean ignoreCase) {
		if(ignoreCase) return server.getRolesByNameIgnoreCase(name);
		return server.getRolesByName(name);
	}
	
	public Collection<Webhook> getWebhooks() {
		return FutureCompleter.handle(server.getWebhooks());
	}
	
	public ServerUpdater callUpdater() {
		return new ServerUpdater(server);
	}
}
