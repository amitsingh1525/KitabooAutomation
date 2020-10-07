package com.hurix.api.utility;

import org.mortbay.log.Log;

public class ExtractCategory {

	public static  String  extractCategory(String category)
	{
		String value = category;
		String catname = null;
		if(value.contains("||")){
			int endIndex = value.lastIndexOf("||");
			String newstr = value.substring(endIndex, value.length());
			Log.info(String.valueOf(newstr.replace("||", "")));
			catname = String.valueOf(newstr.replace("||", ""));
		}else if(value.contains(",")){
			int endIndex = value.lastIndexOf(",");
			String newstr = value.substring(endIndex, value.length());
			Log.info(String.valueOf(newstr.replace(",", "")));
			catname = String.valueOf(newstr.replace(",", ""));
		}else{
			Log.info("value : "+value);
			catname = String.valueOf(value);
		}
		return catname;
	}
}