package com.kwiktwik.feedbackservice.repo;

import java.util.List;

import com.kwiktwik.feedbackservice.entity.UserSlot;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CalenderRepo extends MongoRepository<UserSlot, String> {
    List<UserSlot> findByUserId(String title);
}