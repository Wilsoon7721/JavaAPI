package com.gmail.calorious.api.javacord;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.javacord.api.entity.DiscordClient;
import org.javacord.api.entity.Icon;
import org.javacord.api.entity.activity.Activity;
import org.javacord.api.entity.channel.PrivateChannel;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.entity.user.UserFlag;
import org.javacord.api.entity.user.UserStatus;

import com.gmail.calorious.api.InternalFactory;
import com.gmail.calorious.api.javacord.util.UserDetail;
import com.gmail.calorious.util.ExceptionLogger;
import com.gmail.calorious.util.Formatter;

public class API_User {
	private String discriminator = "";
	private String username = "";
	private String combinedUser = "";
	private User user = null;
	private Icon avatar = null;
	private long id = -1L;
	private Set<Activity> activities = null;
	protected API_User(User user) {
		if(user == null) return;
		this.user = user;
		updateAllDetails();
	}
	
	public static API_User from(User user) {
		return new API_User(user);
	}
	
	public static API_User newInstance(User user) {
		return new API_User(user);
	}
	
	public User parent() {
		return user;
	}
	
	public User getUser() {
		return user;
	}
	
	public List<Role> getServerRole(Server server) {
		return user.getRoles(server);
	}
	
	public UserStatus getUserStatus() {
		return user.getStatus();
	}
	
	public boolean hasNickname(Server server) {
		return user.getNickname(server).isPresent();
	}
	
	public String getNickname(Server server) {
		if(hasNickname(server)) 
			return user.getNickname(server).get();
		return user.getName();
	}
	
	public String getMentionTag() {
		return user.getMentionTag();
	}
	
	public API_User updateInstance() {
		this.user = user.getLatestInstance().join();
		API_User newUser = new API_User(user);
		newUser.updateAllDetails();
		return newUser;
	}
	
	public ServerVoiceChannel getConnectedVoiceChannel(Server server) {
		if(!user.getConnectedVoiceChannel(server).isPresent()) return null;
		return user.getConnectedVoiceChannel(server).get();
	}
	
	public Collection<API_Server> getMutualServers() {
		return user.getMutualServers().stream().map(server -> API_Server.from(server)).collect(Collectors.toCollection(LinkedHashSet::new));
	}
	
	public void resetNickname(Server server, String reason) {
		user.resetNickname(server, reason).exceptionally(new ExceptionLogger<Void>());
		return;
	}
	
	public void resetNickname(Server server) {
		user.resetNickname(server).exceptionally(new ExceptionLogger<Void>());
		return;
	}
	
	public PrivateChannel openDirectMessage() {
		if(!(user.getPrivateChannel().isPresent()))
			try {
				return user.openPrivateChannel().exceptionally(new ExceptionLogger<PrivateChannel>()).get();
			} catch (InterruptedException | ExecutionException e) {}
		return user.getPrivateChannel().get();
	}
	
	public void addRoleToUser(Server server, Role role, String reason) {
		if(reason.contentEquals("") || reason.isEmpty() || reason == null) {
			server.addRoleToUser(user, role).exceptionally(new ExceptionLogger<Void>());
			return;
		}
		server.addRoleToUser(user, role, reason).exceptionally(new ExceptionLogger<Void>());
		return;
	}
	
	public EnumSet<UserFlag> getBadges() {
		return user.getUserFlags();
	}
	
	public boolean isServerMuted(Server server) {
		return user.isMuted(server);
	}
	
	public boolean isSelfMuted(Server server) {
		return user.isSelfMuted(server);
	}
	
	public boolean isServerDeafened(Server server) {
		return user.isDeafened(server);
	}
	
	public boolean isSelfDeafened(Server server) {
		return user.isSelfDeafened(server);
	}
	
	public String getCreationTimestamp(DateTimeFormatter formatter, ZoneId zoneId) {
		LocalDateTime dateTime = LocalDateTime.ofInstant(user.getCreationTimestamp(), zoneId == null ? ZoneId.of("Asia/Singapore") : zoneId);
		DateTimeFormatter format = (formatter == null ? DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss") : formatter);
		return Formatter.formatTimestamp(dateTime, format);
	}
	
	public boolean hasDefaultAvatar() {
		return user.hasDefaultAvatar();
	}
	
	public List<DiscordClient> getCurrentActiveClients() {
		return user.getCurrentClients().stream().collect(Collectors.toList());
	}
	
	public String getServerJoinTimestamp(Server server, DateTimeFormatter formatter, ZoneId zoneId) {
		if(!(user.getJoinedAtTimestamp(server).isPresent())) return "User did not join this server.";
		LocalDateTime dateTime = LocalDateTime.ofInstant(user.getJoinedAtTimestamp(server).get(), zoneId == null ? ZoneId.of("Asia/Singapore") : zoneId);
		DateTimeFormatter format = (formatter == null ? DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss") : formatter);
		return Formatter.formatTimestamp(dateTime, format);
	}
	
	public void updateDetails() {
		HashMap<UserDetail, String> map = InternalFactory.Javacord.updateUser(user);
		this.discriminator = map.get(UserDetail.DISCRIMINATOR);
		this.username = map.get(UserDetail.USERNAME);
		this.combinedUser = map.get(UserDetail.COMBINED_NAME);
		// Complete
		return;
	}
	
	public void updateAllDetails() {		
		HashMap<UserDetail, String> map = InternalFactory.Javacord.updateUser(user);
		this.discriminator = map.get(UserDetail.DISCRIMINATOR);
		this.username = map.get(UserDetail.USERNAME);
		this.combinedUser = map.get(UserDetail.COMBINED_NAME);
		this.id = user.getId();
		this.avatar = user.getAvatar();
		this.activities = user.getActivities();
	}
	
	public Set<Activity> getCurrentActivities() {
		if(activities == null) return user.getActivities();
		return activities;
	}
	
	public Icon getAvatar() {
		if(avatar == null) return user.getAvatar();
		return avatar;
	}
	
	public long getUserID() {
		if(id == -1L) return user.getId();
		return id;
	}
	
	public String getCombinedUsername() {
		return combinedUser;
	}
	
	public String getDiscriminator() {
		return discriminator;
	}
	
	public String getUsername() {
		return username;
	}
}
