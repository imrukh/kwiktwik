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

//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin
@RestController
@RequestMapping(value = "/api/slot")
public class SlotController {

    @Autowired
    private Logger logger;

    @Autowired
    private CalenderService calenderService;

    @Autowired
    private UserInterviewService userInterviewService;

    @Autowired
    private GoogleFirebase firebase;

    @PostMapping(value = "add")
    public ServiceResponse<?> createRecord(@RequestHeader String Authorization, @RequestBody UserSlot slots) {
        String logId = LoggerUtil.generateLogID();
        long startTime = System.currentTimeMillis();
        try {
            String userEmail = firebase.getUserEmailFromAuth(Authorization);
            slots.setUserId(userEmail);
            String id = calenderService.addSlot(slots);

            logger.logCommonApiResponse(
                    LoggingAction.Controller.SlotController,
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
                    LoggingAction.Controller.SlotController,
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
                            false, "Failed to process req"),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping(value = "active")
    public ServiceResponse<?> getSlots(@RequestHeader String Authorization) {
        String logId = LoggerUtil.generateLogID();
        String userId = "";
        long startTime = System.currentTimeMillis();
        try {
            userId = firebase.getUserEmailFromAuth(Authorization);
            UserSlot res = calenderService.getUserSlots(userId);
            logger.logCommonApiResponse(
                    LoggingAction.Controller.SlotController,
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
                    LoggingAction.Controller.SlotController,
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
                    LoggingAction.Controller.SlotController,
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
                    LoggingAction.Controller.SlotController,
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
                    LoggingAction.Controller.SlotController,
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
                    LoggingAction.Controller.SlotController,
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
}