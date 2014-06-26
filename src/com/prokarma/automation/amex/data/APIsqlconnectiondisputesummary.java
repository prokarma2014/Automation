package com.prokarma.automation.amex.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.prokarma.automation.amex.arraylistobject.Disputesummaryarraylistobject;

public class APIsqlconnectiondisputesummary extends APIAmexdata{
	
	private Connection sqlConnection = null;
    private Statement stateMent = null;
    private static Logger logger=Logger.getLogger(APIsqlconnectiondisputesummary.class);
    public APIsqlconnectiondisputedetails sqlconnectionDisputedetails = new APIsqlconnectiondisputedetails();
	public APIsqlconnectiondisputesummary sqlconnectionDisputesummary = new APIsqlconnectiondisputesummary();
	public APIsqlconnectiondisputesummarylist sqlconnectionDisputesummarylist = new APIsqlconnectiondisputesummarylist();
    private ResultSet resultSet = null;
    private Disputesummaryarraylistobject disputeSummaryarraylistobject = new Disputesummaryarraylistobject();
    private ArrayList<Disputesummaryarraylistobject> DisputeSummaryarraylistobjectlist = new ArrayList<Disputesummaryarraylistobject>();
    
	
	/**
	 * @param Establish
	 *            the Sql connection
	 * @param Create
	 *            a statement using select command
	 * @param All
	 *            the values are stored in Resultset
	 * @param Converting
	 *            the resultset data into arraylist object
	 * @exception SQLException
	 *                ,ClassNotFoundException
	 */
	public ArrayList<Disputesummaryarraylistobject> connectSqlconnectiondisputesummary()
			throws SQLException, ClassNotFoundException {

		logger.info("connectSqlconnectiondisputesummary - Starts");		
		String connectionUrl = "jdbc:sqlserver://localhost:1433;"
				+ "databaseName=AdventureWorks;user=UserName;password=*****";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			sqlConnection = DriverManager.getConnection(connectionUrl);
			logger.info("Connection is Established");
			String sqlStatement = "Enter the sql statement";
			stateMent = sqlConnection.createStatement();
			resultSet = stateMent.executeQuery(sqlStatement);
			while (resultSet.next()) {
				disputeSummaryarraylistobject.setchargeback(resultSet
						.getInt("chargeback"));
				disputeSummaryarraylistobject.setactiveDisputes(resultSet
						.getInt("activeDisputes"));
				disputeSummaryarraylistobject.setrecentUpdates(resultSet
						.getInt("recentUpdates"));
				disputeSummaryarraylistobject.setrecentlyClosed(resultSet
						.getInt("recentlyClosed"));
				disputeSummaryarraylistobject.setresponseRequired(resultSet
						.getInt("responseRequired"));
				disputeSummaryarraylistobject.inProgress(resultSet
						.getInt("inProgress"));
				disputeSummaryarraylistobject.setclosedInFavor(resultSet
						.getInt("closedInFavor"));
				disputeSummaryarraylistobject.setclosedChargebacks(resultSet
						.getInt("closedChargebacks"));

				DisputeSummaryarraylistobjectlist
						.add(disputeSummaryarraylistobject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("connectSqlconnectiondisputesummary - Starts");
		return DisputeSummaryarraylistobjectlist;
	}
    
    /**
     * @param Used Gson to Convert the list of response
     * @param list
     * @throws IOException
     */
    public void compareDisputesummary(String list) throws IOException{
    
		logger.info("compareDisputesummary - Starts");
		String jsonData = list.toString();
		Gson gson = new Gson();
		Disputesummaryarraylistobject webserviceObject = gson.fromJson(
				jsonData, Disputesummaryarraylistobject.class);
		try {

			if (webserviceObject.equals(DisputeSummaryarraylistobjectlist)) {
				logger.info("DisputeSummaryList values are equal");
			} else {
				logger.debug("DisputeSummaryList values are not equal");
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		logger.info("compareDisputesummary - Ends");
	}
    
}

