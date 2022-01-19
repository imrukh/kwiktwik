package com.kwiktwik.feedbackservice.service;

import com.kwiktwik.feedbackservice.entity.UserSlot;
import com.kwiktwik.feedbackservice.repo.CalenderRepo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class CalenderService {
    @Autowired
    private CalenderRepo calenderRepo;

    public String addSlot(UserSlot slot) throws Exception {
        UserSlot userPreviousSlot = getUserSlots(slot.getUserId());
        if (StringUtils.isNotBlank(userPreviousSlot.getId())) {
            userPreviousSlot.setSlots(slot.getSlots());
            calenderRepo.save(userPreviousSlot);
            return userPreviousSlot.getId();
        } else {
            calenderRepo.save(slot);
            return slot.getId();
        }
    }

    public UserSlot getUserSlots(String userId) throws Exception {
        ArrayList<UserSlot> res = calenderRepo.findByUserId(userId);
//        for(UserSlot s: res){
//            System.out.println(s.getSlots());
//        }
        if(res==null || res.size()==0) return new UserSlot(userId);
        return res.get(res.size() - 1);
    }
}

