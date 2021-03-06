package com.kwiktwik.feedbackservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kwiktwik.feedbackservice.dto.FeedbackSteps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Feedback")
public class Feedback implements Serializable {
    @Id
    private String id;
    private String interviewId;
    private String userId;
    private FeedbackSteps feedback;
}
