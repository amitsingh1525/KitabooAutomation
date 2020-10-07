package com.hurix.api.runner;

import io.restassured.response.Response;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.http.HttpStatus;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.hurix.api.externalAPIs.*;
import com.hurix.api.readerAPIs.*;
import com.hurix.api.utility.*;
import com.hurix.automation.utility.Log;

public class Patch_OrderV2 {

	//public static List<String> detailisbn =  ExcelUtils.getisbn();
	public static String consumerKey;
	public static String userToken;
	public static String consumerSecret;
	public static String excelPath;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static String environMent;
	public static String userName;
	public static String password;
	public static String detail;
	public static String clientID;
	public static String catlevel;
	public static String title;
	public static long nowEpochTime;
	public static String Title;
	public static int epubId;
	//public static String userID;
	public static String isbnMeta;
	public static String isbnIng;
	public static String sqlhost;
	public static String sqlUsername;
	public static String sqlPassword;
	public static String client_Id;
	public static String firstName;
	public static String lastName;
	public static String deviceT;
	public static String refID;
	public static String client_T;
	public static String clientBookID;
	public static String collectionRefID;
	public static String libraryRefID;
	public static String userName1;
	public static String email = null ;
	public static int bookID1;
	public static int userID1=0;
	//public static String deviceLimit;
	//public static String type;


