package com.hurix.api.utility;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;


public class RSADecryption {
	private PrivateKey privateKey;
	private PublicKey publicKey;

	public RSADecryption() throws NoSuchAlgorithmException {
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(1024);
		KeyPair pair = keyGen.generateKeyPair();
		this.privateKey = pair.getPrivate();
		this.publicKey = pair.getPublic();
	}


	public String encrypt(String plainText, PublicKey publicKey) throws Exception {
		Cipher encryptCipher = Cipher.getInstance("RSA");
		encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] cipherText = encryptCipher.doFinal(plainText.getBytes("UTF-8"));
		return Base64.encodeBase64String(cipherText);
	}

	public String decrypt(String cipherText, PrivateKey privateKey) throws Exception {
		byte[] bytes = Base64.decodeBase64(cipherText);
		Cipher decriptCipher = Cipher.getInstance("RSA");
		decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);
		return new String(decriptCipher.doFinal(bytes), "UTF-8");
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public static String RSAEncryptionDecryption(String value, String publicKey, String privateKey) {
		String decryptedValue = null;
		try {
			RSADecryption keyPairGenerator = new RSADecryption();
			
			//encrypt
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			X509EncodedKeySpec publicSpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
			String encrypted =keyPairGenerator.encrypt(value,keyFactory.generatePublic(publicSpec));
			System.out.println("Value: "+value);
			System.out.println("Encrypted: "+encrypted);

			//dcrypted Logic
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
			decryptedValue = keyPairGenerator.decrypt(encrypted,keyFactory.generatePrivate(keySpec));
			System.out.println("Decrypted:"+decryptedValue);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return decryptedValue;
	}

	public static void main(String[] args) throws Exception {
		
		RSADecryption keyPairGenerator = new RSADecryption();
		String publicKey=Base64.encodeBase64String(keyPairGenerator.getPublicKey().getEncoded());
		String privateKey=Base64.encodeBase64String(keyPairGenerator.getPrivateKey().getEncoded());
		System.out.println("public key: "+publicKey);
		System.out.println("private key: "+privateKey);

		//encrypt
		String encVal = "Hello World";
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		X509EncodedKeySpec publicSpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
		String encrypted =keyPairGenerator.encrypt(encVal,keyFactory.generatePublic(publicSpec));
		System.out.println("Value: "+encVal);
		System.out.println("Encrypted: "+encrypted);


		//dcrypted Logic
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
		System.out.println("Decrypted:"+keyPairGenerator.decrypt(encrypted,keyFactory.generatePrivate(keySpec)));

	}
}
