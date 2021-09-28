package com.gmail.calorious.api.javacord;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.javacord.api.audio.AudioConnection;
import org.javacord.api.entity.Icon;
import org.javacord.api.entity.Region;
import org.javacord.api.entity.VanityUrlCode;
import org.javacord.api.entity.auditlog.AuditLog;
import org.javacord.api.entity.auditlog.AuditLogActionType;
import org.javacord.api.entity.auditlog.AuditLogEntry;
import org.javacord.api.entity.channel.ChannelCategory;
import org.javacord.api.entity.channel.ServerChannel;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.emoji.KnownCustomEmoji;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.server.Ban;
import org.javacord.api.entity.server.DefaultMessageNotificationLevel;
import org.javacord.api.entity.server.ExplicitContentFilterLevel;
import org.javacord.api.entity.server.MultiFactorAuthenticationLevel;
import org.javacord.api.entity.server.NsfwLevel;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.server.ServerFeature;
import org.javacord.api.entity.server.VerificationLevel;
import org.javacord.api.entity.server.invite.RichInvite;
import org.javacord.api.entity.user.User;
import org.javacord.api.entity.webhook.IncomingWebhook;
import org.javacord.api.entity.webhook.Webhook;
import org.javacord.api.interaction.SlashCommand;

import com.gmail.calorious.api.javacord.util.BoostStats;
import com.gmail.calorious.util.Formatter;
import com.gmail.calorious.util.FutureCompleter;
import com.gmail.calorious.util.OptionalHandler;

public class API_Server implements API_DiscordEntity {
	private Server server;
	protected API_Server(Server server) {	
		this.server = server;
	}
	
	public API_ChannelCategoryBuilder createChannelCategoryBuilder() {
		return new API_ChannelCategoryBuilder(server);
	}
	
	public API_PermissionsBuilder createPermissionsBuilder() {
		return new API_PermissionsBuilder();
	}
	
	@Override
	public long getId() {
		return server.getId();
	}

	@Override
	public String getName() {
		return server.getName();
	}

	
	public AudioConnection getAudioConnection() {
		return OptionalHandler.handle(server.getAudioConnection());
	}

	
	public boolean hasBoostMessagesEnabled() {
		return server.hasBoostMessagesEnabled();
	}

	
	public boolean hasJoinMessagesEnabled() {		
		return server.hasJoinMessagesEnabled();
	}

	
	public Collection<ServerFeature> getFeatures() {
		return server.getFeatures();
	}

	
	public BoostStats getBoostStats() {
		return new BoostStats(server.getBoostCount(), server.getBoostLevel());
	}
	
	public ServerTextChannel getRulesChannel() {
		return OptionalHandler.handle(server.getRulesChannel());
	}

	
	public String getDescription() {
		return OptionalHandler.handle(server.getDescription());
	}

	
	public NsfwLevel getNsfwLevel() {
		return server.getNsfwLevel();
	}

	
	public ServerTextChannel getModeratorsOnlyChannel() {
		return OptionalHandler.handle(server.getModeratorsOnlyChannel());
	}

	
	public VanityUrlCode getVanityUrlCode() {
		return OptionalHandler.handle(server.getVanityUrlCode());
	}

	
	public Icon getDiscoverySplash() {
		return OptionalHandler.handle(server.getDiscoverySplash());
	}

	
	public Locale getLocale() {
		return server.getPreferredLocale();
	}

	
	public Region getRegion() {
		return server.getRegion();
	}

	
	public String getNickname(User user) {
		return OptionalHandler.handle(server.getNickname(user));
	}

	
	public boolean isPending(long userId) {
		if(!(server.getFeatures().contains(ServerFeature.MEMBER_VERIFICATION_GATE_ENABLED))) return false;
		return server.isPending(userId);
	}

	
	public boolean isSelfMuted(long userId) {
		return server.isSelfMuted(userId);
	}

	
	public boolean isSelfDeafened(long userId) {
		return server.isSelfDeafened(userId);
	}

	
	public boolean isMuted(long userId) {
		return server.isMuted(userId);
	}

	
	public boolean isDeafened(long userId) {
		return server.isDeafened(userId);
	}

	
	public String getUserJoinTimestamp(User user, DateTimeFormatter formatter) {
		return Formatter.formatTimestamp(OptionalHandler.handle(server.getJoinedAtTimestamp(user)), formatter);
	}
	
	public int getMemberCount() {
		return server.getMemberCount();
	}
	
