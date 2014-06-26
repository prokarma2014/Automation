package com.prokarma.automation.amex.data;

import com.pkrm.assertservice.CompareAssert;
import com.pkrm.baseexception.PKRMException;

public class APIerrorcode 
{
	PKRMException expectedException1 = new 
			PKRMException("1001", "NoSEPresent", "EITHER GUID OR SE NO. MUST BE PROVIDED.", "400");
	
	PKRMException expectedException2 = new 
			PKRMException("1002", "InvalidGuid", "INVALID GUID.", "400");
	
	PKRMException expectedException3 = new 
			PKRMException("1024", "InvalidResponseType", "INVALID RESPONSE TYPE.", "400");
	
	PKRMException expectedException4 = new 
			PKRMException("1006", "EmptyListInput", "EMPTY LIST OR INVALID LIST ELEMENTS.", "400");
	
	PKRMException expectedException5 = new 
			PKRMException("1031", "InvalidInputData", "Invalid Input Data.", "400");
	
	PKRMException expectedException6 = new 
			PKRMException("1032", "GenericServiceError", "Error has been encountered.", "400");	
}
