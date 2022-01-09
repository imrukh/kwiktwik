package com.kwiktwik.feedbackservice.repo;

import com.kwiktwik.feedbackservice.entity.UserInterview;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface UserInterviewRepo extends MongoRepository<UserInterview, String> {
    ArrayList<UserInterview> findAllByUserId(String userId);
}