	public static void main(String[] args) {
		//Log.initialization("APITesting");//DIS-1979	
		Log.initialization("PATCH_ORDERV2");
		try {
			//startDate=EpochTime.getEpochTime(""+startDate+"");
			excelPath="./testData/PATCH_ORDERV22.xlsx";
			workbook = new XSSFWorkbook(excelPath);
			sheet= workbook.getSheet("Sheet1");
			for(int i=1;i<=sheet.getLastRowNum();i++)
			{
				DataFormatter formatter = new DataFormatter();
				environMent = formatter.formatCellValue(sheet.getRow(i).getCell(0));
				userName=formatter.formatCellValue(sheet.getRow(i).getCell(1));			
				password=formatter.formatCellValue(sheet.getRow(i).getCell(2));
				String runY_N = formatter.formatCellValue(sheet.getRow(i).getCell(3));
				deviceT =  formatter.formatCellValue(sheet.getRow(i).getCell(4));
				clientBookID =  formatter.formatCellValue(sheet.getRow(i).getCell(5));
				collectionRefID =  formatter.formatCellValue(sheet.getRow(i).getCell(6));
				libraryRefID =  formatter.formatCellValue(sheet.getRow(i).getCell(7));
				String deviceLimit1 = formatter.formatCellValue(sheet.getRow(i).getCell(8));
				String type1 = formatter.formatCellValue(sheet.getRow(i).getCell(9));
				refID = formatter.formatCellValue(sheet.getRow(i).getCell(10));
				client_T = formatter.formatCellValue(sheet.getRow(i).getCell(11));

				Log.info("runY_N : "+runY_N);
				if(runY_N.contains("NO")){Log.info("Permission to Run that Row is Denied!!..Please change YES in Ith row in Respective Sheet of Yours, Thank You");}
				else if(runY_N.contains("YES"))
				{switch(environMent){
				case "QC":
					detail = "http://qc.kitaboo.com";
					sqlhost = "jdbc:mysql://172.18.10.147:3306";
					sqlUsername = "readonly";
					sqlPassword = "readonly@123";
					break;
				case "Staging":
					detail = "http://qacloud.kitaboo.com";
					sqlhost="jdbc:mysql://hurix-staging-db.cbum2u9r6xyc.us-east-1.rds.amazonaws.com";
					sqlUsername="qcteam";
					sqlPassword="JB88F-WT2Q3-DPXTT";	
					break;
				case "BASE_US":
					detail = "http://localhost:12346";
					sqlhost="jdbc:mysql://localhost:12345";
					sqlUsername="shweta-katare";
					sqlPassword="J&P@O4A7HV";	

					break;
				case "BASE_EU":
					detail = "http://localhost:12347";
					sqlhost="jdbc:mysql://localhost:56789";
					sqlUsername="shweta-katare";
					sqlPassword="J&P@O4A7HV";
					break;
				case "PROD_US":
					detail = "http://cloud.kitaboo.com";
					sqlhost="jdbc:mysql://localhost:12345";
					sqlUsername="shweta-katare";
					sqlPassword="J&P@O4A7HV";
					break;
				case "PROD_EU":
					detail = "http://cloud.kitaboo.eu";
					sqlhost="jdbc:mysql://localhost:56789";
					sqlUsername="shweta-katare";
					sqlPassword="J&P@O4A7HV";
					break;
				}				
				io.restassured.RestAssured.baseURI = detail;	

				SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");  
				Date date = new Date();  
				String  time=formatter1.format(date);

				int deviceLimit = Integer.parseInt(""+deviceLimit1+"");
				int type = Integer.parseInt(""+type1+"");


				clientID =JDBC_category.getReader(userName, sqlhost, sqlUsername, sqlPassword);
				//int client_Id = Integer.parseInt(""+client_Id+"");					
				String userName1=null;			

				if(client_T.equals("Existing"))
				{Log.startTestCase("Authenticate");
				Log.info("TotalRows : "+sheet.getLastRowNum());
				Log.info("detail : "+detail);
				Log.info("userName : "+userName);
				Log.info("password : "+password);
				Log.info("clientID : "+clientID);
				Log.info("deviceT : "+deviceT);
				Response authenticateValue = Authenticate.authenticate(clientID, userName, password, "514185",deviceT);
				Log.info("Authenticate Response: "+authenticateValue.then().extract().response().prettyPrint());				
				System.out.println("HERE_Before");
				Log.info("clientID : "+clientID);
				Validation.responseHeaderCodeValidation(authenticateValue, HttpStatus.SC_OK);
				Validation.responseCodeValidation1(authenticateValue, HttpStatus.SC_OK);
				Validation.responseTimeValidation(authenticateValue);
				userToken = authenticateValue.then().extract().path("userToken");
				Log.info("userToken:"+userToken);

				int client_Id = authenticateValue.then().extract().path("user.clientID");
				Log.info("client_Id:"+client_Id);
				userID1 = authenticateValue.then().extract().path("user.id");
				Log.info("userID1:"+userID1);
				firstName = authenticateValue.then().extract().path("user.firstName");
				Log.info("firstName:"+firstName);
				lastName = authenticateValue.then().extract().path("user.lastName");
				Log.info("lastName:"+lastName);
				Log.info("HEWEWEWEWE");
				userName1 = authenticateValue.then().extract().path("user.userName");
				Log.info("userName1:"+""+userName1+"");
				email = authenticateValue.then().extract().path("user.email");
				Log.info("email:"+email);

				consumerKey = JDBC_category.getCK(client_Id, sqlhost, sqlUsername, sqlPassword);
				consumerSecret =JDBC_category.getSK(client_Id, sqlhost, sqlUsername, sqlPassword);


				Response fetchbooklist = FetchBookList.fetchBookList_without_pagination(userToken, "rwds32323", deviceT);
				Validation.responseHeaderCodeValidation(fetchbooklist, HttpStatus.SC_OK);
				Log.info("detail : "+detail);
				Validation.responseCodeValidation1(fetchbooklist, HttpStatus.SC_OK);
				Validation.responseTimeValidation(fetchbooklist);
				bookID1 = fetchbooklist.then().extract().path("bookList.book.id[1]");
				Log.info("bookID_1: "+bookID1);}


				Response orderV2_withBookID=null;
				if(client_T.contains("NON-Existing"))
				{
					orderV2_withBookID = OrderV2.orderV2_withBookID(EpochTime.current(),consumerKey,consumerSecret,bookID1,time,""+EpochTime.current()+"",""+EpochTime.current()+"",""+EpochTime.current()+"",password,""+EpochTime.current()+"", EpochTime.current()+"@yopmail.com",deviceLimit,type);
					Log.info("OrderNumber : " +EpochTime.current());
					Log.info("detail : "+detail);
					Log.info("consumerKey : " +consumerKey);
					Log.info("consumerSecret : " +consumerSecret);
					Log.info("bookID1 : " +bookID1);
					Log.info("activationDate : " +time);
					Log.info("client_T : " +client_T);
					Log.info("firstName : "+firstName);
					Log.info("lastName : "+lastName);
					Log.info("userName : "+"\""+userName+"\"");
					Log.info("password : "+password);
					Log.info("email : "+email);
				}	else if(client_T.contains("Existing"))
				{orderV2_withBookID = OrderV2.orderV2_withBookID(EpochTime.current(),consumerKey,consumerSecret,bookID1,time,firstName,lastName,userName1,password,""+userID1+"", email,deviceLimit,type);
				Log.info("OrderNumber : " +EpochTime.current());
				Log.info("detail : "+detail);
				Log.info("client_T : " +client_T);
				Log.info("consumerKey : " +consumerKey);
				Log.info("consumerSecret : " +consumerSecret);
				Log.info("bookID1 : " +bookID1);
				Log.info("activationDate : " +time);
				Log.info("firstName : "+firstName);
				Log.info("lastName : "+lastName);
				Log.info("userName : "+userName1);
				Log.info("password : "+password);
				Log.info("email : "+email);}
				Validation.responseHeaderCodeValidation(orderV2_withBookID, HttpStatus.SC_OK);
				//Validation.responseCodeValidation1(orderV2_withBookID, HttpStatus.SC_OK);//Order created successfully
				Validation.responseKeyValidation_key(orderV2_withBookID, "Order created successfully");
				Validation.responseTimeValidation(orderV2_withBookID);	


				Response orderV2withclientBookID=null;
				if(client_T.contains("NON-Existing"))
				{
					orderV2withclientBookID = OrderV2.orderV2_withclientBookID(EpochTime.current(),clientBookID, consumerKey, consumerSecret, bookID1, time,""+EpochTime.current()+"",""+EpochTime.current()+"",""+EpochTime.current()+"",password,""+EpochTime.current()+"", EpochTime.current()+"@yopmail.com",deviceLimit,type);
					Log.info("OrderNumber : " +EpochTime.current());
					Log.info("detail : "+detail);
					Log.info("client_T : " +client_T);
					Log.info("consumerKey : " +consumerKey);
					Log.info("consumerSecret : " +consumerSecret);
					Log.info("bookID1 : " +bookID1);
					Log.info("activationDate : " +time);
					Log.info("firstName : "+firstName);
					Log.info("lastName : "+lastName);
					Log.info("userName : "+"\""+userName+"\"");
					Log.info("password : "+password);
					Log.info("email : "+email);
				}
				else{
					orderV2withclientBookID = OrderV2.orderV2_withclientBookID(EpochTime.current(),clientBookID, consumerKey, consumerSecret, bookID1, time,firstName,lastName,userName1,password,""+userID1+"", email,deviceLimit,type);
					Log.info("OrderNumber : " +EpochTime.current());
					Log.info("detail : "+detail);
					Log.info("client_T : " +client_T);
					Log.info("consumerKey : " +consumerKey);
					Log.info("consumerSecret : " +consumerSecret);
					Log.info("bookID1 : " +bookID1);
					Log.info("activationDate : " +time);
					Log.info("firstName : "+firstName);
					Log.info("lastName : "+lastName);
					Log.info("userName : "+"\""+userName+"\"");
					Log.info("password : "+password);
					Log.info("email : "+email);}
				Validation.responseHeaderCodeValidation(orderV2withclientBookID, HttpStatus.SC_OK);
				//Validation.responseCodeValidation1(orderV2withclientBookID, HttpStatus.SC_OK);
				Validation.responseKeyValidation_key(orderV2withclientBookID, "Order created successfully");
				Validation.responseTimeValidation(orderV2withclientBookID);

				Response orderV2withccollectionRefID = null;
				if(client_T.contains("NON-Existing"))
				{
					orderV2withccollectionRefID = OrderV2.orderV2_withcollectionRefID(EpochTime.current(),collectionRefID, consumerKey, consumerSecret, bookID1, time,""+EpochTime.current()+"",""+EpochTime.current()+"",""+EpochTime.current()+"",password,""+EpochTime.current()+"", EpochTime.current()+"@yopmail.com",deviceLimit,type);
					Log.info("OrderNumber : " +EpochTime.current());
					Log.info("detail : "+detail);
					Log.info("consumerKey : " +consumerKey);
					Log.info("client_T : " +client_T);
					Log.info("consumerSecret : " +consumerSecret);
					Log.info("bookID1 : " +bookID1);
					Log.info("activationDate : " +time);
					Log.info("firstName : "+firstName);
					Log.info("lastName : "+lastName);
					Log.info("userName : "+"\""+userName+"\"");
					Log.info("password : "+password);
					Log.info("email : "+email);
				}else
				{orderV2withccollectionRefID = OrderV2.orderV2_withcollectionRefID(EpochTime.current(),collectionRefID, consumerKey, consumerSecret, bookID1, time,firstName,lastName,userName1,password,""+userID1+"", email,deviceLimit,type);
				Log.info("orderNumber : " +EpochTime.current());
				Log.info("detail : "+detail);
				Log.info("client_T : " +client_T);
				Log.info("collectionRefID : " +collectionRefID);
				Log.info("consumerKey : " +consumerKey);
				Log.info("consumerSecret : " +consumerSecret);
				Log.info("bookID1 : " +bookID1);
				Log.info("activationDate : " +time);
				Log.info("firstName : "+firstName);
				Log.info("lastName : "+lastName);
				Log.info("userName : "+"\""+userName+"\"");
				Log.info("password : "+password);
				Log.info("email : "+email);}
				Validation.responseHeaderCodeValidation(orderV2withccollectionRefID, HttpStatus.SC_OK);
				//Validation.responseCodeValidation1(orderV2withccollectionRefID, HttpStatus.SC_OK);
				Validation.responseKeyValidation_key(orderV2withccollectionRefID, "Order created successfully");
				Validation.responseTimeValidation(orderV2withccollectionRefID);


				/*SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");  
			Date date = new Date();  
			String  time =formatter1.format(date);
			int deviceLimit = Integer.parseInt(""+deviceLimit1+"");
			int type = Integer.parseInt(""+type1+"");*/
				Response orderV2withlibraryRefID=null;
				if(client_T.contains("NON-Existing"))
				{	
					orderV2withlibraryRefID = OrderV2.orderV2_withlibraryRef(EpochTime.current(),libraryRefID, consumerKey, consumerSecret, bookID1, time,""+EpochTime.current()+"",""+EpochTime.current()+"",""+EpochTime.current()+"",password,""+EpochTime.current()+"",EpochTime.current()+"@yopmail.com",deviceLimit,type);
					Log.info("OrderNumber : " +EpochTime.current());
					Log.info("detail : "+detail);
					Log.info("consumerKey : " +consumerKey);
					Log.info("client_T : " +client_T);
					Log.info("consumerSecret : " +consumerSecret);
					Log.info("bookID1 : " +bookID1);
					Log.info("activationDate : " +time);
					Log.info("firstName : "+firstName);
					Log.info("lastName : "+lastName);
					Log.info("userName : "+"\""+userName+"\"");
					Log.info("password : "+password);
					Log.info("email : "+email);
				}else{
					Thread.sleep(4000);
					orderV2withlibraryRefID = OrderV2.orderV2_withlibraryRef(EpochTime.current(),libraryRefID, consumerKey, consumerSecret, bookID1,time,firstName,lastName,userName1,password,""+userID1+"", email,deviceLimit,type);
					Log.info("OrderNumber : " +EpochTime.current());
					Log.info("detail : "+detail);
					Log.info("client_T : " +client_T);
					Log.info("consumerKey : " +consumerKey);
					Log.info("consumerSecret : " +consumerSecret);
					Log.info("bookID1 : " +bookID1);
					Log.info("activationDate : " +time);
					Log.info("firstName : "+firstName);
					Log.info("lastName : "+lastName);
					Log.info("userName : "+"\""+userName+"\"");
					Log.info("password : "+password);
					Log.info("email : "+email);
					Log.info("userID1 : "+userID1);}
				Validation.responseHeaderCodeValidation(orderV2withlibraryRefID, HttpStatus.SC_OK);
				//Validation.responseCodeValidation1(orderV2withccollectionRefID, HttpStatus.SC_OK);
				Validation.responseKeyValidation_key(orderV2withlibraryRefID, "Order created successfully");
				Validation.responseTimeValidation(orderV2withlibraryRefID);

				Log.info("***************here before if ************************8");
				Log.info("client_T : "+client_T);
				if(client_T.equals("NON-Existing"))
				{Log.info("***************here before if ************************heree for NON_Existing");
				Response orderV2_withrefID = OrderV2.orderV2_withrefID(EpochTime.current(),refID, consumerKey, consumerSecret, bookID1, time,""+EpochTime.current()+"",""+EpochTime.current()+"",""+EpochTime.current()+"",password,""+EpochTime.current()+"", EpochTime.current()+"@yopmail.com",deviceLimit,type);
				Log.info("OrderNumber : " +EpochTime.current());
				Log.info("detail : "+detail);
				Log.info("client_T : " +client_T);
				Log.info("consumerKey : " +consumerKey);
				Log.info("consumerSecret : " +consumerSecret);
				Log.info("bookID1 : " +bookID1);
				Log.info("activationDate : " +time);
				Log.info("firstName : "+firstName);
				Log.info("lastName : "+lastName);
				Log.info("userName : "+"\""+userName+"\"");
				Log.info("password : "+password);
				Log.info("email : "+email);
				Validation.responseHeaderCodeValidation(orderV2_withrefID, HttpStatus.SC_OK);
				//Validation.responseCodeValidation1(orderV2withccollectionRefID, HttpStatus.SC_OK);
				Validation.responseKeyValidation_key(orderV2_withrefID, "Order created successfully");
				Validation.responseTimeValidation(orderV2_withrefID);}else{}
				}
			}
		}catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			Log.fail("Fails due to "+exp.getCause());
			exp.printStackTrace();
		}
	}	
}

