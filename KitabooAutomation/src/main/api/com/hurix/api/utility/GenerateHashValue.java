package com.hurix.api.utility;

import java.io.UnsupportedEncodingException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class GenerateHashValue {
	
	public static String generateHashValue(String hash ,String counter) throws Exception
	{
		Date now = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(now);
		long mycurrentdate = c.getTimeInMillis();
		String epochTime = Long.toString(mycurrentdate);
		String MD5Generation = hash;
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
		String hashValue = new sun.misc.BASE64Encoder().encode(enc);
		return hashValue;
	}
}
