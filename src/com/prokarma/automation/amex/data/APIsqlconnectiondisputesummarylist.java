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
import com.prokarma.automation.amex.arraylistobject.Disputesummarylistarraylistobject;

public class APIsqlconnectiondisputesummarylist {
	
	private Connection sqlConnection = null;
    private Statement stateMent = null;
    private static Logger logger=Logger.getLogger(APIsqlconnectiondisputesummarylist.class);
    private ResultSet resultSet = null;
    private  Disputesummarylistarraylistobject disputesummarylistarraylistobject = new Disputesummarylistarraylistobject();
    private ArrayList<Disputesummarylistarraylistobject> Disputesummarylistarraylistobject = new ArrayList<Disputesummarylistarraylistobject>();
	
	/**
	 * @param Establish the Sql connection
	 * @param Create a statement using select command
	 * @param All the values are stored in Resultset
	 * @param Converting the resultset data into arraylist object
	 * @exception SQLException,ClassNotFoundException
	 */
	public ArrayList<Disputesummarylistarraylistobject> connectSqlconnectiondisputesummarylist()
			throws SQLException, ClassNotFoundException {

		logger.info("connectSqlconnectiondisputesummarylist - Starts");		
		String connectionUrl = "jdbc:sqlserver://localhost:1433;"
				+ "databaseName=AdventureWorks;user=UserName;password=*****";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			sqlConnection = DriverManager.getConnection(connectionUrl);
			logger.info("Connection is established");
			String sqlStatement = "Enter the sql statement";
			stateMent = sqlConnection.createStatement();
			resultSet = stateMent.executeQuery(sqlStatement);

			while (resultSet.next()) {
				disputesummarylistarraylistobject.settotalDisputeAmt(resultSet
						.getInt("totalDisputeAmt"));
				disputesummarylistarraylistobject.settotalCt(resultSet
						.getInt("totalCt"));
				disputesummarylistarraylistobject.setmaxReturnCt(resultSet
						.getInt("maxReturnCt"));
				disputesummarylistarraylistobject.setmaxIndicatorCt(resultSet
						.getInt("maxIndicatorCt"));
				disputesummarylistarraylistobject.setlowBound(resultSet
						.getInt("lowBound"));
				disputesummarylistarraylistobject.setupbound(resultSet
						.getInt("upbound"));
				disputesummarylistarraylistobject.setdisputeNbr(resultSet
						.getInt("disputeNbr"));
				disputesummarylistarraylistobject.setseNbr(resultSet
						.getInt("seNbr"));
				disputesummarylistarraylistobject
						.setdisputeCategoryDesc(resultSet
								.getString("disputeCategoryDesc"));
				disputesummarylistarraylistobject.setadjustmentNbr(resultSet
						.getInt("adjustmentNbr"));
				disputesummarylistarraylistobject.setdisputeStageCd(resultSet
						.getString("disputeStageCd"));
				disputesummarylistarraylistobject.setinqId(resultSet
						.getInt("inqId"));
				disputesummarylistarraylistobject.sethidden(resultSet
						.getString("hidden"));
				disputesummarylistarraylistobject.setflagged(resultSet
						.getString("flagged"));
				disputesummarylistarraylistobject.setlocked(resultSet
						.getString("locked"));
				disputesummarylistarraylistobject.setlockedByMe(resultSet
						.getString("lockedByMe"));
				disputesummarylistarraylistobject.setchrgBkCurrencyCd(resultSet
						.getString("chrgBkCurrencyCd"));
				disputesummarylistarraylistobject.setrsnCd(resultSet
						.getString("rsnCd"));
				disputesummarylistarraylistobject.setchrgAmt(resultSet
						.getInt("chrgAmt"));
				disputesummarylistarraylistobject
						.setdisputeCurrencyCd(resultSet
								.getString("disputeCurrencyCd"));
				disputesummarylistarraylistobject.setdisputeAmt(resultSet
						.getInt("disputeAmt"));
				disputesummarylistarraylistobject.setcardNbr(resultSet
						.getInt("cardNbr"));
				disputesummarylistarraylistobject.setchrgDt(resultSet
						.getDate("chrgDt"));
				disputesummarylistarraylistobject.setpayeeSENbr(resultSet
						.getInt("payeeSENbr"));
				disputesummarylistarraylistobject.setsettleAmt(resultSet
						.getInt("settleAmt"));
				disputesummarylistarraylistobject.setsettleCurrencyCd(resultSet
						.getString("settleCurrencyCd"));
				disputesummarylistarraylistobject.setsettleDt(resultSet
						.getDate("settleDt"));
				disputesummarylistarraylistobject.setrocInvNbr(resultSet
						.getInt("rocInvNbr"));
				disputesummarylistarraylistobject.setlocationId(resultSet
						.getInt("locationId"));
				disputesummarylistarraylistobject
						.setsubmissionLocationId(resultSet
								.getInt("submissionLocationId"));
				disputesummarylistarraylistobject.setairTktNbr(resultSet
						.getInt("airTktNbr"));
				disputesummarylistarraylistobject
						.setcarRentalAgreementNbr(resultSet
								.getInt("carRentalAgreementNbr"));
				disputesummarylistarraylistobject.setmerchantRespCd(resultSet
						.getInt("merchantRespCd"));
				disputesummarylistarraylistobject.setchrgBkDt(resultSet
						.getDate("chrgBkDt"));
				disputesummarylistarraylistobject.setcardMemberNm(resultSet
						.getString("cardMemberNm"));
				disputesummarylistarraylistobject.setoriginalCardNbr(resultSet
						.getInt("originalCardNbr"));
				disputesummarylistarraylistobject.setdisputeRsnCdDesc(resultSet
						.getString("disputeRsnCdDesc"));
				disputesummarylistarraylistobject.setrecvdDt(resultSet
						.getDate("recvdDt"));

				Disputesummarylistarraylistobject
						.add(disputesummarylistarraylistobject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("connectSqlconnectiondisputesummarylist - Ends");
		return Disputesummarylistarraylistobject;
	}
    
    /**
     * @param Used Gson to Convert the list of response
     * @param list
     * @throws IOException
     */
	public void compareDisputesummarylist(String list) throws IOException {

		logger.info("compareDisputesummartlist - Starts");
		String jsonData = list.toString();
		Gson gson = new Gson();
		Disputesummarylistarraylistobject webserviceObject = gson.fromJson(
				jsonData, Disputesummarylistarraylistobject.class);
		try {

			if (webserviceObject.equals(Disputesummarylistarraylistobject)) {
				logger.info("DisputeSummaryList values are equal");
			} else {
				logger.debug("DisputeSummaryList values are not equal");
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		logger.info("compareDisputesummartlist - Ends");
	}
    
}

