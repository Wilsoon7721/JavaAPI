package com.gmail.calorious.api.javacord;

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

public class JavacordRegistration {
	private static HashMap<String, SafeKey> logins = new HashMap<>();
	
	// Protected functions, methods only visible to containing package
	protected static HashMap<String, SafeKey> getLogins() {
		return logins;
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
	
	
	public static Javacord_JavaAPI registerApplication(String applicationName, String token) {
		// Registers a new hook that is using this API. Opens a connection to Discord to login using the token.
		SafeKey key = null;
		try {
			key = SafeHouse.createSafeKey(token).encrypt();
		} catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException e) {}
		logins.put(StringUtils.capitalize(applicationName.toLowerCase()), key);
		return new Javacord_JavaAPI(key);
	}
}
