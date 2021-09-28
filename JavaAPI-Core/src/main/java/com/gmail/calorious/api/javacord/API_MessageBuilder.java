package com.gmail.calorious.api.javacord;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

import org.javacord.api.entity.Icon;
import org.javacord.api.entity.message.MessageDecoration;
import org.javacord.api.entity.message.component.HighLevelComponent;
import org.javacord.api.entity.message.component.LowLevelComponent;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.message.internal.MessageBuilderDelegate;
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
	
	
	
}