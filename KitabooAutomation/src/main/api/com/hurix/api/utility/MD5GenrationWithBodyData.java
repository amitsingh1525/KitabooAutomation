package com.hurix.api.utility;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.hurix.api.hashAPIs.*;

public class MD5GenrationWithBodyData {

	public static String hashGenration(String url) throws Exception 
	{
		String requestBody = V1RefreshBooksHash.v1refreshBooksBody1;

		String s = url.replaceAll("http://", "").replaceAll("https://", "");
		String concatValue1 = s+requestBody;

		int count = 0;  
		for(int i = 0; i < s.length(); i++) {    
			if(s.charAt(i) != ' ')    
				count++;     }  
		String counter= String.valueOf(count);

		MessageDigest md = MessageDigest.getInstance("MD5"); 
		byte[] messageDigest = md.digest(concatValue1.getBytes()); 
		BigInteger no = new BigInteger(1, messageDigest); 
		String hashtext = no.toString(16); 
		while (hashtext.length() < 32) { 
			hashtext = "0" + hashtext; 
		} 
		
		
		Date now = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(now);
		long mycurrentdate = c.getTimeInMillis();
		String epochTime = Long.toString(mycurrentdate);
		String MD5Generation = hashtext;
		String str = MD5Generation+":"+epochTime;
		Cipher ecipher;
		String keyCountString =  counter;
		String KeyData = keyCountString+"E+DL{c9P";

		byte[] KeyBytes = KeyData.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(KeyBytes, "Blowfish");
		ecipher = Cipher.getInstance("Blowfish");
		ecipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] utf8 = str.getBytes("UTF8");
		byte[] enc = ecipher.doFinal(utf8);
		 //return new sun.misc.BASE64Encoder().encode(enc);
		@SuppressWarnings("restriction")
		String hashValue = new sun.misc.BASE64Encoder().encode(enc);
		
		return hashValue;

	}	

}
