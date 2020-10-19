package com.hurix.api.utility;

import java.sql.Statement;
import java.util.Properties;

import com.hurix.automation.utility.Log;
import com.hurix.automation.utility.UIElements;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseQuerry {

	public static Properties prop = UIElements.getProperty(System.getProperty("user.dir")+"/config/databaseConfig/databaseConfig.properties");

	private static Connection connectivity(String env) {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			if(env.equalsIgnoreCase("QC")) {

				con = DriverManager.getConnection("jdbc:mysql://"+prop.getProperty("hostQC")
				+"?user="+prop.getProperty("usernameQC")
				+"&password="+prop.getProperty("passwordQC")+"");

			}else if(env.equalsIgnoreCase("Stag")) {

				con = DriverManager.getConnection("jdbc:mysql://"+prop.getProperty("hostStag")
				+"?user="+prop.getProperty("usernameStag")
				+"&password="+prop.getProperty("passwordStag")+"");

			}else if(env.equalsIgnoreCase("ProdUS")) {

				con = DriverManager.getConnection("jdbc:mysql://"+prop.getProperty("hostProdUS")
				+"?user="+prop.getProperty("usernameProdUS")
				+"&password="+prop.getProperty("passwordProdUS")+"");

			}
			System.out.println("Databases Connected!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public static String getReaderKey(String env, String username) {
		String readerKey = "ReaderKey_NOT_tFound";
		Connection con = connectivity(env);
		try {
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery("SELECT reader_key FROM cloudCore.CLIENT WHERE id=(SELECT client_id FROM cloudCore.USER WHERE username='"+username+"' LIMIT 1)");
			result.next();
			System.out.println(result.getString("reader_key"));
			readerKey = result.getString("reader_key");
			result.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			Log.errorAPI("ERROR: Failed to get readerKey from database. \n"+e.getMessage());
		}finally
		{
			closeConnection(con);
		}
		return readerKey;
	}
	
	public static String[] getConsumerAndSecretKey(String env, String username)
	{
		String[] consumer_key = null;
		Connection con = connectivity(env);
		try {
			
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery("SELECT consumer_key,shared_secret FROM cloudCore.CLIENT WHERE id=(SELECT client_id FROM cloudCore.USER WHERE username='"+username+"' LIMIT 1)");
			result.next();
			consumer_key = new String[] {result.getString("consumer_key"),result.getString("shared_secret")};
			result.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			Log.errorAPI("ERROR: Failed to get ConsumerKey And SharedSecretKey from database. \n"+e.getMessage());
		}finally
		{
			closeConnection(con);
		}
		return consumer_key;
	}
	
	
	private static void closeConnection(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException shouldNotHandleMe) {

		}
	}


	public static void main(String[] args) {

		String[] details = getConsumerAndSecretKey("prodUS", "enterpriseproduction21jan@gmail.com");
		System.out.println("Consumer: "+details[0]+"Secret: "+ details[1]);
	}

}
