package com.kwiktwik.feedbackservice.service;

import com.kwiktwik.feedbackservice.entity.UserSlot;
import com.kwiktwik.feedbackservice.repo.CalenderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalenderService {
    @Autowired
    private CalenderRepo calenderRepo;

    public String addSlot(UserSlot slot) throws Exception {
        calenderRepo.save(slot);
        return slot.getId();
    }
}

