package com.hurix.automation.utility;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;

public class Gmail_ReadAndDelete {

	
	static String hostName = "smtp.gmail.com";
	static String username = "hurix.reader@gmail.com";
	static String password = "kitaboo@123";
	static int messageCount;
	static int unreadMsgCount;
	static String emailSubject;
	static Message emailMessage;
	private static String resetPassword;
	
	public static void deleteAllMails(){
		
		try {
			Properties sysProps = System.getProperties();
			sysProps.setProperty("mail.store.protocol", "imaps");
			
			Session session = Session.getInstance(sysProps, null);
			Store store = session.getStore();
			store.connect(hostName, username, password);
			Folder emailInbox = store.getFolder("INBOX");
			emailInbox.open(Folder.READ_WRITE);
			messageCount = emailInbox.getMessageCount();
			System.out.println("Total Message Count in Inbox: " + messageCount);
			System.out.println("Please wait we will trying to delete all mails...");
			for(int i=1;i<= messageCount;i++){
				emailMessage = emailInbox.getMessage(i);
				emailMessage.setFlag(Flags.Flag.DELETED, true);
			}
			
			System.out.println("Inbox cleared!");
			emailInbox.close(true);
			store.close();
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String readMails(){
		try {
			Properties sysProps = System.getProperties();
			sysProps.setProperty("mail.store.protocol", "imaps");
			
			Session session = Session.getInstance(sysProps, null);
			Store store = session.getStore();
			store.connect(hostName, username, password);
			Folder emailInbox = store.getFolder("INBOX");
			emailInbox.open(Folder.READ_WRITE);
			for(int i=0;i<=9;i++){
				
				messageCount = emailInbox.getMessageCount();
				System.out.println("Please wait we will trying to fetching mails... now total Message Count is: " + messageCount +".");
				if(messageCount == 1){
					emailMessage = emailInbox.getMessage(messageCount);
					emailSubject = emailMessage.getSubject();
					System.out.println("Yes, I found some mail. Subject is: "+emailSubject);
					Object obj = emailMessage.getContent();
					Multipart mp = (Multipart)obj;
					BodyPart bp = mp.getBodyPart(0);
					String bodyData = bp.getContent().toString();
					
					if(bodyData.contains("Password :")){
						 String lineWithoutSpaces = bodyData.replaceAll("\\s+","");
						 String firstline = "Userpasswordresetsuccessfully:Logincredentialsare:Username:"+username+"Password:";
						 String lastone = "Byusingabovecredential,youagreetoourhttps://www.google.com/andhttps://hurix.com/.";
						 String lastline = "Pleaseuseyourcredentialstologinintothesystem:https://read.kitaboo.comIfyouhaveadditionalquestions,"
						 		+ "orneedassistance,pleasecontactassignorwithfollowinge-mailid:\"no-reply@kitaboo.com\"Note:Thisissystemgeneratedmessage.";
						 
						 String combine = "Pleaseuseyourcredentialstologinintothesystem:https://read.kitaboo.comIfyouhaveadditionalquestions,"
							 		+ "orneedassistance,pleasecontactassignorwithfollowinge-mailid:\"no-reply@kitaboo.com\"Note:Thisissystemgeneratedmessage."
								 +"Byusingabovecredential,youagreetoourhttps://www.google.com/andhttps://hurix.com/.";
						 String data = lineWithoutSpaces.replaceAll(firstline, "");
						 
						 if(data.contains(combine)){
							 String actualData = data.replaceAll(combine, "");
							 resetPassword = actualData;
							 if(actualData.contains(lastline)){
								 String actualData1 = data.replaceAll(lastline, "");
								 resetPassword = actualData1;
							 }
						 }else{
							 String actualData = data.replaceAll(lastone, "");
							 resetPassword = actualData;
						 }
						}
					System.out.println("Password: "+resetPassword);
					emailMessage.setFlag(Flags.Flag.SEEN, true);
					break;
				}
				Thread.sleep(9000);
				Thread.sleep(9000);
				continue;
			}
			
			emailInbox.close(true);
			store.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resetPassword;
	}
	
	   public static void main(String[] args) {
		   readMails();
		   //deleteAllMails();
	   }


}
