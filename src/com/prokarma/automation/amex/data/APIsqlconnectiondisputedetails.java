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
import com.prokarma.automation.amex.arraylistobject.Disputedetails;
import com.prokarma.automation.amex.arraylistobject.Disputesummaryarraylistobject;

public class APIsqlconnectiondisputedetails {

	private Connection sqlConnection = null;
    private Statement stateMent = null;
    private static Logger logger=Logger.getLogger(APIsqlconnectiondisputesummary.class);
    private ResultSet resultSet = null;
    private Disputedetails disputeDetailsresult = new Disputedetails();
    private ArrayList<Disputedetails> disputedetails = new ArrayList<Disputedetails>();
	
	/**
	 * @param Establish the Sql connection
	 * @param Create a statement using select command
	 * @param All the values are stored in Resultset
	 * @param Converting the resultset data into arraylist object
	 * @exception SQLException,ClassNotFoundException
	 */
	public ArrayList<Disputedetails> connectSqlconnectiondisputedetails()
			throws SQLException, ClassNotFoundException {
		
		logger.info("connectSqlconnectiondisputedetails - Starts");		
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
				disputeDetailsresult.setadjustmentNbr(resultSet
						.getInt("adjustmentNbr"));
				disputeDetailsresult.setairTktNbr(resultSet
						.getString("airTktNbr"));
				disputeDetailsresult
						.setcancelDt(resultSet.getDate("cancelDt"));
				disputeDetailsresult.setcancelZone(resultSet
						.getString("cancelZone"));
				disputeDetailsresult.setcancellationNbr(resultSet
						.getInt("cancellationNbr"));
				disputeDetailsresult.setcarRentalAgreementNbr(resultSet
						.getInt("carRentalAgreementNbr"));
				disputeDetailsresult.setcardDepositInd(resultSet
						.getInt("closedInFavor"));
				disputeDetailsresult.setcardMemberNm(resultSet
						.getString("cardMemberNm"));
				disputeDetailsresult.setcardNbr(resultSet.getInt("cardNbr"));
				disputeDetailsresult.setchrgAmt(resultSet.getInt("chrgAmt"));
				disputeDetailsresult.setchrgAmtCurrencyCd(resultSet
						.getString("setchrgAmtCurrencyCd"));
				disputeDetailsresult.setchrgBkAmt(resultSet
						.getInt("chrgBkAmt"));
				disputeDetailsresult.setchrgBkDt(resultSet
						.getString("chrgBkDt"));
				disputeDetailsresult.setchrgBkId(resultSet.getInt("chrgBkId"));
				disputeDetailsresult.setchrgDt(resultSet.getDate("chrgDt"));
				disputeDetailsresult.setchrgRefNbr(resultSet
						.getInt("chrgRefNbr"));
				disputeDetailsresult.setcreditRecvd(resultSet
						.getInt("creditRecvd"));
				disputeDetailsresult.setdaysPending(resultSet
						.getInt("daysPending"));
				disputeDetailsresult.setdisputeAmt(resultSet
						.getInt("disputeAmt"));
				disputeDetailsresult.setdisputeCategoryCd(resultSet
						.getString("disputeCategoryCd"));
				disputeDetailsresult.setdisputeCategoryDesc(resultSet
						.getString("disputeCategoryDesc"));
				disputeDetailsresult.setdisputeCurrencyCd(resultSet
						.getString("disputeCurrencyCd"));
				disputeDetailsresult.setdisputeId(resultSet
						.getInt("disputeId"));
				disputeDetailsresult.setdisputeNbr(resultSet
						.getInt("disputeNbr"));
				disputeDetailsresult.setdisputeRsnCdDesc(resultSet
						.getString("disputeRsnCdDesc"));
				disputeDetailsresult.setdisputeStageCd(resultSet
						.getString("disputeStageCd"));
				disputeDetailsresult.setdisputeStatusCd(resultSet
						.getString("disputeStatusCd"));
				disputeDetailsresult.setdisputeType(resultSet
						.getString("disputeType"));
				disputeDetailsresult.setdisputeUpdId(resultSet
						.getInt("disputeUpdId"));
				disputeDetailsresult.setdisputeUpdType(resultSet
						.getString("disputeUpdType"));
				disputeDetailsresult
						.setflagged(resultSet.getString("flagged"));
				disputeDetailsresult.sethidden(resultSet.getString("hidden"));
				disputeDetailsresult.setindusRefNbr(resultSet
						.getInt("indusRefNbr"));
				disputeDetailsresult.setinqId(resultSet.getInt("inqId"));
				disputeDetailsresult.setlocationId(resultSet
						.getInt("locationId"));
				disputeDetailsresult.setlocked(resultSet.getString("locked"));
				disputeDetailsresult.setlockedByMe(resultSet
						.getString("lockedByMe"));
				disputeDetailsresult.setmerchandiseReturned(resultSet
						.getString("merchandiseReturned"));
				disputeDetailsresult.setmerchandiseType(resultSet
						.getString("merchandiseType"));
				disputeDetailsresult.setmerchantRespCd(resultSet
						.getString("merchantRespCd"));
				disputeDetailsresult.setoriginalCardNbr(resultSet
						.getInt("originalCardNbr"));
				disputeDetailsresult.setrecvdDt(resultSet.getDate("recvdDt"));
				disputeDetailsresult.setreplyByDt(resultSet
						.getInt("replyByDt"));
				disputeDetailsresult.setreservationCanceledDt(resultSet
						.getDate("reservationCanceledDt"));
				disputeDetailsresult.setreservationCanceledInd(resultSet
						.getString("reservationCanceledInd"));
				disputeDetailsresult.setreservationLocation(resultSet
						.getString("reservationLocation"));
				disputeDetailsresult.setreservationMadeDt(resultSet
						.getDate("reservationMadeDt"));
				disputeDetailsresult.setreservationMadeFor(resultSet
						.getString("reservationMadeFor"));
				disputeDetailsresult.setreservationNbr(resultSet
						.getInt("reservationNbr"));
				disputeDetailsresult.setresponseInd(resultSet
						.getString("responseInd"));
				disputeDetailsresult.setreturnedDt(resultSet
						.getDate("returnedDt"));
				disputeDetailsresult.setreturnedHow(resultSet
						.getString("returnedHow"));
				disputeDetailsresult.setreturnedNm(resultSet
						.getInt("returnedNm"));
				disputeDetailsresult.setreturnedRsn(resultSet
						.getInt("returnedRsn"));
				disputeDetailsresult.setrocInvNbr(resultSet
						.getInt("rocInvNbr"));
				disputeDetailsresult.setrsnCd(resultSet.getInt("rsnCd"));
				disputeDetailsresult.setseNbr(resultSet.getInt("seNbr"));
				disputeDetailsresult.setsettleAmt(resultSet
						.getInt("settleAmt"));
				disputeDetailsresult.setsettleCurrencyCd(resultSet
						.getString("settleCurrencyCd"));
				disputeDetailsresult.setsettleDt(resultSet.getInt("settleDt"));
				disputeDetailsresult.setsubmissionLocationId(resultSet
						.getInt("submissionLocationId"));
				disputeDetailsresult.settextNote(resultSet
						.getString("textNote"));
				disputeDetailsresult.settrackingNbr(resultSet
						.getString("trackingNbr"));
				disputeDetailsresult.settransactionDt(resultSet
						.getDate("transactionDt"));
				disputeDetailsresult.setupdDt(resultSet.getDate("updDt"));
				disputedetails.add(disputeDetailsresult);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		logger.info("connectSqlconnectiondisputedetails - Ends");
		return disputedetails;		
	}
    
    /**
     * @param Used Gson to Convert the list of response
     * @param list
     * @throws IOException
     */
	public void compareDisputelist(String list) throws IOException {

		logger.info("compareDisputesummartlist - Starts");
		String jsonData = list.toString();
		Gson gson = new Gson();
		Disputedetails webserviceObject = gson.fromJson(jsonData,
				Disputedetails.class);
		try {
			if (webserviceObject.equals(disputedetails)) {
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
