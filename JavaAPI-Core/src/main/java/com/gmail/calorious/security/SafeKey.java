package com.gmail.calorious.security;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class SafeKey {
	// Saves only the encrypted text
	private String algorithm = "AES/CBC/PKCS5Padding", encryptedText;
	private SecretKey secretKey;
	private IvParameterSpec iv;
	private String input = "";
	protected SafeKey(String input) {
		try {
			secretKey = SafeHouse.generateKey(128);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("Generating SecretKey failed with NoSuchAlgorithmException.");
		}
		iv = SafeHouse.generateIv();
		if(input.contentEquals("")) throw new IllegalStateException("Can't instantiate SafeKey without an input.");
		this.input = input;
	}
	
	public String getBase64Input() {
		return Base64.getEncoder().encodeToString(input.getBytes());
	}
	
	public SafeKey encrypt() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
		byte[] cipherText = cipher.doFinal(input.getBytes());
		encryptedText = Base64.getEncoder().encodeToString(cipherText);
		return this;
	}
	
	public String getEncryptedKeyContent() {
		return encryptedText;
	}
	
	public String decrypt() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		if(encryptedText == null || encryptedText.contentEquals("")) {
			System.out.println("Could not decrypt SafeKey text -- there seems to be no text currently encrypted.");
			return null;
		}
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
		byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
	    return new String(plainText);
	}
}
