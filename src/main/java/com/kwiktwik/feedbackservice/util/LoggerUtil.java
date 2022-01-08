package com.kwiktwik.feedbackservice.util;


import org.apache.log4j.MDC;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class LoggerUtil {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final String LOG_ID = "logId";

    public static final String CONTROLLER_NAME = "controllerName";

    public static final String METHOD_NAME = "methodName";

    public static final String LOG_TYPE = "logType";

    public static final String REQUEST_URL = "requestUrl";

    public static final String FAILURE_REASON = "failureReason";

    public static final String RESPONSE = "response";

    public static final String STATUS = "status";

    public static final String TIME_TAKEN = "timeTaken";

    public static final String SERVICE_NAME = "serviceName";

    public static final String MESSAGE = "message";

    public static final String MANAGER_NAME = "managerName";

    public static final String KAFKA_NAME = "kafkaName";

    public static final String PAYLOAD = "payload";

    public static final String HEADERS = "headers";

    public static final String REQUEST_BODY = "requestBody";


    public void logException() {
        logger.warn("{}", "exception while printing logs");
    }

    private void addMetaData(JSONObject data, String level, LoggingAction.Type loggingActionType) {
        try {
            data.put("application", "BUSINESS_PROFILE_SERVICE");
            data.put("level", level);
            data.put("loggingAction", loggingActionType.name());
            data.put("uIdentifier", MDC.get("uIdentifier"));
            data.put("time", Calendar.getInstance().getTime());
        } catch (JSONException e) {
            logger.info("{}", "exception while printing logs");
        }
    }

    public void warn(JSONObject data, LoggingAction.WarnLevel warnLevel, LoggingAction.Type loggingActionType) {
        addMetaData(data, "warn", loggingActionType);
        try {
            data.put("warnLevel", warnLevel.name());
        } catch (JSONException e) {
            logger.warn("{}", "exception while printing logs");
        }

        logger.warn("{}", data);
    }

    public void info(JSONObject data, LoggingAction.Type loggingActionType) {
        addMetaData(data, "info", loggingActionType);
        logger.info("{}", data);
    }

    public void debug(JSONObject data, LoggingAction.Type loggingActionType) {
        addMetaData(data, "debug", loggingActionType);
        logger.warn("{}", data);
    }

    public void error(JSONObject data, LoggingAction.Type loggingActionType) {
        addMetaData(data, "error", loggingActionType);
        logger.error("{}", data);
    }

    public static Map<String, Object> buildParamsMap(Object... args) {
        Map<String, Object> params = new HashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            params.put(args[i].toString(), args[i + 1]);
        }

        return params;
    }

    public static String generateLogID() {
        return UUID.randomUUID().toString().toUpperCase().replace("-", "");
    }

}

