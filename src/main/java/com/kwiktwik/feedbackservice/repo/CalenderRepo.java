package com.kwiktwik.feedbackservice.repo;

import java.util.ArrayList;
import com.kwiktwik.feedbackservice.entity.UserSlot;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CalenderRepo extends MongoRepository<UserSlot, String> {
    ArrayList<UserSlot> findByUserId(String title);
}