	public VerificationLevel getVerificationLevel() {
		return server.getVerificationLevel();
	}

	
	public ExplicitContentFilterLevel getExplicitContentFilterLevel() {
		return server.getExplicitContentFilterLevel();
	}

	
	public DefaultMessageNotificationLevel getDefaultMessageNotificationLevel() {
		return server.getDefaultMessageNotificationLevel();
	}

	
	public MultiFactorAuthenticationLevel getMultiFactorAuthenticationLevel() {
		return server.getMultiFactorAuthenticationLevel();
	}

	
	public Icon getIcon() {
		return OptionalHandler.handle(server.getIcon());
	}

	
	public Icon getSplash() {
		return OptionalHandler.handle(server.getSplash());
	}

	
	public ServerTextChannel getSystemChannel() {
		return OptionalHandler.handle(server.getSystemChannel());
	}

	
	public ServerVoiceChannel getAfkChannel() {
		return OptionalHandler.handle(server.getAfkChannel());
	}

	
	public int getAfkTimeout(TimeUnit unit) {
		return Formatter.formatSeconds(server.getAfkTimeoutInSeconds(), unit);
	}

	
	public Integer getPruneCount(int days) {
		return FutureCompleter.handle(server.getPruneCount(days));
	}

	
	public Integer pruneMembers(int days, String reason) {		
		return FutureCompleter.handle(server.pruneMembers(days, reason));
	}

	
	public Collection<RichInvite> getInvites() {
		return FutureCompleter.handle(server.getInvites());
	}

	
	public boolean hasAllMembersInCache() {
		return server.hasAllMembersInCache();
	}

	
	public Collection<User> getMembers() {
		return server.getMembers();
	}

	
	public User getMemberById(long id) {
		return OptionalHandler.handle(server.getMemberById(id));
	}

	
	public boolean isMember(User user) {
		return server.isMember(user);
	}
	
	public API_RoleBuilder createRoleBuilder() {
		return new API_RoleBuilder(server);
	}

	
	public List<Role> getRoles() {
		return server.getRoles();
	}

	
	public List<Role> getRoles(User user) {
		return server.getRoles(user);
	}

	
	public Role getRoleById(long id) {
		return OptionalHandler.handle(server.getRoleById(id));
	}

	
	public void delete() {
		if(server.getOwnerId() != server.getApi().getYourself().getId()) return;
		FutureCompleter.handle(server.delete());
	}

	
	public void leave() {
		FutureCompleter.handle(server.leave());
	}

	
	public void addRoleToUser(User user, Role role, String reason) {
		FutureCompleter.handle(server.addRoleToUser(user, role, reason));
	}

	
	public void removeRoleFromUser(User user, Role role, String reason) {
		FutureCompleter.handle(server.removeRoleFromUser(user, role, reason));
	}

	
	public void reorderRoles(List<Role> roles, String reason) {
		FutureCompleter.handle(server.reorderRoles(roles, reason));
	}

	
	public void selfMute() {
		server.selfMute();
	}

	
	public void selfUnmute() {
		server.selfUnmute();
	}

	
	public void selfDeafen() {
		server.selfDeafen();
	}

	
	public void selfUndeafen() {
		server.selfUndeafen();
	}

	
	public void kickUser(User user, String reason) {	
		FutureCompleter.handle(server.kickUser(user, reason));
	}

	
	public void banUser(long userId, int deleteMessageDays, String reason) {
		FutureCompleter.handle(server.banUser(deleteMessageDays, deleteMessageDays, reason));
	}

	
	public void unbanUser(long userId, String reason) {
		FutureCompleter.handle(server.unbanUser(userId, reason));
	}

	
	public Collection<Ban> getBans() {
		return FutureCompleter.handle(server.getBans());
	}

	
	public List<Webhook> getWebhooks() {
		return FutureCompleter.handle(server.getWebhooks());
	}

	
	public List<Webhook> getAllIncomingWebhooks() {	
		return FutureCompleter.handle(server.getAllIncomingWebhooks());
	}

	
	public List<IncomingWebhook> getIncomingWebhooks() {
		return FutureCompleter.handle(server.getIncomingWebhooks());
	}

	
	public AuditLog getAuditLog(int limit) {
		return FutureCompleter.handle(server.getAuditLog(limit));
	}

	
	public AuditLog getAuditLog(int limit, AuditLogActionType type) {
		return FutureCompleter.handle(server.getAuditLog(limit, type));
	}

	
	public AuditLog getAuditLogBefore(int limit, AuditLogEntry before) {
		return FutureCompleter.handle(server.getAuditLogBefore(limit, before));
	}

	
	public AuditLog getAuditLogBefore(int limit, AuditLogEntry before, AuditLogActionType type) {
		return FutureCompleter.handle(server.getAuditLogBefore(limit, before, type));
	}

	
	public Collection<KnownCustomEmoji> getCustomEmojis() {
		return server.getCustomEmojis();
	}

	
	public List<SlashCommand> getSlashCommands() {
		return FutureCompleter.handle(server.getSlashCommands());
	}

	
	public SlashCommand getSlashCommandById(long commandId) {
		return FutureCompleter.handle(server.getSlashCommandById(commandId));
	}

	
	public List<ServerChannel> getChannels() {
		return server.getChannels();
	}

	
	public List<ChannelCategory> getChannelCategories() {
		return server.getChannelCategories();
	}

	
	public List<ServerTextChannel> getTextChannels() {
		return server.getTextChannels();
	}

	
	public List<ServerVoiceChannel> getVoiceChannels() {
		return server.getVoiceChannels();
	}	

	
	public ServerChannel getChannelById(long id) {
		return OptionalHandler.handle(server.getChannelById(id));
	}

	@Override
	public String getIdAsString() {
		return server.getIdAsString();
	}

}
