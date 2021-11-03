package com.gmail.calorious.api.javacord;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.Icon;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageDecoration;
import org.javacord.api.entity.message.Messageable;
import org.javacord.api.entity.message.component.ActionRow;
import org.javacord.api.entity.message.component.HighLevelComponent;
import org.javacord.api.entity.message.component.LowLevelComponent;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.message.internal.MessageBuilderDelegate;
import org.javacord.api.entity.message.mention.AllowedMentions;
import org.javacord.api.entity.user.User;
import org.javacord.api.entity.webhook.IncomingWebhook;
import org.javacord.api.util.internal.DelegateFactory;

public class API_MessageBuilder {
	private MessageBuilderDelegate updater;
	protected API_MessageBuilder() {
		this.updater = DelegateFactory.createMessageBuilderDelegate();
	}
	
	public API_MessageBuilder addActionRow(LowLevelComponent... components) {
		updater.addActionRow(components);
		return this;
	}
	
	public API_MessageBuilder addActionRow(ActionRow actionRow) {
		for(LowLevelComponent component : actionRow.getComponents()) {
			updater.addActionRow(component);
		}
		return this;
	}
	
	public API_MessageBuilder addAttachment(byte[] bytes, String fileName) {
		updater.addAttachment(bytes, fileName);
		return this;
	}
	
	public API_MessageBuilder addAttachment(BufferedImage image, String fileName) {
		updater.addAttachment(image, fileName);
		return this;
	}
	
	public API_MessageBuilder addAttachment(InputStream stream, String fileName) {
		updater.addAttachment(stream, fileName);
		return this;
	}
	
	public API_MessageBuilder addAttachment(Icon icon, boolean spoiler) {
		if(spoiler) {
			updater.addAttachmentAsSpoiler(icon);
			return this;
		}
		updater.addAttachment(icon);
		return this;
	}
	
	public API_MessageBuilder addAttachment(File file, boolean spoiler) {
		if(spoiler) {
			updater.addAttachmentAsSpoiler(file);
			return this;
		}
		updater.addAttachment(file);
		return this;
	}
	
	public API_MessageBuilder addAttachment(URL url, boolean spoiler) {
		if(spoiler) {
			updater.addAttachmentAsSpoiler(url);
			return this;
		}
		updater.addAttachment(url);
		return this;
	}
	
	public API_MessageBuilder addComponents(HighLevelComponent... components) {
		updater.addComponents(components);
		return this;
	}
	
	public API_MessageBuilder addEmbed(EmbedBuilder embed) {
		updater.addEmbed(embed);
		return this;
	}
	
	public API_MessageBuilder addEmbeds(EmbedBuilder... embeds) {
		updater.addEmbeds(embeds);
		return this;
	}
	
	public API_MessageBuilder addFile(byte[] bytes, String fileName) {
		updater.addFile(bytes, fileName);
		return this;
	}
	
	public API_MessageBuilder addFile(BufferedImage image, String fileName) {
		updater.addFile(image, fileName);
		return this;
	}
	
	public API_MessageBuilder addFile(InputStream stream, String fileName) {
		updater.addFile(stream, fileName);
		return this;
	}
	
	public API_MessageBuilder addFile(Icon icon, boolean spoiler) {
		if(spoiler) {
			updater.addFileAsSpoiler(icon);
			return this;
		}
		updater.addFile(icon);
		return this;
	}
	
	public API_MessageBuilder addFile(File file, boolean spoiler) {
		if(spoiler) {
			updater.addFileAsSpoiler(file);
			return this;
		}
		updater.addFile(file);
		return this;
	}
	
	public API_MessageBuilder addFile(URL url, boolean spoiler) {
		if(spoiler) {
			updater.addFileAsSpoiler(url);
			return this;
		}
		updater.addFile(url);
		return this;
	}
	
	public API_MessageBuilder append(Object object) {
		updater.append(object);
		return this;
	}
	
	public API_MessageBuilder append(String message, MessageDecoration... decorations) {
		updater.append(message, decorations);
		return this;
	}
	
	public API_MessageBuilder appendCode(String language, String code) {
		updater.appendCode(language, code);
		return this;
	}
	
	public API_MessageBuilder appendNewLine() {
		updater.appendNewLine();
		return this;
	}
	
	public API_MessageBuilder copy(Message message) {
		updater.copy(message);
		return this;
	}
	
	/*
	 * No linkback to Javacord's MessageBuilder or JavaAPI's MessageBuilder
	 */
	public StringBuilder toStringBuilder() {
		return updater.getStringBuilder();
	}
	
	public API_MessageBuilder removeAllComponents() {
		updater.removeAllComponents();
		return this;
	}
	
	public API_MessageBuilder removeAllEmbeds() {
		updater.removeAllEmbeds();
		return this;
	}
	
	public API_MessageBuilder removeEmbed(EmbedBuilder embed) {
		updater.removeEmbed(embed);
		return this;
	}
	
	public API_MessageBuilder removeEmbeds(EmbedBuilder... embeds) {
		updater.removeEmbeds(embeds);
		return this;
	}
	
	public API_MessageBuilder setReply(long messageId) {
		updater.replyTo(messageId);
		return this;
	}
	
	public API_MessageBuilder setReply(Message message) {
		if(message == null) 
			throw new NullPointerException("Message cannot be null at setReply()");
		updater.replyTo(message.getId());
		return this;
	}
	
	public CompletableFuture<Message> sendMessage(TextChannel channel) {
		return updater.send(channel);
	}
	
	public CompletableFuture<Message> sendMessage(IncomingWebhook webhook) {
		return updater.send(webhook);
	}
	
	public CompletableFuture<Message> sendMessage(Messageable messageable) {
		return updater.send(messageable);
	}
	
	public CompletableFuture<Message> sendMessage(User user) {
		return updater.send(user);
	}
	
	public CompletableFuture<Message> sendWithWebhook(DiscordApi api, long webhookId, String webhookToken) {
		return updater.sendWithWebhook(api, webhookToken, webhookToken);
	}
	
	public API_MessageBuilder setAllowedMentions(AllowedMentions mentions) {
		updater.setAllowedMentions(mentions);
		return this;
	}
	
	public API_MessageBuilder setContent(String messageContent) {
		updater.setContent(messageContent);
		return this;
	}
	
	/*
	 * This method will wipe all current embeds
	 */
	public API_MessageBuilder setEmbed(EmbedBuilder embed) {
		updater.removeAllEmbeds();
		updater.addEmbed(embed);
		return this;
	}
	
	/*
	 * This method will wipe all current embeds
	 */
	public API_MessageBuilder setEmbeds(EmbedBuilder... embeds) {
		updater.removeAllEmbeds();
		updater.addEmbeds(embeds);
		return this;
	}
	
	public API_MessageBuilder setNonce(String nonce) {
		updater.setNonce(nonce);
		return this;
	}
	
	public API_MessageBuilder setTTS(boolean tts) {
		updater.setTts(tts);
		return this;
	}
}