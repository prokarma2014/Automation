package com.prokarma.automation.amex.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class APIdisputedetails{
	
	public static String webserivceUrllang;	
	private static Logger logger=Logger.getLogger(APIdisputedetails.class);
	public APIsqlconnectiondisputedetails sqlconnectionDisputedetails = new APIsqlconnectiondisputedetails();
	private HttpURLConnection httpurlConnection;
	private StringBuffer response = new StringBuffer(); 
	private List <JSONObject>list = new ArrayList<JSONObject>();
    public Properties prop=new Properties();
    
	/**
	 * To Read a a URL and Column Name from the Property
	 * @throws IOException
	 */

	public void readProperty() throws IOException {

		logger.info("readProperty() : BEGINS ");
		File file = new File(System.getProperty("user.dir")
				+ "\\Data\\Resource.properties");
		FileReader fileReader = new FileReader(file);
		prop.load(fileReader);
		webserivceUrllang = prop.getProperty("WebserviceURLdisputesummarylang");
		logger.info("readProperty() : ENDS ");
	}

	/**
	 * To Connect a URL httpurlConnection and establish the connection
	 * @throws IOException
	 */
	
	public void connectToREST() throws IOException {

		logger.info("connectToREST() : BEGINS");
		try {
			URL objUrl = new URL(webserivceUrllang);
			httpurlConnection = (HttpURLConnection) objUrl.openConnection();
			httpurlConnection.setRequestMethod("GET");
			int responseCode = httpurlConnection.getResponseCode();
			logger.info("ResponseCode" + responseCode);
		} catch (Exception exception) {
			logger.error("Connection is not established");
		}
		logger.info("connectToREST() : ENDS ");
	}
	
	/**
	 * Once connection is established and it will read the data from the webservices
	 * @throws IOException
	 */
	
	public void readFromREST() throws IOException {

		logger.info("readFromREST() : BEGINS ");
		BufferedReader bufferedreader = new BufferedReader(
				new InputStreamReader(httpurlConnection.getInputStream()));
		String inputLine;
		while ((inputLine = bufferedreader.readLine()) != null) {
			response.append(inputLine);
		}
		bufferedreader.close();
		logger.info("readFromREST() : ENDS ");
	}

	/**
	 *After reading the data from the webserivce and it will convert the data into json object
	 * @throws IOException
	 */
	
	public void convertToJSON() throws IOException, ParseException {

		logger.info("convertToJSON() : BEGINs ");
		JSONParser jsonParser = new JSONParser();
		Object object = jsonParser.parse(response.toString());
		JSONArray jsArray = (JSONArray) object;
		Iterator<JSONObject> iterator = jsArray.iterator();
		while (iterator.hasNext()) {
			JSONObject jsonObj = iterator.next();
			list.add(jsonObj);
		}

		logger.info(list.toString());
		logger.info("convertToJSON() : ENDS ");
	}
	
	/**
	 * @param Calling the comparedisputesummaru method from the APIsqlconnectiondisputedetails
	 * @throws IOException,ParseException
	 * 
	 */
	public void compareDisputelist() throws IOException, ParseException {

		this.sqlconnectionDisputedetails.compareDisputelist(list.toString());
	}
}
