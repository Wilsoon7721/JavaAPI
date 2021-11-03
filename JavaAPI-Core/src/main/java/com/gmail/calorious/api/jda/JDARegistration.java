package com.gmail.calorious.api.jda;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.lang3.StringUtils;

import com.gmail.calorious.security.SafeHouse;
import com.gmail.calorious.security.SafeKey;
import com.gmail.calorious.util.Printer;

public class JDARegistration {
	private static HashMap<String, SafeKey> logins = new HashMap<>(); 
	
	/*
	 * Convert token to encrypted SafeKey
	 */
	public static JDA_JavaAPI registerApplication(String applicationName, String token) {
		// Registers a new hook that is using this API. Opens a connection to Discord to login using the token.
		SafeKey key = null;
		try {
			key = SafeHouse.createSafeKey(token).encrypt();
		} catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException e) {
			Printer.print("Error encrypting key.");
			Printer.print("Caused by: " + e.getCause());
			Printer.print("Message: " + e.getLocalizedMessage());
			Printer.separator();
			return null;
		}
		logins.put(StringUtils.capitalize(applicationName.toLowerCase()), key);
		return new JDA_JavaAPI(key);
	}
	
	protected static String getApplicationNameFromKey(SafeKey key) {
		if(!(logins.containsValue(key))) return "Could not find an entry - Perhaps your application is not registered?";
		for(Entry<String, SafeKey> entry : logins.entrySet()) {
			if(entry.getValue().equals(key)) {
				return entry.getKey();
			}
			continue;
		}
		return "Could not find an entry - Logins empty";
	}
}
