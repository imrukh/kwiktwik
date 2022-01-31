package com.kwiktwik.feedbackservice.controllers;

import com.kwiktwik.feedbackservice.config.GoogleFirebase;
import com.kwiktwik.feedbackservice.dto.FeedbackForm;
import com.kwiktwik.feedbackservice.entity.Feedback;
import com.kwiktwik.feedbackservice.entity.UserInterview;
import com.kwiktwik.feedbackservice.entity.UserSlot;
import com.kwiktwik.feedbackservice.response.AllFeedbackFormDTO;
import com.kwiktwik.feedbackservice.response.BaseMessageResponse;
import com.kwiktwik.feedbackservice.response.ServiceResponse;
import com.kwiktwik.feedbackservice.service.CalenderService;
import com.kwiktwik.feedbackservice.service.FeedbackFormService;
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

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/feedback")
public class FeedbackController {

    @Autowired
    private Logger logger;

    @Autowired
    private FeedbackFormService feedbackFormService;

    @Autowired
    private UserInterviewService userInterviewService;

    @Autowired
    private GoogleFirebase firebase;

    @PostMapping(value = "add")
    public ServiceResponse<?> createRecord(@RequestHeader String Authorization, @RequestBody FeedbackForm feedback) {
        String logId = LoggerUtil.generateLogID();
        long startTime = System.currentTimeMillis();
        try {
            String userEmail = firebase.getUserEmailFromAuth(Authorization);
            if (StringUtils.isBlank(userEmail)) {
                return new ServiceResponse<>(new HashMap<String, String>() {
                    {
                        put("ErrMsg", "Invalid token passed.");
                    }
                }, HttpStatus.UNAUTHORIZED);
            }

            feedback.setUserId(userEmail);
            String id = feedbackFormService.addNewForm(feedback);

            logger.logCommonApiResponse(
                    LoggingAction.Controller.FeedbackController,
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
            if (id.equals("-1")) {
                return new ServiceResponse<>(new HashMap<String, String>() {
                    {
                        put("ErrMsg", "Records does not exists.");
                    }
                });
            }

            return new ServiceResponse<>(new HashMap<String, String>() {
                {
                    put("id", id);
                }
            });
        } catch (Exception e) {
            logger.logCommonApiResponse(
                    LoggingAction.Controller.FeedbackController,
                    LoggingAction.Method.createRecord,
                    feedback.toString(),
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
                            false, "Failed to process req"),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping(value = "/{id}/{stepId}")
    public ServiceResponse<?> getFeedback(@RequestHeader String Authorization, @PathVariable Integer stepId, @PathVariable String id) {
        String logId = LoggerUtil.generateLogID();
        String userId = "";
        long startTime = System.currentTimeMillis();
        try {
            userId = firebase.getUserEmailFromAuth(Authorization);
            Object res = feedbackFormService.getFeedbackForCurrentStep(id, userId, stepId);

            logger.logCommonApiResponse(
                    LoggingAction.Controller.FeedbackController,
                    LoggingAction.Method.getFeedback,
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
                    LoggingAction.Controller.FeedbackController,
                    LoggingAction.Method.getFeedback,
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
                            false, "Failed to process req"),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping(value = "")
    public ServiceResponse<?> getAllFeedback(@RequestHeader String Authorization) {
        String logId = LoggerUtil.generateLogID();
        String userId = "";
        long startTime = System.currentTimeMillis();
        try {
            userId = firebase.getUserEmailFromAuth(Authorization);
            List<Feedback> feedbacks = feedbackFormService.getAllFromById(userId);
            AllFeedbackFormDTO res = new AllFeedbackFormDTO(userId, SUCCESS.toString(), "", feedbacks);
            logger.logCommonApiResponse(
                    LoggingAction.Controller.FeedbackController,
                    LoggingAction.Method.getFeedback,
                    userId,
                    logId,
                    null,
                    System.currentTimeMillis() - startTime,
                    null,
                    res.toString(),
                    SUCCESS,
                    LoggingAction.Type.CONTROLLER
            );

            return new ServiceResponse<>(res);
        } catch (Exception e) {
            logger.logCommonApiResponse(
                    LoggingAction.Controller.FeedbackController,
                    LoggingAction.Method.getFeedback,
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
                            false, "Failed to process req"),
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

    @GetMapping(value = "interview")
    public ServiceResponse<?> getInterviewSlot(@RequestHeader String Authorization, @RequestParam(defaultValue = "n") String history) {
        String logId = LoggerUtil.generateLogID();
        long startTime = System.currentTimeMillis();
        try {
            if (StringUtils.isBlank(Authorization)) {
                throw new Exception("Empty Authorization");
            }

            String userEmail = firebase.getUserEmailFromAuth(Authorization);
//            String userEmail = "shahrukhm319@gmail.com";
            System.out.println(userEmail);
            List<UserInterview> res = userInterviewService.getAllInterviewsByUserId(userEmail, history);

            logger.logCommonApiResponse(
                    LoggingAction.Controller.TransactionController,
                    LoggingAction.Method.createRecord,
                    Authorization,
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
                    Authorization,
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