package com.kwiktwik.feedbackservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@Document(collection = "UserInterview")
public class UserInterview implements Serializable {
    @Id
    private String id;
    private String cmpId;
    private String userId;
    private Slot slot;
    private String candidateEmail;
    private String candidatePhone;
    private String role;
    private String round;
    private String status;
    private String comments;
    private String cvLink;
}
