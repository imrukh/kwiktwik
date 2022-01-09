package com.kwiktwik.feedbackservice.service;

import com.kwiktwik.feedbackservice.entity.UserInterview;
import com.kwiktwik.feedbackservice.repo.UserInterviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserInterviewService {
    @Autowired
    private UserInterviewRepo userInterviewRepo;

    public String saveInterviewSlot(UserInterview userInterview){
        userInterviewRepo.save(userInterview);
        return userInterview.getId();
    }

    public ArrayList<UserInterview> getAllInterviewsByUserId(String userId){
        return userInterviewRepo.findAllByUserId(userId);
    }
}
