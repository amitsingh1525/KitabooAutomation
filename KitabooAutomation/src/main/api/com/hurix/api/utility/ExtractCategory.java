package com.hurix.api.utility;

public class ExtractCategory {

	public static  String  extractCategory(String category)
	{
		String value = category;
		String catname = null;
		if(value.contains("||")){
			int endIndex = value.lastIndexOf("||");
			String newstr = value.substring(endIndex, value.length());
			System.out.println(String.valueOf(newstr.replace("||", "")));
			catname = String.valueOf(newstr.replace("||", ""));
		}else if(value.contains(",")){
			int endIndex = value.lastIndexOf(",");
			String newstr = value.substring(endIndex, value.length());
			System.out.println(String.valueOf(newstr.replace(",", "")));
			catname = String.valueOf(newstr.replace(",", ""));
		}else{
			System.out.println(value);
			catname = String.valueOf(value);
		}
		return catname;
	}
}