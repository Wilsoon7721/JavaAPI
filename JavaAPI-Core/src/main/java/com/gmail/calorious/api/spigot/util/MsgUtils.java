package com.gmail.calorious.api.spigot.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

public class MsgUtils {
    public static String color(String message) {
	return ChatColor.translateAlternateColorCodes('&', message);
    }
    
    public static void sendConsoleMessage(String message) {
    	Bukkit.getConsoleSender().sendMessage(MsgUtils.color(message));
    }

    public static String translateLanguage(String message, String two_letter_language_identifier) {
	Translate translate = TranslateOptions.getDefaultInstance().getService();
	Translate.TranslateOption.sourceLanguage("en");
	if(two_letter_language_identifier != "") {
	    Translate.TranslateOption.targetLanguage(two_letter_language_identifier);
	} else {
	    return "Unable to translate. Code failed with exit code -5817515: Google servers could not find the target language.";
	}
	Translation translation = translate.translate(message);
	return translation.getTranslatedText();
    }
}
