package com.prokarma.automation.amex.testng;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.prokarma.automation.amex.data.APIAmexdata;
import com.prokarma.automation.amex.data.APIdisputedetails;
import com.prokarma.automation.amex.data.APIsqlconnectiondisputedetails;
import com.prokarma.automation.amex.data.APIsqlconnectiondisputesummary;
import com.prokarma.automation.amex.data.APIsqlconnectiondisputesummarylist;

public class APIautomation 
{
	public WebDriver driver;
	public String WebserviceURLdisputesummary,WebserviceURLdisputesummarylist;
	public APIAmexdata APIamexdata = new APIAmexdata();
	public APIdisputedetails APIdisputedetails = new APIdisputedetails();
	public APIsqlconnectiondisputedetails sqlconnectionDisputedetails = new APIsqlconnectiondisputedetails();
	public APIsqlconnectiondisputesummary sqlconnectionDisputesummary = new APIsqlconnectiondisputesummary();
	public APIsqlconnectiondisputesummarylist sqlconnectionDisputesummarylist = new APIsqlconnectiondisputesummarylist();
	private static Logger logger=Logger.getLogger(APIautomation.class);
	public Properties prop=new Properties();	
	
	/**
	 * @param Read the URLS in the resource.properties
	 * @throws IOException
	 */
	public void readResourcesproperty() throws IOException {

		logger.info("readProperty() : BEGINS ");
		File file = new File(System.getProperty("user.dir")
				+ "\\Data\\Resource.properties");
		FileReader fileReader = new FileReader(file);
		prop.load(fileReader);
		WebserviceURLdisputesummary = prop
				.getProperty("WebserviceURLdisputesummary");
		WebserviceURLdisputesummarylist = prop
				.getProperty("WebserviceURLdisputesummarylist");		
		logger.info("readProperty() : ENDS ");
	}
	/**
	 * It will lanuch the HtmlunitDriver
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IOException
	 */

	@BeforeTest
	public void setup() throws InterruptedException, ClassNotFoundException,
			SQLException {

		logger.info("Lauching the HTMLunit driver");
		driver = new HtmlUnitDriver();
		this.sqlconnectionDisputesummary.connectSqlconnectiondisputesummary();
		this.sqlconnectionDisputesummarylist
				.connectSqlconnectiondisputesummarylist();
		this.sqlconnectionDisputedetails.connectSqlconnectiondisputedetails();
	}
	
	/**
	 * It will quit all the Broswer 
	 * @throws IOException
	 */

	@AfterTest
	public void quit() throws InterruptedException {

		logger.info("Closing the HTMLunit Driver");
		driver.quit();
	}
	
	/**
	 * To Execute the Testcases
	 * @throws IOException
	 */

	@Test
	public void APIautomation() throws Exception {
		
		logger.info("APIautomation Testcases -  Begins");
		
		logger.info("DisputeSummary - Begins");
		this.APIamexdata.readResourcesproperty();
		this.APIamexdata.getAPICredentials();
		this.APIamexdata.jsonRequestdisputesummary();
		this.APIamexdata.connectToREST(WebserviceURLdisputesummary);
		this.APIamexdata.readfromJsonRequesttoHTTPpostsummary();
		this.APIamexdata.postandGetResponsecode();
		this.APIamexdata.readResponseoutput();
		this.APIamexdata.convertResponseoutputtojsonobject();
		this.APIamexdata.compareDisputesummary();
		logger.info("DisputeSummary - Ends");
		
		logger.info("DisputeSummaryList - Begins");
		this.APIamexdata.readResourcesproperty();		
		this.APIamexdata.jsonRequestdisputesummary();
		this.APIamexdata.connectToREST(WebserviceURLdisputesummarylist);
		this.APIamexdata.readfromJsonRequesttoHTTPpostsummarylist();
		this.APIamexdata.postandGetResponsecode();
		this.APIamexdata.readResponseoutput();
		this.APIamexdata.convertResponseoutputtojsonobject();
		this.APIamexdata.compareDisputesummarylist();
		logger.info("DisputeSummaryList - Ends");
		
		logger.info("DisputeDetails - Begins");
		this.APIdisputedetails.readProperty();		
		this.APIdisputedetails.connectToREST();
		this.APIdisputedetails.readFromREST();
		this.APIdisputedetails.convertToJSON();		
		this.APIdisputedetails.compareDisputelist();
		logger.info("DisputeDetails - Ends");		
		
		logger.info("APIautomation Testcases -  Ends");

	}
}
