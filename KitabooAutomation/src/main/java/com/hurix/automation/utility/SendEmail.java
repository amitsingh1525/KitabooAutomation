package com.hurix.automation.utility;

import java.io.File;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

import javax.activation.*;

public class SendEmail 
{
	public SendEmail(final String from, final String frompassword, String mailRecipient) throws Exception
	{
		 //Get properties object    
        Properties props = new Properties();    
        props.put("mail.smtp.host", "smtp.gmail.com");    
        props.put("mail.smtp.socketFactory.port", "465");    
        props.put("mail.smtp.socketFactory.class",    
                  "javax.net.ssl.SSLSocketFactory");    
        props.put("mail.smtp.auth", "true");    
        props.put("mail.smtp.port", "465");    
        //get Session   
        Session session = Session.getDefaultInstance(props,    
         new javax.mail.Authenticator() {    
         protected PasswordAuthentication getPasswordAuthentication() {    
         return new PasswordAuthentication(from, frompassword);  
         }    
        });    
        //compose message   
        
        try {    
         MimeMessage message = new MimeMessage(session);    
       // message.addRecipient(Message.RecipientType.TO,new InternetAddress("amit.singh@hurix.com"));
			  String[] toRecipient = mailRecipient.split(","); 
			  for(String to :toRecipient)
			  { 
				  message.addRecipient(Message.RecipientType.TO,new InternetAddress(to)); 
			  }
			 
         message.setSubject("LoginAndBookLaunch script will Be Executed");    
         Multipart multipart = new MimeMultipart();
         
         MimeBodyPart textFirstPart = new MimeBodyPart();
         MimeBodyPart tabularBodyPart = new MimeBodyPart();
         MimeBodyPart textLastPart = new MimeBodyPart();
         textFirstPart.setText("Hi, \n \nAutomation Execution has been performed -\n \n");
         //tabularBodyPart.setContent(tablePart, "text/html");
         textLastPart.setText("\n Kindly find the details in the attachment.\n \t \t"
         		+ "https://docs.google.com/spreadsheets/d/1K6n_lUAv2jdIn7OasRltL47k1SJjDNU3MBFQdNnpDMY/edit#gid=922114956 "
         		+ "\n \n \nThanks and Regards,\nHurix Digital\n \n \nThis is a system generated Email.");
         
         MimeBodyPart attachmentBodyPart= new MimeBodyPart();
         File Logfileobject= new File("");	 
		 String LogFilePath = "/log/LoginAndBookLaunch.html";
         DataSource source = new FileDataSource(Logfileobject.getAbsolutePath()+LogFilePath);
         attachmentBodyPart.setDataHandler(new DataHandler(source));
         attachmentBodyPart.setFileName("LoginAndBookLaunch.html");
         multipart.addBodyPart(textFirstPart);
         multipart.addBodyPart(tabularBodyPart);
         multipart.addBodyPart(textLastPart);
         multipart.addBodyPart(attachmentBodyPart);

         /*//For Second attachment
         MimeBodyPart attachmentBodyPart1= new MimeBodyPart();
         File Logfileobject1= new File("");	 
		 String LogFilePath1 = "/TestData/LoginTesting.xls";
         DataSource source1 = new FileDataSource(Logfileobject1.getAbsolutePath()+LogFilePath1);
         attachmentBodyPart1.setDataHandler(new DataHandler(source1));
         attachmentBodyPart1.setFileName("LoginTesting.xls");
         multipart.addBodyPart(attachmentBodyPart1);*/
         
         message.setContent(multipart);
         Transport.send(message);    
        } catch (MessagingException e) {
        	System.out.println(e);
        	throw new RuntimeException(e);
        	}
  }  
	

	/*
	 * public static void main(String [] args) throws Exception { new
	 * SendEmail("amit.singh@hurix.com","P@$$word#456"); }
	 */
}