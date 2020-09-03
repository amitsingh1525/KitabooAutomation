package com.hurix.api.utility;

import java.math.BigInteger;
import java.security.MessageDigest;
import com.hurix.api.hashAPIs.*;

public class MD5GenrationWithBodyData {
	
	public static String hashGenration(String url) throws Exception 
	{
		String s = url.replaceAll("http://", "").replaceAll("https://", "");
		String s1 = s+V1RefreshBooksHash.v1refreshBooksBody;
		System.out.println("body:  :: " +s+V1RefreshBooksHash.v1refreshBooksBody);
		int count = 0;  
		for(int i = 0; i < s1.length(); i++) {    
			if(s1.charAt(i) != ' ')    
				count++;     }  
		String counter= String.valueOf(count);		
		//vars.put("StringCounter",counter);
		MessageDigest md = MessageDigest.getInstance("MD5"); 
		byte[] messageDigest = md.digest(s1.getBytes()); 
		BigInteger no = new BigInteger(1, messageDigest); 
		String hashtext = no.toString(16); 
		while (hashtext.length() < 32) { 
			hashtext = "0" + hashtext; 
		} 
		System.out.println("HashValue##########################"+GenerateHashValue.GenerateHashValue(hashtext, counter));
		return GenerateHashValue.GenerateHashValue(hashtext, counter);
	}	

}
