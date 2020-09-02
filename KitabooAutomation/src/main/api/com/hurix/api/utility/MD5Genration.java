package com.hurix.api.utility;
import java.math.BigInteger; 
import java.security.MessageDigest; 

public class MD5Genration {
	
	public static String hashGenration(String url) throws Exception 
	{
		String s = url.replaceAll("http://", "").replaceAll("https://", "");
		int count = 0;  
		for(int i = 0; i < s.length(); i++) {    
			if(s.charAt(i) != ' ')    
				count++;     }  
		String counter= String.valueOf(count);		
		//vars.put("StringCounter",counter);
		MessageDigest md = MessageDigest.getInstance("MD5"); 
		byte[] messageDigest = md.digest(s.getBytes()); 
		BigInteger no = new BigInteger(1, messageDigest); 
		String hashtext = no.toString(16); 
		while (hashtext.length() < 32) { 
			hashtext = "0" + hashtext; 
		} 
		System.out.println("HashValue##########################"+GenerateHashValue.GenerateHashValue(hashtext, counter));
		return GenerateHashValue.GenerateHashValue(hashtext, counter);
	}	
}
