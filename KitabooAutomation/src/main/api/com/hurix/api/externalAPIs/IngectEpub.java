package com.hurix.api.externalAPIs;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import com.hurix.automation.utility.Log;

public class IngectEpub {

	public static String IngectEpubBody;	
	public static String third;
	public static Response ingectEpub_ext(String consumerKey, String consumerSecret,String filePath)
	{		
		Response jsonResponse = null;
		try {
			IngectEpubBody = "{\"filePath\":\""+filePath+"\"}";
			Log.startTestCase("IngectEpub");
			Log.info("filePath: "+IngectEpubBody);
			Log.info("consumerKey : "+consumerKey);
			Log.info("consumerSecret : "+consumerSecret);
			Log.info("filePath : "+filePath);
			Log.info("URL : "+"/DistributionServices/ext/api/book/ingestEpubFile");
			
			jsonResponse = given()
					.auth()
					.oauth(consumerKey, consumerSecret, "", "")
					.header("Content-Type","application/json")
					.body(IngectEpubBody)
					.post("/DistributionServices/ext/api/book/ingestEpubFile");
			
			Log.info("IngectEpub Response: "+jsonResponse.then().extract().response().prettyPrint());
		}
		catch (Exception exp) 
		{
			Log.fail(exp.getMessage());
			exp.printStackTrace();
		}
		Log.endTestCase("End");
		return jsonResponse;		
	}
}
/*if(isbnMeta.contains("2424242424243"))
{

	third ="https://hurix-staging-content.s3.amazonaws.com/test/"+isbnMeta+".epub?AWSAccessKeyId=AKIA4PI2NOPJS3DPE77X&Expires=1603889384&Signature=sixD166ziWfCrWC5cv39MJSF5MA%3D";
	IngectEpubBodythird = "{\"filePath\":\""+third+"\"}";
}
else if(isbnMeta.contains("2424242424242"))
{

	third ="https://hurix-staging-content.s3.amazonaws.com/test/"+isbnMeta+".epub?AWSAccessKeyId=AKIA4PI2NOPJS3DPE77X&Expires=1603889365&Signature=BeH6o9%2F9JfyXG2ANIagKIAEarXg%3D";
	IngectEpubBodythird = "{\"filePath\":\""+third+"\"}";
}
else if(isbnMeta.contains("2424242424241"))
{

	third ="https://hurix-staging-content.s3.amazonaws.com/test/"+isbnMeta+".epub?AWSAccessKeyId=AKIA4PI2NOPJS3DPE77X&Expires=1603889267&Signature=yJt%2BAsW39SwEBzAbOJLlEhVpqoc%3D";
	IngectEpubBodythird = "{\"filePath\":\""+third+"\"}";
}

else if(isbnMeta.contains("2424242424244"))
{

	third ="https://hurix-staging-content.s3.amazonaws.com/test/"+isbnMeta+".epub?AWSAccessKeyId=AKIA4PI2NOPJS3DPE77X&Expires=1603889410&Signature=h2%2FRRXLD%2Bn%2Bb8Rr3%2Fge61UCxIvc%3D";
	IngectEpubBodythird = "{\"filePath\":\""+third+"\"}";
}

else if(isbnMeta.contains("2424242424245"))
{

	third = "https://hurix-staging-content.s3.amazonaws.com/test/"+isbnMeta+".epub?AWSAccessKeyId=AKIA4PI2NOPJS3DPE77X&Expires=1603889449&Signature=Usu9mM9JvtbPyJwKpqUmPmHzNGk%3D";
	IngectEpubBodythird = "{\"filePath\":\""+third+"\"}";
}

else if(isbnMeta.contains("2424242424246"))
{

	third ="https://hurix-staging-content.s3.amazonaws.com/test/"+isbnMeta+".epub?AWSAccessKeyId=AKIA4PI2NOPJS3DPE77X&Expires=1603889496&Signature=S2bM19bw7fjIohfFp5LJ9DfYz20%3D";
	IngectEpubBodythird = "{\"filePath\":\""+third+"\"}";
}
else if(isbnMeta.contains("2424242424247"))
{

	third ="https://hurix-staging-content.s3.amazonaws.com/test/"+isbnMeta+".epub?AWSAccessKeyId=AKIA4PI2NOPJS3DPE77X&Expires=1603889514&Signature=KNsQo4OdTvfColU%2BHKz3MaXtW7g%3D";
	IngectEpubBodythird = "{\"filePath\":\""+third+"\"}";
}
else if(isbnMeta.contains("2424242424248"))
{

	third ="https://hurix-staging-content.s3.amazonaws.com/test/"+isbnMeta+".epub?AWSAccessKeyId=AKIA4PI2NOPJS3DPE77X&Expires=1603889527&Signature=rioCbOzymbeReVUDrY%2FeNgeUEAc%3D";
	IngectEpubBodythird = "{\"filePath\":\""+third+"\"}";
}
else if(isbnMeta.contains("2424242424249"))
{

	third ="https://hurix-staging-content.s3.amazonaws.com/test/"+isbnMeta+".epub?AWSAccessKeyId=AKIA4PI2NOPJS3DPE77X&Expires=1603889542&Signature=NyHTU5a3J8B%2Fg6eDzQ8PVVisZDU%3D";
	IngectEpubBodythird = "{\"filePath\":\""+third+"\"}";
}
else if(isbnMeta.contains("2424242424250"))
{

	third ="https://hurix-staging-content.s3.amazonaws.com/test/"+isbnMeta+".epub?AWSAccessKeyId=AKIA4PI2NOPJS3DPE77X&Expires=1603889556&Signature=RAKdJMCeUZE5Ph9OJyhY2W%2BLwPY%3D";
	IngectEpubBodythird = "{\"filePath\":\""+third+"\"}";
}
else if(isbnMeta.contains("2424242424251"))
{

	third ="https://hurix-staging-content.s3.amazonaws.com/test/"+isbnMeta+".epub?AWSAccessKeyId=AKIA4PI2NOPJS3DPE77X&Expires=1603889556&Signature=RAKdJMCeUZE5Ph9OJyhY2W%2BLwPY%3D";
	IngectEpubBodythird = "{\"filePath\":\""+third+"\"}";
}*/