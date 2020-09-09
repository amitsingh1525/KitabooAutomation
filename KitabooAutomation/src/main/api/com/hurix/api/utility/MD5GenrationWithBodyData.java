package com.hurix.api.utility;

import java.math.BigInteger;
import java.security.MessageDigest;
import com.hurix.api.hashAPIs.*;

public class MD5GenrationWithBodyData {
	
	public static String hashGenration(String url) throws Exception 
	{
		String s = url.replaceAll("http://", "").replaceAll("https://", "") + V1RefreshBooksHash.v1refreshBooksBody1;
		System.out.println("body:  :: " +s);
		int count = 104;  
		/*for(int i = 0; i < s.length(); i++) {    
			if(s.charAt(i) != ' ')    
				count++;     }  */
		String counter= String.valueOf(count);		
		//vars.put("StringCounter",counter);
		MessageDigest md = MessageDigest.getInstance("MD5"); 
		byte[] messageDigest = md.digest(s.getBytes()); 
		BigInteger no = new BigInteger(1, messageDigest); 
		String hashtext = no.toString(16); 
		while (hashtext.length() < 32) { 
			hashtext = "0" + hashtext; 
		} 
		System.out.println("hashtext: "+hashtext);
		return GenerateHashValue.generateHashValue(hashtext, counter);
	}	

}
