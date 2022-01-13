package com.kwiktwik.feedbackservice.controllers;

import com.kwiktwik.feedbackservice.config.GoogleFirebase;
import com.kwiktwik.feedbackservice.entity.UserInterview;
import com.kwiktwik.feedbackservice.entity.UserSlot;
import com.kwiktwik.feedbackservice.response.BaseMessageResponse;
import com.kwiktwik.feedbackservice.response.ServiceResponse;
import com.kwiktwik.feedbackservice.service.CalenderService;
import com.kwiktwik.feedbackservice.service.UserInterviewService;
import com.kwiktwik.feedbackservice.util.Logger;
import com.kwiktwik.feedbackservice.util.LoggerUtil;
import com.kwiktwik.feedbackservice.util.LoggingAction;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.kwiktwik.feedbackservice.util.LoggingAction.Status.SUCCESS;

@RestController
@RequestMapping(value = "api/slot/")
public class FeedbackController {

    @Autowired
    private Logger logger;

    @Autowired
    private CalenderService calenderService;

    @Autowired
    private UserInterviewService userInterviewService;

    @Autowired
    private GoogleFirebase firebase;

    @PostMapping(value = "add")
    public ServiceResponse<?> createRecord(@RequestBody UserSlot slots) {
        String logId = LoggerUtil.generateLogID();
        long startTime = System.currentTimeMillis();
        try {
            String id = calenderService.addSlot(slots);

            logger.logCommonApiResponse(
                    LoggingAction.Controller.TransactionController,
                    LoggingAction.Method.createRecord,
                    id,
                    logId,
                    null,
                    System.currentTimeMillis() - startTime,
                    null,
                    id,
                    SUCCESS,
                    LoggingAction.Type.CONTROLLER
            );

            return new ServiceResponse<>(new HashMap<String, String>() {
                {
                    put("id", id);
                }
            });
        } catch (Exception e) {
            logger.logCommonApiResponse(
                    LoggingAction.Controller.TransactionController,
                    LoggingAction.Method.createRecord,
                    slots,
                    logId,
                    null,
                    System.currentTimeMillis() - startTime,
                    e.getMessage(),
                    false,
                    LoggingAction.Status.EXCEPTION,
                    LoggingAction.Type.CONTROLLER
            );

            return new ServiceResponse<>(
                    new BaseMessageResponse(
                            false, "Failed to create order"),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
    @GetMapping(value = "active")
    public ServiceResponse<?> getSlots(@RequestParam String userId) {
        String logId = LoggerUtil.generateLogID();
        long startTime = System.currentTimeMillis();
        try {
            UserSlot res = calenderService.getUserSlots(userId);

            logger.logCommonApiResponse(
                    LoggingAction.Controller.TransactionController,
                    LoggingAction.Method.createRecord,
                    userId,
                    logId,
                    null,
                    System.currentTimeMillis() - startTime,
                    null,
                    res,
                    SUCCESS,
                    LoggingAction.Type.CONTROLLER
            );

            return new ServiceResponse<>(res);
        } catch (Exception e) {
            logger.logCommonApiResponse(
                    LoggingAction.Controller.TransactionController,
                    LoggingAction.Method.createRecord,
                    userId,
                    logId,
                    null,
                    System.currentTimeMillis() - startTime,
                    e.getMessage(),
                    false,
                    LoggingAction.Status.EXCEPTION,
                    LoggingAction.Type.CONTROLLER
            );

            return new ServiceResponse<>(
                    new BaseMessageResponse(
                            false, "Failed to create order"),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PostMapping(value = "/interview")
    public ServiceResponse<?> addInterviewSlot(@RequestBody UserInterview interviewSlot) {
        String logId = LoggerUtil.generateLogID();
        long startTime = System.currentTimeMillis();
        try {
            String res = userInterviewService.saveInterviewSlot(interviewSlot);

            logger.logCommonApiResponse(
                    LoggingAction.Controller.TransactionController,
                    LoggingAction.Method.createRecord,
                    interviewSlot,
                    logId,
                    null,
                    System.currentTimeMillis() - startTime,
                    null,
                    res,
                    SUCCESS,
                    LoggingAction.Type.CONTROLLER
            );

            return new ServiceResponse<>(res);
        } catch (Exception e) {
            logger.logCommonApiResponse(
                    LoggingAction.Controller.TransactionController,
                    LoggingAction.Method.createRecord,
                    interviewSlot,
                    logId,
                    null,
                    System.currentTimeMillis() - startTime,
                    e.getMessage(),
                    false,
                    LoggingAction.Status.EXCEPTION,
                    LoggingAction.Type.CONTROLLER
            );

            return new ServiceResponse<>(
                    new BaseMessageResponse(
                            false, "Failed to process the req."),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping(value = "/interview")
    public ServiceResponse<?> getInterviewSlot(@RequestHeader String tokenId, @RequestParam(defaultValue = "n") String history) {
        String logId = LoggerUtil.generateLogID();
        long startTime = System.currentTimeMillis();
        try {
            if(StringUtils.isBlank(tokenId)){
                throw new Exception("Empty tokenId");
            }

            String userEmail = firebase.getUserEmailFromAuth(tokenId);
            System.out.println(userEmail);
            List<UserInterview> res = userInterviewService.getAllInterviewsByUserId(userEmail, history);

            logger.logCommonApiResponse(
                    LoggingAction.Controller.TransactionController,
                    LoggingAction.Method.createRecord,
                    tokenId,
                    logId,
                    null,
                    System.currentTimeMillis() - startTime,
                    null,
                    res,
                    SUCCESS,
                    LoggingAction.Type.CONTROLLER
            );

            return new ServiceResponse<>(res);
        } catch (Exception e) {
            logger.logCommonApiResponse(
                    LoggingAction.Controller.TransactionController,
                    LoggingAction.Method.createRecord,
                    tokenId,
                    logId,
                    null,
                    System.currentTimeMillis() - startTime,
                    e.getMessage(),
                    false,
                    LoggingAction.Status.EXCEPTION,
                    LoggingAction.Type.CONTROLLER
            );

            return new ServiceResponse<>(
                    new BaseMessageResponse(
                            false, "Failed to process the req."),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
//
//    @PutMapping(value = "transaction/{txnId}")
//
//    public ServiceResponse<?> createRecord(
//            @PathVariable("txnId") Long txnId,
//            @RequestBody @NotNull Txn txn) {
//        String logId = LoggerUtil.generateLogID();
//        long startTime = System.currentTimeMillis();
//        try {
//            Boolean status = txnService.insert(txnId, txn);
//
//            logger.logCommonApiResponse(
//                    LoggingAction.Controller.TransactionController,
//                    LoggingAction.Method.createRecord,
//                    txn,
//                    logId,
//                    null,
//                    System.currentTimeMillis() - startTime,
//                    null,
//                    status,
//                    LoggingAction.Status.SUCCESS,
//                    LoggingAction.Type.CONTROLLER
//            );
//
//            return new ServiceResponse<>(new HashMap<String, Boolean>() {
//                {
//                    put("status", status);
//                }
//            });
//        } catch (Exception e) {
//            logger.logCommonApiResponse(
//                    LoggingAction.Controller.TransactionController,
//                    LoggingAction.Method.createRecord,
//                    txn,
//                    logId,
//                    null,
//                    System.currentTimeMillis() - startTime,
//                    e.getMessage(),
//                    false,
//                    LoggingAction.Status.EXCEPTION,
//                    LoggingAction.Type.CONTROLLER
//            );
//
//            return new ServiceResponse<>(
//                    new BaseMessageResponse(
//                            false, "Failed to create order"),
//                    HttpStatus.INTERNAL_SERVER_ERROR
//            );
//        }
//    }
//
//    @GetMapping(value = "{companyId}/{userId}")
//    public Txn getTxn(@PathVariable("txnId") Long txnId) {
//        String logId = LoggerUtil.generateLogID();
//        long startTime = System.currentTimeMillis();
//        Txn txn = null;
//        try {
//            txn = txnService.getTxnById(txnId);
//
//            logger.logCommonApiResponse(
//                    LoggingAction.Controller.TransactionController,
//                    LoggingAction.Method.getTxn,
//                    txn,
//                    logId,
//                    null,
//                    System.currentTimeMillis() - startTime,
//                    null,
//                    txn,
//                    LoggingAction.Status.SUCCESS,
//                    LoggingAction.Type.CONTROLLER
//            );
//        } catch (Exception e) {
//            logger.logCommonApiResponse(
//                    LoggingAction.Controller.TransactionController,
//                    LoggingAction.Method.getTxn,
//                    txn,
//                    logId,
//                    null,
//                    System.currentTimeMillis() - startTime,
//                    e.getMessage(),
//                    false,
//                    LoggingAction.Status.EXCEPTION,
//                    LoggingAction.Type.CONTROLLER
//            );
//        }
//        return txn;
//    }
//
//    @GetMapping(value = "transaction/types/{type}")
//    public List<Long> getTxnByType(@PathVariable("type") String type) {
//        String logId = LoggerUtil.generateLogID();
//        long startTime = System.currentTimeMillis();
//        List<Long> txn = null;
//        try {
//            txn = txnService.getTxnByType(type);
//
//            logger.logCommonApiResponse(
//                    LoggingAction.Controller.TransactionController,
//                    LoggingAction.Method.getTxnByType,
//                    txn,
//                    logId,
//                    null,
//                    System.currentTimeMillis() - startTime,
//                    null,
//                    txn,
//                    LoggingAction.Status.SUCCESS,
//                    LoggingAction.Type.CONTROLLER
//            );
//        } catch (Exception e) {
//            logger.logCommonApiResponse(
//                    LoggingAction.Controller.TransactionController,
//                    LoggingAction.Method.getTxnByType,
//                    txn,
//                    logId,
//                    null,
//                    System.currentTimeMillis() - startTime,
//                    e.getMessage(),
//                    false,
//                    LoggingAction.Status.EXCEPTION,
//                    LoggingAction.Type.CONTROLLER
//            );
//        }
//        return txn;
//    }

//    @GetMapping(value = "transaction/sum/{txnId}")
//    public ServiceResponse<?> getSum(@PathVariable("txnId") Long txnId) {
//        String logId = LoggerUtil.generateLogID();
//        long startTime = System.currentTimeMillis();
//        double sum = 0;
//        try {
//            sum = txnService.getSum(txnId);
//
//            logger.logCommonApiResponse(
//                    LoggingAction.Controller.TransactionController,
//                    LoggingAction.Method.getSum,
//                    null,
//                    logId,
//                    null,
//                    System.currentTimeMillis() - startTime,
//                    null,
//                    sum,
//                    LoggingAction.Status.SUCCESS,
//                    LoggingAction.Type.CONTROLLER
//            );
//        } catch (Exception e) {
//            logger.logCommonApiResponse(
//                    LoggingAction.Controller.TransactionController,
//                    LoggingAction.Method.getSum,
//                    null,
//                    logId,
//                    null,
//                    System.currentTimeMillis() - startTime,
//                    e.getMessage(),
//                    false,
//                    LoggingAction.Status.EXCEPTION,
//                    LoggingAction.Type.CONTROLLER
//            );
//        }
//
//        double finalSum = sum;
//        return new ServiceResponse<>(new HashMap<String, Double>() {
//            {
//                put("sum", finalSum);
//            }
//        });
//    }
}