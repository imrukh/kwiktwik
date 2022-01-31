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
public class FeedbackSteps implements Serializable {
    @JsonProperty(value = "currentStep")
    private Integer currentStep;

    @JsonProperty(value = "step1")
    private FeedbackStep1 feedbackStep1;

    @JsonProperty(value = "step2")
    private FeedbackStep2[] feedbackStep2;

    @JsonProperty(value = "step3")
    private FeedbackStep3[] feedbackStep3;
}
