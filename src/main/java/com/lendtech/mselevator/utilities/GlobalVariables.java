package com.lendtech.mselevator.utilities;

public class GlobalVariables {

    /*================================================
     * REQUEST HEADERS
     * ==============================================*/
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String X_CORRELATION_CONVERSATION_ID = "X-Correlation-ConversationID";
    public static final String X_SOURCE_SYSTEM = "X-Source-System";
    public static final String AUTHORIZATION = "Authorization";

    /*================================================
     * SYSTEM INTERACTIONS
     * ==============================================*/
    public static final String TRANSACTION_TYPE = "MTA-Process";
    public static final String HEADERS = "Headers";
    public static final String SOURCE = "APi call";
    public static final String PROCESS_HEADER = "Header Checks";
    public static final String VAL = "Validation";
    public static final String TARGET_SYSTEM_DB = "DATABASE";

    /*================================================
     * CODING FORMATS
     * ==============================================*/
    public static final String REQUEST_DATE_TIME_FORMAT = "yyyyMMddHHmmss";
    public static final String LOGGER_FORMAT = "TransactionID={} | TransactionType={} | Process={} | ProcessDuration={}" +
            " | ServiceId={} | SourceSystem={} | TargetSystem={}  | Response={} | ResponseCode={}  | ResponseMsg={} |" +
            " ErrorDescription={} | RequestPayload={} | ResponsePayload={} | RequestHeaders={}";

    /*================================================
     * RESPONSE CODES
     * ==============================================*/
    public static final int RESPONSE_CODE_0 = 0;
    public static final int RESPONSE_CODE_200 = 200;
    public static final int RESPONSE_CODE_4000 = 4000;
    public static final int RESPONSE_CODE_400 = 400;
    public static final int RESPONSE_CODE_500 = 500;

    /*================================================
     * RESPONSE MESSAGES
     * ==============================================*/
    public static final String RESPONSE_SERVICE_UNREACHABLE = "The service is currently unreachable. Please try later.";
    public static final String RESPONSE_FAILED = "Operation Failed";
    public static final String RESPONSE_INVALID_HEADERS = "Invalid Header Request";
    public static final String RESPONSE_INVALID_REQUEST = "Invalid Request. Please try again.";
    public static final String RESPONSE_SUCCESS = "Operation Success";
    public static final String RESPONSE_SUCCESSFUL = "Your request has been processed successfully";

    public static final String RECORD_NOT_FOUND = "Record Not Found";
    public static final String HEADER_RESPONSE = "The Header body is empty";
    public static final String HEADER_MSG = "no body on header";



    /*================================================
     * SERVICE TEST ENDPOINTS
     * ==============================================*/


    private GlobalVariables() { // Empty Constructor
    }
}