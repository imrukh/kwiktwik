package com.kwiktwik.feedbackservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackForm implements Serializable {
    @JsonProperty(value="id")
    private String id;

    @JsonProperty(value = "cmpId")
    private String cmpId;

    @JsonProperty(value = "UserId")
    private String userId;

    @JsonProperty(value = "role")
    private String role;

    @JsonProperty(value = "feedback")
    private String feedback;
}

