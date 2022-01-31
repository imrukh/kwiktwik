package com.kwiktwik.feedbackservice.service;

import com.kwiktwik.feedbackservice.dto.FeedbackForm;
import com.kwiktwik.feedbackservice.dto.FeedbackSteps;
import com.kwiktwik.feedbackservice.entity.Feedback;
import com.kwiktwik.feedbackservice.repo.FeedbackRepo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackFormService {

    @Autowired
    private FeedbackRepo feedbackRepo;

    public String addNewForm(FeedbackForm newForm) throws Exception {
        Feedback feedbackForm = new Feedback();
        if (StringUtils.isNotBlank(newForm.getId())) {
            feedbackForm = getFromById(newForm.getId(), newForm.getUserId());
            if (feedbackForm == null) {
                return "-1";
            }

            updateCurrentStep(newForm.getFeedback(), feedbackForm.getFeedback());
            feedbackRepo.save(feedbackForm);
            return feedbackForm.getId();
        }

        feedbackForm.setInterviewId(newForm.getInterviewId());
        feedbackForm.setUserId(newForm.getUserId());
        feedbackForm.setFeedback(newForm.getFeedback());
        feedbackRepo.save(feedbackForm);
        return feedbackForm.getId();
    }

    public Feedback getFromById(String id, String userId) throws Exception {
        Optional<Feedback> res = feedbackRepo.findById(id);
        Feedback feedback = res.orElse(null);
        if (feedback == null || !feedback.getUserId().equals(userId)) return null;
        return feedback;
    }

    public List<Feedback> getAllFromById(String userId) throws Exception {
        List<Feedback> res = feedbackRepo.findAllByUserId(userId);
        return res;
    }

    public Object getFeedbackForCurrentStep(String id, String userId, Integer currentStep) throws Exception {
        Feedback form = getFromById(id, userId);
        if (form == null || currentStep == null) return form;

        Object stepRes = null;
        switch (currentStep) {
            case 1:
                stepRes = form.getFeedback().getFeedbackStep1();
                break;
            case 2:
                stepRes = form.getFeedback().getFeedbackStep2();
                break;
            case 3:
                stepRes = form.getFeedback().getFeedbackStep3();
                break;
        }
        return stepRes;
    }

    public void updateCurrentStep(FeedbackSteps formNew, FeedbackSteps formOld) {
        formOld.setCurrentStep(formNew.getCurrentStep());
        switch (formNew.getCurrentStep()) {
            case 1:
                formOld.setFeedbackStep1(formNew.getFeedbackStep1());
                break;
            case 2:
                formOld.setFeedbackStep2(formNew.getFeedbackStep2());
                break;
            case 3:
                formOld.setFeedbackStep3(formNew.getFeedbackStep3());
                break;
        }
    }
}
