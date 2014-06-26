package com.prokarma.automation.amex.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.prokarma.automation.amex.data.APIsqlconnectiondisputesummary;
import com.pkrm.assertservice.CompareAssert;

/*import oracle.search.admin.api.ws.client.AdminPortType;
import oracle.search.admin.api.ws.client.AdminService;
import oracle.search.admin.api.ws.client.Credentials;*/

@SuppressWarnings("deprecation")
public class APIAmexdata {
	
	public String startDate,endDate,seNbrList,sort,columnName,sortOrder,lowBound,upBound,langCd,stage,includeIndicators,webserviceUrl,userName,passWord,jsonValues;
	private static Logger logger=Logger.getLogger(APIAmexdata.class);	
	private StringBuffer response = new StringBuffer(); 
	public JSONObject jsonRequest = new JSONObject();
	private InputStream contentStream = null;
	public List <JSONObject>list = new ArrayList<JSONObject>();	
	public HttpPost httpPost;
	private HttpClient httpClient;
	public Properties prop=new Properties();		
	public APIsqlconnectiondisputesummary sqlconnectionDisputesummary = new APIsqlconnectiondisputesummary();
	public APIsqlconnectiondisputesummarylist sqlconnectionDisputesummarylist = new APIsqlconnectiondisputesummarylist();
	private APIerrorcode errorCode = new APIerrorcode();
	
	/**
	 * To Read a a URL and Column Name from the Property
	 * @Read the Json Responsee of StartDate,Endate and Serial No
	 * @Read login Credentials of webserivceurl, loginname and password
	 * @throws IOException
	 */

	public void readResourcesproperty() throws IOException {

		logger.info("readProperty() : BEGINS ");
		File file = new File(System.getProperty("user.dir")
				+ "\\Data\\Resource.properties");
		FileReader fileReader = new FileReader(file);
		prop.load(fileReader);
		startDate = prop.getProperty("startDt");
		endDate = prop.getProperty("endDt");
		seNbrList = prop.getProperty("seNbrList");
		logger.info("readProperty() : ENDS ");
		webserviceUrl = prop.getProperty("WebserivceURL");
		userName = prop.getProperty("username");
		passWord = prop.getProperty("password");
		columnName = prop.getProperty("columnName");
		sortOrder = prop.getProperty("sortOrder");
		lowBound = prop.getProperty("lowBound");
		upBound = prop.getProperty("upBound");
		langCd = prop.getProperty("langCd");
		stage = prop.getProperty("stage");
		includeIndicators = prop.getProperty("includeIndicators");
	}
	
	/**
	 * To Create a  jsonRequestdisputesummarylist
	 * @param Creating the Json request
	 * @throws IOException,JSONException
	 */
	
	@SuppressWarnings("unchecked")
	public String jsonRequestdisputesummarylist() throws IOException,
			JSONException {

		logger.info("JSON Request() : Starts ");
		JSONObject sortObject = new JSONObject();
		JSONObject capabilityJObject = new JSONObject();
		jsonRequest.put("startDt", startDate);
		jsonRequest.put("endDt", endDate);
		jsonRequest.put("seNbrList", seNbrList);
		sortObject.put("sort", capabilityJObject);
		capabilityJObject.put("columnName", columnName);
		capabilityJObject.put("sortOrder", sortOrder);
		jsonRequest.put("lowBound", lowBound);
		jsonRequest.put("upBound", upBound);
		jsonRequest.put("langCd", langCd);
		jsonRequest.put("stage", stage);
		jsonRequest.put("includeIndicators", includeIndicators);
		logger.info("JSON Request() : ENDS ");
		return jsonValues;
	}
	
	/**
	 * To Create a  jsonRequestdisputesummary
	 * @param Creating the Json request
	 * @return 
	 * @throws IOException,JSONException
	 */

	@SuppressWarnings("unchecked")
	public String jsonRequestdisputesummary() throws IOException, JSONException {

		logger.info("JSON Request() : Starts ");
		jsonRequest.put("startDt", startDate);
		jsonRequest.put("endDt", endDate);
		jsonRequest.put("seNbrList", seNbrList);
		logger.info("JSON Request() : ENDS ");
		return jsonValues;
	}
	
	/**
	 * @param Using
	 *            defaultHttpclient we are sending the Credentials to webservice
	 * @param webserviceUrl
	 *            ,username and password values are read from the
	 *            resource.priperties
	 * @throws IOException
	 */
	public void getAPICredentials() throws IOException {

		try {
			logger.info("webserviceCredentials - get started");
			String host = "";
			DefaultHttpClient defaultHttpclient = new DefaultHttpClient();
			defaultHttpclient.getCredentialsProvider().setCredentials(
					new AuthScope(host, AuthScope.ANY_PORT),
					new UsernamePasswordCredentials(userName, passWord));
			HttpGet dhttpGet = new HttpGet(webserviceUrl);
			logger.info("executing request " + dhttpGet.getRequestLine());
			HttpResponse httpResponse = defaultHttpclient.execute(dhttpGet);
			logger.info("Successfully Login");
		} catch (Exception loginException) {
			logger.error("Invalid username and password or wrong username and password");
		}
	}
	
	/**
	 * To Connect the Webservice using HTTPclient
	 * 
	 * @param Calling
	 *            the JsonRequest methods for posting the values
	 * @exception connection
	 *                will fails
	 * @throws IOException
	 */
	public void connectToREST(String webserviceURl) throws IOException {

		logger.info("connectToREST() : BEGINS");
		try {
			URL webserviceUrl = new URL(webserviceURl);			
			httpClient = new DefaultHttpClient();
			httpPost = new HttpPost(webserviceURl);
		} catch (Exception exception) {
			logger.error("Connection is not established");
		}
		logger.info("connectToREST() : ENDS ");
	}
	
