package com.kwiktwik.feedbackservice.service;

import com.kwiktwik.feedbackservice.dto.FeedbackForm;
import com.kwiktwik.feedbackservice.entity.Feedback;
import com.kwiktwik.feedbackservice.repo.FeedbackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackFormService {

    @Autowired
    private FeedbackRepo feedbackRepo;

    public String addNewForm(FeedbackForm form) throws Exception {
        Feedback feedbackForm = new Feedback();
        feedbackForm.setCmpId(form.getCmpId());
        feedbackForm.setUserId(form.getUserId());
        feedbackForm.setFeedback(form.getFeedback());
        feedbackRepo.save(feedbackForm);
        return feedbackForm.getId();
    }
}
