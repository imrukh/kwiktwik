package com.kwiktwik.feedbackservice.service;

import com.kwiktwik.feedbackservice.entity.UserInterview;
import com.kwiktwik.feedbackservice.repo.UserInterviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserInterviewService {
    @Autowired
    private UserInterviewRepo userInterviewRepo;

    public String saveInterviewSlot(UserInterview userInterview) {
        if (userInterview.getStatus() == null)
            userInterview.setStatus("NOT_SCHEDULED");

        userInterviewRepo.save(userInterview);
        return userInterview.getId();
    }

    public List<UserInterview> getAllInterviewsByUserId(String userId, String history) {
        ArrayList<UserInterview> slots = userInterviewRepo.findAllByUserId(userId);
        List<UserInterview> finRes;
        if (history.equals("y")) {
            finRes = slots.stream().filter(
                    slot -> slot.getStatus() != null && slot.getStatus().toUpperCase().equals("COMPLETED")
            ).collect(Collectors.toList());
        } else {
            finRes = slots.stream().filter(
                    slot -> !(slot.getStatus() != null && slot.getStatus().toUpperCase().equals("COMPLETED"))
            ).collect(Collectors.toList());
        }

        return finRes;
    }
}
