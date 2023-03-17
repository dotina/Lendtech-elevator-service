package com.lendtech.mselevator.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.lendtech.mselevator.utilities.GlobalVariables.LOGGER_FORMAT;
public class LogManager {

    private static final Logger logger = LoggerFactory.getLogger(LogManager.class);

    private LogManager() { // Empty Constructor
    }

    public static void info(String requestId, String transactionType, String process, String processDuration,
                            String serviceId, String sourceSystem, String targetSystem, String response, int responseCode,
                            String responseMsg, String errorDescription, String requestPayload, String responsePayload,
                            String headers) {

        logger.info(LOGGER_FORMAT, requestId, transactionType, process, processDuration, serviceId, sourceSystem,
                targetSystem, response, responseCode, responseMsg, errorDescription, requestPayload, responsePayload,
                headers);

    }

    public static void error(String requestId, String transactionType, String process, String processDuration,
                             String serviceId, String sourceSystem, String targetSystem, String response, int responseCode,
                             String responseMsg, String errorDescription, String requestPayload, String responsePayload,
                             String headers) {

        logger.error(LOGGER_FORMAT, requestId, transactionType, process, processDuration, serviceId, sourceSystem,
                targetSystem, response, responseCode, responseMsg, errorDescription, requestPayload, responsePayload,
                headers);

    }
}
