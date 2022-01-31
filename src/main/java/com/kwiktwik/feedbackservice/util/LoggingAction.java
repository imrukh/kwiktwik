package com.kwiktwik.feedbackservice.util;

public class LoggingAction {
    public enum Type {
        CONTROLLER,
        SERVICE,
        MANAGER,
        KAFKA;
    }

    public enum LogType {
        REQUEST,
        RESPONSE;
    }

    public enum Method {
        createRecord,
        getFeedback,
        getTxn,
        getTxnByType,
        getSum
    }

    public enum Controller {
        SlotController,
        FeedbackController
    }

    public enum WarnLevel {
        WARN,
        CRITICAL,
        FATAL
    }

    public enum Status {
        EXCEPTION,
        SUCCESS,
    }
}

