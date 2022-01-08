package com.kwiktwik.feedbackservice.util;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Map;

@Service
public class Logger {

    @Autowired
    private LoggerUtil loggerUtil;

    public void logCommonApiResponse(
            LoggingAction.Controller api,
            LoggingAction.Method method,
            Object request,
            String logId,
            Map<String, Object> params,
            long timeTaken,
            String failureReason,
            Object response,
            LoggingAction.Status status,
            LoggingAction.Type loggingActionType
    ) {
        JSONObject loggingData = new JSONObject();
        try {
            loggingData.put(LoggerUtil.CONTROLLER_NAME, api);
            loggingData.put(LoggerUtil.METHOD_NAME, method);
            loggingData.put(LoggerUtil.LOG_TYPE, LoggingAction.LogType.RESPONSE);
            if (request != null)
                loggingData.put(LoggerUtil.REQUEST_URL, request);
            loggingData.put(LoggerUtil.LOG_ID, logId);
            if (StringUtils.isNotBlank(failureReason)) {
                loggingData.put(LoggerUtil.FAILURE_REASON, failureReason);
            } else {
                if (response != null)
                    loggingData.put(LoggerUtil.RESPONSE, response);
            }
            loggingData.put(LoggerUtil.STATUS, status);
            loggingData.put(LoggerUtil.TIME_TAKEN, timeTaken);
            if (!CollectionUtils.isEmpty(params)) {
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    loggingData.put(entry.getKey(), entry.getValue());
                }
            }
        } catch (JSONException e) {
            loggerUtil.logException();
        }
        loggerUtil.info(loggingData, loggingActionType);
    }
}

