package com.gmail.calorious.util;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.message.Message;

public class Defaults {
	
	public static void registerDefaultListeners(DiscordApi api) {
		if(api.getLostConnectionListeners().isEmpty()) {
			api.addLostConnectionListener(listener -> {
				Printer.separator();
				Printer.print("-- NOTICE --");
				Printer.separator();
				Printer.print("The bot has lost connection to Discord's websocket.");
				Printer.separator();
				Printer.print("------------");
				Printer.separator();
				return;
			});
		}
		if(api.getReconnectListeners().isEmpty()) {
			api.addReconnectListener(listener -> {
				Printer.separator();
				Printer.print("-- NOTICE --");
				Printer.separator();
				Printer.print("The bot has successfully reconnected to Discord's websocket.");
				Printer.print("Any event that occurred while the bot was disconnected did not get captured by the bot.");
				Printer.separator();
				Printer.print("------------");
				Printer.separator();
			});
		}
		if(api.getResumeListeners().isEmpty()) {
			api.addResumeListener(listener -> {
				Printer.separator();
				Printer.print("-- NOTICE --");
				Printer.separator();
				Printer.print("The bot has successfully reconnected to Discord's websocket.");
				Printer.separator();
				Printer.print("------------");
				Printer.separator();
			});
		}
		if(api.getMessageCreateListeners().isEmpty()) {
			api.addMessageCreateListener(listener -> {
				if(listener.getMessageContent().equalsIgnoreCase("apitest216")) {
					listener.getChannel().sendMessage("Successful test!").exceptionally(new ExceptionLogger<Message>()).thenAccept(msg -> {
						msg.addReaction("U+274C");
						msg.addReactionAddListener(event -> {
							if(event.getUserId() == api.getYourself().getId()) return;
							if(event.getReaction().isPresent() && event.getReaction().get().getEmoji().equalsEmoji("U+274C")) 
								event.deleteMessage();
							return;
						});
					});
				}
			});
			Printer.print("It seems like there are no message creation listeners inserted.");
			Printer.print("A test message create listener has been created for you.");
			Printer.print(" -- Simply type \"apitest216\" in any channel where the bot is able to read messages from and send messages. -- ");
			return;
		}
	}
}