	/**
	 *@categoryRead the value from the jsonRequestdisputesummary of Jsonrequest
	 *@throws IO exeception
	 * 
	 */
	public void readfromJsonRequesttoHTTPpostsummary() throws IOException {

		logger.info("readvaluesFromjsonrequest() : BEGINS");
		try {			
			jsonValues = jsonRequestdisputesummary();
			httpPost.setHeader("Content-Type", "application/json");
			httpPost.setHeader("Accept", "application/json");
			StringEntity stringEntity = new StringEntity(jsonValues);
			stringEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
					"application/json"));
			httpPost.setEntity(stringEntity);
			httpPost.setEntity(new StringEntity(jsonRequestdisputesummary(),
					HTTP.UTF_8));
			for (int i = 0; i < httpPost.getAllHeaders().length; i++) {
				httpPost.getAllHeaders()[i].getValue();
			}
		} catch (Exception exception) {
			logger.error("JsonRequest not to able to read the values");
		}
		logger.info("readvaluesFromjsonrequest() : ENDS ");
	}	
	
	/**
	 *@category Read the value from the jsonRequestdisputesummarylist of Jsonrequest
	 *@throws IO exeception
	 * 
	 */
	public void readfromJsonRequesttoHTTPpostsummarylist() throws IOException {

		logger.info("readvaluesFromjsonrequest() : BEGINS");
		try {
			jsonValues = jsonRequestdisputesummarylist();
			httpPost.setHeader("Content-Type", "application/json");
			httpPost.setHeader("Accept", "application/json");
			StringEntity stringEntity = new StringEntity(jsonValues);
			stringEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
					"application/json"));
			httpPost.setEntity(stringEntity);
			httpPost.setEntity(new StringEntity(jsonRequestdisputesummarylist(),
					HTTP.UTF_8));
			for (int i = 0; i < httpPost.getAllHeaders().length; i++) {
				httpPost.getAllHeaders()[i].getValue();
			}
		} catch (Exception exception) {
			logger.error("JsonRequest not to able to read the values");
		}
		logger.info("readvaluesFromjsonrequest() : ENDS ");
	}		
	
	
	/**
	 * @param Once values are getting posted get responsecode
	 * @return responsecode
	 * @exception if error code is not matched its fails the testcases
	 * @throws IOException
	 */
	public void postandGetResponsecode() throws IOException {
		
		try{
		HttpResponse httpResponse = httpClient.execute(httpPost);
		Header[] headers = httpResponse.getAllHeaders();
		HttpEntity httpEntity = httpResponse.getEntity();
		contentStream = httpEntity.getContent();
		int responsecode = httpResponse.getStatusLine().getStatusCode();
		if (responsecode == 200) {
			logger.info("Status-Ok");
		} else if (CompareAssert.isValidAssert(errorCode.expectedException1)
				|| CompareAssert.isValidAssert(errorCode.expectedException2)
				|| CompareAssert.isValidAssert(errorCode.expectedException3)
				|| CompareAssert.isValidAssert(errorCode.expectedException4)
				|| CompareAssert.isValidAssert(errorCode.expectedException5)
				|| CompareAssert.isValidAssert(errorCode.expectedException6)) {
			logger.error("Error code are not matched");
		}
		else{
			logger.error("Someother error codes are displayed");
		}
		}
		catch (Exception exception){
			exception.printStackTrace();
		}
	}
	
	/**
	 * @param It will store in the response
	 * @throws IOException 
	 */
	public void readResponseoutput() throws IOException {

		logger.info("readResponseoutput() : BEGINS ");
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(contentStream, "utf-8"), 8);
		String inputLine;
		while ((inputLine = bufferedReader.readLine()) != null) {
			response.append(inputLine);
		}
		bufferedReader.close();
		logger.info("readResponseoutput() : ENDS ");
	}

	/**
	 * @param It will convert the responseoutput to the JsonObject and it will stored in the Json array
	 * @throws IOException,ParseException
	 * 
	 */
	public void convertResponseoutputtojsonobject() throws IOException,
			ParseException {

		logger.info("convertResponseoutputtojsonobject() : BEGINs ");
		JSONParser jsonParser = new JSONParser();
		Object object = jsonParser.parse(response.toString());
		JSONArray jsArray = (JSONArray) object;
		Iterator<JSONObject> iterator = jsArray.iterator();
		while (iterator.hasNext()) {
			JSONObject jsonObj = iterator.next();
			list.add(jsonObj);
		}
		logger.info(list.toString());
		logger.info("convertResponseoutputtojsonobject() : ENDS ");
	}

	/**
	 * @param Calling the comparedisputesummary method from the APIsqlconnectiondisputesummary
	 * @throws IOException,ParseException
	 * 
	 */
	public void compareDisputesummary() throws IOException, ParseException {

		this.sqlconnectionDisputesummary.compareDisputesummary(list.toString());
	}
	
	/**
	 * @param Calling the compareDisputesummarylist method from the APIsqlconnectiondisputesummarylist
	 * @throws IOException,ParseException
	 * 
	 */
	
	public void compareDisputesummarylist() throws IOException, ParseException {

		this.sqlconnectionDisputesummarylist.compareDisputesummarylist(list
				.toString());
	}

}
