package com.gmail.calorious.security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class SafeHouse {
	// Encrypt and decrypt
	protected static SecretKey generateKey() throws NoSuchAlgorithmException {
	    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
	    keyGenerator.init(128);
	    SecretKey key = keyGenerator.generateKey();
	    return key;
	}
	
	protected static SecretKey generateKey(int keysize) throws NoSuchAlgorithmException {
	    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
	    keyGenerator.init(keysize);
	    SecretKey key = keyGenerator.generateKey();
	    return key;
	}
	
	protected static IvParameterSpec generateIv() {
	    byte[] iv = new byte[16];
	    new SecureRandom().nextBytes(iv);
	    return new IvParameterSpec(iv);
	}
	
	public static SafeKey createSafeKey(String content) {
		SafeKey key = new SafeKey(content);
		return key;
	}
}
