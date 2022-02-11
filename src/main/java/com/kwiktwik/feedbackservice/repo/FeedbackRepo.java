package com.kwiktwik.feedbackservice.repo;

import com.kwiktwik.feedbackservice.entity.Feedback;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedbackRepo extends MongoRepository<Feedback, String> {
    List<Feedback> findAllByUserId(String userId);

    List<Feedback> findAllByInterviewId(String invId);
}