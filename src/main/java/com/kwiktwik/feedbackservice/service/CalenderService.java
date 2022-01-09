package com.kwiktwik.feedbackservice.service;

import com.kwiktwik.feedbackservice.entity.UserSlot;
import com.kwiktwik.feedbackservice.repo.CalenderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Service
public class CalenderService {
    @Autowired
    private CalenderRepo calenderRepo;

    public String addSlot(UserSlot slot) throws Exception {
        calenderRepo.save(slot);
        return slot.getId();
    }

    public UserSlot getUserSlots(String userId) throws Exception {
        ArrayList<UserSlot> res = calenderRepo.findByUserId(userId);
        for(UserSlot s: res){
            System.out.println(s.getSlots());
        }
        return res.get(res.size()-1);
    }
}

