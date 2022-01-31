package com.kwiktwik.feedbackservice.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kwiktwik.feedbackservice.entity.Feedback;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class AllFeedbackFormDTO {
    @JsonProperty(value = "userId")
    private String userId;

    @JsonProperty(value = "status")
    private String status;

    @JsonProperty(value = "message")
    private String message;

    @JsonProperty(value = "feedbacks")
    private List<Feedback> feedbacks;
}
